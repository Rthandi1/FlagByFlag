package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
	private int lives = 3;
	private int time =0;
	private Random rand = new Random();
	private ArrayList<GameObject> ObjCollection;
	private int lastFlag = 4;
	private Ant User;
	private int MaxSpeed;
	public GameWorld() {
		
	}
	public void init() {
		//Create four flags
		ObjCollection.add(new Flag(200.0,200.0,1));
		ObjCollection.add(new Flag(200.0,800.0,2));
		ObjCollection.add(new Flag(700.0,800.0,3));
		ObjCollection.add(new Flag(900.0,400.0,4));
		//Create an ant
		ObjCollection.add(new Ant(200.0,200,50,0,5));
		MaxSpeed = User.getMaximumSpeed();
		//create two spiders
		//ObjCollection.add(new Spider());
		//ObjCollection.add(new Spider());
		
	}
	public void accelerate() {
		int speed = User.getSpeed();
		if(speed + 1 < User.getMaximumSpeed()&& User.getSpeed() > 0) {
			speed++;
			User.setSpeed(speed);
		}else {
			User.setSpeed(User.getMaximumSpeed());
		}
		User.Move();
	}
	public void brake() {
		int min = 0;
		int speed = User.getSpeed();
		if(speed - 1 > min ) {
			speed--;
			User.setSpeed(speed);
		}else {
			User.setSpeed(min);
		}
		User.Move();
	}
	public void turnR() {
		User.turn(+5);
	}
	public void turnL() {
		User.turn(-5);
	}
	public void end() {
		
	}public void collideFood() {
		
	}
}
