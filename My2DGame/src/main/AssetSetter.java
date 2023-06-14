package main;


//18:27 video #10

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_door;
import object.OBJ_key;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		gp.obj[0] = new OBJ_Boots(gp);
		gp.obj[0].worldX = 3 * gp.tileSize;
		gp.obj[0].worldY = 36 * gp.tileSize;
		
		
		gp.obj[1] = new OBJ_key(gp);
		gp.obj[1].worldX = 19 * gp.tileSize;
		gp.obj[1].worldY = 21 * gp.tileSize;
		
		gp.obj[3] = new OBJ_key(gp);
		gp.obj[3].worldX = 43 * gp.tileSize;
		gp.obj[3].worldY = 15 * gp.tileSize;
		
		gp.obj[4] = new OBJ_key(gp);
		gp.obj[4].worldX = 47 * gp.tileSize;
		gp.obj[4].worldY = 3 * gp.tileSize;
		
		gp.obj[5] = new OBJ_key(gp);
		gp.obj[5].worldX = 47 * gp.tileSize;
		gp.obj[5].worldY = 5 * gp.tileSize;
		
		
		//chest door
		gp.obj[6] = new OBJ_door(gp);
		gp.obj[6].worldX = 5 * gp.tileSize;
		gp.obj[6].worldY = 30 * gp.tileSize;
		
		gp.obj[7] = new OBJ_door(gp);
		gp.obj[7].worldX = 31 * gp.tileSize;
		gp.obj[7].worldY = 6 * gp.tileSize;
		
		gp.obj[8] = new OBJ_door(gp);
		gp.obj[8].worldX = 37 * gp.tileSize;
		gp.obj[8].worldY = 11 * gp.tileSize;
		
		gp.obj[9] = new OBJ_door(gp);
		gp.obj[9].worldX = 46 * gp.tileSize;
		gp.obj[9].worldY = 11 * gp.tileSize;
		
		gp.obj[11] = new OBJ_Chest(gp);
		gp.obj[11].worldX = 1 * gp.tileSize;
		gp.obj[11].worldY = 29 * gp.tileSize;
		
	}

}
