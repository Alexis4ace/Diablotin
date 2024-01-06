/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.model.entites.Decor;
import jeu.model.entites.Decor_Arbre;
import jeu.model.entites.Decor_Floor;
import jeu.model.entites.Decor_Interactif_Door;
import jeu.model.entites.Decor_Mur;
import jeu.model.entites.Items;
import jeu.model.entites.Items_Key;
import jeu.model.entites.Unite;
import jeu.model.entites.Unite_Big_Diablotin;
import jeu.model.entites.Unite_King_Diablotin;
import jeu.model.entites.Unite_Lucifer;
import jeu.model.entites.Unite_Mini_Diablotin;
import jeu.model.entites.Unite_Red_Diablotin;
import jeu.model.entites.Weapon_Epee;

/**
 *
 * @author Alexis
 */
public class Sauvegarde {
    private String DEFAULT_NAME = "sauvegarde_ingame.txt";
    private int DEFAULT_PLATEAU  = 100 ;
  
    
    private Map jeu = new Map(0);// genere une map vide 
    
    private Unite[][] map ;
    private Decor[][] map_decor ;
    private Items[][] map_items ;
    
    private Unite_Lucifer hero ;
    
    private List<Unite> ennemy_list ;
    
    private List<Event> eventzone ;
    
    public Sauvegarde() {  // creer dans le main 
       
        
        
    }
    
    public void NewSauvegarder(Map jeu , String name){ // pour sauvegarde on lance ça sur la map jeu a sauvegarder
        
        map = jeu.getMapUnit();
        map_decor = jeu.getMapDecor();
        map_items = jeu.getMapItems();
        
        hero = jeu.getLucifer();
        
        ennemy_list = jeu.getEnnemy();
        
        eventzone = jeu.getEvent() ;
        
        
        this.jeu = jeu;
        
        SaveLucifer(name);  
      
       
        
    }
    
    public Map loadSauvegarde(String name){
                           
        LoadLucifer(name);                       // recup donné puis créer une nouvelle map avec puis this.map = la nouvelle map         
        return this.jeu;                        // je retourne la map pleine
    }
    
