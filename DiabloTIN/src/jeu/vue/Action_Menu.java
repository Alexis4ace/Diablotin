/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import jeu.model.DataBase;
import jeu.model.Images;
import jeu.model.Leveling;
import jeu.model.Sauvegarde;
import jeu.model.Skills;
import jeu.model.entites.Unite_Lucifer;
/**
 *
 * @author Alexis
 */
public class Action_Menu extends Pane{
    
    private final DataBase db = new DataBase();
    
    private int page = 0 ; // position de la page actuel
    private int MaxPage = 10 ;  // nombre de choix par page
    private int n = 0 ; //pour la boucle for index dans listparti
    private final Image menu = new Image(getClass().getResource("/image/Objet/menu.jpg").toString()); 
    private Map_graph jeu;
    
    private boolean activeThrad = true ;
    
    public Action_Menu(){
        this.loadMenu();
    }
    
    
    
    private void loadMenu(){
        this.getChildren().clear();
        
        Rectangle rect = new Rectangle(1200,1000);
        rect.setFill(new ImagePattern(menu));
        
        Button go = new Button("Lancer une nouvelle partie ");
        go.setOnAction(e -> {
            loadPartie(null);
        });
        go.setLayoutX(500);
        go.setLayoutY(800);
        
        Button load = new Button("Charger une partie ");
        load.setOnAction(a->{
            listPartie();
        });
        load.setLayoutX(500);
        load.setLayoutY(900);
        
        this.getChildren().addAll(rect,go,load);
        
    }
    
