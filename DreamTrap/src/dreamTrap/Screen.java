package dreamTrap;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inputs.KeyboardInputs;
import level.LevelManager;
import entities.Character;
import entities.Item;
import entities.Progression;
import entities.door;

import DAO.JoueurDAO;
import DAO.progressionDAO;
import level.FinalLevel;
import level.GameOverScreen;
import level.LevelOne;
import level.ScoreScreen;
import level.Welcome;
import level.Welcome2;
import level.HelpMethods;
import level.HubLevel;
import entities.Boss;
//import entities.Platform;

@SuppressWarnings("serial")
public class Screen extends JPanel {
	public final static int BLOCK_SIZE = 64;
	public final static int BLOCK_PER_WIDTH = 24;
	public final static int BLOCK_PER_HEIGHT = 13;
	private final static float SCALE = 1f;

	private Game game;
	private static Screen screen;
	private static boolean gotName = false;
	private static boolean initializedItems = false;

	public static LevelManager levelManager;
	private Character character;
	private entities.shop shop;
	private entities.backgroundd backgroundd;
	private static entities.door door0;
	private static entities.door door1;
	private static entities.door door2;
	private entities.door chainedDoor;
	private entities.door doorEndFinalLevel;
	private static entities.door doorEndLevelOne;
	private static entities.Item ailes;
	private static entities.Item pince;
	private entities.ShopInt fleche;
	private entities.ShopInt souris;

	private JoueurDAO joueur;

	private ScoreScreen scoreScreen;
	private GameOverScreen gameOverScreen;
	private Welcome welcomeScreen;
	private Welcome2 welcomeScreen2;
	private JTextField welcomeText;

	private Clip currentClip; // Clip pour le son actuel
	private int lastAnimation = 0; // Dernière animation pour vérifier les changements

