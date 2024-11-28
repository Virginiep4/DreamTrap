package dreamTrap;

import javax.swing.JFrame;

public class Window {
	private JFrame jframe;
	
	public Window(Screen screen) {
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(screen);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
	}

}
