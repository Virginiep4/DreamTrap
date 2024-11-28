package dreamTrap;

public class Game implements Runnable {
	private Window window;
	private Screen screen;
	private Thread gameThread; // is used to handle the game loop
	private final int fpsLimit = 60;
	
	public Game() {
		screen = new Screen();
		window = new Window(screen);
		screen.requestFocus();
		startGameLoop();
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start(); // calls run method
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / fpsLimit; // duration of a frame in nanosecond
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
