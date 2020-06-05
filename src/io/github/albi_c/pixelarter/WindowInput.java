package io.github.albi_c.pixelarter;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowInput extends ComponentAdapter {
	private PixelArter art;
	
	public WindowInput(PixelArter art) {
		this.art = art;
	}
	
	public void componentResized(ComponentEvent e) {
		this.art.windowEvent("resize");
	}
}
