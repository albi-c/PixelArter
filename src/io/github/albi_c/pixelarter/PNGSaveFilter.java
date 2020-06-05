package io.github.albi_c.pixelarter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PNGSaveFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String s = f.getName().toLowerCase();
		
		return s.endsWith(".png");
	}

	@Override
	public String getDescription() {
		return "*.png,*.PNG";
	}

}
