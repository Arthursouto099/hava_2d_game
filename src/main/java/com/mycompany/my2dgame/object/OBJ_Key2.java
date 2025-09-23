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
public class OBJ_Key2 extends SuperObject{
    public String nameRing = "Anel de Fogo";
    public String description = "Este anel permite controlar o fogo com maestria, mas consome energia vital de quem o usa.";
    public OBJ_Key2() {
        name = "ring 2";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
