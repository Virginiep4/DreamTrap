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
		screen.requestFocus();
		startGameLoop();
	}
	
	/**
	 * The function create a Thread that will handle the game loop
	 */
	private void startGameLoop() {
		gameThread = new Thread(this);
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
		double timePerFrame = 1000000000.0 / FPS_LIMIT;
		double timePerUpdate = 1000000000.0 / UPS_LIMIT;
		
		long previousTime = System.nanoTime();
		long crtTime;
		
		double deltaFrame = 0;
		double deltaUpdate = 0;
		
		while (true) {
			crtTime = System.nanoTime();
			deltaFrame += (crtTime - previousTime) / timePerFrame;
			deltaUpdate += (crtTime - previousTime) / timePerUpdate;
			previousTime = crtTime;
			
			if (deltaUpdate >= 1) {
				updateGame();
				deltaUpdate--;
				
			}
			
			if (deltaFrame >= 1) {
				screen.repaint();
				deltaFrame--;
			}		
		}
	}
}
