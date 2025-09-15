/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.my2dgame;

import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class My2DGame {

    public static void main(String[] args) {
        // criando uma janela e definindo operações padrões
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Game 2D");

        // criando o Panel a partir da classe GamePanel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

    }
}
