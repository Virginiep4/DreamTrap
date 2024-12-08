package dreamTrap;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import entities.Character;

public class Window {
	private JFrame jframe;
	private Character character;
	
	public Window(Screen screen) {
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(screen);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
		
		character = screen.getCharacter();
		jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				// should make the game pause to prevent character continue moving
				character.right(false);
				character.left(false);
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}