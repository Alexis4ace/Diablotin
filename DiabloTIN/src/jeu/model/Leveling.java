/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static jeu.model.Skills.InstanceSkill;
import jeu.model.entites.Unite_Lucifer;

/**
 *
 * @author hrhug
 */
public class Leveling extends BorderPane{
    
    
    
    
    private IntegerProperty level_sys;
    private IntegerProperty experience_sys;
    
    private int experience;
    private int experienceMax;
    
    private int level;
    private Unite_Lucifer hero ;
    
    public Leveling(Unite_Lucifer hero){
        this.hero = hero ;
        this.level= hero.getLevel();
        
        this.experience= hero.getExp();
        this.experienceMax = hero.getExpMax();
        
        this.level_sys = hero.getLevelsProperty();
        this.experience_sys = hero.getEXProperty();
        load();
        

        
    }
    
    
    
    public void load(){
        
        Label nom = new Label("Lucifer");
        nom.setStyle("-fx-font-size: 20px;");
        
        Label level = new Label("LVL : "+  this.hero.getLevel());
        level.setStyle("-fx-font-size: 15px;");
        int Taille_barre = 2 ;
       
        StackPane EXP = new StackPane();
       
        Rectangle exp = new Rectangle(this.experience*Taille_barre,25);
        exp.setFill(Color.PURPLE);
        
        Rectangle vide = new Rectangle((this.experienceMax)*Taille_barre,25);
        vide.setFill(Color.WHITE);
        
        EXP.getChildren().addAll(vide,exp);
//        
         this.experience_sys.addListener((observable, oldValue, newValue) -> {
            double ratio = newValue.doubleValue() ; // Calcul du ratio de la nouvelle valeur par rapport à la valeur maximale
            double newWidth = ratio ; // Nouvelle largeur du rectangle vie en fonction du ratio
            exp.setWidth(newWidth); // Mise à jour de la largeur du rectangle vie
        });
         
         
        this.level_sys.addListener((observable, oldValue, newValue) -> {
           level.setText("LVL : "+ this.hero.getLevel());
           // Charge les skills en fonction du niveau
            InstanceSkill(this.hero).Reload_Skill();
        });
         
        EXP.setAlignment(Pos.CENTER_LEFT);
        
        VBox Conteneur_final = new VBox();
         
        Conteneur_final.getChildren().addAll(level ,EXP);
        this.setTop(nom);
        this.setCenter(Conteneur_final);
        
        BorderStroke border = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        this.setBorder(new Border(border));  

    }

    
   
    
    public static void GAIN_EXP( Unite_Lucifer hero , int gain_EXP){
        
        int total = (hero.getExp()+gain_EXP) ;
        if ( total > hero.getExpMax() ){          
            hero.setLevel(hero.getLevel()+1);
            hero.setPvMax(hero.getPvMax()+20);
            hero.setDammage(hero.getDammage()+5);
            total = total -100 ;
            if ( total > 0){
                hero.setExp(total);
            }
            else 
                hero.setExp(0);
        }
        else{
           hero.setExp(hero.getExp()+total);
        }
    }
    
}