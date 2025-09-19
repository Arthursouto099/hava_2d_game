/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;
import javax.swing.JOptionPane;
import com.mycompany.my2dgame.entity.Entity;
import com.mycompany.my2dgame.entity.Npc;
import com.mycompany.my2dgame.entity.Orc;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }

    public void checkEntityColision(Entity entity1, Entity entity2) {

    // Pega os limites da área sólida da entity1
    int e1Left = entity1.worldX + entity1.solidArea.x;
    int e1Right = e1Left + entity1.solidArea.width;
    int e1Top = entity1.worldY + entity1.solidArea.y;
    int e1Bottom = e1Top + entity1.solidArea.height;

    // Pega os limites da área sólida da entity2
    int e2Left = entity2.worldX + entity2.solidArea.x;
    int e2Right = e2Left + entity2.solidArea.width;
    int e2Top = entity2.worldY + entity2.solidArea.y;
    int e2Bottom = e2Top + entity2.solidArea.height;

    // Move a área de entity1 UM TILE para frente na direção que ela está indo
    switch (entity1.direction) {
        case "up":
            e1Top -= gp.tileSize;
            e1Bottom -= gp.tileSize;
            break;
        case "down":
            e1Top += gp.tileSize;
            e1Bottom += gp.tileSize;
            break;
        case "left":
            e1Left -= gp.tileSize;
            e1Right -= gp.tileSize;
            break;
        case "right":
            e1Left += gp.tileSize;
            e1Right += gp.tileSize;
            break;
    }

    // Verifica sobreposição dos retângulos (colisão)
    boolean colisao = 
        e1Right > e2Left &&
        e1Left < e2Right &&
        e1Bottom > e2Top &&
        e1Top < e2Bottom;

   if (colisao) {
    if (entity2 instanceof Npc) {
        if (!entity1.IscollisionWithOuther) {
            entity1.IscollisionWithOuther = true;
        }
    } else if (entity2 instanceof Orc) {
        if (!entity1.isCollisionWithEnemy) {
            System.out.println("colide");
            entity1.isCollisionWithEnemy = true;
        }
    }
}
}


    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRigthWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRigthCol = entityRigthWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.colisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.colisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.colisionOn = true;
                }
                break;
            case "right":
                entityRigthCol = (entityRigthWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.colisionOn = true;
                }
                break;
            default:
                throw new AssertionError();
        }

    }
    
    public int checkObject(Entity entity, boolean  player) {
        int index = 999;
        
        for(int i =0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                entity.solidArea.x =  entity.worldX + entity.solidArea.x;
                entity.solidArea.y =  entity.worldY + entity.solidArea.y;
                
                
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                
                
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.colisionOn = true;
                            }
                            
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                          if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                              if(gp.obj[i].collision) {
                                entity.colisionOn = true;
                            }
                            
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -=  entity.speed;
                          if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                              if(gp.obj[i].collision) {
                                entity.colisionOn = true;
                            }
                            
                            if(player) {
                                index = i;
                            }
                        }
                        
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                          if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                   if(gp.obj[i].collision) {
                                entity.colisionOn = true;
                            }
                            
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreadefaultY;
                
            }
        }
        
        
        return index;
    }
}
