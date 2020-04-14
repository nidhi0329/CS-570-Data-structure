package Maze;

public class PairInt {

	private int x;
	private int y;
	
//	constructor
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
//  x value 
	public int getX() {
		return this.x;
	}
	
	
//  y value
	 public int getY() {
		return this.y;
	}
	
	
//	Set the value of x
	public void setX(int x) {
		this.x = x;
	}
	
//	Set the value of y
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
		if (p instanceof PairInt) {
			PairInt hold = (PairInt) p;
			return (this.x == hold.getX() && this.y == hold.getY());
		} else {
			return false;
		}
	}
	
	
//	Return (x, y)
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	
	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}
}
