/* This is a way to pass a sprite's key information in one entity. (x, y, tag) */

package Data;

public class spriteInfo {
	// Fields
	private Vector2D v2d;
	private String tag;
	
	// Constructor
	public spriteInfo(Vector2D v2d, String tag){
		this.v2d = v2d;
		this.tag = tag;
	}
	
	// Methods
	public String getTag(){
		return tag;
	}
	
	public Vector2D getCoords(){
		return v2d;
	}
	
	public void setTag(String newTag){
		this.tag = newTag;
	}
	
	public void setCoords(Vector2D newV2D){
		this.v2d = newV2D;
	}
	
	public void setCoords(int x, int y){
		this.v2d = new Vector2D(x, y);
	}
	
	public String toString(){
		return "[" + v2d.getX() + ", " + v2d.getY() + ", " + tag + "]";
	}
}
