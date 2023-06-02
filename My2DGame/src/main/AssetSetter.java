package main;

import object.OBJ_Chest;
import object.OBJ_door;
import object.OBJ_key;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		gp.obj[0] = new OBJ_key();
        // 48 4
		gp.obj[0].worldX = 48 * gp.tileSize;
		gp.obj[0].worldY = 4 * gp.tileSize;

		// 7 14
		gp.obj[1] = new OBJ_key();
		gp.obj[1].worldX = 7 * gp.tileSize;
		gp.obj[1].worldY = 14 * gp.tileSize;
		
		//door 12 9
		gp.obj[2] = new OBJ_door();
		gp.obj[2].worldX = 11 * gp.tileSize;
		gp.obj[2].worldY = 9* gp.tileSize;
		
		//chest 21 4
		gp.obj[3] = new OBJ_Chest();
		gp.obj[3].worldX = 20 * gp.tileSize;
		gp.obj[3].worldY = 4 * gp.tileSize;
		
		
		
	}

}
