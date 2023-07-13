package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_key;

public class UI {
	GamePanel gp;
	Graphics2D g2;// da mozemo korisiti g2 u drugim metodama/vise metoda
	Font arial_40;
	Font arial_80;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80 = new Font("Arial", Font.BOLD, 80);

//		OBJ_key key = new OBJ_key(gp);
//		keyImage = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(arial_80);
		g2.setColor(Color.white);

		if (gp.gameState == gp.playState) {
			// do playstate
		}

		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		if(gp.gameState == gp.dialogState) {
			drawDialogScreen();
		}
	}

	public void drawDialogScreen() {
		//subwindow
		int x = gp.tileSize *2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line,x,y);
			y+=40;
		}
		
	}
	
	public void drawSubWindow(int x,int y, int width, int height) {
		Color c = new Color(0,0,0,100); //black, 100 opacity (220 - 100%)
		g2.setColor(c);
		g2.fillRoundRect(x,y,width,height,35,35); //35s for roundnes
		
		c = new Color(255,255,255); //white
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);
	}
	
	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeigth/2;
		
		g2.drawString(text,x,y);
	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

}

















