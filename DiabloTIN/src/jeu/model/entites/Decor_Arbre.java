/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import jeu.model.entites.Decor;
import javafx.scene.image.Image;

/**
 *
 * @author ahaye
 */


public class Decor_Arbre extends Decor {
    
    
    private Image image = new Image(getClass().getResource("/image/arbre.png").toString()); 
    private final String name ="arbre";
    private int pv = 800 ;
    private int posX = 10 ;
    private int posY = 10 ;
    
     public Decor_Arbre( int x, int y ){
         this.posX = x  ;
         this.posY = y  ;
    }

    public Decor_Arbre() {
        
    } 

    
    
    
    @Override
    public String getName() {
        return this.name;
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
