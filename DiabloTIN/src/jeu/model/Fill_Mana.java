/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import jeu.model.entites.Unite_Lucifer;

/**
 *
 * @author ahaye
 */
public class Fill_Mana {
    
    
   private IntegerProperty Mana_Actuel;
   private int Mana_Max ;
   
   private Unite_Lucifer hero;
   
   public Fill_Mana(Unite_Lucifer hero){
       
        this.hero = hero ;

        this.Mana_Actuel = new SimpleIntegerProperty(hero.getMana());
        this.Mana_Max = hero.getMana();
        
        
       
   }
   
   
   public Thread Remplit(int endTime){
        Thread thread = new Thread(() -> {
           while (!Thread.currentThread().isInterrupted()){
            try {
                    Thread.sleep(endTime);
                   Platform.runLater( () -> hero.setMana(hero.getMana()+1));
                } catch (InterruptedException f) {
                    f.printStackTrace();
                }
           }
            }); 
        thread.setDaemon(true);
        return thread ;
           
        
   }
    
   
  
    
}
