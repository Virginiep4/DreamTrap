package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dreamTrap.Game;
import dreamTrap.Screen;
import entities.Progression;
import entities.Character;

public class Welcome2 {
	public Welcome2() {
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 250));
		g.fillRect(0, 0, (int) (Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH * Screen.SCALE), (int) (Screen.BLOCK_SIZE * Screen.BLOCK_PER_HEIGHT * Screen.SCALE));
		int x;
		int y;
		String text;
		g.setFont(g.getFont().deriveFont(Font.BOLD, 40f));
		text = "Vous ouvrez les yeux… mais vous rêvez encore.";
		g.setColor(Color.white);
		x = (int) ((Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH * Screen.SCALE) / 5);
		y = 250;

		g.drawString(text, x, y);

		g.setFont(g.getFont().deriveFont(30f));
		text = "Entrez votre nom de rêveur ";
		g.setColor(Color.white);
		x = (int) ((Screen.BLOCK_SIZE * Screen.BLOCK_PER_WIDTH * Screen.SCALE) / 2.75);
		y = 375;

		g.drawString(text, x, y);
	}
}
