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
public class OBJ_Key3 extends SuperObject{
    public String nameRing = "Anel do Poder";
    public String description = "Forjado nas chamas da Montanha da Perdição, concede força inimaginável ao portador, mas também corrompe sua alma.";
    public OBJ_Key3() {
        name = "ring 3";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
