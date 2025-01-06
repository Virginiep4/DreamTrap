package level;

import java.awt.Graphics;

import javax.swing.Timer;

import dreamTrap.Screen;
import entities.Boss;

public class FinalLevel extends LevelManager {
	
	private static FinalLevel finalLevel;
	private Boss boss;
	private boolean bossOver;

	public FinalLevel(Screen screen) {
		super(screen, "/FinalLevelBlock.png", "/objectSprites.png", "/FinalLevelRGB.png");
		
		finalLevel=this;
		xCharacterSpawn = 12 * Screen.BLOCK_SIZE;
		character.setPosX(xCharacterSpawn);		
		yCharacterSpawn = (Screen.BLOCK_PER_HEIGHT - 6) * Screen.BLOCK_SIZE;
		boss = new Boss(screen.getCharacter());
		bossOver = false;
		bossEvent();
	}

	@Override
	public void starsInitializer() {
		stars = new int[levelHeight][levelWidth];
		
		for (int i = 0; i < stars.length; i++) {
			for (int j = 0; j < stars[i].length; j++) {
				stars[i][j] = -1;
			}
		}
		
	}
	
	@Override
	public void spikesInitializer() {
		spikes = new int[levelHeight][levelWidth];
		for (int i = 0; i < levelHeight; i++) {
			for (int j = 0; j < levelWidth; j++) {
				spikes[i][j] = -1;
			}
		}
	}
	
	public void bossEvent() {
		Timer bossTimer = new Timer(60000, e -> { // PAS LE MÊME QUE Time !!!!!!!!!
			boss=null;
			setBossOver(true);
		});

		bossTimer.setRepeats(false);
		bossTimer.start();
	}

	@Override
	protected void additionalDraw(Graphics g) {
		if (boss != null) {
			g.drawImage(boss.getBoss()[boss.getCurrentAnimation()][boss.getAniIndex()],
					boss.getxBlock() * Screen.BLOCK_SIZE + boss.getmovingXBlock() - character.getPosX() + xCharacterSpawn,
					boss.getyBlock() * Screen.BLOCK_SIZE + boss.getmovingYBlock(), null);
		}
	}

	@Override
	public void update() {
		if (boss != null) {
			boss.update();
			HelpMethods.bossHurts(character, boss);
		}
		HelpMethods.OnSpike(character, this);
	}

	public boolean isBossOver() {
		return bossOver;
	}

	public void setBossOver(boolean bossOver) {
		this.bossOver = bossOver;
	}
	
	public static FinalLevel getInstance() {
		return finalLevel;
	}

}
