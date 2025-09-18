/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;

import com.mycompany.my2dgame.entity.Player;
import com.mycompany.my2dgame.tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import com.mycompany.my2dgame.entity.Npc;
import com.mycompany.my2dgame.object.SuperObject;
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
    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    // criando meu player
    public Player player = new Player(this, keyH);
    public Npc[] npcs = new Npc[10];
    
    
    

    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        // definindo largura e altura da tela
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        this.npcs[0] = new Npc(this, 23, 12);
        this.npcs[1] = new Npc(this, 10, 12);

    }

    public void setupGame() {
        aSetter.setObject();
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
        player.worldX = tileSize * 10;
        player.worldY = tileSize * 10;

        // reseta os NPCs conforme o mapa
        aSetter.setNPC(1);
    }

    public void update() {

        player.update();
        
        for(int i = 0; i < this.npcs.length; i++) {
            if(npcs[i] != null) {
              npcs[i].update();
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
          for(int i = 0; i < this.npcs.length; i++) {
            if(npcs[i] != null) {
              npcs[i].draw((Graphics2D) g2);
         }
            
        }

        g2.dispose();

    }
}
