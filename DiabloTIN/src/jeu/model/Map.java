/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import jeu.model.entites.Decor_Floor;
import jeu.model.entites.Decor_Arbre;
import jeu.model.entites.Decor_Mur;
import jeu.model.entites.Items_Key;
import jeu.model.entites.Items;
import jeu.model.entites.Decor;
import jeu.model.entites.Unite_Lucifer;
import jeu.model.entites.Unite_Mini_Diablotin;
import jeu.model.entites.Unite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static jeu.model.Leveling.GAIN_EXP;
import jeu.model.entites.Decor_Interactif;
import jeu.model.entites.Decor_Interactif_Door;
import jeu.model.entites.Unite_Big_Diablotin;
import jeu.model.entites.Unite_King_Diablotin;
import jeu.model.entites.Unite_Red_Diablotin;
import jeu.model.entites.Weapon_Epee;

/**
 *
 * @author Alexis
 */
public final class Map {  
    
    private final int DEFAULT_PLATEAU  = 100 ; // la taille 
    
    private static Map instanceMap ; // doit rester unique
    
    
    private Unite[][] map ;  // map unite 
    private Decor[][] map_decor ; // map decor ( mur , arbre ... ) 
    private Items[][] map_items ; // map item  ( epee , clée ) 
    
    private Unite_Lucifer hero ; // notre hero 
    
    private List<Unite> ennemy_list ; // la liste des ennemy
    
    private List<Event> eventzone ; //la liste des evenements 
   
    
    
    public Map() {
        
        this.instanceMap = this ;
        this.map = new Unite[DEFAULT_PLATEAU][DEFAULT_PLATEAU];
        this.map_decor = new Decor[DEFAULT_PLATEAU][DEFAULT_PLATEAU];
        this.map_items = new Items[DEFAULT_PLATEAU][DEFAULT_PLATEAU];
        this.hero = new Unite_Lucifer(70,70,this);
        
        this.ennemy_list = new ArrayList<>();
        this.eventzone = new ArrayList<>();
        
        
        
        MapDecor();
    }
    
    //constructeur pour le chargment d'une partie sauvegardé avec une map sauvegardé , hero etc 
    public Map(Unite[][] map , Decor[][] map_decor , Items[][] map_items , Unite_Lucifer hero , List<Unite> list_ennemy , List<Event> eventzone){
         
        this.ennemy_list = list_ennemy;
        this.eventzone = eventzone ;
        this.hero = hero ;
        this.map = map;
        this.map_items = map_items ;
        this.map_decor = map_decor ;
       
    }
    
    public Map(int rien){ 
        this.instanceMap = getInstanceMap() ;// j'avais un probleme et c'est la premiere solution simple que j'ai trouvé
        // mais c'est que pour créer la map a partir des 6 arguments du dessus  ,Parmis eux il y a une list ennemy
        // je pars d'une liste vide et je rajoute de nouveau monstre mais un monstre doit etre relier a une map 
        // mais map est null vu que je la créer apres donc j'utilise ce constructeur qui permet de rien alouer en memoire ( matrice )
        //voir SAUVEGARDE.JAVA 
    }
    
    public static  Map getInstanceMap() {
        if (instanceMap == null) {
            instanceMap = new  Map();
        }
        return instanceMap;
    }
    
    public void setMap(Map m ){
        this.map = m.getMapUnit();
        this.map_decor = m.getMapDecor() ;
        this.map_items = m.getMapItems();
        this.hero = m.getLucifer() ;
        this.ennemy_list = m.getEnnemy();
        this.eventzone = m.getEvent();
        this.instanceMap = this ;
    }
    
     public void MapDecor() {
         for ( int x = 0 ; x < DEFAULT_PLATEAU ; x++){
             for(int y = 0 ; y < DEFAULT_PLATEAU ; y++ ){
                 if(x == 0 || x+1 == DEFAULT_PLATEAU  || y==0 || y+1==DEFAULT_PLATEAU )
                     map_decor[x][y] = new Decor_Mur( x , y  );
                     
                 else{
                     map_decor[x][y] = new Decor_Floor(x , y);
                 }
              
             }
             
         }
    }
     
    /**
     * 
     * @return la liste des ennemy
     */
    public List<Unite> getEnnemy(){
        return this.ennemy_list;
    }
    
