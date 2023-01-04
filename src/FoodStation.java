package com.mycompany.a1;

public class FoodStation extends Fixed {
	public int capacity;
	public FoodStation(double x, double y, int size) {
		super(x,y,size,0,255,0);
		this.capacity = size;
	}
	/*
	 * Gets size of the initial capacity of the food station
	 */
	public int getCapcity() {
		return this.capacity;
	}
	public void setCapcity(int capacity) {
		this.capacity = capacity;
	}
	public String toString() {
		String SuperS = super.toString();
		return "Foodstation: " + SuperS + " capacity = " + this.capacity ;
	}
}