	// getters and setters
	public Character getCharacter() {
		return character;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public Screen(Game game) {
		setScreenSize();
		this.game = game;
		screen = this;
		levelManager = new HubLevel(this);
		character = new Character(0, "0", 0, 0);
		joueur = new JoueurDAO();
		welcomeScreen = new Welcome(character);
		welcomeScreen2 = new Welcome2();
		backgroundd = new entities.backgroundd();
		this.setLayout(null);
		addKeyListener(new KeyboardInputs(this));
	}

	public void updateGame() {
		backgroundd.update();
		if (initializedItems) {
			shop.update();
			door0.update();
			door1.update();
			door2.update();
			chainedDoor.update();
			doorEndLevelOne.update();
			doorEndFinalLevel.update();
			ailes.update();
			pince.update();
			fleche.update();
			souris.update();
			levelManager.update();
			character.update();
		}
		if ((character.getNbCoeurs() == 0 || character.getPosY() > 500) && (backgroundd.getCurrentAnimation() < 9)
				&& (backgroundd.getCurrentAnimation() > 5)) {
			// Game Over
			levelManager = new HubLevel(screen);
			backgroundd.setCurrentAnimation(9);
			character.setLocalEtoiles(0);
		}
	}

	public static Screen getInstance() {
		return screen;
	}

	public entities.shop getShop() {
		return shop;
	}

	public entities.backgroundd getBackgroundd() {
		return backgroundd;
	}

	public static Item getAiles() {
		return ailes;
	}

	public static Item getPince() {
		return pince;
	}

	/**
	 * The function that will allow to draw on the created screen It is never called
	 * on the code because it is automatically done
	 *
	 * @param g you don't have to care about this argument because this is created
	 *          by the extensions used
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // constructor of Jpanel clean and allow to draw on the screen

		// paintComponent is called when Jpanel is created
		// could be optimized by loading all sprite on same image and use getSubimage()

		// if getcurrentanimation== 6 welcomeScreen.draw(g,character,levelManager);

		// if getcurrent animation == 7 getplayer welcomeScreen2.draw(g); et et apres
		// setcurrentanimation a 8 peux importe le chiffre faut juste quon definissent

		// levelManager.draw(g);
		// welcomeScreen.draw(g,character,levelManager);

		/*
		 * g.drawImage(character.getCharacter()[character.getCurrentAnimation()][
		 * character.getAniIndex()], levelManager.getxCharacterSpawn(),
		 * levelManager.getyCharacterSpawn() + (int) character.getPosY(), null);
		 */

		/*
		 * if (progression.getWin()==0) {
		 * 
		 * scoreScreen.drawScroreScreen(g); }
		 */

		BufferedImage characterImage = character.getCurrentCharacter();

		if (backgroundd.getCurrentAnimation() != getLastAnimation()) {
			stopCurrentSound(); // Arrêter le son actuel
			playSoundForAnimation(backgroundd.getCurrentAnimation()); // Jouer le son correspondant à la nouvelle
																		// animation
			setLastAnimation(backgroundd.getCurrentAnimation()); // Mettre à jour la dernière animation
		}

		// gameOverScreen.drawGameOverScreen(g,character,levelManager);
		if (backgroundd.getCurrentAnimation() == 1) {
			welcomeScreen.draw(g, character, levelManager);
		}
		if (backgroundd.getCurrentAnimation() == 2) {
			if (!gotName) {
				getPlayerName();
				gotName = true;
			}
			welcomeScreen2.draw(g);
		}
		if (backgroundd.getCurrentAnimation() == 3) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0,
					0, null);
			levelManager.draw(g);
			g.drawImage(getDoor0().getDoor()[getDoor0().getCurrentAnimation()][getDoor0().getAniIndex()],
					getDoor0().getPlaceX(), getDoor0().getPlaceY(), null);
			g.drawImage(shop.getShop()[shop.getCurrentAnimation()][shop.getAniIndex()], 500, 310, null);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
					(int) character.getPosX(), levelManager.getyCharacterSpawn() + (int) character.getPosY(), null);
		}
		if (backgroundd.getCurrentAnimation() == 4) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0,
					0, null);
			g.drawImage(ailes.getItem()[ailes.getCurrentAnimation()][ailes.getAniIndex()], 500, 400, null);
			g.drawImage(pince.getItem()[pince.getCurrentAnimation()][pince.getAniIndex()], 1000, 400, null);
			g.drawImage(fleche.getFleche()[fleche.getCurrentAnimation()][fleche.getAniIndex()], 745, 700, null);
			g.drawImage(souris.getFleche()[souris.getCurrentAnimation()][souris.getAniIndex()], souris.bougerX(),
					souris.bougerY(), null);
		}

		if (backgroundd.getCurrentAnimation() == 5) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0,
					0, null);
			levelManager.draw(g);
			g.drawImage(door1.getDoor()[door1.getCurrentAnimation()][door1.getAniIndex()], door1.getPlaceX(),
					door1.getPlaceY(), null);
			g.drawImage(door2.getDoor()[door2.getCurrentAnimation()][door2.getAniIndex()], door2.getPlaceX(),
					door2.getPlaceY(), null);
			g.drawImage(chainedDoor.getDoor()[chainedDoor.getCurrentAnimation()][chainedDoor.getAniIndex()],
					chainedDoor.getPlaceX(), chainedDoor.getPlaceY(), null);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()],
					(int) character.getPosX(), levelManager.getyCharacterSpawn() + (int) character.getPosY(), null);
		}
		if (backgroundd.getCurrentAnimation() == 6) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0,
					0, null);
			levelManager.draw(g);
			if (character.isHurting()) {
				float[] scales = { 1.0f, 0.0f, 0.0f, 1.0f };
				RescaleOp redFilter = new RescaleOp(scales, new float[4], null);
				characterImage = (redFilter.filter(characterImage, null));
			}
			g.drawImage(doorEndLevelOne.getDoor()[doorEndLevelOne.getCurrentAnimation()][doorEndLevelOne.getAniIndex()],
					doorEndLevelOne.getPlaceX() - character.getPosX() + levelManager.getxCharacterSpawn(),
					doorEndLevelOne.getPlaceY(), null);
			g.drawImage(characterImage, levelManager.getxCharacterSpawn(),
					levelManager.getyCharacterSpawn() + character.getPosY(), null);
		}
		if (backgroundd.getCurrentAnimation() == 7) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0,
					0, null);
			levelManager.draw(g);
			g.drawImage(character.getCharacter()[character.getCurrentAnimation()][character.getAniIndex()], 150,
					(BLOCK_PER_HEIGHT - 2) * BLOCK_SIZE + character.getPosY() - 40, null);
		}
		if (backgroundd.getCurrentAnimation() == 8) {
			g.drawImage(backgroundd.getBackgroundd()[backgroundd.getCurrentAnimation()][backgroundd.getAniIndex()], 0,
					0, null);
			levelManager.draw(g);
			if (character.isHurting()) {
				float[] scales = { 1.0f, 0.0f, 0.0f, 1.0f };
				RescaleOp redFilter = new RescaleOp(scales, new float[4], null);
				characterImage = (redFilter.filter(characterImage, null));
			}
			if (FinalLevel.getInstance()!=null && FinalLevel.getInstance().isBossOver()) {
				g.drawImage(
						doorEndFinalLevel.getDoor()[doorEndFinalLevel.getCurrentAnimation()][doorEndFinalLevel
								.getAniIndex()],
						doorEndFinalLevel.getPlaceX() - character.getPosX() + levelManager.getxCharacterSpawn(),
						doorEndFinalLevel.getPlaceY(), null);
			}
			g.drawImage(characterImage, levelManager.getxCharacterSpawn(),
					levelManager.getyCharacterSpawn() + character.getPosY(), null);
		}
		if (backgroundd.getCurrentAnimation() == 9) {
			gameOverScreen.drawGameOverScreen(g, character);
		}
		if (Progression.getInstance().getWin() == 0) {
			scoreScreen.drawScroreScreen(g);
		}

