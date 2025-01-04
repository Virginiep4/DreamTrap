package dreamTrap;

import entities.Progression;
import java.time.Duration;
import java.time.Instant;


public class Game implements Runnable {
	private Window window;
	private Screen screen;
	private Thread gameThread; // is used to handle the game loop
	private final static int FPS_LIMIT = 60;
	private final static int UPS_LIMIT = 150; // updates per second
	private static Game game;
	private static Progression progress;
	private Time timer;
	

	
	public Game() {
		
		progress= new Progression("tom","",1);
		screen = new Screen(progress);
		window = new Window(screen);
		screen.requestFocus(); // ask jpanel to be ready to receive at any time input from keyboard
		
		timer=new Time();
		game=this;
		startGameLoop();
	}
	 public static Game getInstance() {
	        return game;
	    }
	
	/**
	 * The function create a Thread that will handle the game loop
	 */
	
	
	private void startGameLoop() {
		gameThread = new Thread(this); // independant sequence of instruction
		gameThread.start(); // calls run method
	}
	
	public void updateGame() {
		screen.updateGame();
		window.update();
		
	}
	
	
	
	
	
	/**
	 * this function is called by Thread.start() and repaint the screen
	 * the amount of time defined by fps_limit if the program is fast enough
	 * 
	 * timePerFrame is the duration of a frame in nanosecond
	 */
	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_LIMIT; //how long each frame(img) last before change img
		double timePerUpdate = 1000000000.0 / UPS_LIMIT;//how long last befor update
		
		long previousTime = System.nanoTime();
		long crtTime;
		
		double deltaFrame = 0;
		double deltaUpdate = 0;
		double timeUpdate = 0;
		
		while (true) {

			crtTime = System.nanoTime(); //current time in nano second
			
			timeUpdate+=(crtTime - previousTime)/ 1000000000.0;
			
			deltaFrame += (crtTime - previousTime) / timePerFrame;//time spend since last img 
			deltaUpdate += (crtTime - previousTime) / timePerUpdate;//time spend since last update 
			previousTime = crtTime;
			
			
			//if enough time spend for update game
			if (deltaUpdate >= 1) {
				updateGame();
				deltaUpdate--;
				
				
				
			}
			
			if(timeUpdate>=1) {
				timer.update();
				timeUpdate--;
				
			}

			//System.out.println("win: "+progress.getWin());
			//System.out.println("time: "+timer.getTotalTimeElapsed());
			/*if (timer.getTotalTimeElapsed() >= 6) {

			if (timer.getTotalTimeElapsed() >= 6) {

				setTimer();
				progress.setWin(0);
			}*/
			
			//if enough time spend for repaint my frame (img)
			if (deltaFrame >= 1) {
				screen.repaint();
				deltaFrame--;
			}		
		}
	}

	public void setTimer() {

		screen.setTime(timer.getTotalTimeElapsed());
	}
	
	
}
