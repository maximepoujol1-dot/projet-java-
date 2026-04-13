package plusOuMoins;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Scanner;  // Import the Scanner class



public class plusOuMoins {
     static void main(String[] args) {
         while (true) {
             Random generateur = new Random();
             int nombreRDM = generateur.nextInt(101);
             Scanner lecteur = new Scanner(System.in);

             while (true) {

                 System.out.print("Entre ton nombre : ");
                 int inputUser = lecteur.nextInt();

                 if (inputUser == nombreRDM) {
                     System.out.println("win");
                     break;
                 } else if (inputUser <= nombreRDM) {
                     System.out.println("plus grand");
                 } else {
                     System.out.println("plus petit");
                 }
             }
             Scanner continuer = new Scanner(System.in);
             System.out.print("continué : ");
             String ouiOuNon = continuer.nextLine();
             if (ouiOuNon == "non"){
                 break;
             }
         }
    }
}