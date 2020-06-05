package io.github.albi_c.pixelarter.tools;

public class ToolHandler {
	public Tool[] tools = new Tool[16];
	
	public ToolHandler(Tool[] tools) {
		this.tools = tools;
	}
	
	public void mouseDown(int x, int y, int button) {
		for (int i = 0; i < this.tools.length; i++) {
			this.tools[i].mouseDown(x, y, button);
		}
	}
	
	public void mouseUp(int x, int y, int button) {
		for (int i = 0; i < this.tools.length; i++) {
			this.tools[i].mouseUp(x, y, button);
		}
	}
	
	public void mouseMove(int x, int y) {
		for (int i = 0; i < this.tools.length; i++) {
			this.tools[i].mouseMove(x, y);
		}
	}
}
