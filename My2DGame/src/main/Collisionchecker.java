package main;

import entity.Entity;

public class Collisionchecker {
	GamePanel gp;

	public Collisionchecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;

		// we need to only check for two borders at the time

		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}

	public int checkObject(Entity entity, boolean player) {
		// we check if player is hitting any object and return index of the object
		int index = 999;

		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				// get entitys solid area positiion(player)
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// get objects solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				// simulating entitys movement and check where it will be after it moved
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					// intersects checks if two rectangles are touching each other
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true; // he cant go through
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true; // he cant go through
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true; // he cant go through
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true; // he cant go through
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;

	}

	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;

		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				// get entitys solid area positiion(player)
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// get objects solid area position
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

				// simulating entitys movement and check where it will be after it moved
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					// intersects checks if two rectangles are touching each other
					if (entity.solidArea.intersects(target[i].solidArea)) {

						entity.collisionOn = true; // he cant go through
						index = i;
					}
					break;

				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {

						entity.collisionOn = true; // he cant go through
						index = i;
					}
					break;

				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {

						entity.collisionOn = true;
						index = i;

					}
					break;

				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {

						entity.collisionOn = true;
						index = i;
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}

	public void checkPlayer(Entity entity) {
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;

		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

		// simulating entitys movement and check where it will be after it moved
		switch (entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			// intersects checks if two rectangles are touching each other
			if (entity.solidArea.intersects(gp.player.solidArea)) {

				entity.collisionOn = true; // he cant go through
			}
			break;

		case "down":
			entity.solidArea.y += entity.speed;
			if (entity.solidArea.intersects(gp.player.solidArea)) {

				entity.collisionOn = true; // he cant go through
			}
			break;

		case "left":
			entity.solidArea.x -= entity.speed;
			if (entity.solidArea.intersects(gp.player.solidArea)) {

				entity.collisionOn = true;

			}
			break;

		case "right":
			entity.solidArea.x += entity.speed;
			if (entity.solidArea.intersects(gp.player.solidArea)) {

				entity.collisionOn = true;

			}
			break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}

}
