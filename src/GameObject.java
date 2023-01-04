package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

public class GameObject {
	private Point2D location;
	public Random rand;
	private int myColor;
	private double x;
	private double y;
	private int size;
	public GameObject() {
		
	}
	public GameObject(double x, double y, int size, int r,int g,int b) {
	 //this.x = Math.round((1025.0 * rand.nextDouble() * 10.0 )) / 10.0;
	// this.y = Math.round((769.0 * rand.nextDouble() * 10.0 )) / 10.0;
	 this.location = new Point2D(x,y);
	 this.myColor = ColorUtil.rgb(r, g, b);
	 this.size = size;
	 
	}
	public double getLocationX() {
		
		return location.getX();
		
	}
	public double getLocationY() {
	
	return location.getY();
	
		
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int s) {
		this.size = s;
	}
	public Point2D getLocation() {
		return location;
		
	}
	public void setLocation(double x, double y) {
		location.setX((Math.round(x)*10.0)/10.0);
		location.setY((Math.round(y)*10.0)/10.0);
		
		
	}
	public int getColor() {
		return this.myColor;
		
	}
	public void setColor(int r, int g, int b) {
		this.myColor = ColorUtil.rgb(r,g,b);
	}
	public String toString() {
		String s = "loc = " + location.getX() + "," + location.getY() + " Color = [" +
				ColorUtil.red(myColor) + "," + ColorUtil.green(myColor) + "," + ColorUtil.blue(myColor) + "]" + " Size = " + this.getSize();
		
		return s;
	}
}
