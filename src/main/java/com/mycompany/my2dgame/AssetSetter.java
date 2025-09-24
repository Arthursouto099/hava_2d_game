/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;

import com.mycompany.my2dgame.entity.Boss;
import com.mycompany.my2dgame.entity.Boss2;
import com.mycompany.my2dgame.entity.Npc;
import com.mycompany.my2dgame.entity.Orc;
import com.mycompany.my2dgame.object.OBJ_Key;
import com.mycompany.my2dgame.object.OBJ_Key2;
import com.mycompany.my2dgame.object.OBJ_Key3;
import com.mycompany.my2dgame.object.OBJ_Key4;
import com.mycompany.my2dgame.object.OBJ_Door;
import com.mycompany.my2dgame.object.OBJ_KeyDoor;

/**
 *
 * @author Usuario
 */ 
public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 41 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;
        gp.obj[1] = new OBJ_Door();
        gp.obj[1].name = "door 1";
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 11 * gp.tileSize;
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].name = "door 2";
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 15 * gp.tileSize;
        gp.obj[3] = new OBJ_KeyDoor();
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 39 * gp.tileSize;
        gp.obj[4] = new OBJ_KeyDoor();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;
        gp.boss2[0] = new Boss2(gp, 38, 7) ;
        gp.obj[5] = new OBJ_Key3();
        gp.obj[5].worldX = 7 * gp.tileSize;
        gp.obj[5].worldY = 2 * gp.tileSize;
        gp.obj[6] = new OBJ_Key2();
        gp.obj[6].worldX = 11 * gp.tileSize;
        gp.obj[6].worldY = 36 * gp.tileSize;
        
        
    }

   public void setNPC(int mapIndex) {
    
    // Resetar arrays antes de configurar qualquer mapa
    for (int i = 0; i < gp.obj.length; i++) {
        gp.obj[i] = null;
    }
    for (int i = 0; i < gp.orcs.length; i++) {
        gp.orcs[i] = null;
    }
    for (int i = 0; i < gp.npcs.length; i++) {
        gp.npcs[i] = null;
    }
    for (int i = 0; i < gp.boss.length; i++) {
        gp.boss[i] = null;
    }
    for (int i = 0; i < gp.boss2.length; i++) {
        gp.boss2[i] = null;
    }

    // Configuração por mapa
    if (mapIndex == 0) {
        
        gp.player.setdefaultValues();
        
        
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 41 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].name = "door 1";
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 11 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].name = "door 2";
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 15 * gp.tileSize;

        gp.obj[3] = new OBJ_KeyDoor();
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 39 * gp.tileSize;

        gp.obj[4] = new OBJ_KeyDoor();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;
        gp.obj[5] = new OBJ_Key3();
        gp.obj[5].worldX = 7 * gp.tileSize;
        gp.obj[5].worldY = 2 * gp.tileSize;
        gp.obj[6] = new OBJ_Key2();
        gp.obj[6].worldX = 11 * gp.tileSize;
        gp.obj[6].worldY = 36 * gp.tileSize;
        
        
        
        gp.npcs[0] = new Npc(gp, 23, 12);
        gp.npcs[1] = new Npc(gp, 11,9);

        gp.boss2[0] = new Boss2(gp, 38, 10);
        
        
        
    }

    if (mapIndex == 1) {
        gp.player.setdefaultValues();
        
        
        
        gp.orcs[0] = new Orc(gp, 23, 12);
        gp.orcs[1] = new Orc(gp, 19, 12);
        gp.orcs[2] = new Orc(gp, 21, 11);
        gp.orcs[3] = new Orc(gp, 20, 13);
        gp.orcs[4] = new Orc(gp, 25, 14);
        gp.orcs[5] = new Orc(gp, 27, 12);
        gp.orcs[6] = new Orc(gp, 30, 27);
        gp.orcs[7] = new Orc(gp, 19, 42);
        gp.orcs[8] = new Orc(gp, 3, 42);

        gp.obj[0] = new OBJ_Key2();
        gp.obj[0].worldX = 19 * gp.tileSize;
        gp.obj[0].worldY = 12 * gp.tileSize;

        gp.player.worldX = 23 * gp.tileSize;
        gp.player.worldY = 7 * gp.tileSize;

        gp.boss[0] = new Boss(gp, 23, 12);
        gp.boss[1] = new Boss(gp, 25, 16);
        gp.boss[1].hp = 250;
        gp.boss[1].attackDelay = 100;
        gp.boss[1].atk = 3;
        gp.boss[1].metaActionCounter = 50;
    }

    if (mapIndex == 2) {
        gp.orcs[0] = new Orc(gp, 23, 12);
        gp.orcs[1] = new Orc(gp, 19, 12);
        gp.orcs[2] = new Orc(gp, 20, 13);
        gp.orcs[3] = new Orc(gp, 25, 14);
        gp.orcs[4] = new Orc(gp, 27, 12);
        gp.orcs[5] = new Orc(gp, 30, 27);
        gp.orcs[6] = new Orc(gp, 19, 42);
        gp.orcs[7] = new Orc(gp, 3, 42);

        gp.obj[0] = new OBJ_Key2();
        gp.obj[0].worldX = 19 * gp.tileSize;
        gp.obj[0].worldY = 12 * gp.tileSize;

        gp.player.worldX = 23 * gp.tileSize;
        gp.player.worldY = 7 * gp.tileSize;
    }
}


}
