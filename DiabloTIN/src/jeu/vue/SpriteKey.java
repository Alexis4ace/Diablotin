
package jeu.vue;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author arthur1haye
 */
public class SpriteKey extends Task<Void> {

    private static SpriteKey instance ; // doit rester unique
    
    private Image[] images = new Image[12];
    private ImageView sprite = new ImageView();
    private int currentIndex = 0;
    
    private Map_graph jeu;
    private volatile boolean isRunning;  // variable pour contrôler l'exécution de la tâche
    
    
    public SpriteKey(Map_graph jeu) {
       
        this.jeu = jeu ;
        InitTab();
        sprite.setImage(images[currentIndex]);
    }

    
    public static SpriteKey getInstanceKey(Map_graph jeu) {
        if (instance == null) {
            instance = new SpriteKey(jeu);
        }
        return instance;
    }
    
    @Override
    protected Void call() throws Exception {
        try {
            while (!isCancelled()) {
                Thread.sleep(70);

                currentIndex = (currentIndex + 1) % images.length; // Changer l'index de l'image actuelle
                Image nextImage = images[currentIndex];

                Platform.runLater(() -> updateImage(nextImage)); // Mettre à jour l'image

            }
        } catch (InterruptedException e) {
            // La tâche a été interrompue, sortir de la boucle
        }

        return null;
    }

    public void InitTab() {
        for (int i = 1; i <= 12; i++) {
            this.images[i - 1] = new Image(getClass().getResource("/image/Objet/Key/key_" + i + ".png").toString());
        }
    }

    public ImageView getSpriteKey() {
         ImageView spriteKey = new ImageView(sprite.getImage());
        
        return spriteKey ;
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
    
    
    public void setIntance( SpriteKey key){
        this.instance = key ;
    }
}
