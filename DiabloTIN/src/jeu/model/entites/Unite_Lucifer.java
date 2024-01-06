/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import jeu.model.Interaction_Mob;
import jeu.model.Inventaire;
import jeu.model.Map;
import static jeu.vue.InfoGameView.getInstanceInfo;

/**
 *
 * @author Alexis
 */
public class Unite_Lucifer extends Unite_Lambda  {
    //-------------------------ATTRIBUT -----------------------------------------
    private Image image = new Image(getClass().getResource("/image/Objet/Lucifer/bas.png").toString()); 
    private final String name ="Lucifer";
    
    private int exp = 1;
    private final int expMax = 100 ;
    private int levels = 1;
    
    // pvMax doit être toujours Egale a pv
    private int pvMax = 800;
    private int pv = 800 ;
    // Pareil pour le Mana
    private int Mana = 100 ;
    private int ManaMax = 100;
    
    private int porte = 1 ;
    private int vitesse = 1 ;
    private int dammage = 80 ;
    private Map map ;
    
     private int posX = 70 ;
    private int posY = 70 ;
    
    private IntegerProperty pvProperty = new SimpleIntegerProperty(this.pv);
    private IntegerProperty  porteProperty = new SimpleIntegerProperty(this.porte);
    private IntegerProperty  dammageProperty = new SimpleIntegerProperty(this.dammage);
    private IntegerProperty  ManaProperty = new SimpleIntegerProperty(this.Mana);
    private IntegerProperty  LevelProperty = new SimpleIntegerProperty(this.levels);
    private IntegerProperty  ExperienceProperty = new SimpleIntegerProperty(this.exp);
    private IntegerProperty  PosXProperty = new SimpleIntegerProperty(this.posX);
    private IntegerProperty  PosYProperty = new SimpleIntegerProperty(this.posY);    
    
    private List<Items> list1 = new ArrayList<Items>();
    Inventaire inv_visuel = new Inventaire();
    
   
    
    private int direction_actuelle = 5 ;
    
    //-------------------------CONSTRUCTEUR -----------------------------------------
    public Unite_Lucifer(){
        
    }
    
   public Unite_Lucifer(int x , int y ,Map map){
        posX = x ; 
        posY = y ;
        this.map = map;
    }
    
    //-------------------------GET -----------------------------------------
    
    @Override
    public String getName(){
        return this.name;
    }
    
    public int getLevel(){
        return this.levels;
    }
    
    public int getExp(){
        return this.exp;
    }
    
    @Override
    public int getVitesse() {
        return this.vitesse;
    }
    
