/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.vue;

import jeu.model.Images;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import jeu.model.Direction;

/**
 *
 * @author arthur1haye
 */
public class ButtonsDeplacement extends BorderPane {
    
    
    public ButtonsDeplacement( Map_graph jeu, Images tab_img){
        
        super();
         // Objet 
        Direction droite = new Direction(tab_img, "droite", jeu);
        Direction gauche = new Direction(tab_img, "gauche", jeu);
        Direction haut = new Direction(tab_img, "haut", jeu);
        Direction haut_gauche = new Direction(tab_img, "haut_gauche", jeu);
        Direction haut_droite = new Direction(tab_img, "haut_droite", jeu);
        Direction bas = new Direction(tab_img, "bas", jeu);
        Direction bas_gauche = new Direction(tab_img, "bas_gauche", jeu);
        Direction bas_droite = new Direction(tab_img, "bas_droite", jeu);
        
         // Conteneur diagonal haut+bas
        HBox haut_conteneur = new HBox();
        haut_conteneur.getChildren().addAll(haut_gauche, haut, haut_droite);
        HBox bas_conteneur = new HBox();
        bas_conteneur.getChildren().addAll(bas_gauche,bas, bas_droite);
        
        
        // Init Placement AllButtons  + Bordure Noir
        this.setBottom(bas_conteneur);
        BorderPane.setAlignment(bas, Pos.CENTER);
        this.setTop(haut_conteneur);
        BorderPane.setAlignment(haut, Pos.CENTER);
        this.setLeft(gauche);
        BorderPane.setAlignment(gauche, Pos.CENTER);
        this.setRight(droite);
        BorderPane.setAlignment(droite, Pos.CENTER);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        // BorderPane plus efficace
        Image image = tab_img.getImage("boots");
        this.setCenter(new ImageView(image));
    }
}
