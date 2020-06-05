package io.github.albi_c.pixelarter.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import io.github.albi_c.pixelarter.PixelArter;

public class Assets {
	private String[] paths = {"pencil.png", "eraser.png", "rect.png", "fillrect.png", "line.png", "bucket.png", "save.png", "saveas.png", "load.png", "new.png", "picker.png", "undo.png"};
	private String basepath = "/res/";
	private ImageLoader loader = new ImageLoader();
	public BufferedImage pencil, eraser, rect, fillrect, line, bucket, picker, save, saveas, load, new_, undo;
	public int selectedImage = 0;
	private PixelArter art;
	
	public Assets(PixelArter art) {
		this.art = art;
		
		pencil = loader.loadImage(basepath + paths[0]);
		eraser = loader.loadImage(basepath + paths[1]);
		rect = loader.loadImage(basepath + paths[2]);
		fillrect = loader.loadImage(basepath + paths[3]);
		line = loader.loadImage(basepath + paths[4]);
		bucket = loader.loadImage(basepath + paths[5]);
		picker = loader.loadImage(basepath + paths[10]);
		
		save = loader.loadImage(basepath + paths[6]);
		saveas = loader.loadImage(basepath + paths[7]);
		load = loader.loadImage(basepath + paths[8]);
		new_ = loader.loadImage(basepath + paths[9]);
		undo = loader.loadImage(basepath + paths[11]);
	}
	
	public BufferedImage getImage(int n) {
		BufferedImage out = null;
		switch (n) {
			case 0:
				out = this.pencil;
				break;
			case 1:
				out = this.eraser;
				break;
			case 2:
				out = this.rect;
				break;
			case 3:
				out = this.fillrect;
				break;
			case 4:
				out = this.line;
				break;
			case 5:
				out = this.bucket;
				break;
			case 6:
				out = this.picker;
				break;
			default:
				break;
		}
		if (out != null) {
			if (n == this.selectedImage) {
				float[] tc = this.art.settings.selectedToolColor;
				out = this.tint(tc[0], tc[1], tc[2], tc[3], out);
			} else {
				float[] tc = this.art.settings.notSelectedToolColor;
				out = this.tint(tc[0], tc[1], tc[2], tc[3], out);
			}
		}
		return out;
	}
	
	public BufferedImage getTopImage(int n) {
		BufferedImage out = null;
		switch (n) {
			case 0:
				out = this.save;
				break;
			case 1:
				out = this.saveas;
				break;
			case 2:
				out = this.load;
				break;
			case 3:
				out = this.new_;
				break;
			case 4:
				out = this.undo;
				break;
			default:
				break;
		}
		if (out != null) {
			float[] tc = this.art.settings.topToolbarColor;
			out = this.tint(tc[0], tc[1], tc[2], tc[3], out);
		}
		return out;
	}
	
	public BufferedImage tint(float r, float g, float b, float a, BufferedImage sprite) {
		BufferedImage tintedSprite = new BufferedImage(sprite.getWidth(), sprite.
		getHeight(), BufferedImage.TRANSLUCENT);
		Graphics2D graphics = tintedSprite.createGraphics();
		graphics.drawImage(sprite, 0, 0, null);
		graphics.dispose();
		
		for (int i = 0; i < tintedSprite.getWidth(); i++) {
			for (int j = 0; j < tintedSprite.getHeight(); j++) {
				int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
				getDataElements(i, j, null));
				int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
				getDataElements(i, j, null));
				int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
				getDataElements(i, j, null));
				int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
				getDataElements(i, j, null));
				rx *= r;
				gx *= g;
				bx *= b;
				ax *= a;
				tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
			}
		}
		return tintedSprite;
	}
}
