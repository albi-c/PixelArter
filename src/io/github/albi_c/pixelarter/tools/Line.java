package io.github.albi_c.pixelarter.tools;

import io.github.albi_c.pixelarter.Image;

public class Line implements Tool {
	private boolean selected = false;
	public Image img;
	private int[] pos1 = {0, 0};
	private boolean nextpos = false;
	
	public Line(Image img) {
		this.img = img;
	}
	
	public void mouseDown(int x, int y, int button) {
		if (button == 1) {
			if (this.selected) {
				if (!this.nextpos) {
					this.pos1 = new int[] {x, y};
					this.nextpos = true;
				} else {
					Image old = this.img.clone();
					this.img.drawLine(this.pos1[0], this.pos1[1], x, y, this.img.selColor);
					this.nextpos = false;
					this.img.art.history.registerChange(old);
				}
			}
		} else if (button == 3) {
			this.nextpos = false;
		}
	}

	public void mouseUp(int x, int y, int button) {
	}

	public void mouseMove(int x, int y) {
	}
	
	public void setSelected(boolean sel) {
		this.selected = sel;
		if (sel == false) {
			this.nextpos = false;
		}
	}
	
	public boolean isSelected() {
		return this.selected;
	}

	public void setImage(Image img) {
		this.img = img;
	}
}
