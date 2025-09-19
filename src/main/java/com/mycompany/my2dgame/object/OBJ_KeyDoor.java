/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Usuario
 */
public class OBJ_KeyDoor extends SuperObject{
    public OBJ_KeyDoor() {
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/keyDoor.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
