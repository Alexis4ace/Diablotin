package jeu.vue;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import jeu.vue.Map_graph;

public class TaskGame extends Task<Void> {

    private static TaskGame instance;  // Instance unique de TaskGame et doit le rester
    private Map_graph jeu;
    private volatile boolean isRunning;  // variable pour contrôler l'exécution de la tâche
    
    public TaskGame(Map_graph jeu) {
        this.jeu = jeu;
         this.isRunning = true;  // Initialisez la variable de contrôle à true
    }

    public static TaskGame getInstance(Map_graph jeu) {
        if (instance == null) {
            instance = new TaskGame(jeu);
        }
        return instance;
    }

    @Override
    protected Void call() throws Exception {
         int i = 0 ;
        try {
             while (isRunning && !Thread.currentThread().isInterrupted() && jeu.getMap().getLucifer().getPv() > 0) {
           // Alors j'ai mis 120 miliseconde , mais si tu met 20 tu te fait oneshot
           // juste en demarrant le jeu...
            Thread.sleep(100);
            
            // Ici on effectue tout les Taches que vous voulez renouveller 
           // Penser a utiliser un Platform.runLater si ça un conçerne un aspect Graphique sinon
           // Pas besoin 
           
          
           if ( i > 2){ // Petit timer pour pas aller trop vite
               // Aspect Non Graphique donc pas de Platform runlater pour jeu.getMap.action
               // *Edit* Bon apres plusieurs test vous pouvez le mettre/enlever
               // En Platform.runLater si sa bug, parceque y'a de l'affichage graphique
               // via l'inventaire avec Action(), mais ça marche sans sinon *Fin Edit*
              Platform.runLater( () -> jeu.getMap().Action());
            i= 0 ;
           }
           i++; 
            
           // Aspect Graphique, donc Platform.runLater ici c'est obligatoire
           Platform.runLater( () -> jeu.loadMap());
             }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        try {
            throw new GAME_OVER("La partie est terminée !");
        } catch (GAME_OVER e) {
            e.afficherMessageEtQuitter();
        }

        return null;
    }

    @Override
    protected void succeeded() {
        // Sert à mettre à jour l'interface utilisateur lorsque la tâche est terminée, si 
        // genre par exemple on dit que le thread s'arrete a la mort du hero par exemple
        // si ca peut vous être util...
    }
    
    public void stopTask() {
        isRunning = false;  // variable de contrôle sur false pour arrêter la tâche
        Thread.currentThread().interrupt();  // Interrompe le thread si nécessaire
    }

    public void stopApplication() {
        // Arrête le thread en appelant la méthode stopTask() de l'instance TaskGame
        this.stopTask();
        // +
        // On peut mettre ici le code supplémentaire pour terminer l'application
        // par exemple, fermer les fenêtres, enregistrer les données, etc.
    }

    public Map_graph getMapGraphTask() {
        return this.jeu;
    }
}
