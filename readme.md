#  Java Multi-Games Collection

Une suite de jeux classiques développée en **JavaFX** (BlackJack, Pendu, Memory, etc.), regroupée sous une interface unique et fluide.

---

##  Installation de l'environnement

Pour exécuter ce projet, tu as besoin de configurer **IntelliJ IDEA** avec le SDK JavaFX.

### 1. Téléchargement
* **IDE** : [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)
* **JDK** : [Eclipse Adoptium (JDK 21 ou +)](https://adoptium.net/)
* **JavaFX** : [SDK JavaFX sur openjfx.io](https://openjfx.io/) (Télécharge la version correspondant à ton OS).

### 2. Configuration dans IntelliJ
1.  Ouvre le projet.
2.  Va dans `File` > `Project Structure` > `Libraries`.
3.  Clique sur le `+`, sélectionne **Java**, et cherche le dossier `lib` à l'intérieur du SDK JavaFX que tu as téléchargé.
4.  Applique les changements.

---

##  Comment lancer le jeu ?

Tout se passe depuis la classe principale : **`MainView.java`**.

1.  Dans l'explorateur de fichiers à gauche, navigue vers :  
    `src/main/java/MainView.java`
2.  Fais un **clic droit** sur le fichier.
3.  Sélectionne **Run 'MainView.main()'**.

> **Note :** Si tu as une erreur de "Modules", vérifie que ton fichier `module-info.java` contient bien les instructions `requires javafx.controls;` et `opens [nom du jeu] to javafx.graphics;`.

---

##  Liste des Jeux
* **BlackJack** : Atteins 21 sans sauter !
* **Le Pendu** : Trouve le mot caché avant la sentence.
* **Memory** : Retrouve toutes les paires de cartes.
* **Plus ou Moins** : Devine le nombre mystère.
* **True or False** : Teste tes connaissances avec notre quiz.

---
*Projet réalisé dans le cadre d'un apprentissage Java & JavaFX.*
