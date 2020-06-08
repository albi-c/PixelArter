package io.github.albi_c.pixelarter.settings;

import java.awt.event.KeyEvent;

public class ShortcutSettings {
	public int[] shortcutColor = {KeyEvent.VK_C, 0, 0, 0};
	public int[] shortcutPencil = {KeyEvent.VK_P, 0, 0, 0};
	public int[] shortcutEraser = {KeyEvent.VK_E, 0, 0, 0};
	public int[] shortcutRect = {KeyEvent.VK_R, 0, 0, 0};
	public int[] shortcutFillrect = {KeyEvent.VK_R, 0, 1, 0};
	public int[] shortcutLine = {KeyEvent.VK_L, 0, 0, 0};
	public int[] shortcutBucket = {KeyEvent.VK_B, 0, 0, 0};
	public int[] shortcutPicker = {KeyEvent.VK_P, 0, 1, 0};
	
	public int[] shortcutSave = {KeyEvent.VK_S, 1, 0, 0};
	public int[] shortcutSaveas = {KeyEvent.VK_S, 1, 1, 0};
	public int[] shortcutOpen = {KeyEvent.VK_O, 1, 0, 0};
	public int[] shortcutNew = {KeyEvent.VK_N, 1, 0, 0};
	public int[] shortcutUndo = {KeyEvent.VK_Z, 1, 0, 0};
}
