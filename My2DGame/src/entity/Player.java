package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	
	//indicates how many keys player has
	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler key) {
		this.gp = gp;
		this.keyH = key;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeigth / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		// for object interaction
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValue();
		getPlayerImage();
	}

	// SPEED
	public void setDefaultValue() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 5;
		direction = "down";
	}

	public void getPlayerImage() {
		up1 = setup("girl_up_1");
		up2 = setup("girl_up_2");
		down1 = setup("girl_down_1");
		down2 = setup("girl_down_2");
		right1 = setup("girl_right_1");
		right2 = setup("girl_right_2");
	    left1 = setup("girl_left_1");
		left2 = setup("girl_left_2");
	}
	
	public BufferedImage setup(String imgName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage scaledImage = null;
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/"+imgName+".png"));			
			scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return scaledImage;
	}

	public void update() {

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true
				|| keyH.leftPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// check object collison
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// if collison is false player can move
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}

			}

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	public void pickUpObject(int i) {
		//if i == 999 -> we didnt touch any object
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				hasKey++;
				gp.playSE(1);
				gp.obj[i] = null; //key disapears
				gp.ui.showMessage("You got a key!!!");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[i] = null;
					gp.ui.showMessage("You opened the door!!!");
					hasKey--;
				}else {
					gp.ui.showMessage("You dont have a key!");
				}
				break;
			case "Chest":
				 //we want to stop the game when chest is found
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			case "Boots":
				gp.ui.showMessage("Speed increased!");
				gp.playSE(2);
				speed += 1;
				gp.obj[i] = null;
				break;
			}
			
		}
	}	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = up1;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null); // null is image observer

	}
}
