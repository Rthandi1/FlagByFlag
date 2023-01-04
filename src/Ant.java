package com.mycompany.a1;

public class Ant extends MoveableGameObject implements ISteerable{

	//attributes
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	
	public Ant(double x, double y, int size, int heading, int speed) {
		super(x,y,size,heading,speed);
		setLocation(x,y);
		//Sets color of ant to brown
		setColor(150,75,0);
		//set max speed
		this.maximumSpeed = 50;
		this.foodLevel = 15;
		this.foodConsumptionRate = 2;
		this.healthLevel = 10;
		this.lastFlagReached = 1;

	
	
		
	}
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}public void setMaximumSpeed(int max) {
		this.maximumSpeed = max;
	}

	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	public void setFoodCOnsumptionRate(int foodConsumptionRate) {
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
	public int gettFoodLevel() {
		return foodLevel;
		
	}
	public int getLastFlag() {
		return this.lastFlagReached;
	}
	public void setLastFlag(int lastflagReached) {
		this.lastFlagReached = lastflagReached;
	}
	@Override
	public void Move() {
		super.Move();
	}
	public void turn(int heading) {
	
		setHeading(((this.getHeading() + heading) % 360));
		
	}
	
	
	public String toString() {
		return "Ant";
	}
}