    // les sauvegardes sont que des chiffres     voir plus bas fonction de traduction ( L'ordre de sauvegarde est important )
    private void SaveLucifer(String name){
        
        try {
                PrintStream myfile = new PrintStream (name) ;
                
                //------load lucifer
                myfile.println( this.hero.getExp() );   
                myfile.println(       this.hero.getLevel()     );
                myfile.println(       this.hero.getPv()    );
                myfile.println(       this.hero.getMana()      );
                myfile.println(       this.hero.getListItem().size()     );
                for(Items i : this.hero.getListItem())
                    myfile.print(      this.Items_to_Int(i) +" "    );
                
                //--------load unite 
                myfile.println(      ennemy_list.size()       );
                
                for(Unite e : ennemy_list){
                   
                    myfile.print(       Unite_to_Int(e)+" "       ); // espace pour eviter de créer un gros nombre    sinon il ecrit genre 1
                    myfile.print(       e.getPosX()   + " "    );  // la 2
                    myfile.print(       e.getPosY()    + " "   );  // la 25
                    myfile.print(       e.getPv()   + " "    );    // si je demande next int pour le load alors il me donne 1225 au lieu de 1 .
                    myfile.print(       e.getFatigue()       );
                    myfile.println();
                
                }
                
                //------load event
                myfile.println(       this.eventzone.size()       );
                
                for(Event e : this.eventzone){
                    EventZone event = ( EventZone) e ;
                    
                    if ("zone".equals(e.getName())) {
                        
                        myfile.print(this.Event_to_int(e) +" "     );
                        myfile.print(       event.getposX1() +" "      );
                        myfile.print(       event.getposX2()    +" "   );
                        myfile.print(       event.getposY1()   + " "   );
                        myfile.print(       event.getposY2()  +" "   ); 
                        myfile.print(       event.getMort()     );   
                    }
                    myfile.println();
                }
                
                //load plateau ( les 3 )
                for(int x = 0 ; x < DEFAULT_PLATEAU ; x++ ){
                    for(int y = 0 ; y < DEFAULT_PLATEAU ; y++ ){
                        
                        myfile.print(       this.Unite_to_Int(map[x][y]) +" "   );
                        myfile.print(       this.Decor_to_Int(map_decor[x][y]) +" "     );
                        myfile.print(       this.Items_to_Int(map_items[x][y])   + " "   );
                        
                    
                    }
                    myfile.println();
                }
                
            
                
                //savegarde
            } catch (FileNotFoundException ex) { 
                Logger.getLogger(Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    } 
   
    
    private void LoadLucifer(String name){
        try {
                
                Scanner file = new Scanner( new File( name ) ) ;
                
                this.hero = new Unite_Lucifer(); 
                
                hero.setExp(file.nextInt());
                
                hero.setLevel(file.nextInt());
                
               
                hero.setPv(file.nextInt());
               
                hero.setMana(file.nextInt());
                
                int taille = file.nextInt();
                
                for(int i= 0 ; i< taille ;i++){
                    
                    
                    hero.ajouteritem(this.Int_to_Items(file.nextInt(), 0, 0));
                }
                
                //-----------LOADALLUNIT
                this.ennemy_list = new ArrayList<>();
                taille = file.nextInt();
                
                for(int i = 0 ; i < taille ; i++){
                    
                    Unite e = this.Int_to_Unite(file.nextInt(), file.nextInt(), file.nextInt());
                    e.setPv(file.nextInt());
                    e.setFatigue(file.nextInt());
                    ennemy_list.add(e);
                }
                //----------LOADEVENT
                this.eventzone = new ArrayList<>();
                taille = file.nextInt();

                for(int i = 0 ; i < taille ; i++){

                    eventzone.add(this.Int_to_Event(file.nextInt(), file.nextInt(), file.nextInt(),file.nextInt(),file.nextInt(),file.nextInt(),null));

                }
                //-----------LOAD MAP 
                

                this.map = new Unite[DEFAULT_PLATEAU][DEFAULT_PLATEAU];
                this.map_decor = new Decor[DEFAULT_PLATEAU][DEFAULT_PLATEAU];
                this.map_items = new Items[DEFAULT_PLATEAU][DEFAULT_PLATEAU];

                for(int x = 0 ; x < DEFAULT_PLATEAU ; x++ ){
                    for(int y = 0 ; y < DEFAULT_PLATEAU ; y++ ){
                        int n = file.nextInt();
                        if(n==2)
                            map[x][y] = this.findGoodDiablotin( x, y) ;
                        if(n==1){
                            this.hero.setPos(x, y);
                            map[x][y] = this.hero ;
                        }
                        map_decor[x][y] = this.Int_to_Decor(file.nextInt(), x, y);
                        map_items[x][y] = this.Int_to_Items(file.nextInt(), x, y);
                    }       
                }
                
                this.jeu = new Map(this.map,this.map_decor,this.map_items,this.hero,this.ennemy_list,this.eventzone);
                this.hero.setMap(jeu);  // je dois relier toutes les unité,evenement et le hero a une map que je créer au dessus à m'aide des données sauvegarder
                for(Unite e : this.ennemy_list)
                    e.setMap(jeu);
                    
                for(Event e : this.eventzone)
                   e.setMap(jeu);

                
                
                    //savegarde
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } 
    }

    public int Decor_to_Int(Decor x ){ // traduit un decor en chiffre 
        switch(x.getName()){
            case "floor":
                return 0;
            case "wall":
                return 1;
            case "arbre":
                return 2;
            case "door":
                return 3;
            default :
                return 0;
        }
    }
    public int Unite_to_Int(Unite u){ // idem unite
        if(u==null)
            return 0;
        switch(u.getName()){
            case "Lucifer":
                return 1;
            case "mini_diablotin":
                return 2;
            case "king_diablotin":
                return 3;
            case "big_diablotin":
                return 4;
            case "red_diablotin":
                return 5;
            default :
                return 0;
        }
    }
    public int Items_to_Int(Items i){ // idem items 
        if(i == null )
            return 0;
        switch(i.getName()){
        
            case "key":
                return 1;
            case "epee":
                return 2;
            default :
                return 0;
        }
    }
    public int Event_to_int(Event e){// idem event
        switch(e.getName()){
            case "zone":
                return 1 ;
            default : 
                return 0;
        }
    }
    
    public Decor Int_to_Decor(int n , int x , int y ){ // traduit un chiffre en decor et le place au coordonné ecrit dans la sauvgarde
        switch(n){
            case 0:
                return new Decor_Floor(x,y);
            case 1:
                return new Decor_Mur(x,y);
            case 2:
                return new Decor_Arbre(x,y);
            case 3:
                return new Decor_Interactif_Door(x,y);
            default :
                return new Decor_Floor(x,y);
        }
    }
    public Unite Int_to_Unite(int n , int x , int y ){ // idem unite
        switch(n){
        
            case 1:
                return new Unite_Lucifer();
            case 2:
                return new Unite_Mini_Diablotin(x,y,this.jeu);
            case 3:
                return new Unite_King_Diablotin(x,y,this.jeu);
            case 4:
                return new Unite_Big_Diablotin(x,y,this.jeu);
            case 5:
                return new Unite_Red_Diablotin(x,y,this.jeu);
            default :
               
                return null;
        }
    }
    public Items Int_to_Items(int n , int x , int y ){ // idem items 
        switch(n){
        
            case 1:
                return new Items_Key(x,y);
            case 2:
                return new Weapon_Epee(x,y);
            default :
                return null;
        }
    }
    public Event Int_to_Event(int n , int posx1 , int posx2 , int posy1 , int posy2 , int nbr_dead , Map jeu){
        switch(n){
            case 1:
                return new EventZone(posx1,posx2,posy1,posy2,nbr_dead,jeu) ;
            default : 
                return null;
        }
    }
    
    
    
    // dans le fichier texte si y'a un gobelin en 4 10 alors en 4 10 il y aura 2 d'ecrit mais on ne connait pas les stats de ce gobelin donc on cherche dans la liste et on l'associe
    public Unite findGoodDiablotin(int  x ,int y ){
        for(Unite e : this.ennemy_list)
            if( e.getPosX() == x && e.getPosY() == y )
                return e;
        return null;
    }   
}
