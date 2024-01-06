/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu.vue;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import jeu.model.Event;
import jeu.model.entites.Unite;

/**
 *
 * @author arthur1haye
 */
public class InfoGameView extends BorderPane {
    
    
    private static InfoGameView instance ;
    public Label Name = new Label(" BULLE COMBAT : ");
    
    private List<Label> ListMessage = new ArrayList<Label>();   
    
    private VBox Conteneur_Graph = new VBox();
    private VBox Conteneur_InfoGame = new VBox() ;
    
    
    public InfoGameView(){
        super();
        this.instance = this ;
        this.setPadding(new Insets(10,20,10,20));
        
        
       
        this.setMinWidth(250);
        this.setMinHeight(600);
        this.setMaxWidth(250);
        this.setMaxHeight(600);

        
        this.Conteneur_Graph.setSpacing(10);
        this.Conteneur_Graph.getChildren().addAll(this.Name,this.Conteneur_InfoGame);
        
        this.setTop(Conteneur_Graph);
           
        this.Conteneur_InfoGame.setSpacing(7);
        
         BorderStroke border = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
         this.setBorder(new Border(border));
       
        
    }
    
    public static InfoGameView getInstanceInfo() {
        if (instance == null) {
            instance = new InfoGameView();
        }
        return instance;
    }
    
    
    public static Label getafficherMessage(String message) {
        Label messageLabel = new Label(message);
        return messageLabel ;
    }
    
    public void add_message( String message){
        
         Label new_message = new Label();
        new_message.setText(message);
        new_message.setWrapText(true);
        if ( this.ListMessage.size() > 15){
            this.ListMessage.remove(0);
            this.Conteneur_InfoGame.getChildren().remove(0);
             this.ListMessage.add(new_message);
            this.Conteneur_InfoGame.getChildren().add(new_message);
        }
        else {

            this.ListMessage.add(new_message);
            this.Conteneur_InfoGame.getChildren().add(new_message);
            
        }
        
    }
}
