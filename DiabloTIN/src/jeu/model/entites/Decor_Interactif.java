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
public abstract class Decor_Interactif extends Decor {

    private Boolean Collision = true ;
    
    
    @Override
    public String getName() {
     return getNameDecor();
    }

    @Override
    public int getPosX() {
      return getPosXDecor();
    }

    @Override
    public int getPosY() {
       return getPosYDecor();
    }
    
   
   public abstract String getNameDecor();
   public abstract int getPosXDecor();
   public abstract int getPosYDecor();
   
   public void setCollision(Boolean etat){
       this.Collision = etat ;
   }
   public abstract Boolean getCollision();


}
