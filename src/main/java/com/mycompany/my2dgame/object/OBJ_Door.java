/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class OBJ_Door extends SuperObject {
     public OBJ_Door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = false;
    }
}
