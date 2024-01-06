/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import jeu.model.entites.Decor_Interactif;

/**
 *
 * @author ahaye
 */
public class Decor_Interactif_Door extends Decor_Interactif {

    private final String name = "door" ; 
    
    
    private int posX ;
    private int posY;
    
    private Boolean locked = true ;
    
    // Oui, il ya collision, NOn, il n'y a pas collision
    private Boolean Collision = true ;
    

    public Decor_Interactif_Door(int x , int y ){
        this.posX = x ;
        this.posY = y ;
       
    }

   

    @Override
    public String getNameDecor() {
        return this.name ;
    }

    @Override
    public int getPosXDecor() {
       return this.posX;
    }

    @Override
    public int getPosYDecor() {
       return this.posY;
    }
    
    @Override
    public Boolean getCollision() {
       return getCollisionDoor();
    }

    public Boolean getEtatDoor(){
        return this.locked ;
    }
    
   
    public Boolean getCollisionDoor(){
        return this.Collision ;
    }
    
    public void setEtatDoor(Boolean etat){
         this.locked = etat;
        if (this.locked == false){
            this.setCollision(false);
        }
        else{
            this.setCollision(true);
        }
    }
    
     public void setUnlock(){
        this.locked = false;
        if (this.locked == false){
           this.Collision = false ;
        }
        else{
           this.Collision = true ;
        }
    }
    
    public void setCollisionDoor(Boolean etat){
       this.Collision = etat ;
       super.setCollision(etat);
    }

}
