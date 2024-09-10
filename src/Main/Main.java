package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import Data.Box;
import Data.Vector2D;
import Data.spriteInfo;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{ 	// Daniel Espinoza Diaz
	
	/* ---- Fields (Static) below ---- */
	
	public static Color c = new Color(255,255,0); // Yellow

	public static stopWatchX timer = new stopWatchX(100);
	
	public static Vector2D currentVec = new Vector2D(600, 485);
	public static Vector2D coordsVec = new Vector2D(560, 385);
	
	public static ArrayList<spriteInfo> walkRight = new ArrayList<>();
	public static ArrayList<spriteInfo> walkLeft = new ArrayList<>();
	public static ArrayList<spriteInfo> walkUp = new ArrayList<>();
	public static ArrayList<spriteInfo> walkDown = new ArrayList<>();
	public static ArrayList<Box> border = new ArrayList<>();
	
	public static int rightSpriteIndex, leftSpriteIndex, upSpriteIndex, downSpriteIndex = 0;
	
	public static spriteInfo ri, li, ui, di;
	
	public static EZFileRead ezr = new EZFileRead("bounds.txt");
		
	public static boolean isKeyPressed, wasWPressed, wasAPressed, wasSPressed, wasDPressed = false;
	public static boolean initialIdleSprite = true;
	public static boolean trigger, interact = false;
	public static boolean isImageDrawn = false;
	
	public static String direction = "";
	public static String inspectChest = "";
	public static String inspectBall = "";
	public static String rmvChestDesc = "";
	public static String rmvBallDesc = "";
	
	public static Box pBox;
	public static Box chest;
	public static Box ball;

	
	// End Static fields...
	
	public static void main(String[] args){
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		
		// Populates ArrayList with frames for walking LEFT & RIGHT
	    for (int frameCounter = 1; frameCounter < 6; frameCounter++) {
	        walkRight.add(new spriteInfo(new Vector2D(100, 100), "r" + frameCounter));
	        walkLeft.add(new spriteInfo(new Vector2D(100, 100), "l" + frameCounter));
	    }
	    
		// Populates ArrayList with frames for walking UP & DOWN
	    for (int frameCounter = 1; frameCounter < 9; frameCounter++) {
	        walkUp.add(new spriteInfo(new Vector2D(100, 100), "u" + frameCounter));
	        walkDown.add(new spriteInfo(new Vector2D(100, 100), "d" + frameCounter));
	    }
	    
	    chest = new Box(330, 430, 317, 352);
		ball = new Box(1000, 1090, 410, 442);
		
/* ------- Parsing 'bounds' / 'bounding boxes' from "bounds.txt" below ------- */
	    
        String raw;
        while ((raw = ezr.getNextLine()) != "END OF FILE") {
            StringTokenizer st = new StringTokenizer(raw, "*");
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            border.add(new Box(x1, x2, y1, y2));
        }
}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		
		ctrl.addSpriteToFrontBuffer(0, 0, "back");
		ctrl.addSpriteToFrontBuffer(300, 280, "chest");
		ctrl.addSpriteToFrontBuffer(1000, 385, "ball");
			
		pBox = new Box(currentVec.getX() + 35, (currentVec.getX() + 60), currentVec.getY() + 20, (currentVec.getY() + 118));
		
		ctrl.drawString(250, 250, inspectChest, c);
		ctrl.drawString(840, 380, inspectBall, c);

/*---------- SPRITE MOVEMENT/DISPLAY STUFF BELOW HERE ----------*/
		
		if (timer.isTimeUp()) {
			
			//Walking LEFT sprites
		    if (!walkLeft.isEmpty()) {
		        li = walkLeft.get(leftSpriteIndex);
		        timer.resetWatch();

		        if (leftSpriteIndex >= walkLeft.size()) {
		            leftSpriteIndex = 0;
		        }
		    }

		    //Walking RIGHT sprites
		    if (!walkRight.isEmpty()) {
		        ri = walkRight.get(rightSpriteIndex);
		        timer.resetWatch();

		        if (rightSpriteIndex >= walkRight.size()) {
		        	rightSpriteIndex = 0;
		        }
		    }
		    
		    //Walking UP sprites
		    if (!walkUp.isEmpty()) {
		        ui = walkUp.get(upSpriteIndex);
		        timer.resetWatch();

		        if (upSpriteIndex >= walkUp.size()) {
		            upSpriteIndex = 0;
		        }
		    }
		    
		    //Walking DOWN sprites
		    if (!walkDown.isEmpty()) {
		        di = walkDown.get(downSpriteIndex);
		        timer.resetWatch();

		        if (downSpriteIndex >= walkDown.size()) {
		            downSpriteIndex = 0;
		        }
		    }
		    
		    
		    //Initializes boolean variables for idle frames
		    wasWPressed = direction.equalsIgnoreCase("w") && isKeyPressed;
		    wasAPressed = direction.equalsIgnoreCase("a") && isKeyPressed;
		    wasSPressed = direction.equalsIgnoreCase("s") && isKeyPressed;
		    wasDPressed = direction.equalsIgnoreCase("d") && isKeyPressed;
		    		    		    
		}
		
	    if (!walkRight.isEmpty() && !walkLeft.isEmpty() && !walkDown.isEmpty() && !walkUp.isEmpty()) {
			
	    	// INSTRUCTIONS WHEN GAME IS STARTED
			if (direction.equalsIgnoreCase(" ") && initialIdleSprite) {
				isKeyPressed = true;
                ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), di.getTag());
                ctrl.drawString(410, 405, "--- DANIEL ESPINOZA DIAZ || 2D JAVA GAME || CSC 130 ---", c);
                ctrl.drawString(480, 430, "PRESS EITHER 'W' 'A' 'S' 'D' TO START", c);
                ctrl.drawString(450, 455, "PRESS 'SPACEBAR' TO INTERACT WITH OBJECTS", c);
                ctrl.drawString(440, 480, "PRESS EITHER 'A' 'S' 'D' TO REMOVE DESCRIPTION", c);

            }
			
	    	//Moving UP
			if(direction.equalsIgnoreCase("w") && ui != null) {
	            isKeyPressed = true;
				ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), ui.getTag());
				}
			
			//Moving DOWN
			if(direction.equalsIgnoreCase("s") && di != null) {
	            isKeyPressed = true;
				ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), di.getTag());
					}
			
			//Moving LEFT
			if(direction.equalsIgnoreCase("a") && li != null) {
	            isKeyPressed = true;
				ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), li.getTag());
					}

		    //Moving RIGHT
			if(direction.equalsIgnoreCase("d") && ri != null) {
	            isKeyPressed = true;
				ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), ri.getTag());
					}
			
			
			/* ----------------- IDLE FRAME/SPRITE STUFF BELOW HERE ----------------- */
			
			if (!(direction.equalsIgnoreCase("d") || direction.equalsIgnoreCase("a") || 
			      direction.equalsIgnoreCase("s") || direction.equalsIgnoreCase("w"))) {
	            isKeyPressed = false;
	        }
			
			
			if(!isKeyPressed) {	
			
			// Removes starting instructions
			if(initialIdleSprite && (wasDPressed || wasAPressed || wasSPressed || wasWPressed)) {
				initialIdleSprite = false;
			}

			// Displays idle sprite for walking to the right
		    if (wasDPressed && direction.equalsIgnoreCase(" ")) {
		    	if(!timer.isTimeUp()) {
		    	ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), ri.getTag());
		    	timer.resetWatch();
	        	}
			}
		    
		    // Displays idle sprite for walking to the left
		    if (wasAPressed && direction.equalsIgnoreCase(" ")) {
		    	if(!timer.isTimeUp()) {
	            ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), li.getTag());
	            timer.resetWatch();
		    	}
	        }
		    
		    // Displays idle sprite for walking to the up
		    if (wasWPressed && direction.equalsIgnoreCase(" ")) {
		    	if(!timer.isTimeUp()) {
	            ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), ui.getTag());
	            timer.resetWatch();
		    	}
	        }
		    
		    // Displays idle sprite for walking to the down
		    if (wasSPressed && direction.equalsIgnoreCase(" ")) {
		    	if(!timer.isTimeUp()) {
	            ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), di.getTag());
	            timer.resetWatch();
		    	}
	        }
		    
		    
				}		           
	    	}

