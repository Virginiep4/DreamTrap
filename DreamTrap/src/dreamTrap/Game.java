package dreamTrap;

public class Game implements Runnable {
	private Window window;
	private Screen screen;
	private Thread gameThread; // is used to handle the game loop
	private final int FPS_LIMIT = 60;
	
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
	
	/**
	 * this function is called by Thread.start() and repaint the screen
	 * the amount of time defined by fps_limit if the program is fast enough
	 * 
	 * timePerFrame is the duration of a frame in nanosecond
	 */
	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_LIMIT;
		long currentFrameStart = System.nanoTime();
		long crtTime = System.nanoTime();
		
		while (true) {
			crtTime = System.nanoTime();
			if (crtTime - currentFrameStart >= timePerFrame) {
				screen.repaint();
				currentFrameStart = crtTime;
			}
		}
	}
}
