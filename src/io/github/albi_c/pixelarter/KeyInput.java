package io.github.albi_c.pixelarter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import io.github.albi_c.pixelarter.settings.ShortcutSettings;

public class KeyInput extends KeyAdapter {
	private PixelArter art;
	public Modifiers mod;
	
	public KeyInput(PixelArter art) {
		this.art = art;
		
		this.mod = new Modifiers();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_CONTROL) this.mod.ctrl = 1;
		if (key == KeyEvent.VK_SHIFT) this.mod.shift = 1;
		if (key == KeyEvent.VK_ALT) this.mod.alt = 1;
		
		if (!this.art.settings.keyboardShortcuts)
			return;
		
		ShortcutSettings s = new ShortcutSettings();
		if (s.shortcutColor[0] == key && s.shortcutColor[1] == this.mod.ctrl && s.shortcutColor[2] == this.mod.shift && s.shortcutColor[3] == this.mod.alt)
			this.art.changeColor();
		
		if (s.shortcutPencil[0] == key && s.shortcutPencil[1] == this.mod.ctrl && s.shortcutPencil[2] == this.mod.shift && s.shortcutPencil[3] == this.mod.alt)
			this.art.selectTool(0);
		if (s.shortcutEraser[0] == key && s.shortcutEraser[1] == this.mod.ctrl && s.shortcutEraser[2] == this.mod.shift && s.shortcutEraser[3] == this.mod.alt)
			this.art.selectTool(1);
		if (s.shortcutRect[0] == key && s.shortcutRect[1] == this.mod.ctrl && s.shortcutRect[2] == this.mod.shift && s.shortcutRect[3] == this.mod.alt)
			this.art.selectTool(2);
		if (s.shortcutFillrect[0] == key && s.shortcutFillrect[1] == this.mod.ctrl && s.shortcutFillrect[2] == this.mod.shift && s.shortcutFillrect[3] == this.mod.alt)
			this.art.selectTool(3);
		if (s.shortcutLine[0] == key && s.shortcutLine[1] == this.mod.ctrl && s.shortcutLine[2] == this.mod.shift && s.shortcutLine[3] == this.mod.alt)
			this.art.selectTool(4);
		if (s.shortcutBucket[0] == key && s.shortcutBucket[1] == this.mod.ctrl && s.shortcutBucket[2] == this.mod.shift && s.shortcutBucket[3] == this.mod.alt)
			this.art.selectTool(5);
		if (s.shortcutPicker[0] == key && s.shortcutPicker[1] == this.mod.ctrl && s.shortcutPicker[2] == this.mod.shift && s.shortcutPicker[3] == this.mod.alt)
			this.art.selectTool(6);
		
		if (s.shortcutSave[0] == key && s.shortcutSave[1] == this.mod.ctrl && s.shortcutSave[2] == this.mod.shift && s.shortcutSave[3] == this.mod.alt)
			this.art.fileOperation(0);
		if (s.shortcutSaveas[0] == key && s.shortcutSaveas[1] == this.mod.ctrl && s.shortcutSaveas[2] == this.mod.shift && s.shortcutSaveas[3] == this.mod.alt)
			this.art.fileOperation(1);
		if (s.shortcutOpen[0] == key && s.shortcutOpen[1] == this.mod.ctrl && s.shortcutOpen[2] == this.mod.shift && s.shortcutOpen[3] == this.mod.alt)
			this.art.fileOperation(2);
		if (s.shortcutNew[0] == key && s.shortcutNew[1] == this.mod.ctrl && s.shortcutNew[2] == this.mod.shift && s.shortcutNew[3] == this.mod.alt)
			this.art.fileOperation(3);
		if (s.shortcutUndo[0] == key && s.shortcutUndo[1] == this.mod.ctrl && s.shortcutUndo[2] == this.mod.shift && s.shortcutUndo[3] == this.mod.alt)
			this.art.fileOperation(4);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_CONTROL) this.mod.ctrl = 0;
		if (key == KeyEvent.VK_SHIFT) this.mod.shift = 0;
		if (key == KeyEvent.VK_ALT) this.mod.alt = 0;
	}
	
	private class Modifiers {
		public int ctrl = 0;
		public int alt = 0;
		public int shift = 0;
	}
}
