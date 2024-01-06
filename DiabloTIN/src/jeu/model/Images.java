/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import java.util.ArrayList;
import javafx.scene.image.Image;
/**
 *
 * @author Alexis
 */
public class Images {  // Le but ici de charger nos Images une seul fois ici et de pouvoir les récuperer partout 
                       // pour eviter de charger pour chaque case la meme image ( probleme de mémoire ) 
    
    // Image Unite
    private final Image floor = new Image(getClass().getResource("/image/floor/herbe/herbe_5.png").toString()); 
    private final Image wall = new Image(getClass().getResource("/image/floor/pierre/p.png").toString()); 
    private  Image Lucifer = new Image(getClass().getResource("/image/Objet/Lucifer/bas.png").toString()); 
    private final Image mini_diablotin = new Image(getClass().getResource("/image/fleche.png").toString()); 
    private final Image mini_diablotin_red = new Image(getClass().getResource("/image/red_diablotin/bas_1.png").toString());
    private final Image big_diablotin = new Image(getClass().getResource("/image/big_diablotin/bas_1.png").toString());
    private final Image king_diablotin = new Image(getClass().getResource("/image/king_diablotin/bas_1.png").toString());
    
    // Direction 
    private final Image fleche = new Image(getClass().getResource("/image/fleche.png").toString()); 
    private final Image haut_gauche = new Image(getClass().getResource("/image/haut_gauche.png").toString()); 
    private final Image haut_droite = new Image(getClass().getResource("/image/haut_droite.png").toString()); 
    private final Image bas_gauche = new Image(getClass().getResource("/image/bas_gauche.png").toString()); 
    private final Image bas_droite = new Image(getClass().getResource("/image/bas_droite.png").toString()); 
    
    // Entity
    private final Image boots = new Image(getClass().getResource("/image/Boots/boots_3.png").toString()); 
    private final Image arbre = new Image(getClass().getResource("/image/arbre.png").toString());
    private final Image zone = new Image(getClass().getResource("/image/Objet/zone.png").toString()); 
    private final Image Key = new Image(getClass().getResource("/image/Objet/Key/key_1.png").toString());
    private final Image fond_pixel = new Image(getClass().getResource("/image/fond_pixel.png").toString()); 
    private final Image epee = new Image(getClass().getResource("/image/Objet/Weapons/epee.png").toString()); 
    private final Image door = new Image(getClass().getResource("/image/door/door_1.png").toString()); 
    private final Image door_2 = new Image(getClass().getResource("/image/door/door_6.png").toString()); 
   
    
    // Carte 
    private final Image hero_pos = new Image(getClass().getResource("/image/rouge.png").toString()); 
    private final Image boss = new Image(getClass().getResource("/image/Carte/boss.png").toString()); 
    private final Image mob = new Image(getClass().getResource("/image/Carte/mob.png").toString()); 
    private final Image pnj = new Image(getClass().getResource("/image/Carte/IA.png").toString()); 
    
    // image de coffre 1 sur 2 
    private final Image items_coffre = new Image(getClass().getResource("/image/Carte/coffre_1.png").toString()); 
    private final Image items_coupe = new Image(getClass().getResource("/image/Carte/coupe.png").toString()); 
    // Pour signaler une porte sur la map :
    private final Image items_verrou = new Image(getClass().getResource("/image/Carte/verrou.png").toString());     
    
    
    private final ArrayList<Image> list_img = new ArrayList<Image>(); //list juste pour la CREATION CARTE car  je créer des boutons pour chaque texture 
    private final ArrayList<String> list_string = new ArrayList<String>(); // comme ça on clique sur la texture puis on la place sur la map  
                                                                              // VOIR DANS LE PACKAGE : JEU.VUE , La classe Creation_carte.java 

