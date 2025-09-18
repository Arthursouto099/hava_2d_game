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
public class OBJ_Key extends SuperObject{
    public OBJ_Key() {
        name = "ring 1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
