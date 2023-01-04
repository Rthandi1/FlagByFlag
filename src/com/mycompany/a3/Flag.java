package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {
	//Private Fields
	private int size;
	private int sequenceNumber;
	private GameWorld gw;
	public Flag(float x, float y,int size, int sequenceNumber,GameWorld gw){
			super(x,y,100,15,0,255);
			setColor(0,0,255);
			this.size = size;
			this.sequenceNumber = sequenceNumber;
			this.gw = gw;
	}
	public int getSequenceNumber() {
		return this.sequenceNumber;
		
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	

	public String toString() {
		String SuperS = super.toString();
		String s = "Flag: " + SuperS + " seqNum = " + this.sequenceNumber;
		return s;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		int[] xPoints = {xLoc , (xLoc - this.getSize()/2),(xLoc + this.getSize()/2) };
		int[] yPoints = {(yLoc + this.getSize()/2),(yLoc- this.getSize()/2),(yLoc- this.getSize()/2)};
		g.setColor(this.getColor());
		if(super.isSelected()) {
			g.drawPolygon(xPoints, yPoints, 3);
			}else {
			g.fillPolygon(xPoints, yPoints, 3);	
			
			}
	
		g.setColor(ColorUtil.GRAY);
		g.drawString(""+this.getSequenceNumber(), xLoc, yLoc);
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
	
		if (!this.getObjectsCollide().contains(otherObject))
		if(otherObject instanceof Ant) {
		
			gw.collideFlag(this);
			otherObject.getObjectsCollide().add(this);
			this.getObjectsCollide().add(otherObject);
	}
		
	}
}
