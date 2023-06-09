package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity, boolean isPlayer) {

        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entityLeftX + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entityTopY + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2, tileNum3, tileNum4;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                if (entityTopRow < 0)
                    entity.collisionOn = true;
                else {
                    tileNum1 = gp.tileM.mapTileNum[0][entityTopRow][entityLeftCol];
                    tileNum2 = gp.tileM.mapTileNum[0][entityTopRow][entityRightCol];
                    tileNum3 = gp.tileM.mapTileNum[1][entityTopRow][entityLeftCol];
                    tileNum4 = gp.tileM.mapTileNum[1][entityTopRow][entityRightCol];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
                            || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                        entity.collisionOn = true;
                    if (isPlayer == true)
                        if (gp.tileM.tile[tileNum1].isFinishLine == true
                                || gp.tileM.tile[tileNum2].isFinishLine == true)
                            if (gp.player.hasID == true && gp.player.hasBook == true
                                    && gp.player.hasPencil == true) {
                                gp.gameState = gp.gameOverState;
                                gp.playSE(5);
                            } else {
                                if (gp.player.hasID == false)
                                    gp.ui.showMsg("You don't have a student ID");
                                else {
                                    if (gp.player.hasBook == false)
                                        gp.ui.showMsg("You don't have OOP lecture book");
                                    else if (gp.player.hasPencil == false)
                                        gp.ui.showMsg("You don't have a pencil");
                                }
                                gp.player.worldY += gp.player.speed;
                            }
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                if (entityBottomRow > gp.maxWorldRow) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[0][entityBottomRow][entityLeftCol];
                    tileNum2 = gp.tileM.mapTileNum[0][entityBottomRow][entityRightCol];
                    tileNum3 = gp.tileM.mapTileNum[1][entityBottomRow][entityLeftCol];
                    tileNum4 = gp.tileM.mapTileNum[1][entityBottomRow][entityRightCol];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
                            || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                        entity.collisionOn = true;
                    if (isPlayer == true)
                        if (gp.tileM.tile[tileNum1].isFinishLine == true
                                || gp.tileM.tile[tileNum2].isFinishLine == true)
                            if (gp.player.hasID == true && gp.player.hasBook == true
                                    && gp.player.hasPencil == true) {
                                gp.gameState = gp.gameOverState;
                                gp.playSE(5);
                            } else {
                                if (gp.player.hasID == false)
                                    gp.ui.showMsg("You don't have a student ID");
                                else {
                                    if (gp.player.hasBook == false)
                                        gp.ui.showMsg("You don't have OOP lecture book");
                                    else if (gp.player.hasPencil == false)
                                        gp.ui.showMsg("You don't have a pencil");
                                }
                                gp.player.worldY -= gp.player.speed;
                            }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                if (entityLeftCol < 0) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[0][entityTopRow][entityLeftCol];
                    tileNum2 = gp.tileM.mapTileNum[0][entityBottomRow][entityLeftCol];
                    tileNum3 = gp.tileM.mapTileNum[1][entityTopRow][entityLeftCol];
                    tileNum4 = gp.tileM.mapTileNum[1][entityBottomRow][entityLeftCol];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
                            || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                        entity.collisionOn = true;
                    if (isPlayer == true)
                        if (gp.tileM.tile[tileNum1].isFinishLine == true
                                || gp.tileM.tile[tileNum2].isFinishLine == true)
                            if (gp.player.hasID == true && gp.player.hasBook == true
                                    && gp.player.hasPencil == true) {
                                gp.gameState = gp.gameOverState;
                                gp.playSE(5);
                            } else {
                                if (gp.player.hasID == false)
                                    gp.ui.showMsg("You don't have a student ID");
                                else {
                                    if (gp.player.hasBook == false)
                                        gp.ui.showMsg("You don't have OOP lecture book");
                                    else if (gp.player.hasPencil == false)
                                        gp.ui.showMsg("You don't have a pencil");
                                }
                                gp.player.worldX += gp.player.speed;
                            }
                    break;
                }
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                if (entityRightCol > gp.maxWorldCol) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[0][entityTopRow][entityRightCol];
                    tileNum2 = gp.tileM.mapTileNum[0][entityBottomRow][entityRightCol];
                    tileNum3 = gp.tileM.mapTileNum[1][entityTopRow][entityRightCol];
                    tileNum4 = gp.tileM.mapTileNum[1][entityBottomRow][entityRightCol];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
                            || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                        entity.collisionOn = true;
                    if (isPlayer == true)
                        if (gp.tileM.tile[tileNum1].isFinishLine == true
                                || gp.tileM.tile[tileNum2].isFinishLine == true)
                            if (gp.player.hasID == true && gp.player.hasBook == true
                                    && gp.player.hasPencil == true) {
                                gp.gameState = gp.gameOverState;
                                gp.playSE(5);
                            } else {
                                if (gp.player.hasID == false)
                                    gp.ui.showMsg("You don't have a student ID");
                                else {
                                    if (gp.player.hasBook == false)
                                        gp.ui.showMsg("You don't have OOP lecture book");
                                    else if (gp.player.hasPencil == false)
                                        gp.ui.showMsg("You don't have a pencil");
                                }
                                gp.player.worldX -= gp.player.speed;
                            }
                }
        }

    }

    public int checkObject(Entity entity, boolean isPlayer) {

        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] == null)
                continue;

            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
            gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

            switch (entity.direction) {
                case "up": {
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true)
                            entity.collisionOn = true;
                        if (isPlayer == true && gp.keyH.enterPressed == true)
                            index = i;
                    }
                    break;
                }
                case "down": {
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true)
                            entity.collisionOn = true;
                        if (isPlayer == true && gp.keyH.enterPressed == true)
                            index = i;
                    }
                    break;
                }
                case "left": {
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true)
                            entity.collisionOn = true;
                        if (isPlayer == true && gp.keyH.enterPressed == true)
                            index = i;
                    }
                    break;
                }

                case "right": {
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true)
                            entity.collisionOn = true;
                        if (isPlayer == true && gp.keyH.enterPressed == true)
                            index = i;
                    }
                    break;
                }
            }

            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity target[]) {

        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] == null || target[i] == entity || target[i].isDead == true)
                continue;

            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
            target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

            switch (entity.direction) {
                case "up": {
                    entity.solidArea.y -= entity.speed;
                    break;
                }
                case "down": {
                    entity.solidArea.y += entity.speed;
                    break;
                }
                case "left": {
                    entity.solidArea.x -= entity.speed;
                    break;
                }

                case "right": {
                    entity.solidArea.x += entity.speed;
                    break;
                }
            }

            if (entity.solidArea.intersects(target[i].solidArea)) {
                entity.collisionOn = true;
                index = i;
            }

            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            target[i].solidArea.x = target[i].solidAreaDefaultX;
            target[i].solidArea.y = target[i].solidAreaDefaultY;

        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        boolean flag = false;
        switch (entity.direction) {
            case "up": {
                entity.solidArea.y -= entity.speed;
                break;
            }
            case "down": {
                entity.solidArea.y += entity.speed;
                break;
            }
            case "left": {
                entity.solidArea.x -= entity.speed;
                break;
            }

            case "right": {
                entity.solidArea.x += entity.speed;
                break;
            }
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            if (entity.name == "Orc" || entity.name == "Slime")
                if (gp.player.isInvicible == false) {
                    gp.player.invincibleCounter = 0;
                    gp.player.isInvicible = true;
                    gp.player.life--;
                    gp.playSE(7);
                }
            flag = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return flag;
    }
}
