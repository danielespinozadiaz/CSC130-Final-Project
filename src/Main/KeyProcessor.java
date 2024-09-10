/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(100);
	
	// Static Method(s)
	public static void processKey(char key){
			if(key == ' ') {
			Main.trigger = false;
			Main.direction = " ";
			return;
			}
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
	    	
		switch(key){
		
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'w':
			Main.direction = "w";
			Main.trigger = true;
			if (Main.walkUp.size() >= 8) {
		        Main.upSpriteIndex++;
		        if (Main.upSpriteIndex >= 8) {
		            Main.upSpriteIndex = 0;
		        }
			 }
			Main.currentVec.adjustY(-8);
			Main.coordsVec.adjustY(-10);
			break;
			
		case 'a':
	        Main.direction = "a";
			Main.trigger = true;
			Main.interact = false;
			Main.inspectChest = "";
			Main.inspectBall = "";
			Main.rmvBallDesc = "";
			Main.rmvChestDesc = "";
		    if (Main.walkLeft.size() >= 5) {
		        Main.leftSpriteIndex++;
		        if (Main.leftSpriteIndex >= 5) {
		            Main.leftSpriteIndex = 0;
		        }
			 }
	        Main.currentVec.adjustX(-8);
	        Main.coordsVec.adjustX(-10);
	        break;
			
		case 's':
			Main.direction = "s";
			Main.trigger = true;
			Main.interact = false;
			Main.inspectChest = "";
			Main.inspectBall = "";
			Main.rmvBallDesc = "";
			Main.rmvChestDesc = "";
			if (Main.walkDown.size() >= 8) {
		        Main.downSpriteIndex++;
		        if (Main.downSpriteIndex >= 8) {
		            Main.downSpriteIndex = 0;
		        }
			 }
			Main.currentVec.adjustY(8);
			Main.coordsVec.adjustY(10);
			break;
			
		case 'd':
		    Main.direction = "d";
			Main.trigger = true;
			Main.interact = false;
			Main.inspectChest = "";
			Main.inspectBall = "";
			Main.rmvBallDesc = "";
			Main.rmvChestDesc = "";
		    if (Main.walkRight.size() >= 5) {
		        Main.rightSpriteIndex++;
		        if (Main.rightSpriteIndex >= 5) {
		            Main.rightSpriteIndex = 0;
		        }
			 }
		    Main.currentVec.adjustX(8);
		    Main.coordsVec.adjustX(10);
		    break;
		    
		case '$':
		    if(Main.isColliding(Main.pBox, Main.chest)) {
			    Main.interact = true;
			    Main.inspectChest = "'A TREASURE CHEST WITH LOTS OF GOLD INSIDE'";
		    }
		    
		    if(Main.isColliding(Main.pBox, Main.ball)) {
			    Main.interact = true;
			    Main.inspectBall = "'A SOCCER BALL INFLATED TO PERFECTION'";
		    }
		    break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
	    	}
		}
	}