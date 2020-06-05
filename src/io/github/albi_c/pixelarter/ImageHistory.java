package io.github.albi_c.pixelarter;

public class ImageHistory {
	private Image[] history = {null, null, null, null, null};
	public Image img;
	
	public ImageHistory(Image img) {
		this.img = img;
	}
	
	public Image[] shiftArrayLeft(Image[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i - 1 >= 0)
				arr[i - 1] = arr[i];
		}
		return arr;
	}
	
	public void registerChange(Image old) {
		this.history[4] = this.history[3];
		this.history[3] = this.history[2];
		this.history[2] = this.history[1];
		this.history[1] = this.history[0];
		this.history[0] = old.clone();
	}
	
	public Image reverseChange() {
		Image out;
		if (this.history[0] != null)
			out = this.history[0].clone();
		else
			out = this.img;
		this.history = this.shiftArrayLeft(this.history);
		return out;
	}
}
