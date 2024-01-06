/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model.entites;

import jeu.model.entites.Entite;
import javafx.scene.image.Image;

/**
 *
 * @author ahaye
 */
public abstract class Decor extends Entite {
     private Image image ;
    private String name ;
    private int posX ;
    private int posY ;
    
    

    public abstract String getName();
    public abstract int getPosX();
    public abstract int getPosY();
}
