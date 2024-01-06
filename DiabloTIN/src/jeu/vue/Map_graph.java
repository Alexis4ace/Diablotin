/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;


import jeu.model.Images;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import jeu.model.entites.Unite_Lucifer;
import jeu.model.Map;
import jeu.model.entites.Decor_Interactif;
import jeu.model.entites.Decor_Interactif_Door;
import jeu.model.entites.Items;
import jeu.model.entites.Unite;
import jeu.model.entites.Unite_Big_Diablotin;
import jeu.model.entites.Unite_King_Diablotin;
import jeu.model.entites.Unite_Mini_Diablotin;
import jeu.model.entites.Unite_Red_Diablotin;

/**
 *
 * @author Alexis (et Hugo lol) (( et alexis ))
 */

// J'ai utiliser un GridPane 
public class Map_graph extends GridPane{
    
    private static Map_graph mapCopy ;
    private Map map = new Map() ;
    private Images tab_img ;
    
    
    private Rectangle[][] matrice_decor = new Rectangle[map.getTaille()][map.getTaille()];
  

    
    public Map_graph(Images imgs){
        this.tab_img = imgs ;
        this.mapCopy = this;
        loadMap();
        
        // Ici c'est le contructeur par defaut, si on y touche pas mal de truc plante, peut-être faire des test sur ca d'ailleurs...
    }
    
