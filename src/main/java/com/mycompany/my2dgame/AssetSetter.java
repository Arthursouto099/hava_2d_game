/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;

import com.mycompany.my2dgame.entity.Npc;
import com.mycompany.my2dgame.object.OBJ_Key;

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
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
    }
    
    public void setNPC(int mapIndex) {
        if(mapIndex == 0) {
            gp.npcs[0] = new Npc(gp, 23, 12);
            gp.npcs[1] = new Npc(gp, 10, 12);
        }
        if(mapIndex == 1) {
            gp.npcs[0] = new Npc(gp, 5, 8);
            gp.npcs[1] = new Npc(gp, 15, 20);
        }
        // e assim por diante
    }

}
