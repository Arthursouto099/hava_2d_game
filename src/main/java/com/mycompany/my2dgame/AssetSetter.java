/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;

import com.mycompany.my2dgame.entity.Npc;
import com.mycompany.my2dgame.entity.Orc;
import com.mycompany.my2dgame.object.OBJ_Key;
import com.mycompany.my2dgame.object.OBJ_Key2;
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
    }

    public void setNPC(int mapIndex) {
       
         
        
        if (mapIndex == 1) {
            gp.npcs[0] = null;
            gp.npcs[1] = null;
            gp.orcs[0] = new Orc(gp, 23, 12);
            gp.orcs[1] = new Orc(gp, 19, 12);
            gp.orcs[0] = new Orc(gp, 23, 12);
            gp.orcs[1] = new Orc(gp, 19, 12);

// Novos orcs em linhas diferentes (com "3")
            gp.orcs[2] = new Orc(gp, 20, 13);
            gp.orcs[3] = new Orc(gp, 25, 14);
            gp.orcs[4] = new Orc(gp, 27, 12);
            gp.orcs[5] = new Orc(gp, 30, 27);
            gp.orcs[6] = new Orc(gp, 19, 42);
            gp.orcs[7] = new Orc(gp, 3, 42);
            gp.obj[0] = new OBJ_Key2();
            gp.obj[0].worldX = 23 * gp.tileSize;
            gp.obj[0].worldY = 7 * gp.tileSize;
            gp.player.worldX = 23 * gp.tileSize;
            gp.player.worldY = 7 * gp.tileSize;

        }
        // e assim por diante
    }

}
