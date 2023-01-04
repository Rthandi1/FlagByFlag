package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;


public class Ant extends MoveableGameObject implements ISteerable{

	//attributes
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private static Ant User;
	private static float x;
	private static float y;
	private static int size;
	private static int heading;
	private static int speed;
	private static GameWorld gw;


	public Ant(float x, float y, int size, int heading, int speed, GameWorld gw) {
		super(x,y,size,heading,speed,150,75,0);
		setLocation(x,y);
		//Sets color of ant to red
		setColor(255,0,0);
		//set max speed
		this.maximumSpeed = 100;
		//set food level
		this.foodLevel = 1500;
		//set consumption rate
		this.foodConsumptionRate = 2;
		//set ant health level
		this.healthLevel = 1000;
		//set the current flag reached
		this.lastFlagReached = 1;

		this.x = x;
		this.y = y;
		this.size = size;
		this.heading = heading;
		this.speed = speed;
		this.gw = gw;
	}
	//Singleton
	public static Ant getAnt() {
		if(User == null) {
			User = new Ant(x,y,size,heading,speed,gw);
		}
		return User;
	}
	//resets the User's Ant back to the intial values
	public Ant reset() {
		User = new Ant(x,y,size,heading,speed,gw);
		return User;
	}
	
	//getter and setters
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}public void setMaximumSpeed(int max) {
		this.maximumSpeed = max;
	}

	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	public int getHealthLevel() {
		return this.healthLevel;
		
	}
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
		
	}
	public int getFoodLevel() {
		return foodLevel;
		
	}
	public int getLastFlag() {
		return this.lastFlagReached;
	}
	public void setLastFlag(int lastflagReached) {
		this.lastFlagReached = lastflagReached;
	}
	@Override
	public void Move(int elapsedTime,Dimension dCmpSize) {
		/*float deltaX = 0;
		float deltaY= 0;
		deltaY = (float) (Math.sin(Math.toRadians( 90 - this.getHeading())) * getSpeed()*(elapsedTime/1000));
		deltaX = (float) (Math.cos(Math.toRadians( 90 - this.getHeading())) * getSpeed()*(elapsedTime/1000));
		//replaces current location with the new
		float newLocationX = this.getLocation().getX() + deltaX;
		float newLocationY = this.getLocation().getY() + deltaY;
		this.setLocation(newLocationX, newLocationY);*/
		super.Move(elapsedTime,dCmpSize);
	}
	public void turn(int heading) {
	
		setHeading(((this.getHeading() + heading) % 360));
		
	}
	
	
	public String toString() {
		String s = super.toString(); 
		return "Ant: " + s + " maxSpeed: " + getMaximumSpeed() + " foodConsumptionRate: " +  getFoodConsumptionRate(); 
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		g.drawArc((int)(this.getLocation().getX() + pCmpRelPrnt.getX() - (getSize()/2)), (int) ((int)this.getLocation().getY() + pCmpRelPrnt.getY() - (this.getSize()/2)), this.getSize(), this.getSize(), 0, 360);
		g.fillArc((int)(this.getLocation().getX() + pCmpRelPrnt.getX() - (getSize()/2)), (int) ((int)this.getLocation().getY() + pCmpRelPrnt.getY() - (this.getSize()/2)), this.getSize(), this.getSize(), 0, 360);
		
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		GameWorld gw = new GameWorld();
		if (!otherObject.getObjectsCollide().contains(this) && !this.getObjectsCollide().contains(otherObject)) {
			if(otherObject instanceof FoodStation) {
				
				gw.collideFood((FoodStation)otherObject);
				
			}
			else if(otherObject instanceof Spider) {
				gw.collideSpider();
			}
			else if(otherObject instanceof Flag) {
				
				gw.collideFlag((Flag)otherObject);
			}
			this.getObjectsCollide().add(otherObject);
			otherObject.getObjectsCollide().add(this);
		}
	}
	
}
