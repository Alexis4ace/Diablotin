/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author ahaye
 */
public class ServiceGame extends Service<Void> {

   private TaskGame Taskjeu ;
  private SpriteKey key ;
  private  static List<SpriteMiniDiablotin> Allmini_diablotin =  new ArrayList<SpriteMiniDiablotin>();
  
  // ALL MOB IN GAME
  private SpriteMiniDiablotin mini_diablotin ;
  private SpriteRedDiablotin red_diablotin ;
  private SpriteBigDiablotin big_diablotin ;
  private SpriteKingDiablotin king_diablotin ;
  
  private Map_graph jeu ; 
  private static  List<SpriteKey> AllKey =  new ArrayList<SpriteKey>();
   
   public ServiceGame(Map_graph jeu){
       
      this.jeu = jeu ;
       this.Taskjeu = TaskGame.getInstance(jeu) ;
       // MOB
      this.key = SpriteKey.getInstanceKey(jeu) ;
      this.mini_diablotin = SpriteMiniDiablotin.getInstanceMiniDiablotin(jeu);
      this.red_diablotin = SpriteRedDiablotin.getInstanceRedDiablotin(jeu);
      this.big_diablotin =SpriteBigDiablotin.getInstanceBigDiablotin(jeu);
      this.king_diablotin =SpriteKingDiablotin.getInstanceKingDiablotin(jeu);
   }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Exécute les deux tâches en parallèle
                Thread thread1 = new Thread(Taskjeu);
                
                // Thread ALL Mob
                Thread thread2 = new Thread(key);
                Thread thread3  = new Thread(mini_diablotin);
                Thread thread4  = new Thread(red_diablotin);
                Thread thread5  = new Thread(big_diablotin);
                Thread thread6  = new Thread(king_diablotin);
                
                thread1.setDaemon(true); // Définit le thread en tant que daemon
                thread2.setDaemon(true); // Définit le thread en tant que daemon
                thread3.setDaemon(true);
                thread4.setDaemon(true);
                thread5.setDaemon(true);
                thread6.setDaemon(true);
                
                thread6.start();
                thread5.start();
                thread4.start();
                thread3.start();
                thread2.start(); 
                thread1.start();
               
               
                try {
                    thread6.join();
                    thread5.join();
                    thread4.join();
                    thread3.join();
                    thread1.join();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                
                return null;
            }
        };
    }
    
    
    public static void  ajouteAllMiniDiablotin( SpriteMiniDiablotin diablo){
        
       Allmini_diablotin.add(diablo);
    }
    
    public static void  ajouteAllKey( SpriteKey key){
        
       AllKey.add(key);
    }
}