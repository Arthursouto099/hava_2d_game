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

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class Boss2 extends Entity {

    GamePanel gp;
    public int hp = 300;   // vida bem maior
    public int atk = 20;   // dano que tira do player
    int actionLockCounter = 0;
    public int attackDelay = 1300;
    private long lastAttackTime = 0;

    public Boss2(GamePanel gp, int worldXMult, int worldYMult) {
        this.gp = gp;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 48;

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
            gp.boss2[0] = null;
            this.gp.player.hp = 100;
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
        long currentTime = System.currentTimeMillis();

        Rectangle bossArea = new Rectangle(worldX, worldY, solidArea.width, solidArea.height);
        Rectangle playerArea = new Rectangle(
                gp.player.worldX,
                gp.player.worldY,
                gp.player.solidArea.width,
                gp.player.solidArea.height
        );

        if (bossArea.intersects(playerArea)
                && (currentTime - lastAttackTime >= attackDelay)
                && gp.player.hp > 0) {

            // Checa se o Boss está de frente para o Player
            boolean deFrente = false;

            switch (this.direction) {
                case "up":
                    if (gp.player.worldY < this.worldY) {
                        deFrente = true;
                    }
                    break;
                case "down":
                    if (gp.player.worldY > this.worldY) {
                        deFrente = true;
                    }
                    break;
                case "left":
                    if (gp.player.worldX < this.worldX) {
                        deFrente = true;
                    }
                    break;
                case "right":
                    if (gp.player.worldX > this.worldX) {
                        deFrente = true;
                    }
                    break;
            }

            if (deFrente) {
                gp.player.hp -= this.atk;
                this.lastAttackTime = currentTime;
                System.out.println("Boss atacou de frente!");
            }

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
        int barWidth = 500;
        int barHeight = 20;
        int barX = gp.screenWidth / 2 - barWidth / 2;
        int barY = 50;

        int currentBarWidth = (int) ((hp / 300.0) * barWidth);

        g2.setColor(Color.black);
        g2.fillRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);

        g2.setColor(Color.red);
        g2.fillRect(barX, barY, currentBarWidth, barHeight);

        g2.setColor(Color.white);
        g2.drawRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);
    }
}
