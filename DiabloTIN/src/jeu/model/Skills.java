/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jeu.model.entites.Unite;
import jeu.model.entites.Unite_Lucifer;
import jeu.vue.Map_graph;
import jeu.vue.SpriteKey;

/**
 *
 * @author hrhug
 */
public class Skills extends GridPane{
    private Button skill1 = new Button("Soin");
    private Button skill2 = new Button("Buff atk");
    private Button skill3 = new Button("Buff Vitesse/Portee");
    private Button skill4 = new Button("cercle de feu");
    private Label skill2locked = new Label("débloqué niv 2");
    private Label skill3locked = new Label("débloqué niv 3");
    private Label skill4locked = new Label("débloqué niv 4");
    
    
    private Unite_Lucifer hero ;
    
    public static Skills instance ;
    
    private int coutRequisMana = 20;
    
    public Skills(Unite_Lucifer hero){
        this.instance = this ;
        this.hero = hero ;
        this.setVgap(4); 
        this.setHgap(4);
        this.setPadding(new Insets(2));
        
        
        
        Skill_Soin(coutRequisMana-10);
        Skill_Buff_Degat(coutRequisMana) ;
        Skill_Buff_Portee(coutRequisMana);
        Skill_Cercle_de_feu();
        
        Reload_Skill();
  
    }
    
    public static Skills InstanceSkill(Unite_Lucifer hero) {
        if (instance == null) {
            instance = new Skills(hero);
        }
        return instance;
    }
    
    
    
    public void Skill_Soin( int coutRequisMana ){
        
        
        this.skill1.setPrefWidth(100);
        this.skill1.setPrefHeight(50);
        
         this.skill1.setOnAction((ActionEvent e) -> {
             if((hero.getPv())==hero.getPvMax())
            {                
            }else {
             if ( hero.getMana() >coutRequisMana  ){
                hero.setMana(hero.getMana()-coutRequisMana);
            
            if((hero.getPv()+50)>=hero.getPvMax())
                hero.setPv(hero.getPvMax());
            else
                hero.setPv(hero.getPv()+50);
             }
             
            }
        });
        
    }
    
    
    public void Skill_Buff_Degat( int coutRequisMana ) {
        
        this.skill2.setPrefWidth(100);
        this.skill2.setPrefHeight(50);
        
        this.skill2.setOnAction(e -> {
           if ( hero.getMana() >coutRequisMana  ){
            hero.setMana(hero.getMana()- coutRequisMana );
            hero.setDammage(hero.getDammage()+10);
            Thread thread = new Thread(() -> {
            long endTime =  15000; 

            try {
                    Thread.sleep(endTime);
                   Platform.runLater( () -> hero.setDammage(hero.getDammage()-10));
                } catch (InterruptedException f) {
                    f.printStackTrace();
            } });
            thread.setDaemon(true);  
            thread.start();
            }
            });
        

    }
    
    public void Skill_Buff_Portee( int coutRequisMana){
        
        this.skill3.setPrefWidth(100);
        this.skill3.setPrefHeight(50);

        this.skill3.setOnAction(e -> {
            if (hero.getMana() > coutRequisMana){
            hero.setMana(hero.getMana()- coutRequisMana);
            hero.setPorte(hero.getPorte()+1);
            Thread thread = new Thread(() -> {
            long endTime =  5000; 
            try {
                    Thread.sleep(endTime);
                   Platform.runLater( () -> hero.setPorte(hero.getPorte()-1));
                } catch (InterruptedException f) {
                    f.printStackTrace();
            } });
            thread.setDaemon(true);  
            thread.start();
            }
           
            });
        
    }
    
    
    public void Skill_Cercle_de_feu(){
        
        this.skill4.setPrefWidth(100);
        this.skill4.setPrefHeight(50);
        
        this.skill4.setOnAction(e -> {
            if (hero.getMana() > coutRequisMana*4){
                hero.setMana(hero.getMana()- coutRequisMana*4);
                for(Unite uni : hero.getMap().getEnnemy() ){
                    if( hero.getPosX()-3<= uni.getPosX() && hero.getPosX()+3>=uni.getPosX() && hero.getPosY()-3<=uni.getPosY() && hero.getPosX()+3>=uni.getPosY() )
                        uni.getDammage(100);

                }
            }
            
        });
    }
    
    
   public void Reload_Skill(){
       this.getChildren().clear();
          switch (hero.getLevel()) {
            case 1:
                this.add(this.skill1, 0, 0);
                this.add(this.skill2locked, 2, 0);
                this.add(this.skill3locked, 0, 12);
                this.add(this.skill4locked, 2, 12);
                break;
            case 2:
                this.add(this.skill1, 0, 0);
                this.add(this.skill2, 1, 0);
                this.add(this.skill3locked, 0, 12);
                this.add(this.skill4locked, 1, 12);
                break;
            case 3:
                this.add(this.skill1, 0, 0);
                this.add(this.skill2, 1, 0);
                this.add(this.skill3, 0, 1);
                this.add(this.skill4locked, 1, 1);
                break;
            default:
                this.add(this.skill1, 0, 0);
                this.add(this.skill2, 1, 0);
                this.add(this.skill3, 0, 1);
                this.add(this.skill4, 1, 1);
                break;
             
        }
   }
    
}
