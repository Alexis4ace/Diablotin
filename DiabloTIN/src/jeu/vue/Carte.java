/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *
 * @author ahaye
 */
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jeu.model.entites.Unite;


public class Carte extends Button {

    private Map_graph map;
    private Unite Hero ;
    private double initialMouseX;
    private double initialMouseY;
    // Correspond a la taille des Rectangle en verité
    private int zoom = 15 ;

    public Carte(Map_graph map) {
        super();
        this.map = map;
        this.Hero = map.getMap().getLucifer();
        setText("CARTE");
        setOnAction(e -> afficherCarte());
        
        //Modif la Taille du button
        this.setPrefWidth(100);
        this.setPrefHeight(50);
    }

    private void afficherCarte() {
        // Créer une copie de Map_graph pour chaque fenêtre
        Map_graph mapCopy ;
        mapCopy = this.map.getCopyMap();
        mapCopy.loadCarte(this.zoom);
        
        int PosCentrageX = this.Hero.getPosX();
        int PosCentrageY = this.Hero.getPosY();
        
        BorderPane vue = new BorderPane();
        vue.setCenter(mapCopy);
        vue.setPadding(new Insets(10, 20, 10, 20));
        Stage stage = new Stage();
        Scene scene = new Scene(vue, 1000, 750);
        
        
         // Centrer la carte sur le Personnage Principale LUCIFER
        double centeringX = -(PosCentrageX * zoom) + (scene.getWidth() / 2) - (zoom / 2);
        double centeringY = -(PosCentrageY *zoom) + (scene.getHeight() / 2) - (zoom / 2);
        vue.setTranslateX(centeringX);
        vue.setTranslateY(centeringY);
        // Il faudrait penser a mettre des images Pour definir le hors Carte 
        
        vue.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               // Enregistrer la position initiale de la souris
               initialMouseX = event.getSceneX();
               initialMouseY = event.getSceneY();
           }
       });

       vue.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               // Calculer le déplacement de la souris
               double offsetX = event.getSceneX() - initialMouseX;
               double offsetY = event.getSceneY() - initialMouseY;

               // Déplacer la carte en fonction du déplacement de la souris
               vue.setTranslateX(vue.getTranslateX() + offsetX);
               vue.setTranslateY(vue.getTranslateY() + offsetY);

               // Mettre à jour la position initiale de la souris pour le prochain déplacement
               initialMouseX = event.getSceneX();
               initialMouseY = event.getSceneY();
           }
       });

        
        
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    public int getZoomCarte(){
        return zoom ;
    }
    
    public void setZoomCarte(int new_zoom){
        this.zoom = new_zoom ;
    }
    
}