    public void loadMap() {
        // Decor ici
         loadMapDecor();
         
        // Beaucoup de possibilité d'améliorer le truc mais pour l'instant je vérife juste que tout marche par 
        // Rapport a l'héritage
         int LuciferPosX_Neg = map.getLucifer().getPosX() - 5 ;
         int LuciferPosY_Neg = map.getLucifer().getPosY() - 5 ;
         int LuciferPosX_Pos = map.getLucifer().getPosX() + 6 ;
         int LuciferPosY_Pos = map.getLucifer().getPosY() + 6 ; 
         int limite = map.getTaille() ;
         // Verif Dimension 
         if ( LuciferPosX_Neg < 0  ){
             LuciferPosX_Pos += LuciferPosX_Neg*(-1);
         }
         if ( LuciferPosY_Neg < 0  ){
             LuciferPosY_Pos += LuciferPosY_Neg*(-1);
         }

         if ( LuciferPosX_Pos > limite  ){
             LuciferPosX_Neg += (LuciferPosX_Pos - limite)*(-1);
             LuciferPosX_Pos -= (LuciferPosX_Pos - limite);
         }
         if ( LuciferPosY_Pos > limite  ){
             LuciferPosY_Neg += (LuciferPosY_Pos - limite)*(-1);
             LuciferPosY_Pos -= (LuciferPosY_Pos - limite);
         }

        for (int x = LuciferPosX_Neg; x < LuciferPosX_Pos; x++) {
            for (int y = LuciferPosY_Neg; y < LuciferPosY_Pos; y++) {
                Image image;
                 Rectangle rect = new Rectangle(50, 50);
                if (y < 0 || x < 0){

                }
                else{

                // J'enregistre les Unite dans la Matrice_Unite. Si on veut supprimer une image d'une unité on
                // va dans matrice Unite, les images se trouve sur des Rectangles/Matrice_Decor d'ou l'interet de recuperer 
                // le decor également
                 Map_Graph_Unite_King_Diablotin( x , y ,  matrice_decor[x][y] ) ;
                 Map_Graph_Unite_Big_Diablotin( x , y ,  matrice_decor[x][y] ) ;
                 Map_Graph_Unite_Red_Diablotin( x , y ,  matrice_decor[x][y] ) ;
                 Map_Graph_Unite_Mini_Diablotin( x , y ,  matrice_decor[x][y] ) ;
                 Map_Graph_Unite_Arbre( x , y ,  matrice_decor[x][y] ) ;
                 Map_Graph_Unite_Lucifer( x ,y , matrice_decor[x][y] );
                 Map_Graph_Items_Key(x,y,matrice_decor[x][y]);

                } 
            }
        }
    }



  
    public void loadMapDecor() {
        this.getChildren().clear();

         int LuciferPosX_Neg = map.getLucifer().getPosX() - 5 ;
         int LuciferPosY_Neg = map.getLucifer().getPosY() - 5 ;
         int LuciferPosX_Pos = map.getLucifer().getPosX() + 6 ;
         int LuciferPosY_Pos = map.getLucifer().getPosY() + 6 ; 
         int limite = map.getTaille() ;
         // Verif Dimension 
         if ( LuciferPosX_Neg < 0  ){
             LuciferPosX_Pos += LuciferPosX_Neg*(-1);
         }
         if ( LuciferPosY_Neg < 0  ){
             LuciferPosY_Pos += LuciferPosY_Neg*(-1);
         }

         if ( LuciferPosX_Pos > limite  ){
             LuciferPosX_Neg += (LuciferPosX_Pos - limite)*(-1);
             LuciferPosX_Pos -= (LuciferPosX_Pos - limite);
         }
         if ( LuciferPosY_Pos > limite  ){
             LuciferPosY_Neg += (LuciferPosY_Pos - limite)*(-1);
             LuciferPosY_Pos -= (LuciferPosY_Pos - limite);
         }

        for (int x = LuciferPosX_Neg; x < LuciferPosX_Pos; x++) {
            for (int y = LuciferPosY_Neg; y < LuciferPosY_Pos; y++) {
                Image image;
                 Rectangle rect = new Rectangle(50, 50);
                if (y < 0 || x < 0){
                }
                else{
                    if ( map.getDecor(x,y) instanceof Decor_Interactif){
                        this.matrice_decor[x][y] = rect ;
                        Map_Graph_Decor_Door( x , y ,  rect );
                    }
                    else{
                        image = tab_img.getImage(map.getDecor(x, y).getName());
                        rect.setFill(new ImagePattern(image));
                    }
                    // J'enregistre le decore dans la Matrice_decor, si on veut supprimer un élement de decor on
                    // va dans matrice decor
                    this.matrice_decor[x][y] = rect ;
                    this.add(rect,x,y);
                    } 
            }
        }
    }

    
    public void loadBigMap(int zoom) {
        this.getChildren().clear();
        int taille = map.getTaille();
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                Image image;
                Rectangle rect = new Rectangle(zoom, zoom);
                image = tab_img.getImage(map.getDecor(x, y).getName()); 
                Items Temp_Items = this.map.getItems(x, y);
                Unite Temp_Unite = this.map.getUnit(x, y) ;
                if ( Temp_Unite != null ){
                        if (Temp_Unite.getName() == "Lucifer" ){
                            image = tab_img.getImage("hero_pos");
                            rect.setStroke(Color.RED);}
                        if ( Temp_Unite.getName() == "mini_diablotin")
                             image = tab_img.getImage("mob");
                        if ( Temp_Unite.getName() == "red_diablotin")
                             image = tab_img.getImage("mob");
                        if ( Temp_Unite.getName() == "big_diablotin")
                             image = tab_img.getImage("mob");
                        if ( Temp_Unite.getName() == "king_diablotin")
                             image = tab_img.getImage("boss");
                        if ( Temp_Unite.getName() == "pnj")
                             image = tab_img.getImage("pnj");
                       
                }
                else if ( Temp_Items != null){
                    image = tab_img.getImage("items_coffre");
                }
                else { rect.setStroke(Color.BLACK); }
                rect.setFill(new ImagePattern(image));
                rect.setStroke(Color.BLACK);
                this.matrice_decor[x][y] = rect ;
                this.add(rect,x,y);
            }
        }
    }

     public void loadCarte( int zoom) {
        this.getChildren().clear();
        int taille = map.getTaille();
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                Image image;
                Rectangle rect = new Rectangle(zoom, zoom);
                image = tab_img.getImage(map.getDecor(x, y).getName()); 
                Unite Temp = this.map.getUnit(x, y) ;
                if ( Temp != null ){
                        if (Temp.getName() == "Lucifer" ){
                            image = tab_img.getImage("hero_pos");
                            rect.setStroke(Color.RED);}
                        if ( Temp.getName() == "mini_diablotin")
                             image = tab_img.getImage("mob");
                        if ( Temp.getName() == "red_diablotin")
                             image = tab_img.getImage("mob");
                        if ( Temp.getName() == "big_diablotin")
                             image = tab_img.getImage("mob");
                        if ( Temp.getName() == "king_diablotin")
                             image = tab_img.getImage("boss");
                        if ( Temp.getName() == "pnj")
                             image = tab_img.getImage("pnj");
                }
                else { rect.setStroke(Color.BLACK); }
                rect.setFill(new ImagePattern(image));
                rect.setStroke(Color.BLACK);
                this.matrice_decor[x][y] = rect ;
                this.add(rect,x,y);
            }
        }
    }

    
   
    public Rectangle[][] getMatriceDecor(){
        return this.matrice_decor;
    }
    public void setMatriceDecor( Rectangle[][] matrice){
        this.matrice_decor = matrice ;
    }
            
            
    public GridPane getPane(){
        return this;
    }
    
    public Map getMap(){
        return this.map;
    }
    public void setMap( Map map){
        this.map.setMap(map);
        
    }
    
    public Images getImage(){
        return this.tab_img ;
    }
    
   
    
    public void setCopyMap(Map_graph jeu){
        this.mapCopy = jeu ;
    }
    
    public Map_graph getCopyMap() {
        Map_graph mapCopy = new Map_graph(this.tab_img);
        //mapCopy.setLucifer(this.map.getLucifer());
        mapCopy.setMatriceDecor(matrice_decor);
        mapCopy.setMap(map);
        return mapCopy ;
    }

 
   
  
    
    public void modifyMap(int x , int y , String name ){
        map.modifMap(x, y, name);
     
    }
    
    
    public void Map_Graph_Unite_Mini_Diablotin( int x , int y , Rectangle rect ){
    if ( map.getUnit(x, y) != null ){
         Image image;
         image = tab_img.getImage("floor");
        if ( map.getUnit(x, y).getName() == "mini_diablotin"){
           
                Unite_Mini_Diablotin mob =  (Unite_Mini_Diablotin) this.getMap().getUnit(x, y);
                
               SpriteMiniDiablotin diablo = new SpriteMiniDiablotin(this);
               
                ImageView imageView = diablo.getInstanceMiniDiablotin(this).getSpriteMiniDiablotin();
                rect.setFill(new ImagePattern(image));
                     //Ajoute l'image dans le GridPane
                this.add(imageView, x, y); 
            }
        }
    }
    
    public void Map_Graph_Unite_Red_Diablotin( int x , int y , Rectangle rect ){
    if ( map.getUnit(x, y) != null ){
         Image image;
         image = tab_img.getImage("floor");
        if ( map.getUnit(x, y).getName() == "red_diablotin"){
           
                Unite_Red_Diablotin mob =  (Unite_Red_Diablotin) this.getMap().getUnit(x, y);
                
               SpriteRedDiablotin diablo = new SpriteRedDiablotin(this);
               
                ImageView imageView = diablo.getInstanceRedDiablotin(this).getSpriteRedDiablotin();
                rect.setFill(new ImagePattern(image));
                     //Ajoute l'image dans le GridPane
                this.add(imageView, x, y); 
            }
        }
    }
    
    public void Map_Graph_Unite_King_Diablotin( int x , int y , Rectangle rect ){
    if ( map.getUnit(x, y) != null ){
         Image image;
         image = tab_img.getImage("floor");
        if ( map.getUnit(x, y).getName() == "king_diablotin"){
           
                Unite_King_Diablotin mob =  (Unite_King_Diablotin) this.getMap().getUnit(x, y);
                
               SpriteKingDiablotin diablo = new SpriteKingDiablotin(this);
               
                ImageView imageView = diablo.getInstanceKingDiablotin(this).getSpriteKingDiablotin();
                rect.setFill(new ImagePattern(image));
                     //Ajoute l'image dans le GridPane
                this.add(imageView, x, y); 
            }
        }
    }
    
    public void Map_Graph_Unite_Big_Diablotin( int x , int y , Rectangle rect ){
    if ( map.getUnit(x, y) != null ){
         Image image;
         image = tab_img.getImage("floor");
        if ( map.getUnit(x, y).getName() == "big_diablotin"){
           
                Unite_Big_Diablotin mob =  (Unite_Big_Diablotin) this.getMap().getUnit(x, y);
                
               SpriteBigDiablotin diablo = new SpriteBigDiablotin(this);
               
                ImageView imageView = diablo.getInstanceBigDiablotin(this).getSpriteBigDiablotin();
                rect.setFill(new ImagePattern(image));
                     //Ajoute l'image dans le GridPane
                this.add(imageView, x, y); 
            }
        }
    }

    
    
    public void Map_Graph_Unite_Lucifer( int x , int y , Rectangle rect ){
    if ( map.getUnit(x, y) != null ){
        if ( map.getUnit(x, y).getName() == "Lucifer"){
            Image image;
                Image image2 = tab_img.getImage("Lucifer");
                image = tab_img.getImage("floor");
                ImageView imageView = new ImageView(image2);
                rect.setFill(new ImagePattern(image));
               
                this.add(imageView, x, y); 
            }
        }
    }
    
    public void Map_Graph_Unite_Arbre( int x , int y , Rectangle rect ){
        if ( map.isArbre(x, y) == true ){
                Image image;
                Image image2 = new Image("/image/Objet/arbre.png");
                image = tab_img.getImage("floor");
                ImageView imageView = new ImageView(image2);
                rect.setFill(new ImagePattern(image));
                // Ajoute l'image dans le GridPane
                this.add(imageView, x, y); 
            }
    }
    
    public void Map_Graph_Items_Key( int x , int y , Rectangle rect){
         if ( map.getItems(x, y) != null ){
             if ( map.getItems(x, y).getName() == "key"){
                Image image;
                    
                    image = tab_img.getImage("floor");
                    
                    rect.setFill(new ImagePattern(image));
                     //Ajoute l'image dans le GridPane
                    this.add(SpriteKey.getInstanceKey(this).getSpriteKey(), x, y); 
             }
             if ( map.getItems(x, y).getName() == "epee"){
                Image image;
                Image image2;   
                image = tab_img.getImage("floor");
                image2 = tab_img.getImage("epee");
                
                rect.setFill(new ImagePattern(image));
                
                
                     //Ajoute l'image dans le GridPane
                    this.add(new ImageView(image2), x, y); 
             }
            }
    }
    
     public void Map_Graph_Decor_Door( int x , int y , Rectangle rect){
        if ( map.getDecor(x, y) != null ){
         if ( map.getDecor(x,y) instanceof Decor_Interactif){
           if ( map.getDecorInteractif(x, y) instanceof Decor_Interactif_Door ) {
              Decor_Interactif_Door Find = (Decor_Interactif_Door) map.getDecorInteractif(x, y);
                Image image ;
                Image image2 ;
                image = tab_img.getImage("floor");
                if ( Find.getEtatDoor() == true){
                    image2 = tab_img.getImage("door");}
                else {
                image2 = tab_img.getImage("door_2");}
                ImageView imageView = new ImageView(image);
                rect.setFill(new ImagePattern(image2));
                this.add(imageView, x, y);}
           }
        }
   }

}


