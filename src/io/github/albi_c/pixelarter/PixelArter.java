package io.github.albi_c.pixelarter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import java.lang.Math;

import io.github.albi_c.pixelarter.images.Assets;
import io.github.albi_c.pixelarter.images.ImageReader;
import io.github.albi_c.pixelarter.images.ImageWriter;
import io.github.albi_c.pixelarter.settings.Settings;
import io.github.albi_c.pixelarter.settings.YamlParser;
import io.github.albi_c.pixelarter.tools.Bucket;
import io.github.albi_c.pixelarter.tools.Eraser;
import io.github.albi_c.pixelarter.tools.FillRect;
import io.github.albi_c.pixelarter.tools.Line;
import io.github.albi_c.pixelarter.tools.Pencil;
import io.github.albi_c.pixelarter.tools.Picker;
import io.github.albi_c.pixelarter.tools.Rect;
import io.github.albi_c.pixelarter.tools.Tool;
import io.github.albi_c.pixelarter.tools.ToolHandler;
import io.github.albi_c.pixelarter.updates.UpdateChecker;
import io.github.albi_c.pixelarter.updates.Updater;

public class PixelArter extends Canvas implements Runnable {
	private static final long serialVersionUID = -4108775352043798144L;
	
	private boolean running = false;
	Thread thread;
	
	public int WIDTH = 1280, HEIGHT = 720;
	
	Random rand = new Random();
	
	Window window;
	Image img;
	ToolHandler handler;
	Assets assets;
	FileChooser fileChooser;
	YamlParser yamlParser;
	public Settings settings;
	public ImageHistory history;
	public UpdateChecker checker = new UpdateChecker();
	public Updater updater;
	
	ImageWriter writer = new ImageWriter();
	ImageReader reader;
	
	Tool[] tools = new Tool[16];
	
	public int pixelsize = 16;
	public int imgsize = 32;
	
	public int imgOffsetX = 52;
	public int imgOffsetY = 52;
	
