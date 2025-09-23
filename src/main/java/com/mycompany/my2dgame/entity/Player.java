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
import com.mycompany.my2dgame.object.SuperObject;
import com.mycompany.my2dgame.object.OBJ_Key;
import com.mycompany.my2dgame.object.OBJ_Key3;
import com.mycompany.my2dgame.object.OBJ_Key2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import com.mycompany.my2dgame.services.RingService;
import java.awt.FontMetrics;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hp = 100;
    public int atk = 5;
    public String dialogText = "";
    public int dialogTimer = 0;
    public Color dialogColor = Color.WHITE;
    public boolean isFireBuff = false;
    public int hasRing = 0;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeigth / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        setdefaultValues();
        getPlayerImage();
    }

    public void setdefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        // lendo as imagens
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attackBoss() {
        long currentTime = System.currentTimeMillis();
        Rectangle playerArea = new Rectangle(
                worldX,
                worldY,
                solidArea.width,
                solidArea.height
        );

// Ataca os bosses do array gp.boss
        for (int i = 0; i < gp.boss.length; i++) {
            if (gp.boss[i] != null) {
                Rectangle bossArea = new Rectangle(
                        gp.boss[i].worldX,
                        gp.boss[i].worldY,
                        gp.boss[i].solidArea.width,
                        gp.boss[i].solidArea.height
                );

                if (playerArea.intersects(bossArea) && gp.boss[i].hp > 0) {

                    int chance = (int) (Math.random() * 4) + 1;

                    // Define dano base
                    int dano = this.atk;

                    // Aplica dano de fogo se a chance for 3
                    if (chance == 3 && isFireBuff == true) {
                        int danoFogo = 9;
                        dano += danoFogo;
                        hp -= 2;
                        dialogText = "Dano de fogo aplicado";
                        dialogTimer = 0;
                    } else {
                        System.out.println("Dano normal: " + dano);
                    }

                    gp.boss[i].hp -= dano;

                }
            }
        }

// Ataca os bosses do array gp.boss2
        for (int i = 0; i < gp.boss2.length; i++) {
            if (gp.boss2[i] != null) {
                Rectangle bossArea = new Rectangle(
                        gp.boss2[i].worldX,
                        gp.boss2[i].worldY,
                        gp.boss2[i].solidArea.width,
                        gp.boss2[i].solidArea.height
                );

                if (playerArea.intersects(bossArea) && gp.boss2[i].hp > 0) {
                    int chance = (int) (Math.random() * 4) + 1;

                    // Define dano base
                    int dano = this.atk;

                    // Aplica dano de fogo se a chance for 3
                    if (chance == 3 && isFireBuff == true) {
                        int danoFogo = 9;
                        dano += danoFogo;
                        hp -= 2;
                        dialogText = "Dano de fogo aplicado";
                        dialogTimer = 0;
                    } else {
                        System.out.println("Dano normal: " + dano);
                    }

                    gp.boss2[i].hp -= dano;

                }
            }
        }

    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rigthPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rigthPressed == true) {
                direction = "right";

            }

            colisionOn = false;
            IscollisionWithOuther = false;
            isCollisionWithEnemy = false;
            gp.cChecker.checkTile(this);

            for (int i = 0; i < gp.boss.length; i++) {
                if (gp.boss[i] != null) {
                    if (keyH.atack) {           // só ataca se tecla T foi pressionada
                        attackBoss();
                        keyH.atack = false;     // reseta para não atacar de novo até pressionar T novamente
                    }
                }
            }

            for (int i = 0; i < gp.boss2.length; i++) {
                if (gp.boss2[i] != null) {
                    if (keyH.atack) {           // só ataca se tecla T foi pressionada
                        attackBoss();
                        keyH.atack = false;     // reseta para não atacar de novo até pressionar T novamente
                    }
                }
            }

            for (int i = 0; i < gp.boss.length; i++) {
                if (gp.boss[i] != null) {
                    gp.cChecker.checkPlayer(gp.boss[i]);
                }
            }
            for (int i = 0; i < gp.boss2.length; i++) {
                if (gp.boss2[i] != null) {
                    gp.cChecker.checkPlayer(gp.boss2[i]);
                }
            }

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (this.hp <= 0) {
                this.hp = 100;
                this.hasRing = 0;
                this.hasKey = 0;
                this.atk = 5;
                this.isFireBuff = false;
                gp.changeMap("/maps/map01.txt", 0);

            }

            // checando as colisões com os npcs
            for (int i = 0; i < gp.npcs.length; i++) {
                if (gp.npcs[i] != null) {
                    gp.cChecker.checkEntityColision(this, gp.npcs[i]);
                }
            }
            for (int i = 0; i < gp.orcs.length; i++) {
                if (gp.orcs[i] != null) {
                    gp.cChecker.checkEntityColision(this, gp.orcs[i]);
                }
            }

            if (isCollisionWithEnemy) {
                dialogText = "!";
                dialogTimer = 0;
                dialogColor = Color.RED;
            }

            if (IscollisionWithOuther) {
                gp.npcs[1].dialogNpc = "Vi que você não possui força suficiente, "
                        + "perto dessa casa existe um anel que te deixara poderoso";
                gp.npcs[0].dialogNpc = "Vença sua versão maligna e retome o proximo anel";

            }

            // If COLLISION is false, PLAYER can move
            if (colisionOn == false) {
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

                    default:
                        break;
                }
            }

            SpriteCounter++;
            if (SpriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                SpriteCounter = 0;
            }
        }

