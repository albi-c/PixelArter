package io.github.albi_c.pixelarter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WindowListener extends WindowAdapter {
	PixelArter art;
	
	public WindowListener(PixelArter art) {
		this.art = art;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if (art.img.saved) {
			System.exit(0);
		} else {
			if (JOptionPane.showConfirmDialog(this.art.window.frame, "You have unsaved changes! Do you want to close this window?", "Close window?",
											  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