    /**
     * Fonction qui charge toutes nos images ( a ne pas faire de doublons )
     */
    public Images(){
        
        // -----on ajoute tout ce que l'on veut pouvoir placer pour la creation_carte ----- 
        
        list_img.add(floor);   //le sol
        list_string.add("floor");
        
        list_img.add(wall);  // le mur
        list_string.add("wall");
        
        list_img.add(arbre);   // l'arbre
        list_string.add("arbre");
        
        list_img.add(door);  // la porte
        list_string.add("door");

        list_img.add(Lucifer);  // notre hero 
        list_string.add("Lucifer");  
        
        list_img.add(mob);   // diablotin de base 
        list_string.add("mob"); 
        
        list_img.add(mini_diablotin_red);  // diablotin rouge 
        list_string.add("mini_diablotin_red");
        
        list_img.add(big_diablotin);  // gros diablotin
        list_string.add("big_diablotin");
        
        list_img.add(king_diablotin);  // le boss final 
        list_string.add("king_diablotin");
         
        list_img.add(this.epee);  // l'epee
        list_string.add("epee");
 
        list_img.add(Key);  // la clée 
        list_string.add("key");
        
        list_img.add(zone);  // l'evenement zone 
        list_string.add("zone");
        
        // ----- Fin Ajout pour la carte ----- 
        
      
        
    }
    
    /**
     * renvoi une image par rapport à son nom , 
     * si le nom est inconnu renvoi un sol 
     * @param name
     * @return
     */
    public Image getImage(String name){ // ici on recupere une image par son nom 
        
        switch(name){
            case "floor":
                return floor;
               
            case "wall":
                return wall;
             
            case "Lucifer":
                return Lucifer;
                
            case "key" :
                return Key ;
            
            case "fleche" :
                return fleche;
            
            case "haut_gauche" :
                return haut_gauche;
            
            case "haut_droite" :
                return haut_droite;
            
            case "bas_gauche" : 
                return bas_gauche;
            
            case "bas_droite" :
                return bas_droite ;
            
            case "boots" :
                    return boots ;
            
            case "arbre" :
                return arbre ;
            
            case "fond_pixel" :
                return fond_pixel;
            
            case "mini_diablotin":
                return mini_diablotin;
            
            case "epee":
                return epee;
            
            case "zone":
                return zone;
            
            case "door" :
                return door;
            
            case "door_2" :
                return door_2;
        
            case "hero_pos" :
                return hero_pos ;
            
            case "mob" :
                return mob ;
            
            case "boss" :
                return boss ;
            
            case "pnj" :
                return pnj ;
            
            case  "items_coffre" :
                return items_coffre;
            
            case  "items_verrou" :
                return items_verrou;
            
            case  "items_coupe" :
                return items_coupe;
            
            default : 
               
                return floor;
        }
    }
    
    /**
     *
     * @return la liste des nom des images dans le meme ordre de la liste image
     */
    public ArrayList<String> getListName(){
        return list_string;
    }
    
    /**
     *
     * @return la liste des images dans le même ordre de la liste des noms
     */
    public ArrayList<Image> getList(){
        return list_img;
       
    }
    
    /**
     * Décor : "floor" "wall" "arbre" "door"
     * @param name
     * @return true si c'est un decor , false sinon 
     */
    public boolean isDecor(String name ){
        switch(name){
            case "floor" : 
                return true;
            case "wall" : 
                return true;
            case "arbre" :
                return true ;
            case "door" : 
                return true;
            default :
                return false;
        }
    }
    
   
    public boolean isDecor_Interactif(String name ){
        switch(name){
            case "door" : 
                return true;
            default :
                return false;
        }
    }
    
    /**
     * Les items sont " epee"  "key" 
     * @param name
     * @return vrai si c'est un item 
     * sinon faux 
     */
    public boolean isItem(String name ){
        switch(name){
            case "epee" : 
                return true;
            case "key" : 
                return true;

            default :
                return false;
        }
    }
    
    /**
     * Unite: "Lucifer" "mob" "big_diablotin" "king_diablotin" "mini_diablotin_red"
     * @param name
     * @return vrai si c'est une unite sinon faux 
     */
    public boolean isUnite(String name ){
        switch(name){
            case "Lucifer" : 
                return true;
            case "mob" :
                return true;
            case "big_diablotin" : 
                return true;
            case "king_diablotin" :
                return true;
            case "mini_diablotin_red":
                return true;
            default :
                return false;
        }
    }
    
    /**
     * EVENT : "zone"
     * @param name
     * @return booleean vrai si c'est un evenement sinon faux 
     */
    public boolean isEvent(String name){
        switch(name){
            case "zone" : 
                return true;
            default :
                return false;
        }
    }
    
    public void setImageLucifer( Image img ){
        this.Lucifer = img ;
    }
    
    public Image getImageLucifer(){
        return this.Lucifer ;
        
    }
    
    
    
}
