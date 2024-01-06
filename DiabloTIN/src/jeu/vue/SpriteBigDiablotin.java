/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jeu.model.entites.Unite_Big_Diablotin;

/**
 *
 * @author ahaye
 */
public class SpriteBigDiablotin extends Task<Void> {
    
    
    private static SpriteBigDiablotin instance ; 
    
    // Separer en plusieurs partie
    private Image[] images_bas = new Image[3];
    private Image[] images_haut = new Image[3];
    private Image[] images_gauche = new Image[3];
    private Image[] images_droite = new Image[3];
    
    
    
    
    private ImageView sprite = new ImageView();
    private int currentIndex = 0;
    
    private Map_graph jeu;
    private volatile boolean isRunning;  // variable pour contrôler l'exécution de la tâche
    
    private Unite_Big_Diablotin diablo ;
    
     public SpriteBigDiablotin(Map_graph jeu) {
        this.jeu = jeu ;
        this.InitTab();
        sprite.setImage(images_gauche[2]);
    }

     public void InitTab() {
        for (int i = 1; i <= 3; i++) {
            this.images_bas[i - 1] = new Image(getClass().getResource("/image/big_diablotin/bas_" + i + ".png").toString());
            this.images_haut[i - 1] = new Image(getClass().getResource("/image/big_diablotin/haut_" + i + ".png").toString());
            this.images_gauche[i - 1] = new Image(getClass().getResource("/image/big_diablotin/gauche_" + i + ".png").toString());
            this.images_droite[i - 1] = new Image(getClass().getResource("/image/big_diablotin/droite_" + i + ".png").toString());
        }
    }

    public static SpriteBigDiablotin getInstanceBigDiablotin(Map_graph jeu) {
        if (instance == null) {
            instance = new SpriteBigDiablotin(jeu);
        }
        return instance;
    }
     
    @Override
    protected Void call() throws Exception {
        Image[] tab = images_bas ;
         while (!isCancelled()) {
       
           
               
                
                currentIndex = (currentIndex +1) % tab.length; // Changer l'index de l'image actuelle
               
                Platform.runLater(new Runnable() {
                @Override
                public void run() {
                if ( diablo != null && diablo.getInteraction().getNewPosX() != -1 && diablo.getInteraction().getNewPosY() != -1 ){
                     Image[]  tab = images_bas ;
                     currentIndex = (currentIndex +1) % tab.length; // Changer l'index de l'image actuelle
                }
                }
                 });
                 
                Image nextImage = tab[currentIndex];
                
                // Mettre à jour l'image
        
        try {
             Thread.sleep(70);
             Platform.runLater(() -> updateImage(nextImage)); 
        } catch (InterruptedException e) {
            // La tâche a été interrompue, sortir de la boucle
        }
         }
        return null;
    }

    
     public ImageView getSpriteBigDiablotin() {
         ImageView spriteBigDiablotin = new ImageView(sprite.getImage());
        
        return spriteBigDiablotin ;
    }
    private void updateImage(Image image) {
        this.sprite.setImage(image);
    }

    @Override
    protected void cancelled() {
        cleanup();
    }
    



    private void cleanup() {
        // Effectuer le nettoyage nécessaire ici
    }
     
    public void setBigDiablotin(Unite_Big_Diablotin diablo){
        this.diablo = diablo ;
    }
    
}
