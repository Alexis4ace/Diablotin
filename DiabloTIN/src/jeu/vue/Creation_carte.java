package jeu.vue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jeu.model.Images;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jeu.model.Event;
import jeu.model.EventZone;
import jeu.model.Sauvegarde;
import jeu.model.entites.Decor_Floor;
import jeu.model.entites.Unite;
import jeu.model.entites.Unite_Big_Diablotin;
import jeu.model.entites.Unite_King_Diablotin;
import jeu.model.entites.Unite_Mini_Diablotin;
import jeu.model.entites.Unite_Red_Diablotin;

/**
 *
 * @author Alexis
 */
public class Creation_carte extends Application {
    private String select = "floor";
    
    private int clickX = 0;
    private int clickY = 0;
    
    
    
    private int tempX = -1;
    private int tempY = -1;
    
    private GridPane creation = new GridPane() ;
    private Images tab_img = new Images();
    private Map_graph jeu = new Map_graph(tab_img);
    
    private List<Unite> list_ennemy = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage ) {
    
        Pane droite = new Pane() ; // pane partie droite
        
        AllButton buttons = new AllButton(droite); // on lui ajoute les boutons voir tout en bas
        
        Pane root = new Pane(); // pane general avec la carte

        
 
        jeu.loadBigMap(8);
    
        root.getChildren().addAll(jeu,droite);
        
        root.setOnMouseDragged(event->{
            fctclick(event.getX(),event.getY(),"drag");
        });
        root.setOnMouseClicked(event->{
            fctclick(event.getX(),event.getY(),"one");
        });
        
        Scene scene = new Scene(root, 1400, 800);
        
        primaryStage.setTitle("CREATION_CARTE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void fctclick( double x , double y , String name){
            Sauvegarde save = new Sauvegarde();
            if ( x <= 900 && y <= 900 && x >=0  && y>=0){
                clickX =   (int) ( (x) / 9);
                clickY =   (int) ( (y) /9 ) ;
                if(tab_img.isDecor(select)){
                    jeu.modifyMap(clickX, clickY, select);
                    jeu.getMap().getMapUnit()[clickX][clickY] = null ;
                    jeu.getMap().getMapItems()[clickX][clickY] = null ;
                }
                if(tab_img.isEvent(select)){
                    
                    if(this.tempX <=0 && this.tempY <=0 ){
                        this.tempX = clickX ;
                        this.tempY = clickY;
                    }
                    else{
                  
                        jeu.getMap().getEvent().add(new EventZone(clickX,clickY,tempX,tempY,10,jeu.getMap()));
                        tempX = -1;
                        tempY = -1;
                     
                    }
                  
                    
                }
                else if(name == "one"){
                    
                    jeu.modifyMap(clickX, clickY, select);
                    if(tab_img.isUnite(select) && select != jeu.getMap().getLucifer().getName())
                       
                        switch(select){
                            case "mini_diablotin_red":
                                this.list_ennemy.add( new Unite_Red_Diablotin(clickX,clickY,jeu.getMap()) );
                                break;
                            case "big_diablotin":
                                 this.list_ennemy.add( new Unite_Big_Diablotin(clickX,clickY,jeu.getMap()) );
                                break;
                            case "mob":
                                 this.list_ennemy.add( new Unite_Mini_Diablotin(clickX,clickY,jeu.getMap()) );
                                break;
                            case "king_diablotin":
                                 this.list_ennemy.add( new Unite_King_Diablotin(clickX,clickY,jeu.getMap()) );
                                break;
                            default :
                                
                                break;
                                
                        };
                        
                     
                }   
                
                
                jeu.loadBigMap(8);
               
                
                
                    
            }
        }
    
    private class AllButton {
        
        private String message = "DECOR" ;
        
        private final Button sauvegarde = new Button();
        private final Button charger = new Button();
        private final Button changement = new Button(message);
        private final ArrayList<Button> list_decor  = new ArrayList<>() ;
        
        private int nbr = 0 ;
        private final Pane root ;
        
        public AllButton(Pane root){
            
            
            
            this.root = root;
            
            Button_changement();
            Button_sauvegarde();
            Button_charger();
            
            Load();
            
        }
        private void Button_changement(){
            changement.setText(message);
            changement.setLayoutX(1200);
            changement.setLayoutY(700);
            changement.setOnAction((ActionEvent event) -> { 
                
                if(this.message == "DECOR" )
                    this.message = "UNITE";
                else if (this.message == "UNITE" )
                    this.message = "ITEM";
                else if( this.message == "ITEM")
                    this.message = "EVENT";
                else
                    this.message = "DECOR";
                changement.setText(message);
                Load();
                            
            });
            
        }
        private void Button_sauvegarde(){
            Sauvegarde save = new Sauvegarde();
            sauvegarde.setText("Sauvegarde");
            sauvegarde.setOnAction((ActionEvent event) -> {
                try {
                    FileOutputStream fos = new FileOutputStream("CREATION/sauvegarde_creation.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    
                    int size = jeu.getMap().getMapDecor().length;
                    
                    for(int x=0 ; x < size ; x++)
                        for(int y= 0 ; y<size ;y++){
                            oos.writeShort(           save.Decor_to_Int(    jeu.getMap().getMapDecor()[x][y]   )           );
                            oos.writeShort(           save.Items_to_Int(jeu.getMap().getMapItems()[x][y]   )           );
                            oos.writeShort(           save.Unite_to_Int(jeu.getMap().getMapUnit()[x][y]   )           );
                            
                        }
                    
                    oos.writeShort(      list_ennemy.size()       );
                  
                for(Unite e : list_ennemy){
                    
                    oos.writeShort(       save.Unite_to_Int(e)       );
                    oos.writeShort(       e.getPosX()       );
                    oos.writeShort(       e.getPosY()       );  
                    oos.writeShort(       e.getPv()       );
                    oos.writeShort(       e.getFatigue()       );
                
                }
                    
                    oos.writeShort(       jeu.getMap().getEvent().size()       );
                  

            
                    for(Event e : jeu.getMap().getEvent()){
                        EventZone eventzone = ( EventZone) e ;
                       
                        if( "zone".equals(e.getName()) ){
                            
                            oos.writeShort( save.Event_to_int(e)      );
                            oos.writeShort(       eventzone.getposX1()       );
                            oos.writeShort(       eventzone.getposX2()       );
                            oos.writeShort(       eventzone.getposY1()      );
                            oos.writeShort(       eventzone.getposY2()     );  
                            oos.writeShort(       eventzone.getMort()     );    
                        }
                    }
             
                    oos.close();
                    fos.close();
                    //savegarde
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
            });
            sauvegarde.setLayoutX(1320);
            sauvegarde.setLayoutY(700);
            
        }
        private void Button_charger(){ // charger une carte deja créer
            
            charger.setText("charger");
            charger.setOnAction((ActionEvent event) -> {
                list_ennemy.clear();
            
                jeu.getMap().ChargerNiveau("CREATION/sauvegarde_creation.txt");
                list_ennemy = jeu.getMap().getEnnemy();
                jeu.loadBigMap(8);
                
            });
            charger.setLayoutX(1320);
            charger.setLayoutY(750);
   
        
        }
        
        private void Button_list(String name , Image img){ // list des bouton pour chaque texture 
                    Button b = new Button(name);

                    ImageView view = new ImageView();
                    view.setImage(    img   );

                    b.setGraphic(view);
                    b.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                    b.setOnAction(e -> {
                        select = b.getText();
                       
                    }
                    );

                    b.setLayoutX(1320);
                    b.setLayoutY(0+nbr*50);
                    this.list_decor.add(b);
                    this.root.getChildren().add(b);
                    nbr++;

        }
     
        
        private void Load(){ // acutalise
            root.getChildren().clear();
            
            root.getChildren().addAll(this.sauvegarde,this.charger,this.changement);
            
            ArrayList<Image> list = tab_img.getList();
            ArrayList<String> list_name = tab_img.getListName();
            
            int taille = list.size();
            nbr = 0 ;
            
            for( int i=0 ; i < taille ; i++){
                if ( tab_img.isDecor( list_name.get(i)) && this.message == "DECOR" )
                    this.Button_list( list_name.get(i) ,list.get(i) );
                else if ( tab_img.isUnite( list_name.get(i)) && this.message == "UNITE" )
                    this.Button_list( list_name.get(i) ,list.get(i) );
                else if ( tab_img.isItem( list_name.get(i)) && this.message == "ITEM" )
                    this.Button_list( list_name.get(i) ,list.get(i) );
                else if(tab_img.isEvent( list_name.get(i)) && this.message == "EVENT" )
                    this.Button_list( list_name.get(i) ,list.get(i) );
                else
                    {}
            }
           
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
//    
}