/* --------------- COLLISION STUFF BELOW HERE --------------- */

	    for(int i = 0; i < border.size(); i++) {
	    	if(isColliding(pBox, border.get(i))) {
	            stopPlayer();
	    	} 	
	    }
	}
	
	public static boolean isColliding(Box b1, Box b2) {
	    if (b1.getX2() < b2.getX1() || b1.getX1() > b2.getX2() ||
	        b1.getY2() < b2.getY1() || b1.getY1() > b2.getY2()) {
	        return false; 
	    } else {
	        return true; 
	    }
	}
	
		public static void stopPlayer() {			
			if(isColliding(pBox, border.get(0))) { // Right
				currentVec.adjustX(1);
		} else if (isColliding(pBox, border.get(1))) { // Left
			currentVec.adjustX(-1);
		} else if (isColliding(pBox, border.get(2))) { // Up
			currentVec.adjustY(1);
		} else if (isColliding(pBox, border.get(3))) { // Down
			currentVec.adjustY(-1);
			
/* ------------------------------- Colliding with chest ----------------------------------------------------- */
			
		} else if(isColliding(pBox, border.get(4))) { 
			if(direction.equalsIgnoreCase("w")) {
				currentVec.adjustY(1);
			} else if(direction.equalsIgnoreCase("s")) {
				currentVec.adjustY(-1);
			}

    	} else if(isColliding(pBox, border.get(5))) {
				currentVec.adjustX(-1);
    	} else if(isColliding(pBox, border.get(6))) {
				currentVec.adjustX(1);
				
/* --------------------------------- Colliding with Soccer Ball ------------------------------------------------ */

				
    	} else if(isColliding(pBox, border.get(7))) { 
    		if(direction.equalsIgnoreCase("w")) {
				currentVec.adjustY(1);
    		} else if(direction.equalsIgnoreCase("s")) {
				currentVec.adjustY(-1);
			} 
    		
    	} else if(isColliding(pBox, border.get(8))) {
			currentVec.adjustX(-1);
    	} else if(isColliding(pBox, border.get(9))) {
			currentVec.adjustX(1);
    	}
			
	}	
}