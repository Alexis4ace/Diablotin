/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.entites.Unite_Lucifer;
import jeu.model.entites.Unite;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jeu.model.entites.Unite_Lambda;

/**
 *
 * @author ahaye
 */
public class Interaction_Mob {
        // AJout 
      private final Unite_Lambda mob ;
              
      private final Unite_Lucifer hero ;
      private Map map ;
      private int PosX_mob;
      private int PosY_mob;
      private final int speed ;
      private int porte ;
      
      
      
      
      public Interaction_Mob( Unite mob){
          
          this.mob = (Unite_Lambda) mob ;
          this.PosX_mob = mob.getPosX();
          this.PosY_mob = mob.getPosY() ;
          this.speed = mob.getVitesse();
          this.map = mob.getMap();
          this.hero = mob.getMap().getLucifer() ;
          this.porte = mob.getPorte();
          
      }
    public void setPorte(int porte){
        this.porte = porte;
    }  
      
    public void setMap(Map m ) {
        this.map = m ;
    }
  
    
    public void deplacement_Mob(){
     if (this.mob.getAggroMob())
     {   
        if ( this.map.Is_Collision(this.PosX_mob +directionThis_HeroX() ,this.PosY_mob + directionThis_HeroY())   )
        {
            // Entre en collision doit choisir une case , puis verife qu'on nest pas au cac
            if ( !IsCac( this.PosX_mob +directionThis_HeroX() , this.PosY_mob+ directionThis_HeroY(),1 , hero ) ) 
            {
               
            // On en collision certe mais veiller a ne pas rentrer dans le joueur si il se trouve a proximité..
            // Calcule ensuite une case vide ou se deplacer toujours dans la direction voulu
                int [] newpos = FindEmptyCase( this.PosX_mob , this.PosY_mob , 1 );// ATTENTION LA VALEUR 1 DIT TU TE TELEPORTE AUTOUR de TOI de 1CASE
                if ( newpos != null){
                    // Coordonné de la nouvell direciton
                      this.PosX_mob = newpos[0];
                      this.PosY_mob = newpos[1];
                }
           }
        }
        else
        {
            this.PosX_mob+=this.speed * directionThis_HeroX() ;
            this.PosY_mob+=this.speed * directionThis_HeroY();
        }
     }
    }
    
    
    //regarde si le hero est à gauche ou droite ou meme position en X 
    private int directionThis_HeroX(){
        if( hero.getPosX() > this.PosX_mob ){ // si il est à droite 
            return 1;
        }
        else if( hero.getPosX() < this.PosX_mob ) // si il est est à gauche 
            return -1;
        else   // si il est au dessus ou en dessous 
            return 0;
    }
    
    private int directionThis_HeroY(){  // pareil pour Y 
        if( hero.getPosY() > this.PosY_mob  ){
            return 1;
        }
        else if( hero.getPosY() < this.PosY_mob  )
            return -1;
        else
            return 0;
    }
    
   
    public Boolean IsCac( int x , int y , int range, Unite cible ){
   // Reste au cac sans penetrer le hero de force
   // Range ne sert pas comme je l'aurais voulu mais il faut qu'il reste a 1
       for (int i =  (x - range); i <=  (x + range); i++) {
        for (int j =  (y - range); j <=  (y + range); j++) {
               if( this.map.getUnit(x, y) == cible ){
                
                   return true ;
               }
        }
     }
         return false ;
    }  
    
    
    
    
    
// Lors d'un blocage choisi une case vise aleatoire ou il n'y aura pas Collision et la renvoie int[0] = x int[1] = yy
     public  int[] FindEmptyCase( int x , int y , int range  ){
         // Range ne sert pas comme je l'aurais voulu mais il faut qu'il reste a 1
         int[] result = null ;
         List<int[]> cells = new ArrayList<>();
         
        for (int i =  (x - range); i <=  (x + range); i++) {
        for (int j =  (y - range); j <=  (y + range); j++) {
                   if ( (i != x || j != y )) {
                         if (  !this.map.Is_Collision(i, j)) // Collision
                         {
                         cells.add(new int[]{i, j});
                       
                         }
                    }
            }
        }
        // Aleatoire pour rendre L'ia bete un peu 
        if( cells.size() > 0 ) {
            int randomIndex = new Random().nextInt(cells.size()  );
        
       
        if ( randomIndex < 0 ){ randomIndex =1 ;} 
        result = cells.get(randomIndex);
        }
        
        return result ;

    }   
    
    
    public int getNewPosX(){
        return this.PosX_mob ;
    }
    
    public int getNewPosY(){
        return this.PosY_mob ;
    }
    
    /**
     *  Regarde si l'unite peut attaquer notre hero  , l'inverse est possible si on configure la porter de l'unite identique à celle de notre hero 
     * @return vrai si l'unite est a porter et qu'il n'y a pas d'obstacle entre sinon faux  
     */
    public boolean isAPortee(){ // detecter si on peux attaquer la cible ( bonne distance + pas d'obstacle entre eux ) 
        
        double squareX = (double) (this.hero.getPosX() - this.PosX_mob ) * (this.hero.getPosX() - this.PosX_mob ) ; 
        double squareY = (double) (this.hero.getPosY() - this.PosY_mob ) * (this.hero.getPosY() - this.PosY_mob ) ;
        int distance = ( int )sqrt( squareX  + squareY ) ; // calcul d'une distance 
     
        if ( this.porte >= distance ){  // si bonne distance 
            boolean decouvert = true ;   // on peut attaquer pour le moment
            for(int i = 0 ; i < distance ; i++){ // pour chaque case entre les deux perso on regarde si obstacle 
                if( decouvert && (  map.isArbre(this.PosX_mob+directionThis_HeroX(), this.PosY_mob+directionThis_HeroY()) || map.isMur(this.PosX_mob+directionThis_HeroX(), this.PosY_mob+directionThis_HeroY()))  ) // si obstacle 
                    decouvert = false ; // alors faux pour toujours 
            }
                                        // sinon rien donc vrai ( si il n' y a pu eu d'obstacle ) 
            return decouvert; // renvoie la reponse
        } 
            
        else return false;  // si pas la bonne distance alors faux 
    }
    
    
    
    
    
    
}
