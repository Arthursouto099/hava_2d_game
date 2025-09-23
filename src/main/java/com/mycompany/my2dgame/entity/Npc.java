/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.entity;

/**
 *
 * @author Usuario
 */
import com.mycompany.my2dgame.GamePanel;
import com.mycompany.my2dgame.KeyHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Npc extends Entity {

    GamePanel gp;

    public final int screenX;
    public final int screenY;
    public int hp = 100;
    public int atk;
    public int worldXMult;
    public int worldMYult;
    public int dialogTimer = 0;
    public String dialogNpc = "";

    public Npc(GamePanel gp, int worldXMult, int worldYMult) {
        this.gp = gp;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeigth / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        this.worldMYult = worldYMult;
        this.worldXMult = worldXMult;

        setdefaultValues();
        getPlayerImage();
    }

    public void setdefaultValues() {
        worldX = gp.tileSize * worldXMult;
        worldY = gp.tileSize * worldMYult;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        // lendo as imagens
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int actionLockCounter = 0;

    public void initNpcDialog() {
        long currentTime = System.currentTimeMillis();

        Rectangle NpcArea = new Rectangle(worldX, worldY, solidArea.width, solidArea.height);
        Rectangle playerArea = new Rectangle(
                gp.player.worldX,
                gp.player.worldY,
                gp.player.solidArea.width,
                gp.player.solidArea.height
        );

        if (NpcArea.intersects(playerArea)) {
            System.out.println("com.mycompany.my2dgame.entity.Npc.initNpcDialog()");

        }

    }

    public void update() {

        actionLockCounter++;

        dialogTimer++;
        if (dialogTimer > 230) { // aqui 60 frames = 1 segundo se rodando a 60fps
            dialogNpc = "";
            dialogTimer = 0;
        }

        initNpcDialog();

        if (actionLockCounter > 200) { // troca de direção a cada 2 segundos
            int i = (int) (Math.random() * 4);
            switch (i) {
                case 0:
                    direction = "up";
                    break;
                case 1:
                    direction = "down";
                    break;
                case 2:
                    direction = "left";
                    break;
                case 3:
                    direction = "right";
                    break;
            }
            actionLockCounter = 0;
        }

        colisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, false);

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

        SpriteCounter++;
        if (SpriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            SpriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
//         g2.setColor(Color.white);
//        // inicialmente na posição 100 100
//        // posição 100 100, largura e altura do tile de 16
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }

                break;

        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        if (!dialogNpc.isEmpty()) {
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g2.getFontMetrics();
            int lineHeight = fm.getHeight();

            // Divide o texto em linhas
            String[] lines = dialogNpc.split("\n");

            // Calcula a largura máxima
            int maxWidth = 0;
            for (String line : lines) {
                int width = fm.stringWidth(line);
                if (width > maxWidth) {
                    maxWidth = width;
                }
            }

            // Altura total do bloco de texto
            int totalHeight = lines.length * lineHeight;

            // Definir dimensões da caixa
            int padding = 12; // margem interna
            int boxX = screenX + gp.tileSize / 2 - maxWidth / 2 - padding;
            int boxY = screenY - 20 - totalHeight - padding;
            int boxWidth = maxWidth + padding * 2;
            int boxHeight = totalHeight + padding * 2;

            // Fundo (semi-transparente)
            g2.setColor(new Color(0, 0, 0, 180)); // preto translúcido
            g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15);

            // Borda
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15);

            // Texto
            g2.setColor(Color.WHITE);
            for (int i = 0; i < lines.length; i++) {
                int textWidth = fm.stringWidth(lines[i]);
                g2.drawString(
                        lines[i],
                        screenX + gp.tileSize / 2 - textWidth / 2,
                        screenY - 20 + (i * lineHeight)
                );
            }
        }

        // === Desenhar barra de vida ===
// Tamanho máximo da barra
//        int barWidth = 50;  // largura total da barra;
//        int barHeight = 10;  // altura da barra
//        int barX = screenX;
//        int barY = screenY - 20;
//
//// Calcula a largura proporcional ao HP atual
//        int currentBarWidth = (int) ((hp / 100.0) * barWidth);
//
//// Fundo (preto)
//        g2.setColor(Color.black);
//        g2.fillRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);
//
//// Vida atual (vermelho)
//        g2.setColor(Color.red);
//        g2.fillRect(barX, barY, currentBarWidth, barHeight);
//
//// Borda opcional
//        g2.setColor(Color.white);
//        g2.drawRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);

    }
}
