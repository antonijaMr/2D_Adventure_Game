package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); // returns the integer keyCode asociated with the key

		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			if (gp.ui.titleScreenState == 0) {
				if (code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}

				}
				if (code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}
				}

				if (code == KeyEvent.VK_ENTER) {
					if (gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
//					gp.playMusic(0);
					}
					if (gp.ui.commandNum == 1) {
						// add later
					}
					if (gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
			}
			
			else if (gp.ui.titleScreenState == 1) {
				if (code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}

				}
				if (code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}

				if (code == KeyEvent.VK_ENTER) {
					if (gp.ui.commandNum == 0) {
						System.out.println("Do some fighter stuff");
						gp.gameState = gp.playState;
					}
					if (gp.ui.commandNum == 1) {
						System.out.println("Do some thief stuff");
						gp.gameState = gp.playState;
					}
					if (gp.ui.commandNum == 2) {
						System.out.println("Do some sorcer stuff");
						gp.gameState = gp.playState;
					}
					if (gp.ui.commandNum == 3) {
						gp.ui.titleScreenState = 0;
						
						
					}
				}
			}
		}

		if (gp.gameState == gp.playState) {
			// PLAY STATE
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		// PAUSE state
		else if (gp.gameState == gp.pauseState) {
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}

		// Dialogue state
		else if (gp.gameState == gp.dialogState) {
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}

	}

}
