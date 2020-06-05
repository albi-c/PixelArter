package io.github.albi_c.pixelarter;

import java.awt.Dimension;

import javax.swing.JFrame;

import io.github.albi_c.pixelarter.settings.Settings;
import io.github.albi_c.pixelarter.settings.YamlParser;

public class Window {
	public JFrame frame;
	
	public Window(int w, int h, String title, PixelArter art) {
		YamlParser yaml = new YamlParser();
		Settings settings = yaml.getYamlSettings();
		art.setPreferredSize(new Dimension(settings.defaultWindowSize[0], settings.defaultWindowSize[1]));
		art.setMinimumSize(new Dimension(settings.defaultWindowSize[0], settings.defaultWindowSize[1]));
		art.setMaximumSize(new Dimension(settings.defaultWindowSize[0], settings.defaultWindowSize[1]));
		
		JFrame frame = new JFrame(title);
		this.frame = frame;
		frame.add(art);
		frame.pack();
		frame.addWindowListener(new WindowListener(art));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		art.window = this;
		art.start();
	}
}