	private void init() {
		this.reader = new ImageReader(this);
		this.yamlParser = new YamlParser();
		this.settings = this.yamlParser.getYamlSettings();
		this.updater = new Updater(this);
		if (this.settings.automaticUpdates) {
			this.updater.update();
		}
		
		WIDTH = this.settings.defaultWindowSize[0];
		HEIGHT = this.settings.defaultWindowSize[1];
		
		assets = new Assets(this);
		fileChooser = new FileChooser(this);
		
		this.pixelsize = (int) Math.floor((HEIGHT - 100) / imgsize);
		
		this.img = new Image(this.settings.defaultImageSize, this.settings.defaultImageSize, this);
		this.windowEvent("resize");
		this.history = new ImageHistory(this.img);
		
		Pencil pencil = new Pencil(this.img);
		Eraser eraser = new Eraser(this.img);
		Rect rect = new Rect(this.img);
		FillRect fillrect = new FillRect(this.img);
		Line line = new Line(this.img);
		Bucket bucket = new Bucket(this.img);
		Picker picker = new Picker(this.img);
		pencil.setSelected(true);
		this.tools = new Tool[] {pencil, eraser, rect, fillrect, line, bucket, picker};
		this.handler = new ToolHandler(this.tools);
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
		this.addMouseMotionListener(new MouseMotionInput(this));
		this.window.frame.addComponentListener(new WindowInput(this));
		
		this.window.frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
			}
		});
	}
	
	private int getClickedIcon(int x, int y) {
		if (x >= 10 && x <= 42) {
			if (y >= this.imgOffsetY && y <= this.imgOffsetY + 32)
				return 0;
			else if (y >= this.imgOffsetY + 52 && y <= this.imgOffsetY + 84)
				return 1;
			else if (y >= this.imgOffsetY + 84 && y < this.imgOffsetY + 116)
				return 2;
			else if (y >= this.imgOffsetY + 126 && y <= this.imgOffsetY + 158)
				return 3;
			else if (y >= this.imgOffsetY + 168 && y <= this.imgOffsetY + 200)
				return 4;
			else if (y >= this.imgOffsetY + 210 && y <= this.imgOffsetY + 242)
				return 5;
			else if (y >= this.imgOffsetY + 252 && y < this.imgOffsetY + 284)
				return 6;
			else if (y >= this.imgOffsetY + 294 && y < this.imgOffsetY + 326)
				return 7;
			else
				return -1;
		} else if (y >= 10 && y <= 42) {
			if (x >= this.imgOffsetX + 10 && x <= this.imgOffsetX + 42)
				return 10;
			else if (x >= this.imgOffsetX + 52 && x <= this.imgOffsetX + 84)
				return 11;
			else if (x >= this.imgOffsetX + 94 && x <= this.imgOffsetX + 126)
				return 12;
			else if (x >= this.imgOffsetX + 135 && x <= this.imgOffsetX + 167)
				return 13;
			else if (x >= this.imgOffsetX + 180 && x <= this.imgOffsetX + 210)
				return 14;
			else
				return -1;
		} else {
			return -1;
		}
	}
	
	private void changeColor() {
		Color col = JColorChooser.showDialog(this.window.frame, "Choose color", new Color(this.img.selColor[0], this.img.selColor[1], this.img.selColor[2]));
		this.img.selColor =  new int[] {col.getRed(), col.getGreen(), col.getBlue(), 255};
	}
	
	private void selectTool(int t) {
		for (int i = 0; i < this.handler.tools.length; i++) {
			this.handler.tools[i].setSelected(false);
		}
		this.handler.tools[t].setSelected(true);
		this.assets.selectedImage = t;
	}
	
	private void saveImage() {
		if (this.img.filename != "") {
			this.writer.writeImage(this.img, this.img.filename);
			this.img.saved = true;
		} else {
			this.saveImageAs(1);
		}
	}
	
	private void saveImageAs(int mode) {
		String path = this.fileChooser.saveFile();
		if (path == "") {
			return;
		} else {
			this.writer.writeImage(this.img, path);
			this.img.saved = true;
			this.img.filename = path;
		}
	}
	
	private void openImage() {
		String path = this.fileChooser.openFile();
		if (path == "") {
			return;
		} else {
			boolean o = true;
			if (!this.img.saved) {
				if (JOptionPane.showConfirmDialog(this.window.frame, "You have unsaved changes! Do you want to open another file?", "Open another file?",
												  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
					o = false;
				}
			}
			if (o) {
				Image bi = this.img.clone();
				this.img = this.reader.readImage(path);
				this.img.filename = path;
				this.img.saved = true;
				if (this.img == null) {
					this.img = bi.clone();
				}
				this.refreshToolsImage();
			}
		}
	}
	
	private void newImage() {
		int nw = 32, nh = 32;
		if (!this.img.saved) {
			if (JOptionPane.showConfirmDialog(this.window.frame, "You have unsaved changes! Do you want to open new image?", "Open new image?",
					  						  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
				return;
			}
		}
		int[] dim = this.showDimensionsDialog();
		if (dim != null) {
			Image bi = this.img.clone();
			nw = dim[0];
			nh = dim[1];
			this.img = new Image(nw, nh, this);
			this.img.selColor = bi.selColor;
			this.windowEvent("resize");
			this.refreshToolsImage();
		}
	}
	
	private void refreshToolsImage() {
		for (int i = 0; i < this.tools.length; i++) {
			if (this.tools[i] != null) {
				this.tools[i].setImage(this.img);
			}
		}
	}
	
	private int[] showDimensionsDialog() {
		String result = JOptionPane.showInputDialog(this.window.frame, "Enter size of the new image:", "Enter size", JOptionPane.QUESTION_MESSAGE);
		if (result == null)
			return null;
		result = result.replaceAll(" ", "");
		boolean ok = result.matches("^[1-6][0-9]$");
		if (ok) {
			return new int[] {Integer.parseInt(result), Integer.parseInt(result)};
		}
		return null;
	}
	
	private void reverseChange() {
		int[] sel = this.img.selColor;
		this.img = this.history.reverseChange();
		this.img.selColor = sel;
		this.history.img = this.img;
		this.refreshToolsImage();
	}
	
	private void fileOperation(int o) {
		if (o == 0) {
			this.saveImage();
		} else if (o == 1) {
			this.saveImageAs(0);
		} else if (o == 2) {
			this.openImage();
		} else if (o == 3) {
			this.newImage();
		} else if (o == 4) {
			this.reverseChange();
		}
	}
	
	private void onIconClick(int i) {
		if (i == 0) {
			this.changeColor();
		} else if (i >= 1 && i <= 9) {
			this.selectTool(i - 1);
		} else if (i >= 10 && i <= 14) {
			this.fileOperation(i - 10);
		}
	}
	
	public void mouseEvent(String name, int x, int y, int btn) {
		//System.out.println(name + " " + x + ", " + y + " - " + btn);
		if (name == "down") {
			if (x >= this.imgOffsetX && x <= this.pixelsize * this.imgsize + this.imgOffsetX && y >= this.imgOffsetY && y <= this.pixelsize * this.imgsize + this.imgOffsetY)
				this.handler.mouseDown((int)Math.floor((x - this.imgOffsetX) / this.pixelsize), (int)Math.floor((y - this.imgOffsetY) / this.pixelsize), btn);
			int option = getClickedIcon(x, y);
			this.onIconClick(option);
		} else if (name == "up") {
			this.handler.mouseUp((int)Math.floor((x - this.imgOffsetX) / this.pixelsize), (int)Math.floor((y - this.imgOffsetY) / this.pixelsize), btn);
		} else if (name == "move") {
			this.handler.mouseMove((int)Math.floor((x - this.imgOffsetX) / this.pixelsize), (int)Math.floor((y - this.imgOffsetY) / this.pixelsize));
		}
	}
	
	public void windowEvent(String name) {
		if (name == "resize") {
			WIDTH = getWidth();
			HEIGHT = getHeight();
			this.imgsize = this.img.h;
			this.pixelsize = (int) Math.floor((HEIGHT - 100) / this.imgsize);
		}
	}
	
	public synchronized void start() {
		if (running)
			return;
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int updates = 0;
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		////////////////////////////////////
		
		g.setColor(Color.black);
		g.fillRect(0,  0,  getWidth(), getHeight());
		
		this.img.draw(this.imgOffsetX, this.imgOffsetY, g, this.pixelsize, true);
		
		int[] c = this.img.selColor;
		g.setColor(new Color(c[0], c[1], c[2], c[3]));
		g.fillRect(10, this.imgOffsetY + 10, 32, 32);
		g.drawImage(this.assets.getImage(0), 10, this.imgOffsetY + 52, 32, 32, null);
		g.drawImage(this.assets.getImage(1), 10, this.imgOffsetY + 42 * 2, 32, 32, null);
		g.drawImage(this.assets.getImage(2), 10, this.imgOffsetY + 42 * 3, 32, 32, null);
		g.drawImage(this.assets.getImage(3), 10, this.imgOffsetY + 42 * 4, 32, 32, null);
		g.drawImage(this.assets.getImage(4), 10, this.imgOffsetY + 42 * 5, 32, 32, null);
		g.drawImage(this.assets.getImage(5), 10, this.imgOffsetY + 42 * 6, 32, 32, null);
		g.drawImage(this.assets.getImage(6), 10, this.imgOffsetY + 42 * 7, 32, 32, null);
		
		g.drawImage(this.assets.getTopImage(0), this.imgOffsetX + 10, 10, 32, 32, null);
		g.drawImage(this.assets.getTopImage(1), this.imgOffsetX + 52, 10, 32, 32, null);
		g.drawImage(this.assets.getTopImage(2), this.imgOffsetX + 47 * 2, 10, 32, 32, null);
		g.drawImage(this.assets.getTopImage(3), this.imgOffsetX + 45 * 3, 10, 32, 32, null);
		g.drawImage(this.assets.getTopImage(4), this.imgOffsetX + 44 * 4, 10, 32, 32, null);
		
		////////////////////////////////////
		
		g.dispose();
		bs.show();
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
	}
	
	public static void main(String args[]) {
		new Window(1280, 720, "Pixelarter", new PixelArter());
	}
}
