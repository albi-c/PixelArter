package io.github.albi_c.pixelarter;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	public JFrame frame;
	
	public Window(int w, int h, String title, PixelArter art) {
		art.setPreferredSize(new Dimension(w, h));
		art.setMinimumSize(new Dimension(w, h));
		art.setMaximumSize(new Dimension(w, h));
		
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
