package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler key) {
		this.gp = gp;
		this.keyH = key;
		setDefaultValue();
		getPlayerImage();
	}

	public void setDefaultValue() {
		x=100;
		y=100;
		speed=4;
		direction="down";
	}
	
	public void getPlayerImage() {
		try {
			System.out.println("Hey");
			up1 =ImageIO.read(getClass().getResourceAsStream("/player/girl_up_1.png"));		
			up2 =ImageIO.read(getClass().getResourceAsStream("/player/girl_up_2.png"));	
			down1 =ImageIO.read(getClass().getResourceAsStream("/player/girl_down_1.png"));	
			down2 =ImageIO.read(getClass().getResourceAsStream("/player/girl_down_2.png"));	
			right1 =ImageIO.read(getClass().getResourceAsStream("/player/girl_right_1.png"));	
			right2 =ImageIO.read(getClass().getResourceAsStream("/player/girl_right_2.png"));	
			left1 =ImageIO.read(getClass().getResourceAsStream("/player/girl_left_1.png"));	
			left2 =ImageIO.read(getClass().getResourceAsStream("/player/girl_left_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed ==true || keyH.rightPressed == true || keyH.leftPressed == true) {
		if(keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		if(keyH.downPressed == true) {
			direction = "down";
			y+= speed;
		}
		if(keyH.leftPressed == true) {
			direction = "left";
			x-= speed;
		}
		if(keyH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
		
		spriteCounter++;
		if(spriteCounter >12) {
			if(spriteNum == 1){
				spriteNum =2;
			}else if(spriteNum ==2) {
				spriteNum =1;
			}
			spriteCounter = 0;
		}
		}
		
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = up1;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum ==2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum ==2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum ==2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize,null); //null is image observer
		
	}
}
