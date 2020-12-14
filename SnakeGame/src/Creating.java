import java.awt.Color;
import java.awt.Font;

import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.window.WindowControls;


public class Creating {

private int width;
private int height;
private int bs; // blocksize
private SimpleWindow w;
private WindowControls controls;

	public Creating(int width, int height, int window) {
		this.width = width;
		this.height = height;
		this.w = new SimpleWindow(width*window, height*window, "SNAKE"); 
	}
	
	public void block(int x, int y, int bs, java.awt.Color color ) {
		w.setLineColor(color);
		this.bs = bs;
		int left = x * bs;
		int right = left + bs - 1;
		int top = y * bs;
		int bottom = top + bs - 1;
		for(int row = top; row <= bottom; row ++){
		w.moveTo(left, row);
		w.lineTo(right, row); 
		}
	}
	
	public void Controls() {
	 controls = w.getAdvancedControls();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public char waitForKey() {
		return w.waitForKey();
	}
	
	public char getKey() {
		return w.getKey();
	}
	
	public void moveTo(int x, int y) {
		w.moveTo(x, y);
	}
	public void rectangle(int x, int y, int width, int height, java.awt.Color c) {
		int i = 10;
		for (int yy = y; yy < y + height; yy++){
			for(int xx = x; xx < x + width; xx++){
				block(xx, yy, i, c);
			}
		}
	}
	
	public void writeText(String s, Color fc, String fn, int fs) {
		controls.setFontName(fn);                    
		controls.setFontSize(fs);
		w.setLineColor(fc);
		w.writeText(s);	
	}
	
	public void clear() {
		w.clear();
	}
	
	
	public int KEY_EVENT() {
		return WindowControls.KEY_EVENT;
	}
	
	public int TIMEOUT_EVENT() {
		return WindowControls.TIMEOUT_EVENT;
	}
	
	
	public int waitForUserInput(int ms) {
		return controls.waitForUserInput(ms); 
	}
	
	public void quit() {
		w.close();	
	}
}