    /**
     *
     * @return la liste des evenements
     */
    public List<Event> getEvent(){
        return this.eventzone;
    }
    
    /**
     * renvoi une unite dans la taille du plateau ( x,y > 0 && x,y  < taille max ) 
     * @param x
     * @param y
     * @return une unite au coord x y 
     */
    public Unite getUnit(int x , int y ){
        if( x < DEFAULT_PLATEAU && x >= 0 && y < DEFAULT_PLATEAU && y >= 0)
            return map[x][y] ; 
        else 
            return null;
    }
    
  
    public Decor getDecor(int x , int y ){
        if( x < DEFAULT_PLATEAU && x >= 0 && y < DEFAULT_PLATEAU && y >= 0)
            return map_decor[x][y] ; 
        else 
            return null;
    }
    
    public Decor_Interactif getDecorInteractif(int x , int y ){
        
        if( x < DEFAULT_PLATEAU && x >= 0 && y < DEFAULT_PLATEAU && y >= 0 )
            if (map_decor[x][y] != null && (map_decor[x][y] instanceof Decor_Interactif)  ){
                return (Decor_Interactif) map_decor[x][y] ;}
            else {  
                return null ; }
        else 
            return null;
    }
    
    public Items getItems( int x , int y ){
         if( x < DEFAULT_PLATEAU && x >= 0 && y < DEFAULT_PLATEAU && y >= 0)
            return map_items[x][y] ; 
        else 
            return null;
    }
    
    
    public Unite[][] getMapUnit(){
        return map ;
    }
    
    public Decor[][] getMapDecor(){
        return map_decor ;
    }
    
    public Items[][] getMapItems(){
        return this.map_items ;
    }
    
    public Boolean FindDecor( int x , int y ){
        Decor find =  this.getDecor(x,y);
        if ( find != null ){
            return true ; 
        }
        else {
            return false ;
        }
    }
    
    
    
    public Decor_Floor getFloor(int x , int y ){ 
        Decor find =  this.getDecor(x,y);
        if ( find.getName() == "floor") {
             return (Decor_Floor) find ;
        }
        else {
           return null ;
            }   
    }
    
    public Boolean isFloor(int x , int y ){  
        Decor find =  this.getDecor(x,y);
        if ( find.getName() == "floor") {
             return true ;
        }
        else {
           return false;
            }   
    }
    
    public Boolean isMur(int x , int y ){ 
        Decor find =  this.getDecor(x,y);
        if ( find.getName() == "wall") {
             return true ;
        }
        else {
           return false;
            }   
    }
    
    public Boolean isArbre(int x , int y ){
        Decor find =  this.getDecor(x,y);
        if ( find.getName() == "arbre") {
             return true ;  
        }
        else {
           return false;
            }   
    }
     
    public Boolean isItem(int x , int y ){ 
        Items find =  getItems(x, y);
        if ( find != null ){


         if ( find.getName() == "key") {  
             return true ;  // j'ai inversé les bool dsl mais c'est contre intuitiff de dire :  si c'est un item alors faux ??
            }
         else if ( find.getName() == "epee")
             return true;
         else{
             return false ;
         }
        }
        else {
           return false;
            }   
    }
    
    
    public int getTaille(){
        return DEFAULT_PLATEAU;
    }
    
    /**
     * Par rapport au indication du string deplace notre hero d'un coup 
     * @param direction ex : "gauche" , "bas" , "haut_droite"
     */
    public void moveHero(String direction){
        Unite temp ;
        int  x = hero.getPosX() ;
        int  y = hero.getPosY() ;
        
        int dirX = x ; // position apres depalcement d'un coup 
        int dirY = y ; // on l'incremente ou pas apres       
        
        switch(direction){
            case "gauche":
                dirX--;
                break;

            case "droite":
                dirX++;
                break;
                
            case "haut":
                dirY--;
                break;
               
            case "haut_gauche" :    
                dirX--;
                dirY--;
                break;   
            case "haut_droite" :    
                dirX++;
                dirY--;
                break;
            case "bas":
                dirY++;
                break;
            case "bas_gauche":
                dirX--;
                dirY++;
                break;
            case "bas_droite":
                dirX++;
                dirY++;
                break;
            default :
               
                break;     
        }
        if (  this.isItem(dirX, dirY) ){ // si on passe sur un item on l'ajoute 
            hero.ajouteritem(getItems(dirX, dirY));
            map_items[dirX][dirY] = null ;
        }
        if( !Is_Collision(dirX, dirY) ) { // si il n'y a pas de collision alors on avance 
            temp = map[dirX][dirY] ;      
            map[dirX][dirY] = hero ;
            map[x][y] = temp ;
            hero.setPos(dirX,dirY);
        }
        
    }
    
