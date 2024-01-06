package jeu.model.entites;


import javafx.scene.image.Image;
import jeu.model.Interaction_Mob;
import jeu.model.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexis
 */
public abstract class Unite extends Entite {
    private Image image ;
    private String name ;
    
    private int pv;
    private int posX ;
    private int posY ;
    private int dammage;
    private int vitesse ;
    private int porte;
    
    private Map map ;
    
    

    public abstract String getName();
    public abstract int getPv();
    public abstract int getPosX();
    public abstract int getPosY();
    public abstract int getDammage();
    public abstract int getVitesse();
    public abstract int getPorte();
    public abstract int getFatigue();
    public abstract Interaction_Mob getInteraction();
    public abstract Map getMap();
    public abstract int getExp();
    
    public abstract void setPv(int val);
    public abstract void setFatigue(int val);
    public abstract void setMap(Map m);
   
    public abstract void setPosX(int x);
    public abstract void setPosY(int y);
    public abstract void setDammage(int dmg);
    public abstract void setVitesse(int vitesse);
    public abstract void setPorte(int porte);
    public abstract void setName(String name );
    public abstract void setInteraction(Unite e);
    public abstract void setExpRapporter(int experience);
    
    public abstract void Action();
    public abstract boolean enVie();
    public abstract void getDammage(int val);
    public abstract void deplacement();
    public abstract void attaque(Unite ennemy);
        
    
    
}
