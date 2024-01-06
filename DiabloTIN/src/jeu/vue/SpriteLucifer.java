/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.vue;

import javafx.scene.image.Image;
import jeu.model.entites.Unite_Lucifer;

/**
 *
 * @author arthur1haye
 */
public class SpriteLucifer {
    
    // Sprite Haut 1 à 4
     private final Image haut = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/haut.png").toString()); 
      private final Image haut_A = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/haut_A.png").toString()); 
      private final Image haut_B = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/haut_B.png").toString()); 
      private final Image haut_C = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/haut_C.png").toString()); 
    // Sprite Bas 5 à 8
       private final Image bas = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/bas.png").toString()); 
      private final Image bas_A = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/bas_A.png").toString()); 
      private final Image bas_B= new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/bas_B.png").toString()); 
      private final Image bas_C = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/bas_C.png").toString()); 
      // Sprite Droite 9 à 12
       private final Image droite = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/droite.png").toString()); 
      private final Image droite_A = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/droite_A.png").toString()); 
      private final Image droite_B = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/droite_B.png").toString()); 
      private final Image droite_C = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/droite_C.png").toString()); 
      //Sprite Gauche 12 à 16
       private final Image gauche = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/gauche.png").toString()); 
      private final Image gauche_A = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/gauche_A.png").toString()); 
      private final Image gauche_B = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/gauche_B.png").toString()); 
      private final Image gauche_C = new javafx.scene.image.Image(getClass().getResource("/image/Objet/Lucifer/gauche_C.png").toString()); 
      
      
      private Image Image_Actuelle ;
      private Unite_Lucifer Lucifer ;
      private int Direction_Actuelle ;
      
      
      public SpriteLucifer(Unite_Lucifer lucifer){
          this.Lucifer = lucifer ;
          this.Direction_Actuelle = this.Lucifer.getDirection();
          this.Image_Actuelle = this.Lucifer.getImage();
      }
      
      
      public Image BonneDirectionSprite( String direction, int direction_actuelle, Unite_Lucifer lucif){
          
          
          switch(direction){
            case "haut" : 
               if ( direction_actuelle == 1) { lucif.setDirection(2);return haut_A ; }
               if ( direction_actuelle == 2) { lucif.setDirection(3);return haut_B ; }
               if ( direction_actuelle == 3) { lucif.setDirection(4);return haut_C ; }
               if ( direction_actuelle == 4)  { lucif.setDirection(1);return haut ; }
               else  { lucif.setDirection(1);return haut ; }
               
            case "bas" : 
                if ( direction_actuelle == 5) { lucif.setDirection(6); return bas_A ; }
                if ( direction_actuelle == 6) { lucif.setDirection(7); return bas_B ; }
                if ( direction_actuelle == 7) { lucif.setDirection(8); return bas_C ; }
                if ( direction_actuelle == 8) { lucif.setDirection(5); return bas ; }
                else { lucif.setDirection(5); return bas ; }
                
            case "droite" : case "haut_droite" : case "bas_droite" : 
               if ( direction_actuelle == 9) { lucif.setDirection(10); return droite_A ; }
                if ( direction_actuelle == 10)  { lucif.setDirection(11); return droite_B ; }
                if ( direction_actuelle == 11)  { lucif.setDirection(12); return droite_C ; }
                if ( direction_actuelle == 12)  { lucif.setDirection(9); return droite ; } 
                else  { lucif.setDirection(9); return droite ; }
            case "gauche" : case "haut_gauche" : case "bas_gauche": 
                if ( direction_actuelle == 13) { lucif.setDirection(14); return gauche_A ; }
                if ( direction_actuelle == 14)  { lucif.setDirection(15); return gauche_B ; }
                if ( direction_actuelle == 15)  { lucif.setDirection(16); return gauche_C ; }
                if ( direction_actuelle == 16)  { lucif.setDirection(13); return gauche ; } 
                else  { lucif.setDirection(13); return gauche ; }
              
            default : 
                
                return bas ; 
        }
      }
      
      
     
      
     

}

