/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;


import javafx.scene.Scene;
import javafx.stage.Stage;

public class Interface extends Stage {

    public Interface() {
        
        
        Action_Menu root = new Action_Menu();
        
        Scene scene = new Scene(root, 1200, 1000);
        
        setTitle("Diablotin");
        setScene(scene);
        show();
    }
}
