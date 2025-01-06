package dreamTrap;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.progressionDAO;
import entities.Character;
import entities.backgroundd;

public class Window extends JFrame {
	private JFrame jframe;
	private static Window window;
	private Character character;
	private progressionDAO progression;

	public Window(Screen screen) {
		jframe = new JFrame();
		window=this;
		progression = new progressionDAO();

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(screen);
		jframe.setResizable(false);
		jframe.pack(); // choose the preferenced size that I fix on Screen
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

	public void SaveBeforeexit() {
		jframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int response = JOptionPane.showConfirmDialog(jframe, "Voulez-vous sauvegarder avant de quitter ?",
						"Sauvegarde", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					progression.Save();
					System.exit(0);
				}
				System.exit(0);
			}
		});
	}
	
	public static Window getInstance() {
		return window;
	}

	private Image importImg(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}