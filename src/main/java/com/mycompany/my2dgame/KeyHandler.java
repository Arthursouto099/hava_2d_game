/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Usuario
 */
public class KeyHandler implements  KeyListener{
    
    public boolean upPressed, downPressed, leftPressed, rigthPressed;
    public boolean pausedAction = false;

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // eventos do teclado
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rigthPressed = true;
                break;
            case KeyEvent.VK_Q:
                if(pausedAction) {
                     pausedAction = false;
                }
                else {
                    pausedAction = true;
                }
                break;
            case KeyEvent.VK_C: 
                
                break;
            default:
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       int code = e.getKeyCode();
       switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rigthPressed = false;
                break;
        
                
        }
    }
    
}
