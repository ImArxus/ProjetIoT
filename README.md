# ProjetIoT

Ceci est un projet universitaire réalisé par des étudiants de l'ESIR (Ecole Superieure d'Ingénieurs de Rennes). 
Le but de celui-ci est la création d'une maison autonome en utilisant le language de programmation Java.
Nous avons découpé notre projet en différents sprints, réprésentant des versions fonctionnelles de notre projet à des stades de plus en plus avancés. Ci-dessous un petit récapitulatif de nos versions.

v1: Back end et affichage sur la console

v2: Affichage graphique sans interactions avec les objets, sérialisation

v3: Affichage et interaction graphique avec Java FX

Notre version actuelle allie un système d'interface graphique, de sauvegarde, d'un mode administrateur et d'un système d'intéractions avec l'environnement. Cette dernière version se situe sur la branche JavaFX du projet GitHub, contrairement aux versions 1 et 2 ébergées sur la branche master.


Notre projet nécessite que son utilisateur possède un compilateur Java, ainsi que le SDK de Java FX.
Afin d'utiliser notre programme, veuillez lancer la classe Main et vous aurez ensuite accès à de nombreuses fonctionnalités. 
Tout d'abord vous accédez à la phase de connexion, où vous pouvez vous connecter, créer un compte ou jouer en mode invité.
Passé cette étape, vous accédez au choix de la maison, vous avez de nouveau 3 possibilités, vous pouvez soit entrez dans une maison par défaut (Barry House), soit créer une maison ou soit charger votre dernière maison sauvegardée.
Après cette sélection, vous accédez à l'écran de jeu, qui se décompose en un bandeau d'information juxtaposé à la représentation de notre pièce actuelle.


Dans Barry House, vous pouvez effectuer de nombreuses manipulations:

- Vous pouvez observer votre pseudo, le nom de votre maison, celui de la pièce actuelle, l'heure, la luminosité et la température de la pièce grâce au bandeau d'information;

- Vous pouvez vous déplacer de pièce en piéce avec le bouton "Se déplacer" situé dans la bande d'information;

- Vous pouvez changer le nom de votre maison avec le bouton "Changer nom maison" situé dans la bande d'information;

- Vous pouvez sauvegarder votre progression avec le bouton "Sauvegarder" situé dans la bande d'information;

- Vous pouvez quitter le jeu avec le bouton "Quitter" situé dans la bande d'information;

- Vous pouvez utiliser un équipement, en cliquant dessus, vous aurez ainsi accès à toutes ses techniques d'utilisations.


Si vous avez le mode administrateur enclenché, vous avez accès à de nombreuses fonctionnalités en cliquant sur le bouton "Modes admin", voici ces fonctionnalités:

- Créer une pièce, qui permet de créer une nouvelle pièce de n'importe quel type, adjacente à notre pièce actuelle: après activation de cette commande vous serez déplacé dans cette nouvelle pièce;

- Supprimer une pièce, qui supprimera une pièce: si c'est votre pièce actuelle vous serez déplacé dans la pièce principale de votre maison;

- Créer un équipement, qui permet d'ajouter un équipement du type souhaité dans votre pièce actuelle;

- Supprimer un équipement, qui permet de supprimer un équipement de votre pièce actuelle;

- Supprimer tous les équipements de la pièce, qui permet de supprimer tous les équipements de votre pièce actuelle;

- Se déplacer dans n'importe quelle pièce, qui permet de se transporter dans une pièce même si cette dernière n'est pas adjacente;

- Changer la couleur du bandeau, qui permet de modifier la couleur du bandeau d'information;

- Changer l'avatar, qui permet de sélectionner un nouvel avatar.


Le code est découpé en 3 packages : 

- Un package equipements, regroupant les classes de chacun de nos équipements disponibles;

- Un package pieces, regoupant les classes des différents types de pièce disponibles;

- Un package main, regroupant les classes nécessaires à la création et au fonctionnement de notre simulation.

À ceux-là viennent s'ajouter 4 autres packages contenant les images de nos pièces, objets et avatars.


Le package main contient, comme dit ci-dessus, l'ensemble des classes nécessaires à la création et au fonctionnement de l'ensemble. Les fonctions qui traitent de l'environnement (température, luminosité) dans lequel évolue l'utilisateur sont disposées dans la classe Main. La classe BarryHouse gère la création de la maison par défaut (qui s'appelle Barry House) avec toutes les pièces et équipements qu'elle contient. Les classes Maison, Piece et Equipement définissent des classes mères pour respectivement chaque maison, chaque pièce et chaque équipement. La classe ListeUtilisateurs crée une Map contenant identifiants et mots de passe des utilisateurs, ainsi que la liste des comptes admin qui sont définis à l'intérieur de cette classe. La classe Sauvegarde gère tout ce qui est sauvegarde et chargement des comptes et maisons. Ensuite viennent s'ajouter 3 controllers, dont le MainController qui gère la majeures parties des intéractions utilisateur depuis la fenêtre graphique. Cette dernière parait très dense mais est en réalité très répétitive dans ses dernières fonctions (elles créent souvent des menus déroulant nécessitants plusieures lignes pour chaque item). En dernier lieu, on peut lister 10 fichiers codés en fxml dans le package main ; ceux-là créent nos fenêtres graphiques et certains éléments d'intéraction comme des boutons ou des champs d'image.

Nous espèrons que notre projet satisfera vos attentes !
En cas de questions, n'hésitez pas à nous contacter via les adresses mail suivantes.
Contact: meziane.khodja@etudiant.univ-rennes1.fr, alexandre.lebalanger@etudiant.univ-rennes1.fr, vincent.dollo@etudiant.univ-rennes1.fr, eloy.martinez@etudiant.univ-rennes1.fr
