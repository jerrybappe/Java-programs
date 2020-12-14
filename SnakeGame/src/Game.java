import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class Game {
	private Creating c;
	private int score;			
	private boolean b; 
	private int wt; //user input wait time, ms. lower -> increased speed
	private int sl; // snake length			
	private int ps; // block size

	public static void main(String[] args) {
	    Game g = new Game(100, 70, 10);
	    g.start();
	}
	
	public Game(int width, int height, int window) {
		c = new Creating(width, height, window);
	}
	
	public void start() {
		c.rectangle(0, 0, 100, 70, Colors.PINK);		
		c.Controls();
		c.moveTo(250, 300);
		c.writeText("SNAKE", Colors.SOIL, Font.MONOSPACED, 50);
		c.moveTo(250, 350);
		c.writeText("press a button to start.", Colors.SOIL, Font.MONOSPACED, 15);
		c.moveTo(250, 400);
		c.writeText("press 'w' for up, 'a' for left, 's' for down & 'd' for right.", Colors.SOIL, Font.MONOSPACED, 15);
		c.waitForKey();
		c.clear();
		c.rectangle(0, 0, 100, 70, Colors.PINK);
		c.rectangle(300, 300, 10, 10, Color.white);
		snake();
	}
	
	private void snake() {
		while (true) {
			score = 0;
			
			c.rectangle(0, 0, 100, 70, Colors.PINK);		
			c.Controls();
			c.rectangle(90, 60, 6, 6, Colors.PINK);
			c.moveTo(910, 633);
			c.writeText(Integer.toString(score), Color.black, Font.MONOSPACED, 30); //ändra detta
				
			sl = 3;
			wt = 100;
			ps = 13;
			
			Random rand = new Random();
			int rands = 10; // number of point blocks
			int[] randx = new int[10000];
			int[] randy = new int[10000];
			
			int ylim = 54;
			int xlim = 77;
			
			b = true;
			
			for (int i = 0; i <= rands - 1; i++) {
				randx[i] = rand.nextInt(xlim);
				randy[i] = rand.nextInt(ylim);
				c.block(randx[i], randy[i], ps, Color.red); // create the point blocks
			}
					
			int x = c.getWidth() / 2; 
			int y = c.getHeight() / 2;
			int[] pixelsy = new int[100000]; // y-position of snake head
			int[] pixelsx = new int[100000]; // x-position of snake head
			pixelsx[0] = x;
			pixelsy[0] = y;
			pixelsx[1] = x + 1;
			pixelsy[1] = y + 1;
			pixelsx[2] = x + 2;
			pixelsy[2] = y + 2;
			pixelsx[3] = x + 3;
			pixelsy[3] = y + 3;
		
			b = true;
			while (b == true) { 
				c.block(pixelsx[0], pixelsy[0], ps , Colors.SNAKE);
				c.block(pixelsx[sl], pixelsy[sl], ps, Colors.PINK);
			
			for(int i = sl; i > 0; i--) {
				pixelsy[i] = pixelsy[i-1];
				pixelsx[i] = pixelsx[i-1];
			}
				
			int ev = c.waitForUserInput(wt);
			char key = c.getKey();
			if (ev == c.KEY_EVENT()) { 
				if (key == 'w') { 
					pixelsy[0]--;
				}	else if (key == 'a') 
					{ pixelsx[0]--;
				}	else if (key == 's') 
					{ pixelsy[0]++;
				}	else if (key == 'd') 
					{ pixelsx[0]++;
				}	else { 
					
				}

            } else if (ev == c.TIMEOUT_EVENT()) {  
            	if (key == 'w') { 
            		pixelsy[0]--;
				}	else if (key == 'a') { 
					pixelsx[0]--;
				}	else if (key == 's') { 
					pixelsy[0]++;
				}	else if (key == 'd') { 
					pixelsx[0]++;
				}
            }
			
			if (pixelsx[0] >= 77 || pixelsy[0] >= 54 || pixelsx[0] <= 0 || pixelsy[0] <= 0) {
				lost();
			}
		
			for (int i = 1; i <= sl-1; i++) {
				if (pixelsy[0] == pixelsy[i] && pixelsx[0] == pixelsx[i]) { // lose if going backwards and on tail
					lost();
				}
			} 
			
			for (int i = 0; i <= rands - 1; i++) { // loop through score block positions
				if (pixelsx[0] == randx[i] && pixelsy[0] == randy[i]) { // if score
					randx[i] = 0; //remove old point block by putting it in corner where snake can't reach
					randy[i] = 0;
	            	randx[rands] = rand.nextInt(xlim);
	        		randy[rands] = rand.nextInt(ylim);
	            	rands++; // new point block
	        		c.block(randx[rands-1], randy[rands-1], ps , Color.red);
	        		sl++; //increase length of snake
	        		score++;
	        		if (wt > 20) { //increase speed but not under 20 ms
	        			wt = wt - 5;
	        		}
	        		pixelsx[sl] = pixelsx[sl-1];
	        		pixelsy[sl] = pixelsy[sl-1];
	        		c.rectangle(90, 60, 6, 6, Colors.PINK);
	    			c.moveTo(910, 633);
	    			c.writeText(Integer.toString(score), Color.black, Font.MONOSPACED, 30);
				}
			}
	     }
		}
	}
	
	private void lost() {
		c.clear();
		c.rectangle(0, 0, 100, 70, Colors.PINK);
		c.moveTo(250, 250);
		c.writeText("GAME OVER", Colors.SOIL, Font.MONOSPACED, 50);
		c.moveTo(250, 300);
		c.writeText("You got " + score + " points.", Colors.SOIL, Font.MONOSPACED, 25);
		c.moveTo(250, 350);
		c.writeText("press any key to try again.", Colors.SOIL, Font.MONOSPACED, 15);
		c.waitForUserInput(30000);
		c.clear();
		b = false;
	}

}

