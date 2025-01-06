package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Progression;
import entities.Character;

public class Welcome {
	private Character character;

	public Welcome(Character character) {
		this.character = character;
	}

	public void draw(Graphics g, Character character, LevelManager levelManager) {

		g.setColor(new Color(0, 0, 0, 250));
		g.fillRect(0, 0, (int) (Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH), (int) (Screen.BLOCK_SIZE * Screen.BLOCK_PER_HEIGHT));
		int x;
		int y;
		String text;
		g.setFont(g.getFont().deriveFont(Font.BOLD, 50f + Screen.BLOCK_PER_WIDTH * 2));
		text = "Dream Trap";
		g.setColor(Color.white);
		x = (int) ((Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH) / 3.3);
		y = 200;

		g.drawString(text, x, y);

		g.drawImage(character.getCharacter()[0][0], ((int) ((Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH) / 3)), 250, 2000, 2400,
				null);

		// Play
		g.setFont(g.getFont().deriveFont(25f + Screen.BLOCK_PER_WIDTH / 2));
		text = "PLAY";
		x = (int) ((Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH) / 2.25);
		y = 500;
		g.drawString(text, x, y);
		g.drawString(">", x - 40, y);

		g.drawString(">", x - 40, y);

	}
}
