/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.vue;

import jeu.model.Images;
import java.util.List;
import jeu.model.Inventaire;
import jeu.model.entites.Items;
import jeu.model.entites.Unite_Lucifer;

/**
 *
 * @author arthur1haye
 */
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Inventaire_Graph extends Button {

    private Inventaire inv;
    private Unite_Lucifer l;
    private List<Items> liste;
    private Images image;
    
    private Stage stage; 

    public Inventaire_Graph(Unite_Lucifer l, Images im) {
        super("INVENTAIRE");

        this.l = l;
        this.liste = l.getListItem();
        this.image = im;
        this.setOnAction(e -> afficherInventaire(im));
        this.setPrefWidth(100);
        this.setPrefHeight(50);
    }
   

    private void afficherInventaire(Images im) {
        // Le if permet de fermer la fenetre et de réouvrir sans surcharge
        // de donnée, il detruit le stage quand tu clique sur la croix
        // Autre Option : Ajouter un Bouton fermer 
        if ( stage == null ){
            
            stage = new Stage();
            stage.setOnCloseRequest(event -> {
                stage.close();
                stage = null ;
                
            });
        }
        // Créer une copie de l'inventaire pour chaque fenêtre
        this.inv = new Inventaire(this.liste, this.image);
        this.l.setInvVisuel(inv);
        
        StackPane Inventaire = new StackPane();
        Inventaire.setPadding(new Insets(20,40,20,40));
        Inventaire.getChildren().add(inv);
        Scene scene = new Scene(new StackPane(Inventaire), 600, 750);

        // Il faudrait penser à mettre des images pour définir le hors Carte

        stage.setScene(scene);
        stage.show();
        
        
    }
}