    /**
     *
     * @return Lucifer 
     */
    public Unite_Lucifer getLucifer(){
        return this.hero;
    }
    
    // PArtie Arbre Ajoute Random , traitement Graph non traité 
    public int randomInt(int lowerBound, int upperBound) {
    Random rand = new Random();
    return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
     }
    
    
    public void Add_Random_Arbre(){
        int RandX  = randomInt(10,this.DEFAULT_PLATEAU-10);
        int RandY  = randomInt(10,this.DEFAULT_PLATEAU-10);
         if (  FindDecor( RandX , RandY )){
             Decor_Arbre a = new Decor_Arbre(RandX,RandY);
                this.map_decor[RandX][RandY] = a ;
         }
    }
    
    
    public void Add_Arbre_Random_NBR(int nbr){
         for ( int x = 0 ; x <  nbr ; x++){
             Add_Random_Arbre();
         }
    }
    /// Fin d'ajout de Arbre Random 
    
    /**
     * palce ou remplace dans la map au coordonnée x,y par ce qui correspond au string ,
     * utile uniqument pour la creation_carte.java car on clique sur une carte pour ajouter un mur , une unite ; une epee etc 
     * @param x
     * @param y
     * @param name
     */
    public void modifMap( int x , int y , String name){
        
        switch(name){
            
            case "floor":
                map_decor[x][y] = new Decor_Floor(x,y);
                break;
               
            case "wall":
                map_decor[x][y] = new Decor_Mur(x,y);
                break;
            
            case "arbre" :
                map_decor[x][y] = new Decor_Arbre(x,y);
                break;
                
            case "Lucifer":
                map[x][y] = new Unite_Lucifer(x,y,this);
                break;
                
            case "key" :
               map_items[x][y] = new Items_Key(x,y);
               break;
            case "epee":
                map_items[x][y] = new Weapon_Epee(x,y);
            case "boss":
                break;
            case "mob":
                map[x][y] = new Unite_Mini_Diablotin(x,y,this);
            case "door":
                map_decor[x][y] = new Decor_Interactif_Door(x,y);
                break;
            case "big_diablotin":
                map[x][y] = new Unite_Big_Diablotin(x,y,this);
                break;
            case "king_diablotin":
                map[x][y] = new Unite_King_Diablotin(x,y,this);
                break;
            case "mini_diablotin_red":
                map[x][y] = new Unite_Red_Diablotin(x,y,this);
                break;
            default : 
               
                break;
        }
    }
    
    /**
     * Déplace tout les ennemy de la liste puis si ils sont en vie ils font leurs action ( attaquer ou repos )
     */
    public void move_all_ennemi(){
        List<Unite> list = new ArrayList<Unite>();
        list.addAll(0,this.ennemy_list );
        for( Unite ennemi : list ){
            map[ennemi.getPosX()][ennemi.getPosY()] = null ;  
            map_decor [ennemi.getPosX()][ennemi.getPosY()] = new Decor_Floor(ennemi.getPosX(),ennemi.getPosY());
            
            if(ennemi.enVie()){
                ennemi.Action();
                map[ennemi.getPosX()][ennemi.getPosY()] = ennemi ;
            }
            else{
                this.supprimeUnite(ennemi);
                  }
        }
    }
    
    /**
     * Action de toute la map : on bouge les adversaire , on regarde les evenments actifs , on fait l'action de notre hero 
     */
    public void Action(){
        this.move_all_ennemi();
        
        if(this.getEvent() != null){
            List<Event> list = new ArrayList<>() ;
            list.addAll(this.eventzone);
            for(Event e : list) // sur une copy
                e.checkEvent(); //  car si l'evenement est terminé il est supprimé de la liste original , du coup ferais planter la boucle for vu qu'elle est pas terminé
        }
     
        this.hero.Action();
    }
   
    public void add_item(Items i){
        this.map_items[i.getPosX()][i.getPosY()] = i ;
        
    }
    
