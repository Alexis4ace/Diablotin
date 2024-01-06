/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahaye
 */

package jeu.model.entites;

import jeu.model.Map;

public class Unite_Red_Diablotin extends Unite_Lambda{
    
 
    
    public Unite_Red_Diablotin( int x , int y , Map map){
        setMiniDiablotinRed(x,y,map);
    }
    
    private void setMiniDiablotinRed(int x , int y , Map map){
        this.setName("red_diablotin");
        this.setExpRapporter(7);
        this.setPosX(x);
        this.setPosY(y);
        this.setPv(100);
        this.setDammage(10);
        this.setVitesse(1);
        this.setPorte(2);
        this.setFatigue(1);
        this.setMap(map);
        this.setInteraction(this);
        
         this.setAggroMob(false);
        this.setZoneAggroMob(3);
    }

    
    public Boolean AggroMob() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}