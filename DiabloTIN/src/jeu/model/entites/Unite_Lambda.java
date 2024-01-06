/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import jeu.model.Interaction_Mob;
import jeu.model.Map;
import static jeu.vue.InfoGameView.getInstanceInfo;

/**
 *
 * @author ahaye
 */
public abstract class Unite_Lambda extends Unite {
    
    private String name = "";
    
    private int EXP_RAPPORTER = 2 ;
    
    private int posX ;
    private int posY ; 
    private int pv = 100 ;
    private int dammage = 10 ;
    private int speed = 1 ; 
    private int porte = 1;
    private int fatigue = 1;
    
    private Boolean EtatAgressif = false ;
    private int ZoneAgro = 1 ;
    

    private Map map ;
    private Interaction_Mob bouge_mob  ;
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosX() {
        return posX;
    }
    
    @Override
    public int getVitesse() {
        return this.speed;
    }
    
    @Override
    public int getPosY() {
        return posY;
    }
   
    @Override
    public void deplacement() {
        
        this.bouge_mob.deplacement_Mob();
        this.posX = this.bouge_mob.getNewPosX();
        this.posY = this.bouge_mob.getNewPosY();
        
    }
    
    @Override
    public void Action(){
        
        if(this.bouge_mob.isAPortee() && fatigue==1){
            this.attaque(this.map.getLucifer());
            fatigue = 1 - fatigue ;
        }  
        else if(fatigue==1)
            this.deplacement();
        else 
            fatigue = 1- fatigue ; // permet de nous laisser un peu d'avance apres un attaque 
        
    }
    @Override
    public void attaque(Unite ennemy) {
        if(ennemy==null)
            System.out.print("null");
        ennemy.getDammage(this.dammage);
        
        getInstanceInfo().add_message('\n'+ this.name + " Attaque " + " : " + this.dammage + " dégat subis");
        getInstanceInfo().add_message("LUCIFER PV Restant : "+ ennemy.getPv() + '\n' );
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public int getPorte() {
        return this.porte;
    }

    @Override
    public int getPv() {
        return this.pv;
    }

    @Override
    public int getDammage() {
        return this.dammage;
    }
    
    @Override
    public void getDammage(int val) {
        this.pv = this.pv - val ;
    }
    @Override
    public boolean enVie(){
        return this.pv > 0;
    }
    
    @Override
    public Interaction_Mob getInteraction(){
        return this.bouge_mob;
    }

    @Override
    public int getFatigue() {
        return  this.fatigue ;
    }

    @Override
    public void setPv(int val) {
        this.pv = val ;
    }

    @Override
    public void setFatigue(int val) {
        this.fatigue = val ;
    }

    @Override
    public void setMap(Map m) {
        this.map = m ;
        this.bouge_mob = new Interaction_Mob(this);
        
    }
    
    @Override
    public int getExp() {
        return  this.EXP_RAPPORTER ;
    }

    @Override
    public void setPosX(int x) {
        this.posX = x ;
    }

    @Override
    public void setPosY(int y) {
        this.posY = y ;
    }

    @Override
    public void setDammage(int dmg) {
        this.dammage = dmg ;
    }

    @Override
    public void setVitesse(int vitesse) {
        this.speed = vitesse ;
    }

    @Override
    public void setPorte(int porte) {
        this.porte = porte;
        
    }

    @Override
    public void setName(String name) {
        this.name = name ;
    }

    @Override
    public void setInteraction(Unite e) {
        this.bouge_mob = new Interaction_Mob( e );
    }

    @Override
    public void setExpRapporter(int experience) {
        this.EXP_RAPPORTER = experience ;
    }
    
    public Boolean getAggroMob(){
        return this.EtatAgressif;
    }
    
    public void setAggroMob(Boolean new_etat){
        this.EtatAgressif = new_etat;
    }
    
     public int getZoneAggroMob(){
        return this.ZoneAgro;
    }
    
    public void setZoneAggroMob(int new_zone){
        this.ZoneAgro = new_zone ;
    }

}
