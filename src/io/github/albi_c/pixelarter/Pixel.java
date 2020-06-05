package io.github.albi_c.pixelarter;

public class Pixel {
	private int[] color = {0, 0, 0, 0};
	
	public Pixel(int r, int g, int b, int a) {
		int[] col = {r, g, b, a};
		this.color = col;
	}
	
	public Pixel(int[] col) {
		this.color = col;
	}
	
	public int[] getColor() {
		return this.color;
	}
	
	public void setColor(int r, int g, int b, int a) {
		int[] col = {r, g, b, a};
		this.color = col;
	}
	
	public void setColor(int[] col) {
		this.color = col;
	}
	
	public Pixel clone() {
		return new Pixel(this.color);
	}
}
