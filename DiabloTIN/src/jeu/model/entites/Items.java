/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.model.entites;

import jeu.model.entites.Entite;
import javafx.scene.image.Image;

/**
 *
 * @author arthur1haye
 */
public abstract class Items extends Entite {
    private Image image ;
    private String name ;
    private String desc;
    
    private int posX ;
    private int posY ;
    
    
    public abstract String getName();
    public abstract int getPosX();
    public abstract int getPosY();
    public abstract Image getImage();
    public abstract String getDesc();
    public abstract Boolean USE();
    
    
    
    //  public abstract Use();
    // public abstract Stock() ;
    // Penser a faire l'inventaire
    
    
}
