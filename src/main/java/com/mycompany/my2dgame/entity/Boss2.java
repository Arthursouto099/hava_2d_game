/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.entity;

import com.mycompany.my2dgame.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.mycompany.my2dgame.services.PlayerService;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class Boss2 extends Entity {

    GamePanel gp;
    // vida bem maior
    public int atk = 22;   // dano que tira do player
    public int hpMax = 320;
    public int hp = hpMax;
    int actionLockCounter = 0;
    public int attackDelay = 700;
    private long lastAttackTime = 0;

    public Boss2(GamePanel gp, int worldXMult, int worldYMult) {
        this.gp = gp;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48 * 2;
        solidArea.height = 48 * 2;

        worldX = gp.tileSize * worldXMult;
        worldY = gp.tileSize * worldYMult;

        speed = 2;
        direction = "down";

        getBossImage();
    }

    public void getBossImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/boss2/reverseboy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void update() {
        // O boss tenta seguir o jogador
        // Calcular distância para o player
        int dx = gp.player.worldX - worldX;
        int dy = gp.player.worldY - worldY;

        if (this.hp <= 0) {
            PlayerService.incrementDefeatedBossessWeb(1);
            gp.boss2[0] = null;
            this.gp.player.hp += 30;
        }

        actionLockCounter++; // aumenta o contador a cada frame

        // Só muda de direção se o contador passar do limite (ex: 30 frames = 0.5s se rodando a 60fps)
        if (actionLockCounter > 30) {
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            } else {
                if (dy > 0) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }
            actionLockCounter = 0; // reseta o contador
        }

        // Colisão e movimento
        colisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkPlayer(this);

        if (!colisionOn) {
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

        attackPlayer();

        // troca de sprites
        SpriteCounter++;
        if (SpriteCounter > 15) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            SpriteCounter = 0;
        }
    }

    public void attackPlayer() {
        long now = System.currentTimeMillis();

        if (gp.player.hp <= 0 || (now - lastAttackTime) < attackDelay) {
            return;
        }

        // Corpo real considerando offsets do solidArea
        Rectangle bossBody = new Rectangle(
                this.worldX + this.solidArea.x,
                this.worldY + this.solidArea.y,
                this.solidArea.width,
                this.solidArea.height
        );

        Rectangle playerBody = new Rectangle(
                gp.player.worldX + gp.player.solidArea.x,
                gp.player.worldY + gp.player.solidArea.y,
                gp.player.solidArea.width,
                gp.player.solidArea.height
        );

        // Hitbox do golpe à frente do boss
        int reach = gp.tileSize / 2; // ajuste fino
        Rectangle hitbox;

        switch (this.direction) {
            case "up":
                hitbox = new Rectangle(bossBody.x, bossBody.y - reach, bossBody.width, reach);
                break;
            case "down":
                hitbox = new Rectangle(bossBody.x, bossBody.y + bossBody.height, bossBody.width, reach);
                break;
            case "left":
                hitbox = new Rectangle(bossBody.x - reach, bossBody.y, reach, bossBody.height);
                break;
            default: // "right"
                hitbox = new Rectangle(bossBody.x + bossBody.width, bossBody.y, reach, bossBody.height);
                break;
        }

        if (hitbox.intersects(playerBody)) {
            gp.player.hp -= this.atk;
            lastAttackTime = now;
            System.out.println("Boss acertou!");
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);

        // Barra de vida
        int barWidth = 50;  // largura total da barra
        int barHeight = 10;
        int barX = screenX;
        int barY = screenY - 20;

// calcula largura proporcional
        int currentBarWidth = (int) (((double) hp / hpMax) * barWidth);

        g2.setColor(Color.black);
        g2.fillRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);

        g2.setColor(Color.red);
        g2.fillRect(barX, barY, currentBarWidth, barHeight);

        g2.setColor(Color.white);
        g2.drawRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);
    }
}
