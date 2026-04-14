package pendu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class jeuDuPendu {

    public static void main(String[] args) {
        String[] dictionnaire = {
                "INTERFACE",
                "PROGRAMME",
                "ORDINATEUR",
                "CASTOR",
                "ORNITHORYNQUE",
                "SANGLIER",
        };
        int vies = 7;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String mot = dictionnaire[random.nextInt(dictionnaire.length)];
        char[] motcaracteres = mot.toCharArray();
        char[] motdevinette = new char[motcaracteres.length];
        for (int i = 0; i < motcaracteres.length; i++) {
            motdevinette[i] = '_';
        }
        while (vies > 0) {
            System.out.println("\nMot à deviner : " + new String(motdevinette));
            System.out.print("Veuillez entrer une lettre : ");

            String saisie = scanner.nextLine().toUpperCase();
            if (saisie.isEmpty()) continue;

            char c = saisie.charAt(0);

            boolean trouve = false;
            for (int i = 0; i < motcaracteres.length; i++) {

                if (motcaracteres[i] == c) {
                    motdevinette[i] = c;
                    trouve = true;
                }
            }

            if (!trouve) {
                vies--;
                System.out.println("Lettre incorrecte. Vies restantes : " + vies);
            }

            if (!new String(motdevinette).contains("_")) {
                System.out.println("\nBRAVO ! Le mot était : " + mot);
                break;
            }
        }

        if (vies == 0) {
            System.out.println("\nPERDU ! Le mot était : " + mot);
        }
    }
}