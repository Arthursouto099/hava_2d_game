/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.entity;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class PlayerInfo {
    private int id;
    private String name;
    private int health;
    private int attack;
    private int  deaths;
    private int defeatedBosses;
    

    public PlayerInfo() {}

    public int getId() {
        return id;
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setDeaths(int deaths) {
       this.deaths = deaths;
    }
    
    public int getDefeatedBosses() {
        return this.defeatedBosses;
    }
    
    public void setDefeatedBosses(int defeatBosses) {
       this.defeatedBosses = defeatBosses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