//        int portalX = 48 * gp.tileSize;;;
//        int portalY = 20 * gp.tileSize;
//
//        if (worldX > portalX && worldY > portalX) {
//            gp.changeMap("/maps/world02.txt", 1);
//        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {

            switch (gp.obj[index].name) {
                case "ring 1":

                    // logica de enviar para o meu banco de dados
                    if (gp.boss2[0] == null) {
                        hasRing += 1;
                        dialogText = "Você pegou o anel dos elfos 50 pontos a mais de defesa";
                        dialogTimer = 0;
                        hp = hp + 50;
                        if (gp.obj[index] instanceof OBJ_Key) {
                            OBJ_Key key = (OBJ_Key) gp.obj[index]; // faz o cast para acessar os campos da classe
                            RingService.postRingRegister(key.nameRing, key.description, 1);
                            gp.changeMap("/maps/mapa_50x50_dungeon.txt", 1);
                            gp.obj[index] = null;
                        }

                    }

                    break;

                case "ring 2":

                    dialogText = "Você pegou o anel do fogo, controle o fogo porém perca vida por isso. TUDO TEM UM PREÇO";
                    dialogTimer = 0;
                    isFireBuff = true;
                    hasRing += 1;

                    if (gp.obj[index] instanceof OBJ_Key2) {
                        OBJ_Key2 key = (OBJ_Key2) gp.obj[index]; // faz o cast para acessar os campos da classe
                        RingService.postRingRegister(key.nameRing, key.description, 1);
                        gp.obj[index] = null;
                    }
                    break;

                case "ring 3":

                    dialogText = "Você pegou o anel do poder! 20+ de dano!";
                    dialogTimer = 0;
                    atk = atk + 20;
                    hasRing += 1;

                    if (gp.obj[index] instanceof OBJ_Key3) {
                        OBJ_Key3 key = (OBJ_Key3) gp.obj[index]; // faz o cast para acessar os campos da classe
                        RingService.postRingRegister(key.nameRing, key.description, 1);
                        gp.obj[index] = null;
                    }

                    break;
                case "door 1":
                    if (hasKey > 0) {
                        gp.obj[index].collision = false;
                    } else {
                        dialogText = "PORTA FECHADA";
                        dialogTimer = 0;
                    }
                    break;
                case "door 2":
                    if (hasKey > 1) {
                        gp.obj[index].collision = false;
                    } else {
                        dialogText = "PORTA FECHADA";
                        dialogTimer = 0;
                    }
                    break;
                case "key":
                    gp.obj[index] = null;
                    hasKey += 1;
                    break;

            }
        }
    }

    public void draw(Graphics2D g2) {
//         g2.setColor(Color.white);
//        // inicialmente na posição 100 100
//        // posição 100 100, largura e altura do tile de 16
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        dialogTimer++;
        if (dialogTimer > 90) { // aqui 60 frames = 1 segundo se rodando a 60fps
            dialogText = "";
            dialogTimer = 0;
            dialogColor = Color.WHITE;
        }

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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        if (!dialogText.isEmpty()) {
            g2.setFont(new Font("Arial", Font.BOLD, 15));
            FontMetrics fm = g2.getFontMetrics();

            int textWidth = fm.stringWidth(dialogText);
            int textHeight = fm.getHeight();

            // Definição da caixa de fundo
            int padding = 8; // margem interna
            int boxX = screenX + gp.tileSize / 2 - textWidth / 2 - padding;
            int boxY = screenY - 40 - textHeight;
            int boxWidth = textWidth + padding * 2;
            int boxHeight = textHeight + padding * 2;

            // Fundo (preto translúcido)
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);

            // Borda
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);

            // Texto
            g2.setColor(dialogColor);
            g2.drawString(
                    dialogText,
                    screenX + gp.tileSize / 2 - textWidth / 2,
                    screenY - 40
            );
        }

        // === Desenhar barra de vida ===
// Tamanho máximo da barra
        int barWidth = 50;  // largura total da barra
        int barHeight = 10;  // altura da barra
        int barX = screenX;
        int barY = screenY - 20;

// Calcula a largura proporcional ao HP atual
        int currentBarWidth = (int) ((hp / 150.0) * barWidth);

// Fundo (preto)
        g2.setColor(Color.black);
        g2.fillRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);

// Vida atual (vermelho)
        g2.setColor(Color.red);
        g2.fillRect(barX, barY, currentBarWidth, barHeight);

// Borda opcional
        g2.setColor(Color.white);
        g2.drawRect(barX - 1, barY - 1, barWidth + 2, barHeight + 2);

    }
}
