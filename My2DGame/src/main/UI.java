package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_key;

public class UI {
	GamePanel gp;
	Graphics2D g2;// da mozemo korisiti g2 u drugim metodama/vise metoda
	Font pikselFont;
	

	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";

	// for titlescreen
	public int commandNum = 0;

	public int titleScreenState = 0; // 0 - first screen, 2 - second screen

	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/PikselFont.ttf");
			pikselFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(pikselFont);
		g2.setColor(Color.white);

		// titlestate
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}

		if (gp.gameState == gp.playState) {
			// do playstate
		}

		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}

		if (gp.gameState == gp.dialogState) {
			drawDialogScreen();
		}
	}

	public void drawTitleScreen() {

		if (titleScreenState == 0) {

			g2.setColor(Color.PINK);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);

			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
			String text = "pink adventure";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;

			// shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x + 5, y + 5);

			// main color
			g2.setColor(Color.black);
			g2.drawString(text, x, y);

			// girls image
			x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
			y += gp.tileSize * 2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

			// menu
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

			text = "new game";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3.5;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "load game";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "quit";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}
		else if(titleScreenState ==1 ) {
			g2.setColor(Color.PINK);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
			
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
			
			String text = "Select Your class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Theif";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,45F));
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
		}

	}

	public void drawDialogScreen() {
		// subwindow
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		drawSubWindow(x, y, width, height);

		x += gp.tileSize;
		y += gp.tileSize;

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}

	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 100); // black, 100 opacity (220 - 100%)
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35); // 35s for roundnes

		c = new Color(255, 255, 255); // white
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeigth / 2;

		g2.drawString(text, x, y);
	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

}
