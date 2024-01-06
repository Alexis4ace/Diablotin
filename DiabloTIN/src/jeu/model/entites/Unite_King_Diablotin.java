/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

/**
 *
 * @author ahaye
 */
import jeu.model.entites.Unite;
import jeu.model.Interaction_Mob;
import jeu.model.Map;
import static jeu.vue.InfoGameView.getInstanceInfo;

/**
 *
 * @author Alexis
 */
public class Unite_King_Diablotin extends Unite_Lambda{
    
    
    
    public Unite_King_Diablotin( int x , int y , Map map){
        
        setKingDiablotin(x,y,map);
    }
    
    private void setKingDiablotin(int x , int y , Map map){
        this.setName("king_diablotin");
        this.setExpRapporter(999);
        this.setPosX(x);
        this.setPosY(y);
        this.setPv(5000);
        this.setDammage(30);
        this.setVitesse(1);
        this.setPorte(1);
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

    

