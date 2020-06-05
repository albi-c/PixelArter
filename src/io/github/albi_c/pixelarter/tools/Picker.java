package io.github.albi_c.pixelarter.tools;

import io.github.albi_c.pixelarter.Image;

public class Picker implements Tool {
	private boolean selected = false;
	public Image img;
	
	public Picker(Image img) {
		this.img = img;
	}
	
	public void mouseDown(int x, int y, int button) {
		if (button == 1) {
			if (this.selected) {
				this.img.selColor = this.img.getPixel(x, y);
			}
		}
	}

	public void mouseUp(int x, int y, int button) {
	}

	public void mouseMove(int x, int y) {
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
