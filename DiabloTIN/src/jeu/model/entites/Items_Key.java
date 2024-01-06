/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.model.entites;

import jeu.model.entites.Items;
import javafx.scene.image.Image;
import jeu.model.Map;
import static jeu.model.Map.getInstanceMap;
import static jeu.vue.InfoGameView.getInstanceInfo;

/**
 *
 * @author arthur1haye
 */
public class Items_Key extends Items {

    
    private Image image ;
    private final String name ="key";
    private final String desc ="Open locks";
    
    private int posX = 95 ;
    private int posY = 95 ;
    
    private Map map ;
    
    public Items_Key( int x , int y  ){
       
        this.posX = x ;
        this.posY = y ;
    }
    
    public Items_Key(){
        
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPosX() {
      return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }
    
    @Override
    public Image getImage() {
        return this.image;
    }
    
    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public Boolean USE() {
        if (  getInstanceMap() != null){
        Boolean result = getInstanceMap().Use_Key_For_Door_Around();
        if ( result == false ){ getInstanceInfo().add_message( '\n'+"Mauvais Endroit pour Utiliser votre cle"+'\n'); }
        else {getInstanceInfo().add_message( '\n'+"CLE UTILISER"+'\n'); } 
        return result ;
        }
        else {
            
          return false ;
        }
    }
}