//		if (character.isHurting()) {
//	        float[] scales = {1.0f, 0.0f, 0.0f, 1.0f};
//	        RescaleOp redFilter = new RescaleOp(scales, new float[4], null);
//	        characterImage = redFilter.filter(characterImage, null);
//	    }
	}

	/**
	 * Where the screen size is determined : (1280x720) or (1920x1080)
	 */
	private void setScreenSize() {
		Dimension size = new Dimension((int) (BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE),
				(int) (BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE)); // Screen resolution
		setPreferredSize(size);

	}

	public static entities.door getDoor0() {
		return door0;
	}

	public static door getDoor1() {
		return door1;
	}

	public static door getDoor2() {
		return door2;
	}

	public door getChainedDoor() {
		return chainedDoor;
	}

	public static door getdoorEndLevelOne() {
		return doorEndLevelOne;
	}

	public door getdoorEndFinalLevel() {
		return doorEndFinalLevel;
	}

	public void setTime(int tmp) {
		scoreScreen.setTotalTimeElapsed(tmp);
	}

	public void getPlayerName() {
		welcomeText = new JTextField();
		// x, y, width, height
		welcomeText.setPreferredSize(new Dimension(250, 40));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		welcomeText.setFont(font);
		welcomeText.setBounds((int) ((BLOCK_SIZE * BLOCK_PER_WIDTH * SCALE) / 2.50),
				(int) ((BLOCK_SIZE * BLOCK_PER_HEIGHT * SCALE) / 1.80), 300, 50);
		this.add(welcomeText);
		this.revalidate();
		this.repaint();

		welcomeText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String nom = welcomeText.getText();
				character = joueur.loadsave(nom);
				System.out.println(character);
				initializeScreens();
				welcomeText.setVisible(false);
				backgroundd.setCurrentAnimation(3);
				repaint();
				welcomeText.setFocusable(false);
			}
		});
		welcomeText.setVisible(true);
		setFocusable(true);
		this.requestFocus();
		character = Character.getInstance();
	}

	private void initializeScreens() {
		gameOverScreen = new GameOverScreen(character);
		scoreScreen = new ScoreScreen(character);
		shop = new entities.shop(this);
		door0 = new entities.door(1400, 525, 1, this);
		door1 = new entities.door(500, 525, 0, this);
		door2 = new entities.door(1000, 150, 0, this);
		chainedDoor = new entities.door(1000, 525, 2, this);
		doorEndLevelOne = new entities.door(10376, 397, 1, this);
		doorEndFinalLevel = new entities.door(1118, 79, 2, this);

		ailes = new entities.Item("ailes", 50, "popo", 0, 0, character);
		pince = new entities.Item("pince", 50, "popo", 0, 1, character);
		fleche = new entities.ShopInt(0, this);
		souris = new entities.ShopInt(1, this);
		addKeyListener(new KeyboardInputs(this));
		initializedItems = true;
	}

	public void playSoundForAnimation(int animation) {

		String soundFile = ""; // Chemin vers le fichier audio

		switch (animation) {
		case 1:
			soundFile = "/sounds/weirdwelcome.wav"; // Exemple de son pour l'animation 1
			break;
		case 2:
			soundFile = "/sounds/sadWelcometwo.wav"; // Exemple de son pour l'animation 2
			break;
		case 3:
			soundFile = "/sounds/calm.wav"; // Exemple de son pour l'animation 3
			break;
		case 4:
			// shop
			soundFile = "/sounds/Boutique.wav"; // Exemple de son pour l'animation 4
			break;
		case 5:
			// door room
			soundFile = "/sounds/calm.wav"; // Exemple de son pour l'animation 5
			break;
		case 6:
			// lvl1
			soundFile = "/sounds/calm.wav"; // Exemple de son pour l'animation 6
			break;
		case 7:
			// lvl2
			soundFile = "/sounds/calm.wav"; // Exemple de son pour l'animation 7
			break;
		case 8:
			soundFile = "/sounds/weirdBos.wav"; // Exemple de son pour l'animation 8
			break;
		case 9:
			soundFile = "/sounds/sadWelcometwo.wav"; // Exemple de son pour l'animation 8
			break;
		default:
			break;
		}

		if (!soundFile.isEmpty()) {
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));

				currentClip = AudioSystem.getClip();
				currentClip.open(audioInputStream);
				currentClip.loop(Clip.LOOP_CONTINUOUSLY); // Joue en boucle
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void stopCurrentSound() {
		if (currentClip != null && currentClip.isRunning()) {
			currentClip.stop();
			currentClip.close();
		}
	}

	// Pour les enlever les chaînes

	public void setChainedDoorCurrentAnimation(int currentAnimation) {
		chainedDoor.setCurrentAnimation(currentAnimation);
	}

	public int getLastAnimation() {
		return lastAnimation;
	}

	public void setLastAnimation(int lastAnimation) {
		this.lastAnimation = lastAnimation;
	}
}
