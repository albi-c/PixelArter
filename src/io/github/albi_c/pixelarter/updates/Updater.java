package io.github.albi_c.pixelarter.updates;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

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
			String os = System.getProperty("os.name").toLowerCase();
			try {
				if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") >= 0) {
					rt.exec("wget https://github.com/albi-c/PixelArter/releases/download/v" + latestVer + "/PixelArter-" +
							latestVer + ".jar PixelArter.jar -O PixelArter-download.jar");
					try {
						ZipFile file = new ZipFile(new File("PixelArter-download.jar"));
						boolean ok = file.getEntry("META-INF/MANIFEST.MF") != null;
						file.close();
						if (!ok)
							return;
					} catch (FileNotFoundException e) {
					} catch(ZipException e) {
					}
					rt.exec("mv -f PixelArter-download.jar " + jarPath);
				} else if (os.indexOf("win") >= 0) {
					URL url = new URL("https://github.com/albi-c/PixelArter/releases/download/v" + latestVer + "/PixelArter-" + 
									  latestVer + ".jar");
					ReadableByteChannel rbc = Channels.newChannel(url.openStream());
					FileOutputStream fos = new FileOutputStream("PixelArter-download.jar");
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					fos.close();
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
					}
					try {
						ZipFile file = new ZipFile(new File("PixelArter-download.jar"));
						boolean ok = file.getEntry("META-INF/MANIFEST.MF") != null;
						file.close();
						if (!ok)
							return;
					} catch (FileNotFoundException e) {
					} catch(ZipException e) {
					}
					rt.exec("move PixelArter-download.jar " + jarPath); // TODO: finsh //
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
