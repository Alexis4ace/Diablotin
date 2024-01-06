/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.entites.Weapon_Epee;
import jeu.model.entites.Items;

/**
 *
 * @author Alexis
 */
public class EventZone extends Event{  // le but est :  dans une zone rectangulaire faire un nombre de victime pour avoir la recompense
    
    private final String name = "zone";  // nom de l'évenement
    private int nbr_dead = 10 ;  // nombre de mort pour la recompense 
    
    private int posX1 =15;
    private int posX2 =25;  // coin en haut à gauche
    private int posY1 =15;
    private int posY2 =25;  // coin en bas à droite
    
    private Map map ;  // associé à une map
    private Items recompense = new Weapon_Epee(posX1 + (posX2-posX1)/2 ,posY1 + (posY2-posY1)/2) ;  // item à gagner ( poser au milieu de la carte si l'inventaire est plein )
    
    /**
     *
     * @param posX1 coin en haut à gauche
     * @param posX2
     * @param posY1 coin en bas à droite
     * @param posY2
     * @param nbr_dead  objectif à atteindre
     * @param map
     */
    public EventZone(int posX1 , int posX2, int posY1 , int posY2 ,int nbr_dead ,Map map){
        this.posX1=posX1;
        this.posX2=posX2;
        this.posY1=posY1;
        this.posY2=posY2;
        this.nbr_dead=nbr_dead;
       
        
        this.map=map;
    }
    
    /**
     * Créer un event au valeur par defaut : 10 mort , du coin en x:15 y:15 jusqu'a x2:25 y2:25
     * @param map
     */
    public EventZone(  Map map){    
        this.map = map;
    }
    
    /** regarde l'avancement de l'evenement , si evenement terminé :  si l'inventaire n'est pas plein alors on lui ajoute dedans sinon on l'ajoute sur la map.
     *                                      sinon evenement non terminé on fait rien 
     * si l'evenemnt est terminé on le supprime
     */
    @Override
    public void checkEvent(){  
        
        if(this.nbr_dead <= 0){  
            
            if(map.getLucifer().getListItem().size()<5){  
                map.getLucifer().ajouteritem(recompense);
                map.getLucifer().getInventaireVisuel().load();
            }
            else   
                map.add_item(recompense);
            map.getEvent().remove(this);  
        }
        
    }
   
    /**
     * regarde si la posX posY est dans l'evenement , si oui on décremente l'objectif de 1 , la fonction InEvent est appelé lors de la mort d'une unité 
     * @param x
     * @param y
     */
    public void inEvent(int x , int y){                                       
        if ( x >=posX1 && x<=posX2 && y <= posY2 && y >= posY1 )
            decremente();                                                      
        
        checkEvent();
        
    }
    
    /**
     * décremente d'un l'objectif de cette evenement
     */
    private void decremente(){
        this.nbr_dead--;
        
    }
    
    /**
     *
     * @return String name = "zone"
     */
    @Override
    public String getName(){
        return this.name;
    }
   
    /**
     *
     * @return position X en haut à gauche de la zone
     */
    public int getposX1(){
        return this.posX1;
    }
    
    /**
     *
     * @return position X2 en bas à droite de la zone
     */
    public int getposX2(){
        return this.posX2;
    }
    
    /**
     *
     * @return position Y en haut à gauche de la zone
     */
    public int getposY1(){
        return this.posY1;
    }
    
    /**
     *
     * @return position Y2 en bas à droite de la zone
     */
    public int getposY2(){
        return this.posY2;
    }
    
    /**
     *
     * @return le nombre de mort restant 
     */
    public int getMort(){
        return this.nbr_dead;
    }
    
    /**
     *
     * @return la largeurX de la zone 
     */
    public int largeurX(){
       return this.posX2-this.posX1;
    }
    
    /**
     *
     * @return la largeurY de la zone 
     */
    public int largeurY(){
       return this.posY2-this.posY1;
    }
    
    /**
     * relie cette evenement à une map 
     * @param m
     */
    @Override
    public void setMap(Map m) {
        this.map = m;
    }
    
    /**
     * positionne en X le coin en haut a gauche 
     * @param n
     */
    public void setposX1(int n ){
         this.posX1 = n;
    }
    
    /**
     * positionne en X le coin en bas a droite 
     * @param n
     */
    public void setposX2(int n ){
         this.posX2=n;
    }
    
    /**
     * positionne en Y le coin en haut a gauche 
     * @param n
     */
    public void setposY1(int n ){
         this.posY1=n;
    }
    
    /**
     * positionne en Y le coin en bas a droite 
     * @param n
     */
    public void setposY2(int n ){
         this.posY2=n;
    }
    
    /**
     * actualise le nombre de mort pour valider l'evenement
     * @param n
     */
    public void setMort(int n ){
         this.nbr_dead=n;
    }
}
