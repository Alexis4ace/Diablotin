/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import javafx.scene.image.Image;

/**
 *
 * @author ahaye
 */
public abstract class  Weapon extends Items {

    
    @Override
    public String getName() {
       return getNameWeapons();
    }

    @Override
    public int getPosX() {
        return getWeaponsPosX();
    }

    @Override
    public int getPosY() {
        return  getWeaponsPosY();
    }

    @Override
    public Image getImage() {
       return getImageWeapons();
    }

    @Override
    public String getDesc() {
       return getDescWeapons();
    }

    public abstract String getNameWeapons();
    public abstract int getWeaponsPosX();
    public abstract int getWeaponsPosY();
    public abstract Image getImageWeapons();
    public abstract String getDescWeapons();
    public abstract int getDmg();
    
}
