package io.github.albi_c.pixelarter;

import java.awt.FileDialog;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class FileChooser {
	private JFileChooser chooser;
	private PixelArter art;
	
	public FileChooser(PixelArter art) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		this.art = art;
	}
	
	public String saveFile() {
		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.chooser = new JFileChooser();
		this.chooser.addChoosableFileFilter(new PNGSaveFilter());
		int result = this.chooser.showSaveDialog(this.art.window.frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return "";*/
		
		FileDialog fd = new FileDialog(this.art.window.frame, "Save as", FileDialog.SAVE);
		fd.setDirectory(System.getProperty("user.home"));
		fd.setFile("image.png");
		fd.setVisible(true);
		String fn = fd.getFile();
		String d = fd.getDirectory();
		if (fn == null || d == null)
			return "";
		return d + fn;
	}
	
	public String openFile() {
		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.chooser = new JFileChooser();
		int result = this.chooser.showOpenDialog(this.art.window.frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return "";*/
		
		FileDialog fd = new FileDialog(this.art.window.frame, "Open", FileDialog.LOAD);
		fd.setDirectory(System.getProperty("user.home"));
		fd.setFile("*.png");
		fd.setVisible(true);
		String fn = fd.getFile();
		String d = fd.getDirectory();
		if (fn == null || d == null)
			return "";
		return d + fn;
	}
}
