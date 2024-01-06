/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue;

import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author ahaye
 */
public class GAME_OVER  extends Exception{


    public GAME_OVER(String message) {
        super(message);
    }

    public void afficherMessageEtQuitter() {
        // Si ca bug enlever le JOptions
        JOptionPane.showMessageDialog(null, getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}


