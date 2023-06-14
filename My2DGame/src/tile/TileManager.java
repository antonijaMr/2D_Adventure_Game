package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;

	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
//		loadMap("/maps/map01.txt");
//		loadMap("/maps/worldMap2.txt");
		loadMap("/maps/world_Map.txt");

	}

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {

		}
	}

	public void getTileImage() {
		setup(0, "000", false);
		setup(1, "001", false);
		setup(2, "002", false);
		setup(3, "003", false);
		setup(4, "004", false);
		setup(5, "005", false);
		setup(6, "006", false);
		setup(7, "007", false);
		setup(8, "008", false);
		setup(9, "009", false);
		setup(10, "010", false);
		setup(11, "011", false);
		setup(12, "012", false);
		setup(13, "013", false);
		setup(14, "014", false);
		setup(15, "015", false);
		setup(16, "016", true);
		setup(17, "017", false);
		setup(18, "018", true);
		setup(19, "019", true);
		setup(20, "020", true);
		setup(21, "021", true);
		setup(22, "022", true);
		setup(23, "023", true);
		setup(24, "024", true);
		setup(25, "025", true);
		setup(26, "026", true);
		setup(27, "027", true);
		setup(28, "028", true);
		setup(29, "029", true);
		setup(30, "030", true);
		setup(31, "031", true);
		setup(32, "032", true);
		setup(33, "033", true);
		setup(34, "034", false);
		setup(35, "035", true);
		setup(36, "036", false);
		setup(37, "037", false);
		setup(38, "038", true);
		setup(39, "039", false);
		setup(40, "040", true);
		setup(41, "041", true);
		setup(42, "042", false);
		setup(43, "043", true);
		setup(44, "044", true);
		setup(45, "045", true);
		setup(46, "046", true);


	}

	public void setup(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}

		}
	}

}