    public void loadPartie(String name){ // nouvelle partie
        this.getChildren().clear();
        Images tab_img = new Images();
        // Conteneur Jeu 
         
       
        Sauvegarde menu_save = new Sauvegarde();     
        this.jeu = new Map_graph(tab_img);
        
        if(name != null ){
            Sauvegarde save = new Sauvegarde();
            System.out.println(name);
            jeu.setMap(save.loadSauvegarde(name));
            jeu.loadMap();
        }else
            jeu.getMap().ChargerNiveau("NIVEAU/STAGE1.txt");
            
        
        
        // Label
        Label TITRE_GAME = new Label("DIABLO");
        BorderPane.setAlignment(TITRE_GAME, Pos.CENTER);
        BorderPane.setMargin(TITRE_GAME, new Insets(20));
        
        // Conteneur
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 20, 10, 20));
        ButtonsDeplacement AllButtons = new ButtonsDeplacement(jeu, tab_img);

        // Modif Taille des Pane 
        AllButtons.setPrefSize(150, 150);
        HBox Button_And_Carte = new HBox();
        Button_And_Carte.setSpacing(30); // définit l'espacement entre les éléments à 10 pixels
        Button_And_Carte.setMaxWidth(1150);
        Button_And_Carte.setMinWidth(1150);
        
        
        Unite_Lucifer l = this.jeu.getMap().getLucifer() ;
      
        // Conteneur stats
        VBox Conteneur_stats_final = new VBox();
        HBox Conteneur_stats = new HBox();
        Region spring_stats = new Region();
        spring_stats.setPrefWidth(30);
        // Leveling
        Leveling leveling= new Leveling(l);
        StatsView stats = new StatsView(l);
        Conteneur_stats_final.getChildren().addAll(leveling,stats);
        root.setRight(Conteneur_stats_final);
        Conteneur_stats.setPadding(new Insets(0,20,10,10));
        Conteneur_stats.getChildren().addAll(spring_stats , Conteneur_stats_final);
        // Skill
         VBox skills = new VBox();
        Label nomSk = new Label("   SKILLS");
        Skills sk = new Skills(l);
        skills.getChildren().addAll(nomSk,sk);
        
         // Conteneur Save 
        VBox Conteneur_save  = new VBox();
        
        TextField txt = db.getSatut() ? new TextField("GAME N°"+db.getDataSauvegarde().length ):  new TextField("GAME N°1");
        Button save = new Button("sauvegarder"); // ici button sauvegarde a replacer 
        save.setOnAction(e->{
            if(db.getSatut() )
                db.insertSauvegarde( txt.getText() , "63000" );
            menu_save.NewSauvegarder(jeu.getMap(),"Sauvegarde/"+txt.getText()+".txt");
            jeu.loadMap();
        });
        save.setPrefWidth(100);
        save.setPrefHeight(50);
        Label Name_Menu_Save = new Label("PARTIE");
        Conteneur_save.getChildren().addAll(Name_Menu_Save,save,txt);
        Conteneur_save.setSpacing(30);
        
        
        // Conteneur Carte + Inventaire : 
        HBox inv_carte_skill = new HBox();
        VBox Inv_plus_carte = new VBox();
        Inv_plus_carte.setSpacing(30);
        Inventaire_Graph inv = new Inventaire_Graph(l,tab_img);
        Carte carte = new Carte(jeu);
        Inv_plus_carte.getChildren().addAll(inv,carte);
        //////
        inv_carte_skill.getChildren().addAll(Inv_plus_carte,skills);
        inv_carte_skill.setPadding(new Insets(10, 20, 10, 20));
        inv_carte_skill.setSpacing(30);
        // Init Conteneur Button_And_Conteneur inv+carte
        Button_And_Carte.getChildren().addAll(AllButtons, inv_carte_skill, Conteneur_save);
        Button_And_Carte.setPadding(new Insets(10, 20, 10, 20));
         Button_And_Carte.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                 
         
        // Bulle Info Conteneur
        HBox Conteneur_info1 = new HBox();
        Region spring = new Region();
        spring.setPrefWidth(30);
        InfoGameView info = new InfoGameView(); 
        Conteneur_info1.getChildren().addAll(info, spring);
        Conteneur_info1.setPadding(new Insets(0,0,10,10));
        
        // Placement Principale  
        root.setBottom(Button_And_Carte);
        root.setCenter(jeu);
        BorderPane.setAlignment(jeu, Pos.CENTER_RIGHT);
        root.setTop(TITRE_GAME);
        root.setRight(Conteneur_stats);
        root.setLeft(Conteneur_info1);
        
        
        
          // boolean a false si on charge une partie en cours car lancer une partie en cours =
            // ---ACTIVATION DU THREAD --------///  lancer une nouvelle partie puis rechanger toutes les valeurs mais impossible une fois le thread lancé
            ServiceGame task = new ServiceGame(jeu);
                // Lance le Thread ici
            task.start();
        
        // Arrete le Thread quand le quitte 
       // this.setOnCloseRequest(event -> task.stop());
        
        ///------------- EXPLICATION FONCTION MESSAGE BULLE -------------------///
        // getInstanceInfo().add_message() est utilisable partout sans avoir a un instancier
        // quoi que ce soit c'est une méthode statique
        //getInstanceInfo().add_message("Exemple de message"); // 30 caractèremax 
        //Le message sera couper en deux a 22 caractère espace inclus
        this.getChildren().add(root);
    }
    
   
    
    public void listPartie(){
        this.getChildren().clear();
        Rectangle rect = new Rectangle(1200,1000);
        rect.setFill(new ImagePattern(menu));
      
        String[][] dataBase_donnée = db.getSatut() ? db.getDataSauvegarde() : new String[1][3];
        
        if(db.getSatut() == false ){
            dataBase_donnée[0][0] = "0" ;
            dataBase_donnée[0][1] = "GAME N°1";
            dataBase_donnée[0][2] = "60000" ; // la taille en ko pour faire beau mais on l utilisera pas et l'affichera pas
        }
        
        
        VBox list_vbox = new VBox();
        list_vbox.setLayoutX(500);
        list_vbox.setLayoutY(500);
        
        int nombre_sauvegarde = dataBase_donnée.length;
    
        for( int i=this.page*this.MaxPage ; i<nombre_sauvegarde ; i++){
            
            if( i < this.MaxPage){
                Button load = new Button(dataBase_donnée[i][0]+" : "+dataBase_donnée[i][1]);  // exemple:  0 : nom_de_la_sauvegarde
            
                load.setOnAction(e->{
                    
                    loadPartie("Sauvegarde/"+dataBase_donnée[ load.getText().charAt(0) - '0'  ][1]+".txt"); //  
                    this.activeThrad = false ;
                });
                list_vbox.getChildren().add(load);
            }
            else{
                break;
            }
        
        }
        
        if( (this.page+1)*this.MaxPage < nombre_sauvegarde){
            Button suivant = new Button("page suivante");
            suivant.setOnAction(e->{
                this.page++;
                listPartie();
            });
            suivant.setLayoutX(800);
            suivant.setLayoutY(800);
            this.getChildren().add(suivant);
        }
        
        if( (this.page +1) * this.MaxPage < nombre_sauvegarde){
            Button precedant = new Button("page precedente");
            precedant.setOnAction(e->{
                this.page--;
                listPartie();
            });
            precedant.setLayoutX(0);
            precedant.setLayoutY(800);
            this.getChildren().add(precedant);
        }
        
        Button back = new Button("retour");
        back.setOnAction(e->{
            this.loadMenu();
            this.page = 0 ;
        });
        back.setLayoutX(500);
        back.setLayoutY(900);
       
        
        this.getChildren().addAll(rect,back,list_vbox);
    }
}
