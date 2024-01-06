/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.vue;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jeu.model.Fill_Mana;
import jeu.model.entites.Unite_Lucifer;

/**
 *
 * @author arthur1haye
 */

    
    
public class StatsView extends BorderPane {

    private IntegerProperty Vie_Actuel;
    private int Vie_Max;
    
    private IntegerProperty Mana_Actuel;
    private int Mana_Max ;
    private Fill_Mana remplit_mana ;

    private int Xp_Actuel;
    private IntegerProperty Dammage;
    private IntegerProperty portee;

    private Unite_Lucifer hero;

    private IntegerProperty PosX;
    private IntegerProperty PosY;
    
    public StatsView(Unite_Lucifer hero) {
        super();

        // Set les Stats
        this.hero = hero;
        
        this.Vie_Actuel = new SimpleIntegerProperty(hero.getPv());
        this.Vie_Max = hero.getPvMax();
        
        this.Mana_Actuel = new SimpleIntegerProperty(hero.getMana());
        this.Mana_Max = hero.getMana();
        
        this.Dammage = hero.getDamageProperty();
        this.portee = hero.getporteProperty();
        
         // Thread qui remplit le mana en cours de jeu
         int Time_remplit = 500 ;
         this.remplit_mana = new Fill_Mana(this.hero);
         this.remplit_mana.Remplit(Time_remplit).start();
        //
        
        this.PosX = hero.getPosXProperty();
        this.PosY = hero.getPosYProperty();
        
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setTop(ConteneurStats());
        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        this.setBorder(new Border(borderStroke));
    }

    public VBox ConteneurHPView() { // Ici Concerne les Stats HP, On ne peut pas toucher au
        // TextFields mais on les vois bien modifié si des dégat sont subis
        VBox Conteneur = new VBox();
        this.Vie_Actuel = this.hero.getpvProperty();
        HBox HautduConteneur = new HBox();
        int Taille_barre = 100 ;
        StackPane HP = new StackPane();
        
        // Barre de vie A l'ints
        Rectangle vie = new Rectangle((this.Vie_Max/this.Vie_Actuel.getValue())*Taille_barre,25);
        vie.setFill(Color.RED);
        
        Rectangle non_vie = new Rectangle((this.Vie_Max/this.Vie_Actuel.getValue())*Taille_barre,25);
        non_vie.setFill(Color.WHITE);
        
        vie.setStrokeWidth(2);
        vie.setStroke(Color.BLACK);
        non_vie.setStrokeWidth(2);
        non_vie.setStroke(Color.BLACK);
        
        HP.getChildren().addAll(non_vie, vie);
        HP.setAlignment(Pos.CENTER_LEFT);
        
        Label NomHp = new Label("HP : ");
        TextField entier = new TextField(); 
        entier.setEditable(false);

        // Le Binding c'est ici 
        entier.textProperty().bind(this.Vie_Actuel.asString());
       

        HautduConteneur.getChildren().addAll(NomHp, entier);

        this.Vie_Actuel.addListener((observable, oldValue, newValue) -> {
            double ratio = newValue.doubleValue() / Vie_Max; // Calcul du ratio de la nouvelle valeur par rapport à la valeur maximale
            double newWidth = ratio * Taille_barre; // Nouvelle largeur du rectangle vie en fonction du ratio
            vie.setWidth(newWidth); // Mise à jour de la largeur du rectangle vie
        });


        Conteneur.setSpacing(10);
        Conteneur.getChildren().addAll(HautduConteneur, HP);

        return Conteneur;
    }

    
     public VBox ConteneurManaView(){
        VBox Conteneur = new VBox();
        
        Label NomMana = new Label("Mana :");
         StackPane Mana = new StackPane();
         int scaling_barre = 200 ;
         this.Mana_Actuel = this.hero.getManaProperty();
        
        // Barre de vie A l'ints
        Rectangle mana = new Rectangle(scaling_barre,25);
        mana.setFill(Color.BLUE);
        
        Rectangle non_mana = new Rectangle(scaling_barre,25);
        non_mana.setFill(Color.WHITE);
        
         mana.setStrokeWidth(2);
        mana.setStroke(Color.BLACK);
        non_mana.setStrokeWidth(2);
        non_mana.setStroke(Color.BLACK);

        // Le Binding c'est ici 
       
        Mana.setAlignment(Pos.CENTER_LEFT);
        Mana.getChildren().addAll(non_mana, mana);
        
        this.Mana_Actuel.addListener((observable, oldValue, newValue) -> {
            double ratio = newValue.doubleValue() / Mana_Max *scaling_barre; 
            double newWidth = ratio ; 
            mana.setWidth(newWidth);
        });
        
        Conteneur.setSpacing(10);
        Conteneur.getChildren().addAll(NomMana, Mana);
        return Conteneur;
    }
    
    public VBox ConteneurDamageView(){
        VBox Conteneur = new VBox();
        
        Label NomPortee = new Label("Degat:");
        TextField entier = new TextField(); 
        entier.setEditable(false);
        entier.textProperty().bind(this.Dammage.asString());
        
         Conteneur.setSpacing(10);
        Conteneur.getChildren().addAll(NomPortee, entier);
        return Conteneur;
    }
    
    
    
    public VBox ConteneurPorteeView(){
        VBox Conteneur = new VBox();
        
        Label NomPortee = new Label("RANGE :");
        TextField entier = new TextField(); 
        entier.setEditable(false);
        entier.textProperty().bind(this.portee.asString());
       
        
         Conteneur.setSpacing(10);
        Conteneur.getChildren().addAll(NomPortee, entier);
        return Conteneur;
    }
    

    public VBox ConteneurStats() {
        VBox ConteneurStats = new VBox();

        Label name = new Label();
        name.setText("-- STATISTIQUES --");

        ConteneurStats.getChildren().addAll(
                name, 
                ConteneurHPView(), 
                ConteneurManaView(),
                ConteneurDamageView(),
                ConteneurPorteeView(),
                ConteneurPosition());
        ConteneurStats.setSpacing(30);
        return ConteneurStats;
    }
    
    
    
    public VBox ConteneurPosition(){
        VBox ConteneurPos= new VBox();
        Label name = new Label();
        name.setText("-- Position --");
        
        Label X = new Label("X : "+hero.getPosX());
        Label Y = new Label("Y : "+hero.getPosY());

        HBox ConteneurXY= new HBox();
        Region espace = new Region();
        espace.setPrefWidth(30);
        ConteneurXY.getChildren().addAll(X,espace,Y);
        this.PosX.addListener((observable, oldValue, newValue) -> {
             X.setText("X : "+ this.hero.getPosX());
        });
        
         this.PosY.addListener((observable, oldValue, newValue) -> {
             Y.setText("X : "+ this.hero.getPosY());
        });
         
        ConteneurPos.getChildren().addAll(name,ConteneurXY);
        return ConteneurPos ;
    }


    
    
}