    public Boolean Is_Collision(int dirX , int dirY){
        if( ( !this.isMur(dirX , dirY) && !this.isArbre(dirX , dirY))  && this.getUnit(dirX, dirY) == null ){
            if ( this.getDecorInteractif(dirX, dirY) == null )
                return false ;
            else if ( this.getDecorInteractif(dirX, dirY) != null &&  this.getDecorInteractif(dirX, dirY).getCollision() != true){
                return false ;
            }
            else return true ;
        }
        else return true ;
    }
    
   public Boolean Use_Key_For_Door_Around(){
        int x;
        x = getInstanceMap().hero.getPosX();
        int y = getInstanceMap().hero.getPosY();
        Decor_Interactif_Door A = (Decor_Interactif_Door) this.getDecorInteractif(x+1, y); 
        Decor_Interactif_Door B = (Decor_Interactif_Door)  this.getDecorInteractif(x, y+1); 
        Decor_Interactif_Door C = (Decor_Interactif_Door)  this.getDecorInteractif(x-1, y); 
        Decor_Interactif_Door D = (Decor_Interactif_Door)  this.getDecorInteractif(x, y-1);
        if ( (A != null  ) || (B != null )|| (C != null )  || (D != null  )){
                if (A instanceof Decor_Interactif_Door && A.getEtatDoor() == true) {
                    A.setUnlock();
                    return true ;
                } else if (B instanceof Decor_Interactif_Door && B.getEtatDoor() == true) {
                    B.setUnlock();
                    return true ;
                } else if (C instanceof Decor_Interactif_Door && C.getEtatDoor() == true) {
                    C.setUnlock();
                    return true ;
                } else if (D instanceof Decor_Interactif_Door && D.getEtatDoor() == true) {
                    D.setUnlock();
                    return true ;
               }
                else {  return false ; } }
        else return false ;
    }
    
    /**
     *Supprime une unite , on regarde si elle est était dans un eventZone puis on donne l'experience au hero.
     * On l'a supprime de la matrice puis de la liste .
     * @param perso
     */
    public void supprimeUnite( Unite perso){
        
        for(Event e : this.eventzone){
            EventZone event = (EventZone )e ;
            if(e.getName()=="Zone")                
                event.inEvent(perso.getPosX(), perso.getPosY());
        }
        GAIN_EXP( this.hero ,perso.getExp());
        
        this.map[perso.getPosX()][perso.getPosY()]=null;
        this.ennemy_list.remove(perso);
        
        if(perso.getName() == "king_diablotin")
            System.exit(0);
       
        
    
        
    }
    
    /**
     * Charge un niveau d'une map créer par creation carte et non une sauvegarde d'une partie en cours
     * par rapport au nom
     * @param name
     */
    public void ChargerNiveau(String name){
        Sauvegarde save = new Sauvegarde();
        try {
                 
                FileInputStream fos = new FileInputStream(name);
                ObjectInputStream oos = new ObjectInputStream(fos);
                this.ennemy_list.clear();
                this.eventzone.clear();
                
                int size = this.DEFAULT_PLATEAU;
                
                for(int x=0 ; x < size ; x++)
                    for(int y= 0 ; y<size ;y++){
                        this.map_decor[x][y] = save.Int_to_Decor(oos.readShort(), x, y);
                        this.map_items[x][y] = save.Int_to_Items(oos.readShort(),x,y);
                        this.map[x][y] = save.Int_to_Unite(oos.readShort(), x, y);
                        if(map[x][y] != null){
                            if( map[x][y].getName() == "Lucifer")
                                this.hero.setPos(x, y);
                            
                        }
                    }
        
                int taille = oos.readShort();
               
                for(int i = 0 ; i < taille ; i++){

                    Unite e = save.Int_to_Unite(oos.readShort(), oos.readShort(), oos.readShort());
                    e.setPv(oos.readShort());
                    e.setFatigue(oos.readShort());
                    e.setMap(this);
                    ennemy_list.add(e);
                    
                   
                }
                            
                       
                
                
                taille = oos.readShort();
                
                for(int i = 0 ; i < taille ; i++){

                    this.eventzone.add(save.Int_to_Event(oos.readShort(), oos.readShort(), oos.readShort(),oos.readShort(),oos.readShort(),oos.readShort(),this));
                    
                
                
                }
                oos.close();
                fos.close();
                //savegarde
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    } // charge une map faite par la creation de carte

   
    
}
