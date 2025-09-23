/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;

import com.mycompany.my2dgame.entity.Boss;
import com.mycompany.my2dgame.entity.Boss2;
import com.mycompany.my2dgame.entity.Elf;
import com.mycompany.my2dgame.entity.Player;
import com.mycompany.my2dgame.entity.PlayerInfo;
import com.mycompany.my2dgame.tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import com.mycompany.my2dgame.entity.Npc;
import com.mycompany.my2dgame.entity.Orc;
import com.mycompany.my2dgame.object.OBJ_Door;
import com.mycompany.my2dgame.object.OBJ_Key;
import com.mycompany.my2dgame.object.OBJ_KeyDoor;
import com.mycompany.my2dgame.object.SuperObject;
import com.mycompany.my2dgame.services.PlayerService;
import java.awt.Font;

/**
 *
 * @author Usuario
 */
public class GamePanel extends JPanel implements Runnable {

    // Configurações de tala
    final int originalTileSize = 16; // 16x16 para todos os personagens e elementos
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //40x40 tile O mapa continua lógico em 16x16, mas visualmente aparece maior.
    public final int maxScreenCol = 16; // horizontal
    public final int maxScreenRow = 12; // vertical
    public final int screenWidth = tileSize * maxScreenCol; // 760 pixels
    public final int screenHeigth = tileSize * maxScreenRow; // 576 pixels

    // configurações do mundo
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeigth = tileSize * maxScreenRow;
    public boolean paused = false;

    // fps
    int FPS = 60;
    public TileManager tileM = new TileManager(this);

    public KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    // criando meu player
    public Player player = new Player(this, keyH);

    public Npc[] npcs = new Npc[10];
    public Elf[] elfs = new Elf[10];
    public Orc[] orcs = new Orc[10];
    public Boss[] boss = new Boss[10];
    public Boss2[] boss2 = new Boss2[10];

    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        // definindo largura e altura da tela
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.npcs[0] = new Npc(this, 23, 12);
        this.npcs[1] = new Npc(this, 11, 9);

    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void resetGame() {

        this.player.setdefaultValues();
        obj[0] = new OBJ_Key();
        obj[0].worldX = 41 * tileSize;
        obj[0].worldY = 10 * tileSize;
        obj[1] = new OBJ_Door();
        obj[1].name = "door 1";
        obj[1].worldX = 10 * tileSize;
        obj[1].worldY = 11 * tileSize;
        obj[2] = new OBJ_Door();
        obj[2].name = "door 2";
        obj[2].worldX = 38 * tileSize;
        obj[2].worldY = 15 * tileSize;
        obj[3] = new OBJ_KeyDoor();
        obj[3].worldX = 11 * tileSize;
        obj[3].worldY = 39 * tileSize;
        obj[4] = new OBJ_KeyDoor();
        obj[4].worldX = 10 * tileSize;
        obj[4].worldY = 7 * tileSize;
        boss2[0] = new Boss2(this, 38, 7);
    }

    public void startGameThread() {
        gameThread = new Thread(this);

        gameThread.start();
    }

    public void pauseGameThread() {

    }

    // Criando o GAME LOOP[
    @Override
//    public void run() {
//        double drawInterval = 1000000000 / FPS; // 0.016666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//

    ////            long currentTime = System.nanoTime();
////            System.out.println("Current Time: " +  currentTime );
//            // 1 UPDATE: Informações sobre o personagem e posições
//            update();
//
//            // 2 DRAW: Desenha na tela  com base nas informações do update 
//            repaint();
//            
//           
//            try {
//                double remainigTime = nextDrawTime - System.nanoTime();
//                remainigTime = remainigTime/1000000;
//                
//                if(remainigTime < 0) {
//                    remainigTime = 0;
//                }
//                
//                Thread.sleep((long) remainigTime);
//                
//                nextDrawTime += drawInterval;
//                
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        };
//
//    }
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            // calcula quanto tempo se passou desde o último frame
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                if (!keyH.pausedAction) {
                    update();   // lógica do jogo
                }

                repaint();  // desenha de novo
                delta--;    // consome 1 ciclo
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void changeMap(String mapFile, int mapIndex) {
        tileM.loadMap(mapFile);

        // reseta posição do player
        // reseta os NPCs conforme o mapa
        aSetter.setNPC(mapIndex);
    }

    public void update() {

        player.update();

        for (int i = 0; i < this.npcs.length; i++) {
            if (npcs[i] != null) {
                npcs[i].update();
            }

        }
        for (int i = 0; i < this.orcs.length; i++) {
            if (orcs[i] != null) {
                orcs[i].update();
            }

        }
        for (int i = 0; i < this.boss.length; i++) {
            if (boss[i] != null) {
                boss[i].update();
            }

        }
        for (int i = 0; i < this.boss2.length; i++) {
            if (boss2[i] != null) {
                boss2[i].update();
            }

        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics g2 = (Graphics2D) g;
        // TILE

        tileM.draw((Graphics2D) g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw((Graphics2D) g2, this);
            }
        }

        //PLAYER
        player.draw((Graphics2D) g2);
        for (int i = 0; i < this.npcs.length; i++) {
            if (npcs[i] != null) {
                npcs[i].draw((Graphics2D) g2);
            }

        }
        for (int i = 0; i < this.orcs.length; i++) {
            if (orcs[i] != null) {
                orcs[i].draw((Graphics2D) g2);
            }

        }
        for (int i = 0; i < this.boss.length; i++) {
            if (boss[i] != null) {
                boss[i].draw((Graphics2D) g2);
            }

        }
        for (int i = 0; i < this.boss2.length; i++) {
            if (boss2[i] != null) {
                boss2[i].draw((Graphics2D) g2);
            }

        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));

// Exemplo de estatísticas
        // CONFIGURAÇÕES DE ESTILO
        Font hudFont = new Font("Arial", Font.PLAIN, 18);
        g2.setFont(hudFont);
        // Texto das estatísticas
        
        PlayerInfo info = PlayerService.getPlayerWeb(1);

        

        String vidaText = "name: " + info.getName();
        String chavesText = "id: " + info.getId();
        String inimigosText = "Inimigos: " + boss.length;

// Altura da linha e margem
        int padding = 25;
        int lineHeight = 25;

// Tamanho da HUD
        int hudWidth = 350;
        int hudHeight = (lineHeight * 4) + (padding * 2);

// Posição no canto inferior esquerdo
        int x = 10;
        int y = screenHeigth - hudHeight - 10;

// FUNDO PRETO TRANSPARENTE
        g2.setColor(new Color(0, 0, 0, 170)); // Preto com transparência
        g2.fillRoundRect(x, y, hudWidth, hudHeight, 15, 15);

// TEXTO BRANCO
        g2.setColor(Color.WHITE);
        g2.drawString(vidaText, x + padding, y + padding + lineHeight);
        g2.drawString(chavesText, x + padding, y + padding + lineHeight * 2);
        g2.drawString(inimigosText, x + padding, y + padding + lineHeight * 3);

        g2.dispose();

    }
}
