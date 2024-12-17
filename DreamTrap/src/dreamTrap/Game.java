package dreamTrap;


public class Game implements Runnable {
	private Window window;
	private Screen screen;
	private Thread gameThread; // is used to handle the game loop
	private final int FPS_LIMIT = 60;
	private final int UPS_LIMIT = 150; // updates per second

	
	
	public Game() {
		
		
		screen = new Screen();
		window = new Window(screen);
		screen.requestFocus();//ask jpanel to be ready to recieve at any time input from keyboard
		startGameLoop();
	}
	
	/**
	 * The function create a Thread that will handle the game loop
	 */
	
	
	private void startGameLoop() {
		gameThread = new Thread(this);//independant sequence of instruction
		gameThread.start(); // calls run method
	}
	
	public void updateGame() {
		screen.updateGame();
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
		
		while (true) {
			crtTime = System.nanoTime(); //current time in nano second
			deltaFrame += (crtTime - previousTime) / timePerFrame;//time spend since last img 
			deltaUpdate += (crtTime - previousTime) / timePerUpdate;//time spend since last update 
			previousTime = crtTime;
			
			//if enough time spend for update game
			if (deltaUpdate >= 1) {
				updateGame();
				deltaUpdate--;
				
			}
			
			//if enough time spend for repaint my frame (img)
			if (deltaFrame >= 1) {
				screen.repaint();
				deltaFrame--;
			}		
		}
	}
}
