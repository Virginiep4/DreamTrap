package level;

import java.awt.Graphics;

import dreamTrap.Screen;

public class HubLevel extends LevelManager {

	public HubLevel(Screen screen) {
		super(screen, "/blockSprites.png", "/objectSprites.png", "/hubLevel.png");
		
		xCharacterSpawn = 200;
		yCharacterSpawn = (int) ((Screen.BLOCK_PER_HEIGHT - 5) * Screen.BLOCK_SIZE);
	}

	@Override
	public void draw(Graphics g) {
		for (int i = levelHeight - 1; i > levelHeight - Screen.BLOCK_PER_HEIGHT - 1; i--)
			for (int j = 0; j < Screen.BLOCK_PER_WIDTH + 1; j++) {
				int block = level[i][j];

				if (block != -1) {
					if (block < blocksLength) {
						g.drawImage(blocks[block], j * Screen.BLOCK_SIZE, (Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
								Screen.BLOCK_SIZE, null);

					} else if (block < blocksLength + objectsLength) {
						g.drawImage(objects[block - blocksLength],j * Screen.BLOCK_SIZE, (Screen.BLOCK_PER_HEIGHT - levelHeight + i) * Screen.BLOCK_SIZE, Screen.BLOCK_SIZE,
								Screen.BLOCK_SIZE, null);
					}
				}
			}
	}

	@Override
	protected void additionalDraw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void starsInitializer() {

	}

	@Override
	public void spikesInitializer() {
	}
}
