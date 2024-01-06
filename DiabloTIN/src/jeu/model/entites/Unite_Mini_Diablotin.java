/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import jeu.model.Map;

/**
 *
 * @author Alexis
 */
public class Unite_Mini_Diablotin extends Unite_Lambda{
    
        
    public Unite_Mini_Diablotin( int x , int y , Map map){
        
        setMiniDiablotin(x,y,map);
        
        
        
    }
    
    private void setMiniDiablotin(int x , int y , Map map){
        
        this.setName("mini_diablotin");
        this.setExpRapporter(5);
        this.setPosX(x);
        this.setPosY(y);
        this.setPv(100);
        this.setDammage(10);
        this.setVitesse(1);
        this.setPorte(1);
        this.setFatigue(1);
        this.setMap(map);
        this.setInteraction(this);
        
        this.setAggroMob(false);
        this.setZoneAggroMob(3);
    }
    
    // si le diablotin a des trucs en plus les ajouter ici 

    
   
    
}
