package io.github.albi_c.pixelarter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	// UNCOMMENT TO USE // private PixelArter art;
	
	public KeyInput(PixelArter art) {
		// UNCOMMENT TO USE // this.art = art;
	}
	
	public void keyPressed(KeyEvent e) {
		// UNCOMMENT TO USE // int key = e.getKeyCode();
		/*
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) tempObject.setVelX(5);
				if (key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
			}
		}
		*/
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
}
