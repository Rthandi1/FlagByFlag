package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public class MoveableGameObject extends GameObject {
	private int Speed;
	private int Heading;
	
	public MoveableGameObject(double x, double y,int size, int heading, int speed) {
		this.Heading = heading;
		this.Speed = speed;
		
	}
	public void Move() {
		//Create delta X an Y variables
		double deltaX = 0;
		double deltaY= 0;
		//Create the old and new location variables
		
		Point2D oldLocation = getLocation();
		Point2D newLocation = new Point2D(0,0);
		//Find the angle of theta
		int theta = 90 - getHeading();
	
		//Find delta x and y using the dis*speed equation
		deltaY = Math.sin(Math.toRadians(theta)) * getSpeed();
		deltaX = Math.sin(Math.toRadians(theta)) * getSpeed();
		
		
		//Change the newLocation values using delta x and y and the old locations
		newLocation.setX(deltaX + oldLocation.getX());
		newLocation.setY(deltaY + oldLocation.getY());
		
		//replaces the current location to newLocation
		setLocation(newLocation.getX(), newLocation.getY());
		
	}
	public void setSpeed(int s) {
		Speed = s;
		
	}
	public int getSpeed() {
		return Speed;
	}
	public int getHeading() {
		return Heading;
	}
	public void setHeading(int h) {
		Heading = h;
	}
	public String toString() {
		String superS = super.toString();
		String s = "Speed = " + Speed + " Heading = " + Heading;
		String r = superS + s;
		return r;
	}
}
