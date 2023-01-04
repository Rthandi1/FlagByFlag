package com.mycompany.a3;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

import com.codename1.ui.geom.Dimension;

public class GameWorld extends Observable{
	//Private Fields
	private int lives = 3;
	private int time;
	private Random rand = new Random();
	private int lastFlag = 4;
	private int height;
	private int width;
	private boolean sound;
	private GameObjectCollection gameCollection;
	private boolean pause;
	private boolean position;
	//private MapView mv;
	//private GameObject g;
	
	BGSound Bg;
	Sound hitSpider;
	Sound cheerFlag;
	Sound AntConsume;
	
	public GameWorld() {
		
		gameCollection = new GameObjectCollection();
		
	}
	public void init() {
	 this.pause = false;
	 this.sound = false;
	 this.time = 0;
	 this.position = false;
	 
	//Create four flags
	gameCollection.add(new Flag(200,200,100,1,this));
	gameCollection.add(new Flag(200,800,100,2,this));
	gameCollection.add(new Flag(700,800,100,3,this));
	gameCollection.add(new Flag(900,400,100,4,this));
	gameCollection.add(new FoodStation(rand.nextFloat()*this.getWidth() , rand.nextFloat()*this.getHeight(),100,this));
	gameCollection.add(new FoodStation(rand.nextFloat()*this.getWidth() , rand.nextFloat()*this.getHeight(),110,this));
	new Ant(200,200,50,180,5,this);
	gameCollection.add(getUser());
	//create two spiders
	gameCollection.add(new Spider(rand.nextFloat()*this.getWidth() , rand.nextFloat()*this.getHeight(),rand.nextInt(50)+50,rand.nextInt(360),rand.nextInt(15)+5));
	gameCollection.add(new Spider(rand.nextFloat()*this.getWidth() , rand.nextFloat()*this.getHeight(),rand.nextInt(50)+50,rand.nextInt(360),rand.nextInt(15)+5));
	
	this.setChanged();
	this.notifyObservers();
	 
	}
	
	//Accessors and Mutators
		public int getTime() {
			return this.time;
		}
		public int getLastFlag() {
			return this.lastFlag;
		}
	

		public Ant getUser() {
			return Ant.getAnt();
		}
		public boolean getSound() {
			return this.sound;
		}
		public void setSound(boolean sound) {
			this.sound = sound;
			this.setChanged();
			this.notifyObservers();
			
			if (sound == true) {
				sound=false;
				System.out.println("Sound on.");
			}else {
				sound = true;
				System.out.println("Sound off.");
			}
				
				
		}
		
		public BGSound getBgSound() {
			return this.Bg;
		}
	
		public GameObjectCollection getCollection() {
			return this.gameCollection;
			
		}
	
		public int getFood() {
			return getUser().getFoodLevel();
		}
		public int getLives() {
			return this.lives;
		}
		public int getHealth() {
			return getUser().getHealthLevel();
		}
		public void setHeight(int mapHeight) {
			this.height = mapHeight;
			
		}
		public void setWidth(int mapWidth) {
			this.width = mapWidth;
			
		}
		public int getWidth() {
			return this.width;
		}
		public int getHeight() {
			return this.height;
		}
		
		public boolean pauseMode() {
			return this.pause;
		}
		public void setPause(boolean pause) {
			this.pause = pause;
		}
		
		public void disablePosition(boolean p) {
			this.position = p;
		}
		
