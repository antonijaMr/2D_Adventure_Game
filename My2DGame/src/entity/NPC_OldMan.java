package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialogue();
	}

	public void getImage() {
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
	}
	
	public void setDialogue() {
		
		dialogue[0] = "You have summoned me";
		dialogue[1] = "You made it to this island to find treasure \nThis treasure is hard to find!!";
		dialogue[2] = "I have many powers, myb I will help you";
		dialogue[3] = "oh well, good luck silly girl";
	}

	public void setAction() {
		// seting character behaviour
		// overriding setAction from Entity

		actionLockCounter++;

		if (actionLockCounter == 120) {
			Random random = new Random(); 
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";

			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";

			}
			if (i > 75 && i < 100) {
				direction = "right";
			}

			actionLockCounter = 0;
		}

	}
	
	public void speak() {
		super.speak();
	}

}
