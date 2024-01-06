/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import jeu.model.entites.Decor;
import javafx.scene.image.*;



/**
 *
 * @author Alexis
 */
public class Decor_Floor extends Decor {
      
    
    private final String name ="floor";
    private int posX ;
    private int posY ;
    
    public Decor_Floor(int x , int y ){
        this.posX = x ;
        this.posY = y ;
    }
  

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    

    
    
}
