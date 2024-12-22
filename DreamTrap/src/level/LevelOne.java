package level;

import java.awt.Graphics;

import dreamTrap.Screen;
import entities.Boss;

public class LevelOne extends LevelManager {
	private Boss boss;
	
	public LevelOne(Screen screen) {
		super(screen);
		boss = new Boss(screen.getCharacter());
		
	}

	@Override
	protected void additionalDraw(Graphics g) {
		g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
				boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX() - Screen.BLOCK_SIZE,
				boss.getyBlock() * Screen.BLOCK_SIZE + boss.getmovingYBlock(), Screen.BLOCK_SIZE * 2,
				Screen.BLOCK_SIZE * 2, null);
		
	}

	@Override
	public void update() {
		boss.update();
		
	}

}
