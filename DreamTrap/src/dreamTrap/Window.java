package dreamTrap;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entities.Character;

public class Window extends JFrame {
	private JFrame jframe;
	private Character character;
	
	public Window(Screen screen) {
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(screen);
		
		jframe.setResizable(false);
		jframe.pack(); //choose the preferenced size that I  fix on Screen 
		jframe.setVisible(true);
		jframe.setLayout(null);
		
		character = screen.getCharacter();
		
		jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				// should make the game pause to prevent character continue moving
				character.moving.right(false);
				character.moving.left(false);
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}