package jeu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//javac Base.java // pour la compilation
//java -classpath ".:sqlite-jdbc-3.41.2.1.jar" Base // pour run le programme 

public class DataBase {  //https://terminalroot.com/how-to-connect-to-sqlite-with-java/ // le tuto que j'ai suivis 
	
    private Connection connection = null;
    private Statement statement = null;
    
    private boolean statut ;
    
    private int ID_BIBLE = 0 ;
    private int ID_SAUVEGARDE = 0 ;
    
    public DataBase(){
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:base.db"); //conection a la base de donnée
            
            statement = connection.createStatement();  // pour pouvoir effectuer des requetes à la bd
            statement.setQueryTimeout(30); // attendre un peu pour la connection
            statut = true ;
        
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            statut = false ;
            
        }
        
    
    }
    
    public boolean getSatut(){
        return this.statut;
    }
    
   //-----------------------------BIBLE------------------------------------------------
    /**
     * CREER LA TABLE ET SUPPRIME AVANT SI ELLE EXSITE , LA TABLE BIBLE  DE  4 COLONNES :                                                                                 
     *  ID:INT NAME:STRING TYPE:STRING DESCRIPTION:STRING                                                                              
     */
    public void createTableBible() {
        try {
            deleteTableBible();
            statement.executeUpdate("CREATE TABLE Bible (id INTEGER, name STRING ,  type STRING , description STRING )");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * SUPPRIME LA TABLE BIBLE si ELLE EXISTE
     */
    public void deleteTableBible(){
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS Bible");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  INSERT UNE LIGNE DANS LA TABLE BIBLE avec un ID incrementer automatiquement , puis avec name pour le nom , type pour WEAPONS , DECOR , MOB ,HERO ... 
     * et description ça description ( uniquement du string ) 
     * @param name
     * @param type
     * @param description
     */
    public void insertBible(String name , String type , String description ) {
        try {
            statement.executeUpdate("INSERT INTO Bible VALUES(" +ID_BIBLE+ ", '"+name+"', '"+type+"', '"+description+"')");
            ID_BIBLE++;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * recupere toutes les lignes de la table bible et le met dans une matrice de string comme ce si :
     * PREMIERE DIMENSION designe la ligne 
     * DEUXIEME DIMENSION designe la colonne de 0 à 3 tel que ID NAME TYPE DESCRIPTION
     * @return String[][]
     */
    public String[][] getDataBible() {
        
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT * FROM Bible ");
        
        int taille = 0 ;
        while(rs.next()){	
           taille++;
        }
        String[][] matrice_data = new String[taille][4]; // ID   NAME    TYPE    DESCRIPTION
        taille = 0 ;
        while(rs.next()) {
            
            matrice_data[taille][0] = rs.getInt("id")+"" ;
            matrice_data[taille][1] = rs.getString("name");
            matrice_data[taille][2] = rs.getString("type");
            matrice_data[taille][3] = rs.getString("description");
            taille++;
	}
        return matrice_data;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    //-------------------------------SAUVEGARDE----------------------------------------
    /**
     * CREER LA TABLE ET SUPPRIME AVANT SI ELLE EXSITE , LA TABLE Sauvegarde  DE  4 COLONNES :                                                                                 
     *  ID:INT NAME:STRING taille:STRING                                                                              
     */
    public void createTableSauvegarde() {
        try {
            deleteTableBible();
            statement.executeUpdate("CREATE TABLE Sauvegarde (id INTEGER, name STRING ,  taille STRING  )");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     /**
     * SUPPRIME LA TABLE Sauvegarde si ELLE EXISTE
     */
    public void deleteTableSauvegarde(){
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS Sauvegarde");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Insert dans LA TABLE Sauvegarde si ELLE EXISTE 
     * @param name
     * @param taille
     */
    public void insertSauvegarde(String name , String taille  ) {
        try {
            statement.executeUpdate("INSERT INTO Sauvegarde VALUES(" +ID_SAUVEGARDE+ ", '"+name+"', '"+taille+"' )");
            ID_SAUVEGARDE++;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * recupere toutes les lignes de la table Sauvegarde et le met dans une matrice de string comme ce si :
     * PREMIERE DIMENSION designe la ligne 
     * DEUXIEME DIMENSION designe la colonne de 0 à 2 tel que ID NAME TAILLE
     * @return String[][]
     */
    public String[][] getDataSauvegarde() {
        
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT * FROM Sauvegarde ");
        
            int taille = 0 ;
            while(rs.next()){	
               taille++;    // ici on cherche à connaitre la taille de la base de donnée
            }

            String[][] matrice_data = new String[taille][3]; // ID   NAME    TAILLE
            
            taille = 0 ;
            while(rs.next()) {

                matrice_data[taille][0] = rs.getInt("id")+"" ;
                matrice_data[taille][1] = rs.getString("name");
                matrice_data[taille][2] = rs.getString("taille");
                taille++;
            }
            return matrice_data;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }
    
    public static void main(String[] args) {
     
          
    }
}
