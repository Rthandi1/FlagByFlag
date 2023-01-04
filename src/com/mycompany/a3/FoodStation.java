package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed {
	public int capacity;
	
	private GameWorld gw;
	public FoodStation(float x, float y, int size,GameWorld gw) {
		super(x,y,size,0,255,0);
		this.capacity = this.getSize();
		setColor(0,255,0);
		this.gw = gw;
		
	}
	/*
	 * Gets size of the initial capacity of the food station
	 */
	public int getCapacity() {
		return this.capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String toString() {
		String SuperS = super.toString();
		return "Foodstation: " + SuperS + " capacity = " + this.capacity ;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int w = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int h = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		g.setColor(this.getColor());
	    if(super.isSelected()) {
		g.drawRect( w -(int)(this.getSize()/2), h -(int)(this.getSize()/2),this.getSize(),this.getSize());
		}else {
		g.fillRect( w -(int)(this.getSize()/2), h -(int)(this.getSize()/2), this.getSize(),this.getSize());

		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(""+ this.getCapacity(), w -(int)(this.getSize()/2),  h -(int)(this.getSize()/2));
		
	}
	@Override
	public void handleCollision(GameObject otherObject) {
		//if hasn't handled collision
		if (!this.getObjectsCollide().contains(otherObject)) {
			if(otherObject instanceof Ant)
				gw.collideFood((FoodStation)this);
				this.getObjectsCollide().add(otherObject);
				otherObject.getObjectsCollide().add(this);
				
		
			
		}
		
	}
	
	}