    @Override
    public Map getMap() {
        
        return this.map;
    }
    
    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }
    
    public Image getImage(){
        return this.image ;
    }   
    
    public int getDirection(){
        return this.direction_actuelle;
    }
    
    public List<Items> getListItem(){
        return this.list1;
    }
    
    public Inventaire getInventaireVisuel(){
        return this.inv_visuel;
    }   
    
    
    @Override
    public int getPv(){
        return this.pv;
    }
    
    public int getMana(){
        return this.Mana;
    }
    
    public int getExpMax(){
        return this.expMax;
    }
    
   
    
    @Override
    public void setPv( int newpv){
        if ( newpv > 0){
        this.pv = newpv ;
        this.pvProperty.set(pv); // Mettre à jour la propriété pvProperty 
        }
    }
    
    @Override
    public void getDammage(int val) {
        if ( this.pv-val >= 0){
        this.pv -= val;
         this.pvProperty.set(pv); // Mettre à jour la propriété pvProperty
        }
    }
    
    @Override
    public int getDammage() {
        return this.dammage;
    }
    
    public int getPvMax(){
        return this.pvMax;
    }
    
    public void setPvMax( int new_PvMax){
        this.pvMax = new_PvMax ;
    }
    
    
    
    //-------------------------SET -----------------------------------------
    @Override
    public void setDammage( int newdamm){
        this.dammage = newdamm ;
        this.dammageProperty.set(newdamm);
    }
    
    @Override
    public void setVitesse( int newVit){
        this.vitesse = newVit ;        
    }
    
    public void setMana( int newmana){
        if ( newmana < this.ManaMax && newmana >= 0){
        this.Mana = newmana ;  
        this.ManaProperty.set(Mana);}
    }
    @Override
    public void setPorte( int Porte){
        this.porte = Porte ;  
        this.porteProperty.set(this.porte);
    }
    
    public void setExp( int experience){
        this.exp = experience ;
        this.ExperienceProperty.set(this.exp);
    }
    
    @Override
    public void setMap(Map m){
        this.map = m ;
    }
    
    public void setLevel(int niveau){
        this.levels = niveau ;
        this.LevelProperty.set(this.levels);
    }
    
    public void setInvVisuel(Inventaire inv ){
        this.inv_visuel = inv ;    
    }
    
    public void setDirection(int direction){
        this.direction_actuelle = direction ;
    }
  
    public void setPos(int x , int y ){
        this.posX = x ;
        this.posY = y ;
        this.PosXProperty.set(x);
        this.PosYProperty.set(y);
    }
    
    public void setImage(Image img){
        this.image = img ;
    }
    
    public int equipement(){
        int n=0;
        for(int i = 0 ; i< this.list1.size() ; i++){
            if(list1.get(i).getName() == "epee")
                n+= 50;
        }
        return n;
    }

    
    
    //-------------------------METHODE OVERRIDE-----------------------------------------
    @Override
    public void deplacement() {
        //rien à faire 
    }
    @Override
    public boolean enVie(){
        if ( this.pv <= 0 ){ 
            return false;
        }
        return true;
    }
    
    @Override
    public void attaque(Unite ennemy) { // plus tard 
        ennemy.getDammage(this.dammage);
    }
    
    @Override
    public int getPorte() {
        return this.porte;
    }
    
    @Override
    public void Action() {
        // but ici pour chaque adversaire , regarder si il est a porter si oui alors l'attaquer 
        List<Unite> Listennemy = this.map.getEnnemy(); 
        Interaction_Mob interaction ;
 
        int maxdmg = this.dammage ;

        for( Unite ennemy : Listennemy){ // pour chaque ennemy de la liste 
            Unite_Lambda Temp =(Unite_Lambda) ennemy ;
            if ( TuLaChercherIlsArriventChezToiHugo(this.posX,this.posY, ennemy.getPosX(), ennemy.getPosY() ,Temp.getZoneAggroMob()) ){
                Temp.setAggroMob(Boolean.TRUE);
            }
            interaction = ennemy.getInteraction();  // on recupere l'interaction du mob x
            interaction.setPorte(this.porte);   // on lui met la porter de l'hero
            if( interaction.isAPortee() ){   // on regarde si le mob avec la porte de l hero peut frapper notre hero 
                ennemy.getDammage(this.dammage+equipement());  // si oui alors notre hero a donc la porte aussi donc notre hero frappe
                getInstanceInfo().add_message('\n'+"Vous Attaquer : ");
                getInstanceInfo().add_message( ennemy.getName().toUpperCase() + " : " + (maxdmg+equipement()) + " degat infliger");
                getInstanceInfo().add_message(ennemy.getName().toUpperCase() + " PV Restant : " + ennemy.getPv()+ '\n');
                
            }
            interaction.setPorte(ennemy.getPorte()); // on remet la porte du mob x pour pas de dinguerie plus tard 
            
        }                           
      
    }
    //-------------------------METHODE BONUS-----------------------------------------
    
    public void ajouteritem(Items item){
        if(this.list1.size()<=5){
             getInstanceInfo().add_message( '\n'+  item.getName().toUpperCase() +" AJOUTER INVENTAIRE"+ '\n' );
            this.list1.add(item);
            this.inv_visuel.load();    
        }
        else{
            getInstanceInfo().add_message("INVENTAIRE PLEIN");
        }
    }
    
    
    // ---------------------------INTEGER PROPERTY-------------------------------------- ///
    public IntegerProperty getpvProperty() {
        return this.pvProperty ;
    }
    
    public IntegerProperty getporteProperty(){
        return this.porteProperty;
    }
    public IntegerProperty getDamageProperty(){
        return this.dammageProperty;
    }
    
    public IntegerProperty getManaProperty(){
        return this.ManaProperty;
    }
    
    public IntegerProperty getLevelsProperty(){
        return this.LevelProperty ;
    }
    
    public IntegerProperty getEXProperty(){
        return this.ExperienceProperty;
    }

    public IntegerProperty getPosXProperty(){
        return this.PosXProperty;
    }
    
    public IntegerProperty getPosYProperty(){
        return this.PosYProperty;
    }
     // ---------------------------   -------------------------------------- ///
    public Boolean TuLaChercherIlsArriventChezToiHugo(int x_hero, int y_hero, int x_mob, int y_mob , int DistanceAggro){
        int deltaX = Math.abs(x_mob - x_hero);
        int deltaY = Math.abs(y_mob - y_hero);
        int nombreCases = Math.max(deltaX, deltaY);

        if ( nombreCases <= DistanceAggro){
            return true;
        }
        else{
            return false ;
        }
    }
 }