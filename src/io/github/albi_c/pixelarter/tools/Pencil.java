package io.github.albi_c.pixelarter.tools;

import io.github.albi_c.pixelarter.Image;

public class Pencil implements Tool {
	private boolean selected = false;
	public Image img;
	private boolean mousePressed = false;
	private Image old;
	
	public Pencil(Image img) {
		this.img = img;
	}
	
	public void mouseDown(int x, int y, int button) {
		if (button == 1) {
			this.mousePressed = true;
			if (this.selected) {
				this.old = this.img.clone();
				this.img.setPixel(x, y, this.img.selColor);
			}
		}
	}

	public void mouseUp(int x, int y, int button) {
		if (button == 1) {
			this.mousePressed = false;
			if (this.old != null) {
				this.img.art.history.registerChange(this.old);
				this.old = null;
			}
		}
	}

	public void mouseMove(int x, int y) {
		if (this.selected && this.mousePressed) {
			this.img.setPixel(x, y, this.img.selColor);
		}
	}
	
	public void setSelected(boolean sel) {
		this.selected = sel;
	}
	
	public boolean isSelected() {
		return this.selected;
	}

	public void setImage(Image img) {
		this.img = img;
	}
}
