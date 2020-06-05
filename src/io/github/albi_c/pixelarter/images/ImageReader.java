package io.github.albi_c.pixelarter.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.albi_c.pixelarter.Image;
import io.github.albi_c.pixelarter.PixelArter;

public class ImageReader {
	PixelArter art;
	
	public ImageReader(PixelArter art) {
		this.art = art;
	}
	
	private int[] int2rgb(int rgb) {
		Color c = new Color(rgb);
		int[] out = {c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()};
		return out;
	}
	
	public Image readImage(String path) {
		BufferedImage i = null;
		try {
			i = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (i == null)
			return null;
		int w = i.getWidth();
		int h = i.getHeight();
		if (w > 64 || h > 64)
			return null;
		Image img = new Image(w, h, this.art);
		int rgb = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				rgb = i.getRGB(x, y);
				img.setPixel(x, y, this.int2rgb(rgb));
			}
		}
		return img;
	}
}
