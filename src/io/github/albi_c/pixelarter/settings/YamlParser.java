package io.github.albi_c.pixelarter.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlParser {
	private String settingsFile = "PixelArter/settings.yml";
	private String settingsDir = "PixelArter/";
	private String homePath = System.getProperty("user.home");
	public static String defaultSettingsString = String.join("\n",
			   												 "selectedToolColor: [0.0, 1.0, 1.0, 1.0]",
			   												 "notSelectedToolColor: [1.0, 1.0, 1.0, 1.0]",
			   												 "topToolbarColor: [1.0, 1.0, 1.0, 1.0]",
			   												 "defaultImageSize: 32",
			   												 "defaultWindowSize: [1000, 700]");
	public static Settings defaultSettings = new Settings();
	
	public YamlParser() {
		this.settingsFile = Paths.get(this.homePath, this.settingsFile).toString();
		this.settingsDir = Paths.get(this.homePath, this.settingsDir).toString();
		File file = new File(this.settingsDir);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		file = new File(this.settingsFile);
		if (!file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			FileWriter fw = null;
			try {
				fw = new FileWriter(file);
				fw.write(YamlParser.defaultSettingsString);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
	}
	
	public Settings getYamlSettings() {
		Constructor constructor = new Constructor(Settings.class);
		Yaml yaml = new Yaml(constructor);
		try {
			InputStream inputStream = new FileInputStream(new File(this.settingsFile));
			return (Settings) yaml.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return YamlParser.defaultSettings;
	}
}
