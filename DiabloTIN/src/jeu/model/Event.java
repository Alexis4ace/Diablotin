/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.entites.Unite;

/**
 *
 * @author Alexis
 */
public abstract class Event {   // pour définir des évenements sur la carte 
    
    private Unite hero ;
    private String name ;
    public abstract void checkEvent();
    public abstract String getName();
    public abstract void setMap(Map m);
}
