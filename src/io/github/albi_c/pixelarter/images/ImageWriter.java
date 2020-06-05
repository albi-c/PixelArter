package io.github.albi_c.pixelarter.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.albi_c.pixelarter.Image;
import io.github.albi_c.pixelarter.Pixel;

public class ImageWriter {
	public void writeImage(Image img, String path) {
		BufferedImage bi = new BufferedImage(img.w, img.h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		Pixel[][] i = img.getImage();
		
		for (int y = 0; y < img.h; y++) {
			for (int x = 0; x < img.w; x++) {
				int[] c = i[y][x].getColor();
				g.setColor(new Color(c[0], c[1], c[2], c[3]));
				g.fillRect(x, y, 1, 1);
			}
		}
		
		File outputfile = new File(path);
		try {
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
