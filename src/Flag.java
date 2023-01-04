package com.mycompany.a1;

public class Flag extends Fixed {

	private int sequenceNumber;
	
	public Flag(double x, double y, int sequenceNumber){
			super(x,y,10,0,255,0);
			setColor(0,255,0);
			this.sequenceNumber = sequenceNumber;
			
	}
	public int getSequenceNumbner() {
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
}
