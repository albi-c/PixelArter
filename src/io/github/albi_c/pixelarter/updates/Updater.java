package io.github.albi_c.pixelarter.updates;

import java.io.IOException;

import io.github.albi_c.pixelarter.PixelArter;

public class Updater {
	private PixelArter art;
	
	public Updater(PixelArter art) {
		this.art = art;
	}
	
	public void update() {
		if (this.art.checker.updateAvailable()) {
			String latestVer = this.art.checker.getLatestVersion();
			if (latestVer == "")
				return;
			String jarPath = System.getProperty("java.class.path");
			if (jarPath == "")
				return;
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec("wget https://github.com/albi-c/PixelArter/releases/download/v" + latestVer + "/PixelArter-v" +
						latestVer + ".jar PixelArter.jar -O PixelArter-download.jar");
				rt.exec("mv -f PixelArter-download.jar " + jarPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
