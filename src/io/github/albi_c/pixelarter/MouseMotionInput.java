package io.github.albi_c.pixelarter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionInput implements MouseMotionListener {
	private PixelArter art;
	
	public MouseMotionInput(PixelArter art) {
		this.art = art;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		this.art.mouseEvent("move", e.getX(), e.getY(), 0);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.art.mouseEvent("move", e.getX(), e.getY(), 0);
	}
}
