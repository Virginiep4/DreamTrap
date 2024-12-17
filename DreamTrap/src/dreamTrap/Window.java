package dreamTrap;

import javax.swing.JFrame;
@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private JFrame jframe;
	
	public Window(Screen screen) {
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(screen);
		jframe.setResizable(false);
		jframe.pack(); //choose the preferenced size that I  fix on Screen 
		jframe.setVisible(true);
	}

}