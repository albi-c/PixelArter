package io.github.albi_c.pixelarter;

import java.awt.Color;
import java.awt.Graphics;

public class Image {
	private Pixel[][] img = new Pixel[64][64];
	public int w;
	public int h;
	public int[] selColor = {255, 255, 255, 255};
	public String filename = "";
	public boolean saved = true;
	public PixelArter art;
	
	public Image(int w, int h, PixelArter art) {
		this.w = w;
		this.h = h;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				this.img[y][x] = new Pixel(0, 0, 0, 0);
			}
		}
		this.art = art;
	}
	
	public boolean identic(Image o) {
		return o.getImage() == this.img;
	}
	
	public Pixel[][] getImage() {
		return this.img;
	}
	
	public int[] createColor(int r, int g, int b, int a) {
		int[] out = {r, g, b, a};
		return out;
	}
	
	public boolean setPixel(int x, int y, int[] color) {
		this.saved = false;
		try {
			this.img[y][x].setColor(color);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public int[] getPixel(int x, int y) {
		this.saved = false;
		try {
			return this.img[y][x].getColor();
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean erasePixel(int x, int y) {
		this.saved = false;
		try {
			this.img[y][x].setColor(new int[] {0, 0, 0, 0});
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, int[] c) {
	    int x, y, dx, dy, dx1, dy1, px, py, xe, ye, i;
	    
	    dx = x2 - x1;
	    dy = y2 - y1;
	    
	    dx1 = Math.abs(dx);
	    dy1 = Math.abs(dy);
	    
	    px = 2 * dy1 - dx1;
	    py = 2 * dx1 - dy1;
	    
	    if (dy1 <= dx1) {
	        
	        if (dx >= 0) {
	            x = x1; y = y1; xe = x2;
	        } else {
	            x = x2; y = y2; xe = x1;
	        }
	        this.setPixel(x, y, c);
	        
	        for (i = 0; x < xe; i++) {
	            x = x + 1;
	            
	            if (px < 0) {
	                px = px + 2 * dy1;
	            } else {
	                if ((dx < 0 && dy < 0) || (dx > 0 && dy > 0)) {
	                    y = y + 1;
	                } else {
	                    y = y - 1;
	                }
	                px = px + 2 * (dy1 - dx1);
	            }
	            
	            this.setPixel(x, y, c);
	        }
	    } else {
	        if (dy >= 0) {
	            x = x1; y = y1; ye = y2;
	        } else {
	            x = x2; y = y2; ye = y1;
	        }
	        this.setPixel(x, y, c);
	        
	        for (i = 0; y < ye; i++) {
	            y = y + 1;
	            
	            if (py <= 0) {
	                py = py + 2 * dx1;
	            } else {
	                if ((dx < 0 && dy<0) || (dx > 0 && dy > 0)) {
	                    x = x + 1;
	                } else {
	                    x = x - 1;
	                }
	                py = py + 2 * (dx1 - dy1);
	            }
	            
	            this.setPixel(x, y, c);
	        }
	    }
	    if (i == 0)
	    	return;
	    return;
	}
	
	public void drawRect(int x, int y, int w, int h, int[] c) {
		this.drawLine(x, y, x + w, y, c);
		this.drawLine(x + w, y, x + w, y + h, c);
		this.drawLine(x + w, y + h, x, y + h, c);
		this.drawLine(x, y + h, x, y, c);
		this.setPixel(x, y, c);
		this.setPixel(x + w, y, c);
		this.setPixel(x + w, y + h, c);
		this.setPixel(x, y + h, c);
	}
	
	public void fillRect(int dx, int dy, int w, int h, int[] c) {
		this.drawRect(dx, dy, w, h, c);
		this.setPixel(dx, dy, c);
		this.setPixel(dx + w, dy, c);
		this.setPixel(dx + w, dy + h, c);
		this.setPixel(dx, dy + h, c);
		if (w < 0) {
			dx += w;
			w = Math.abs(w);
		}
		if (h < 0) {
			dy += h;
			h = Math.abs(h);
		}
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				this.setPixel(x + dx, y + dy, c);
			}
		}
	}
	
	public void floodFill(int x, int y, int[] r) {
		this.floodFill(x, y, this.getPixel(x, y), r);
	}
	
	public void floodFill(int x, int y, int[] t, int[] r) {
		if (x < 0 || y < 0 || x > this.w || y > this.h) {
			return;
		} else if (this.compareColors(this.getPixel(x, y), r)) {
			return;
		} else if (!this.compareColors(this.getPixel(x, y), t)) {
			return;
		}
		this.setPixel(x, y, r);
		this.floodFill(x, y + 1, t, r);
		this.floodFill(x, y - 1, t, r);
		this.floodFill(x + 1, y, t, r);
		this.floodFill(x - 1, y, t, r);
	}
	
	private boolean compareColors(int[] c1, int[] c2) {
		return this.compareColors(c1, c2, true);
	}
	
	private boolean compareColors(int[] c1, int[] c2, boolean ignoreAlpha) {
		if (c1 == null || c2 == null)
			return false;
		if (ignoreAlpha)
			return c1[0] == c2[0] && c2[1] == c2[1] && c1[2] == c2[2];
		else
			return c1[0] == c2[0] && c2[1] == c2[1] && c1[2] == c2[2] && c1[3] == c2[3];
	}
	
	public void draw(int dx, int dy, Graphics g, int pixelsize, boolean grid) {
		int[] c = {0, 0, 0, 0};
		for (int y = 0; y < this.h; y++) {
			for (int x = 0; x < this.w; x++) {
				c = this.img[y][x].getColor();
				g.setColor(new Color(c[0], c[1], c[2]));
				g.fillRect(x * pixelsize + dx, y * pixelsize + dy, pixelsize, pixelsize);
			}
		}
		int xleft = pixelsize - dx;
		int ydown = pixelsize - dy;
		g.setColor(Color.white);
		for (int x = 1; x < this.w + 2; x++) {
			g.drawLine(x * pixelsize - xleft, dy, x * pixelsize - xleft, dy + (this.h * pixelsize));
		}
		for (int y = 1; y < this.h + 2; y++) {
			g.drawLine(dx, y * pixelsize - ydown, dx + (this.w * pixelsize), y * pixelsize - ydown);
		}
	}
	
	public Image clone() {
		Image i = new Image(this.w, this.h, this.art);
		i.selColor = this.selColor;
		i.saved = this.saved;
		i.filename = this.filename;
		for (int y = 0; y < this.h; y++) {
			for (int x = 0; x < this.w; x++) {
				i.setPixel(x, y, this.getPixel(x, y));
			}
		}
		return i;
	}
}
