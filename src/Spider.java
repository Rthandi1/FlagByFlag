package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public class Spider extends MoveableGameObject{
public Spider(int x, int y, int size,int heading, int speed) {
	super(x,y,size,heading,speed);
	//set spider to black
	setColor(0,0,0);
}
	


@Override
public void setColor(int r, int g, int b) {
	
}
@Override
public void Move() {
	int heading = getHeading();
	int speed = getSpeed();
	//Create delta X an Y variables
	double deltaX = 0;
	double deltaY= 0;
	//Create the old and new location variables
			
	Point2D oldLocation = getLocation();
			//Find the angle of theta
			int theta = 90 - getHeading();
		
			//Find delta x and y using the dis*speed equation
			deltaY = Math.sin(Math.toRadians(theta)) * getSpeed();
			deltaX = Math.sin(Math.toRadians(theta)) * getSpeed();
			
			
			//Change the newLocation values using delta x and y and the old locations
			double newLocationX =(deltaX + oldLocation.getX());
			double newLocationY =(deltaY + oldLocation.getY());
			
			//conditions to prevent the spider from being located the edge of screen or outside
			if((newLocationX <= 1024 && newLocationY <= 786) && (newLocationX >= 0 && newLocationY >= 0)) {
				deltaY = Math.sin(Math.toRadians(theta)) * getSpeed();
				deltaX = Math.sin(Math.toRadians(theta)) * getSpeed();
				newLocationX =(deltaX + oldLocation.getX());
				newLocationY =(deltaY + oldLocation.getY());
			}
			
			
			}
public String toString() {
	return "";
}
}
