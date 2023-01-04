package com.mycompany.a3;

import com.codename1.ui.geom.Point;

public abstract class Fixed extends GameObject implements ISelectable {
	
	private boolean isSelected = false;
	//Fixed needed parameters fixed location of x and y, color, and size
	public Fixed(float x, float y, int size,int r, int g, int b) {
		super(x,y,size,r,g,b);
	
		
	}
	

	
	@Override 
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		
		int xLoc = (int)(pCmpRelPrnt.getX()+ this.getLocation().getX() - (this.getSize()/2));// shape location relative
		int yLoc = (int)(pCmpRelPrnt.getY()+ this.getLocation().getY() - (this.getSize()/2));
		if ( (px >= xLoc) && (px <= xLoc+getSize()) && (py >= yLoc) && (py <= yLoc+getSize()) ) {
			
				return true; 
			}else {
				return false;
			}
		}
	@Override
	public boolean isSelected() {
		return this.isSelected;
	}
	@Override 
	public void setSelected(boolean b) {
		this.isSelected = b;
	}
}
