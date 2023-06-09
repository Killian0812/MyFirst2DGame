package main;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][];

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;

	public int requesting = 0;

	public EventHandler(GamePanel gp) {
		this.gp = gp;

		eventRect = new EventRect[gp.maxWorldRow][gp.maxWorldCol];

		int row = 0;
		int col = 0;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[row][col] = new EventRect();
			eventRect[row][col].x = 0;
			eventRect[row][col].y = 0;
			eventRect[row][col].width = gp.tileSize;
			eventRect[row][col].height = gp.tileSize;
			eventRect[row][col].eventRectDefaultX = eventRect[row][col].x;
			eventRect[row][col].eventRectDefaultY = eventRect[row][col].y;

			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}

		}

	}

	public void checkEvent() {
		// check if the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}

		if (canTouchEvent == true) {

			// VR ROOM
			if (hit(14, 21, "any") == true
					|| hit(14, 20, "any") == true
					|| hit(14, 19, "any") == true
					|| hit(14, 18, "any") == true) {
				teleport(112, 32, gp.dialogueState);
				canTouchEvent = false;
			}

			// HOSPITAL
			if (hit(65, 62, "any") == true
					|| hit(65, 61, "any") == true
					|| hit(65, 60, "any") == true) {
				healing(65, 62, gp.dialogueState);
				canTouchEvent = false;
			}
		}

	}

	public boolean hit(int row, int col, String reqDirection) {
		boolean hit = false;

		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[row][col].x = col * gp.tileSize + eventRect[row][col].x;
		eventRect[row][col].y = row * gp.tileSize + eventRect[row][col].y;

		if (gp.player.solidArea.intersects(eventRect[row][col]) && eventRect[row][col].eventDone == false) {
			if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;

				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}

		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[row][col].x = eventRect[row][col].eventRectDefaultX;
		eventRect[row][col].y = eventRect[row][col].eventRectDefaultY;

		return hit;
	}

	public void damage(int row, int col, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fall into a pit";
		gp.player.life -= 1;
		// eventRect[row][col].eventDone = true;
		canTouchEvent = false;
	}

	public void healing(int row, int col, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Pay 1 dollar to fully recover HP? /n/n Y: Yes           N: No";
		// eventRect[row][col].eventDone = true;
		requesting = 2;
	}

	public void teleport(int row, int col, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Play in VR World? /n/n Y: Yes           N: No";
		// eventRect[row][col].eventDone = true;
		requesting = 1;
	}
}