		public boolean getPosition() {
			return position;
		}
	public void accelerate() {
		int counter = 0;
		//adds the current speed by 1 and determines whether is more than 0 and less than the maxspeed
		if(getUser().getSpeed()+ 1 < getUser().getMaximumSpeed()&& getUser().getSpeed() >= 0) {
			//counter keeps count of the number of times accelerate was used
			counter += 2;
			getUser().setSpeed(getUser().getSpeed() + counter);
			System.out.println("+2 to Speed: " + getUser().getSpeed());
		}else {
			//assigns the max speed to ant since it tried to exceed 50
			getUser().setSpeed(getUser().getMaximumSpeed());
			System.out.println("Speed has been maximized to 100");
			
		}
	
		
		this.setChanged();
		this.notifyObservers();
	}
	public void brake() {
		int min = 0;
		int speed = getUser().getSpeed();
		//checks if decreasing speed by 1 is greater than min
		if(speed - 1 > min ) {
			//decrease speed by 1
			speed -= 2;
			getUser().setSpeed(speed);
			System.out.println("-1 to Speed: " + getUser().getSpeed());
		}else {
			//Sets speed to min if speed is less than min
			getUser().setSpeed(min);
			System.out.println("Speed has been minimized to 0");
		}
	
		this.setChanged();
		this.notifyObservers();
	}
	/*
	 * Changes heading by +15
	 */
	public void turnR() {
		getUser().turn(+15);
		System.out.println("Ant's Heading has gone down to: " + getUser().getHeading());
	
		this.setChanged();
		this.notifyObservers();
	}
	/*
	 * Changes heading by -15
	 */
	public void turnL() {
		getUser().turn(-15);
		System.out.println("Ant's Heading has gone down to: " + getUser().getHeading());
		
		this.setChanged();
		this.notifyObservers();
	}

