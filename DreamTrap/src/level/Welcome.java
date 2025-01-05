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
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 24;
	public final static int BLOCK_PER_HEIGHT = 13;
	private final static float SCALE = 1f;

	public Welcome(Character character) {
		this.character = character;
	}

	public void draw(Graphics g, Character character, LevelManager levelManager) {

		g.setColor(new Color(0, 0, 0, 250));
		g.fillRect(0, 0, (int) (BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE), (int) (BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE));
		int x;
		int y;
		String text;
		g.setFont(g.getFont().deriveFont(Font.BOLD, 110f));
		text = "Dream Trap";
		g.setColor(Color.white);
		x = (int) ((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3.3);
		y = 200;

		g.drawString(text, x, y);

		g.drawImage(character.getCharacter()[0][0], ((int) ((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 3)), 250, 200, 182,
				null);

		// Play Again
		g.setFont(g.getFont().deriveFont(40f));
		text = "PLAY";
		x = (int) ((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 2.25);
		y = 500;
		g.drawString(text, x, y);
		g.drawString(">", x - 40, y);
		// putt current animation variable in a number exp 6

		g.drawString(">", x - 40, y);
		// putt current animation variable in a number exp 7

	}
}
