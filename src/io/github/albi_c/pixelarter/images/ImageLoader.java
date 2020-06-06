package io.github.albi_c.pixelarter.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import io.github.albi_c.pixelarter.PixelArter;

public class ImageLoader {
	public BufferedImage loadImage(String path) {
		URL imgUrl = PixelArter.class.getResource(path);
		try {
			if (imgUrl != null)
				return ImageIO.read(imgUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