	public void collideFood(FoodStation SelectedFoodstation) {
	
	
		//checks if the Foodstation has a capacity that doesn't equal 0
		if(SelectedFoodstation.getCapacity() != 0) {
			
			
		//Increase ant's foodlevel
		getUser().setFoodLevel(SelectedFoodstation.getCapacity() + getUser().getFoodLevel());
		SelectedFoodstation.setColor(144,238,144);
		SelectedFoodstation.setCapacity(0);
		//FoodStation newFoodStation = new FoodStation(rand.nextFloat()*this.getWidth() , rand.nextFloat()*this.getHeight(), rand.nextInt(50)+50,this);
		gameCollection.add(new FoodStation(rand.nextFloat()*this.getWidth() , rand.nextFloat()*this.getHeight(), rand.nextInt(50)+50,this));
		//Plays sound 
		if(sound == true) {
			AntConsume.play();
		}
	
	
		}
		
		this.setChanged();
		this.notifyObservers();
	
		
	}
	public boolean collideFlag(Flag picked) {
		 boolean r = false;
		//checks if the User's last flag reached is equal to the next flag (substract 1) to make sure it equals 
		if(getUser().getLastFlag() == (picked).getSequenceNumber()- 1) {
			getUser().setLastFlag((picked).getSequenceNumber());
			//plays sound  
			if(sound == true) {
				cheerFlag.play();
			}
			
		}
		
		//Checks if the ant collided with last flag 
		if(getUser().getLastFlag() == lastFlag){
			//plays sound 
			if(sound == true) {
				cheerFlag.play();
			}
			//end prompt
			System.out.println("You have reached the last flag, Flag " + getUser().getLastFlag());
			System.out.println("Hooray, you have won, total time: " + time);
			com.codename1.ui.Display.getInstance().exitApplication();
		}
		this.setChanged();
		this.notifyObservers();
		//returns true once it is reached
		r = true;
		return r;
	}
	public void collideSpider() {
		//decrease the health of ant since it collided with spider
		int damage = 25;
		getUser().setHealthLevel(getUser().getHealthLevel() - damage);
		
		System.out.println("The spider has collided with the spider, Current Health: " + getUser().getHealthLevel());
		
		//change color of ant as the color of ant decreases 
		getUser().setColor(150,75,0 + damage);
		
		//decrease speed of ant based on the damage
		if(getUser().getSpeed() < getUser().getMaximumSpeed()) {
			getUser().setSpeed(getUser().getSpeed()-damage);
			
		}else {
			//make sure ant doesn't exceed max speed
			getUser().setSpeed(getUser().getMaximumSpeed());
		}
		//play sound once hit 
			
		//If Users lives is greater than 0 and Users Ant health is zero then..
		if(lives>0 && getUser().getHealthLevel() == 0) {
			//increment -1 life
			lives--;
			
			//clear out the arraylist
			gameCollection.clear();
			System.out.println("Ant's speed is " + getUser().getSpeed()); 
			//print remaining lives
			System.out.println("Current Health is 0, lost 1 life, Remaining Lives: " + lives);
			//resets the user
			getUser().reset();
			//re-initialize 
			init();
		}
		//if remaining live is 0 then exit program
		if(lives == 0) {
			//Resets game collection
			gameCollection.clear();
			System.out.println("You have " +lives +" lives. Game Over!");
			//Exits program
			com.codename1.ui.Display.getInstance().exitApplication();
		}
		
		//Changes and notifies the observers
		this.setChanged();
		this.notifyObservers();
	}
	public void tick(int elapsedTime, Dimension dCmpSize) {
		
		//Spiders update their heading as indicated above.
		IIterator itr = gameCollection.getIterator();
		while(itr.hasNext()) {
			GameObject s = itr.getNext(); 
			//checks if object is a spider
			if( s instanceof Spider) {
				//heading range between 5 and -5 
				((Spider) s).setHeading(((Spider) s).getHeading()+(rand.nextInt(5-(-5))+(-5)));
			}
		}

		//all moveable objects are told to update their positions according to their current heading and speed
		IIterator itr2 = gameCollection.getIterator();
		//Iterates through the gameCollection
		while(itr2.hasNext()) {
			GameObject g = (GameObject)itr2.getNext();
			//Checks if its a moveableObject
			if(g instanceof MoveableGameObject) {
				//Moveables update their heading and speed
				((MoveableGameObject) g).Move(elapsedTime, dCmpSize);
			}
			
		}
		//Checks if there is any collisions
		IIterator iter1 = gameCollection.getIterator();
		while(iter1.hasNext()) {
			GameObject curObj = (GameObject)iter1.getNext();
			IIterator iter2 = gameCollection.getIterator();
			while(iter2.hasNext()) {
				GameObject otherObj = (GameObject)iter2.getNext();
				if(otherObj != curObj) {
					if(curObj.collidesWith(otherObj)) {
						curObj.handleCollision(otherObj);
						
						
					}
					//removes the objects that aren't colliding anymore
						curObj.NotCollision(otherObj);
					}
				}
			}
		
		
		
		//the ants food level is reduced by the amount indicated by its foodConsumptionRate,
		if(getUser().getFoodLevel()>0) {
		getUser().setFoodLevel(getUser().getFoodLevel() - getUser().getFoodConsumptionRate());
		//Prevent FoodLevel to be negative
		}else {
			getUser().setFoodLevel(0);	
			getUser().setSpeed(0);
		}
		if(getUser().getFoodLevel() == 0) {
			
			//If Users lives is greater than 0 and Users Ant health is zero then..
			if(lives>0) {
				//increment -1 life
				lives--;
				//clear out the arraylist
				gameCollection.clear();
				//Reset User's health and Food level so it won't end game automatically
				getUser().reset();
				//print remaining lives
				System.out.println("Current Food Level is 0, lost 1 life, Remaining Lives: " + lives);
				//re-initialize 
				init();
			}
			//if remaining live is 0 then exit program
			if(lives == 0) {
				//Resets gameCollection
				gameCollection.clear();
				System.out.println("You have " +lives +" lives. Game Over!");
				//Exits program
				com.codename1.ui.Display.getInstance().exitApplication();
				
			}
		}
		//the elapsed time game clock is incremented by one
		
		time++;
		
		
		//Changes and notifies the observers
		this.setChanged();
		this.notifyObservers();
	}
	/*
	 * Iterates through the gameCollection arraylist and prints out each objects
	 * toString
	 */
	public void PrintMap() {
	IIterator iter = gameCollection.getIterator();
	while(iter.hasNext()) {
		System.out.println((iter.getNext().toString()));
	}
	System.out.println();
	}
	public void Display() {
		System.out.println("Lives Remaining: " + lives);
		System.out.println("Current Time Lapse: " + time);
		System.out.println("Highest Flag Reached: " + (getUser().getLastFlag()));
		System.out.println("Current Ant's Food Level: " + getUser().getFoodLevel());
		System.out.println("Current Ant's Health Level: " + getUser().getHealthLevel());
		
	}
	
	public void PositionOn() {
		if(position == true) {
			position = false;
		}else {
			position = true;
		}
	} 

	//creates the sounds 
	public void createSounds() {
		hitSpider = new Sound("hit.wav");
		cheerFlag = new Sound("FlagCheer.wav");
		AntConsume = new Sound("AntConsume.wav");
		Bg = new BGSound("BackGround.wav");

	}
}
