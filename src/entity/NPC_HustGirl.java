package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_HustGirl extends Entity {

    public NPC_HustGirl(GamePanel gp) {

        super(gp);
        direction = "down";
        name = "HustGirl";
        speed = 1;
        getImage();

    }

    public void getImage() {

        up1 = setup("/npc/hust_girl/hust_girl_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/hust_girl/hust_girl_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/hust_girl/hust_girl_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/hust_girl/hust_girl_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/hust_girl/hust_girl_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/hust_girl/hust_girl_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/hust_girl/hust_girl_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/hust_girl/hust_girl_right_2", gp.tileSize, gp.tileSize);

    }

    public void setAction() {

        actionLockCounter++;
        // change direction after 2s
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25)
                direction = "up";
            else if (i <= 50)
                direction = "down";
            else if (i <= 75)
                direction = "left";
            else
                direction = "right";
            actionLockCounter = 0;
        }
    }

}