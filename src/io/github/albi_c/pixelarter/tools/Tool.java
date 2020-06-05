package io.github.albi_c.pixelarter.tools;

import io.github.albi_c.pixelarter.Image;

public interface Tool {
	public void mouseDown(int x, int y, int button);
	public void mouseUp(int x, int y, int button);
	public void mouseMove(int x, int y);
	public void setSelected(boolean sel);
	public void setImage(Image img);
}
