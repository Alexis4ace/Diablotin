/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.entites.Unite_Lucifer;
import jeu.vue.Map_graph;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jeu.vue.SpriteLucifer;

/**
 *
 * @author Alexis
 */
public class Direction extends Button{
    
    private Images tab_img ;
    private Map jeu ;
    private Map_graph graphique ;
    private String direction ;
    private Unite_Lucifer Lucifer ;
    private SpriteLucifer new_Lucifer ;
    
    public Direction(Images imgs , String direction , Map_graph j){
        
        
       
        this.tab_img = imgs ;
        this.jeu = j.getMap() ;
        this.graphique = j ;
        this.direction = direction ;
        

        
        ImageView view = new ImageView();
        view.setImage( tab_img.getImage("fleche") );
        
        switch(direction){
            case "haut" : 
                view.setRotate(270);
                this.setLayoutX(200);
                this.setLayoutY(600);
                
                break;
            case "haut_gauche" : 
              view.setImage( tab_img.getImage("haut_gauche") );
                
                break;
            case "haut_droite" : 
                view.setImage( tab_img.getImage("haut_droite") );
                
                break;
            case "droite" : 
                this.setLayoutX(250);
                this.setLayoutY(650);
                
                break;
            case "gauche" : 
                view.setRotate(180);
                this.setLayoutX(150);
                this.setLayoutY(650);
               
                break;
            case "bas" : 
                view.setRotate(90);
                this.setLayoutX(200);
                this.setLayoutY(700);
               
                break;
            case "bas_gauche" : 
                view.setImage( tab_img.getImage("bas_gauche") );
                break;
            case "bas_droite" : 
                view.setImage( tab_img.getImage("bas_droite") );
               
                break;
                
              
            default : 
                
                break;
        }
        this.setOnAction(  event -> { 
            Deplace();
            
        }  );
        this.setMaxHeight(50.0);
        this.setMaxWidth(50.0);
        this.setMinHeight(50.0);
        this.setMinWidth(50.0);
        
        this.setGraphic(view);
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        
    }
    
    public void Deplace(){
        
        // Debut Sprite
        this.Lucifer = jeu.getLucifer();
        this.new_Lucifer = new SpriteLucifer(Lucifer);
        // Fin Sprite
        Image img = this.new_Lucifer.BonneDirectionSprite(this.direction, this.Lucifer.getDirection(), this.Lucifer);
        this.tab_img.setImageLucifer(img);
        
        
        jeu.moveHero(this.direction);
        
    }
    
    
    
    
}
