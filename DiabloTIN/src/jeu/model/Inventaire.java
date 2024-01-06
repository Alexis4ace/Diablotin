/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import jeu.model.entites.Items;
import jeu.model.entites.Items_Key;
import jeu.model.entites.Unite_Lucifer;


/**
 *
 * @author hrhug , BIEN JOUE HUGO MAIS FAUT SEPARER LE GRAPHIQUE DES DONNNEE
 */

public class Inventaire extends BorderPane {
    
    private List<Items> list1 = new ArrayList<Items>();
    int n=0;
    int space=0;
    
    private Label iteminv =new Label("    ITEMS : ");
    private Label max = new Label("Stockage: "+(n)+"/5");
    private Images tab_img ;
    
    private VBox Conteneur_Graph = new VBox();
    private VBox Conteneur_Inventaire = new VBox() ;
    
    
    public Inventaire(){
        
    }
    
    // Création des objets à afficher dans l'inventaire
    public Inventaire(List<Items> list1 , Images img) {
       
        tab_img = img ;
       
        iteminv.setStyle("-fx-font-size: 28px;");
        iteminv.setAlignment(Pos.CENTER);
        
        
        this.setTop(this.Conteneur_Graph);
        BorderPane.setAlignment(this.Conteneur_Inventaire, Pos.CENTER);
        
        // J'ai placer le max dans ce Conteneur et il descend en fonction du nombre d'items mais
        // on peut le placer directement a la fin avec this.setBottom (max)
        this.Conteneur_Graph.getChildren().addAll(iteminv, Conteneur_Inventaire, max);
        
        max.setStyle("-fx-font-size: 15px;");
        max.setLayoutX(5);
        
        this.list1=list1;
        
        
        this.load();  // deplacement de la boucle for en bas 
       
     
        this.setWidth(100);
        BorderStroke border = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        this.setBorder(new Border(border));
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMinWidth(320);
        
    }
    
    public void load(){
        
       
        this.Conteneur_Inventaire.getChildren().clear();
        for (int i = 0; i < list1.size(); i++) {
            n=i;
            space=(i*55)+50;
            HBox hb = new HBox();
           
            Button Drop = new Button("Drop");
            Drop.setOnAction(e -> {
                this.Conteneur_Inventaire.getChildren().remove(hb);
                list1.remove(list1.get(n));
                n=n-1;
                space=space-55;
                max.setText("Stockage: "+(n+1)+"/5");
                
            });
            Button Use = new Button("Use");
             Use.setOnAction(a -> {
                // Verif si l'items a été utiliser correctement ou non
                Boolean Use_Or_Not = list1.get(n).USE();
                if ( Use_Or_Not == true){
                    // si oui on le supprime de l'inventaire
                    this.Conteneur_Inventaire.getChildren().remove(hb);
                    list1.remove(list1.get(n));
                    n=n-1;
                    space=space-55;
                    max.setText("Stockage: "+(n+1)+"/5");
                }
                
            });
            
            
            Label lab =new Label(" "+list1.get(i).getName());
            Label desc = new Label("      "+list1.get(i).getDesc());
            
            
            ImageView imageView = new ImageView(   tab_img.getImage(  list1.get(i).getName() )   );
            this.Conteneur_Inventaire.getChildren().add(hb);
            hb.getChildren().addAll(lab,imageView,desc,Use,Drop);
            
            
           lab.setTextAlignment(TextAlignment.LEFT);
           lab.setStyle("-fx-font-size: 20px;");
           desc.setTextAlignment(TextAlignment.RIGHT);
           desc.setStyle("-fx-font-size: 15px;");
           
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setPrefWidth(300);
            hb.setSpacing(20);
            hb.setLayoutX(5);
            hb.setLayoutY(space);
            BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
            hb.setBorder(new Border(borderStroke));
            max.setText("Capacity: "+(n+1)+"/5");
            max.setLayoutY(space+60);
           
            
        }
    }
    
    public List<Items> getListItem(){
        return this.list1;
    }
    
    public void ajouteritem(Items item){
        this.list1.add(item);
        this.load();
        }
        
        
    }


    

    
    

