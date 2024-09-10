package Data;

public class Box {
	private int x1, x2, y1, y2;
	
	public Box(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void setX1(int newX1) {this.x1 = newX1;}
	
	public void setX2(int newX2) {this.x2 = newX2;}
	
	public void setY1(int newY1) {this.y1 = newY1;}
		
	public void setY2(int newY2) {this.y2 = newY2;}
	
	public int getX1() {return x1;}
	
	public int getX2() {return x2;}
	
	public int getY1() {return y1;}
		
	public int getY2() {return y2;}
	
	public String toString() {return "[" + getX1() + ", " + getX2() + ", " + getY1() + ", " + getY2() + "]";}	
}
