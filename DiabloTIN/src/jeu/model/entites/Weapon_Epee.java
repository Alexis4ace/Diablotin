/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import javafx.scene.image.Image;

/**
 *
 * @author Alexis
 */
public class Weapon_Epee extends Weapon {
    
    private String name = "epee";
    private int posX ;
    private int posY ;
    private String desc = "Une epee d'acier qui dégage une aura de Guerrier";
    private int dmg = 50 ; 
    
    public Weapon_Epee(int posX , int posY){
        this.posX = posX ;
        this.posY = posY;
    }
    
    @Override
    public String getNameWeapons() {
        return this.name;
    }

    @Override
    public int getWeaponsPosX() {
        return posX;
    }

    @Override
    public int getWeaponsPosY() {
        return posY;
    }

    @Override
    public Image getImageWeapons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescWeapons() {
        return this.desc;
    }
    
    public int getDmg(){
        return this.dmg;
    }

    @Override
    public Boolean USE() {
        return false;
    }

}
