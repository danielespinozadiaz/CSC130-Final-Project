/* This class is used to give you a handy way to pass a pair of 2D coordinates as a single object. The behavior (methods) of the class should allow for robust options in the future. */

package Data;

public class Vector2D {
	// Fields
	private int x;
	private int y;
	
	// Constructor
	public Vector2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Methods
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int newX){
		x = newX;
	}
	
	public void setY(int newY){
		y = newY;
	}
	
	public void adjustX(int adjustment){
		x += adjustment;
	}
	
	public void adjustY(int adjustment){
		y += adjustment;
	}
	
	public String toString(){
		return "[" + getX() + ", " + getY() + "]";
	}
}
