package io.github.albi_c.pixelarter.updates;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import io.github.albi_c.pixelarter.PixelArter;

public class UpdateChecker {
	public String getLatestVersion() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/albi-c/PixelArter/master/src/VERSION/VERSION.txt");
			Scanner s = new Scanner(url.openStream());
			String out = s.nextLine();
			s.close();
			return out;
		} catch (MalformedURLException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}
	
	public String getCurrentVersion() {
		try {
			URL url = PixelArter.class.getResource("/VERSION/VERSION.txt");
			Scanner s = new Scanner(url.openStream());
			String out = s.nextLine();
			s.close();
			return out;
		} catch (MalformedURLException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}
	
	public boolean updateAvailable() {
		String latest = this.getLatestVersion();
		String current = this.getCurrentVersion();
		if (latest == "" || current == "")
			return false;
		String[] spl1 = latest.split("\\.");
		String[] spl2 = current.split("\\.");
		if (Integer.parseInt(spl1[0]) > Integer.parseInt(spl2[0]) || Integer.parseInt(spl1[1]) > Integer.parseInt(spl2[1]))
			return true;
		if (spl1.length >= 3 && spl2.length >= 3) {
			if (Integer.parseInt(spl1[2]) > Integer.parseInt(spl2[2]))
				return true;
		}
		return false;
	}
}
