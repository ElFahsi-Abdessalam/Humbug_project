# Matricule G44382

## Le modèle v0.1

### Énumération SquareType et Classe Square

Package `g44382.humbug.model` : OK

Javadoc mise à jour : Le paramètre du constructeur de `Square` est effacé.

### Énumération Direction

##### Littéraux

Aucune remarque.

#### Attributs

Les attributs peuvent être déclarés `final`.

#### Méthodes

Aucune remarque.

### Classe Position

#### Généralités

##### Documentation

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits.

##### Tests unitaires 

Valides et suffisants.

#### Attributs

Aucune remarque.

#### Méthodes

##### `equals` et `hascode`

Aucune remarque.

##### Méthode `next(Direction)`

Quelle est l'utilité de la variable `position` ?

### Classe Board

#### Généralités

##### Documentation

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, aucune description des fonctionnalités de la classe, seul l'auteur est mentionné.

##### Tests unitaires 

Valides et suffisants.

Appel à la méthode `setUp()` au lieu d'une correction des  plugins de Maven comme proposé sur poEsi : https://poesi.esi-bru.be/mod/page/view.php?id=1968

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `getInitialBoard()`

Aucune remarque.

##### Méthode `isInside(Position)`

Toutes ces conditions peuvent être résumée en une expression booléenne afin d'en simplifier la lecture.

##### Méthode `getSquareType(Position)`

l'utilisation de la méthode `isInside` doit remplacer a condition de la ligne 87.

##### Méthode `getNbRow()` `getNbColumn()`

Aucune remarque.

##### Méthode `setSquares`

Cette méthode n'est jamais utilisée.

### Classe View

#### Généralités

##### Documentation

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, aucune description des fonctionnalités de la classe, seul l'auteur est mentionné.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `displayBoard(Board board)`

- il vaut mieux placer les constantes chiffrées dans des constantes afin de pouvoir les modifier facilement
- L'ajout de méthode `private` peut améliorer la lisibilité et l'évolutivité, une méthode qui gère l’impression des bords de la case et de l'animal par exemple, d'autres découpages sont envisageables

##### Méthode `displayError(String message)`

L'annotation `Override` est-elle utile dans ce cas ?

##### Méthode `askPosition()`

- quelle est l'utilité de la variable `keyboard`
- le nom de la méthode `askBoardLength` ne correspond pas à ce qu'elle fait, elle ne demande pas la longueur du plateau
- 

##### Méthode `askDirection()`

Aucune remarque.

## Le modèle v0.2

### Classe Abstraite Animal

#### Généralités

##### Documentation

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, aucune description des fonctionnalités de la classe, seul l'auteur est mentionné.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `abstract Position move()`

Aucune remarque.

### Classe Snail

#### Généralités

##### Documentation

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, aucune description des fonctionnalités de la classe, seul l'auteur est mentionné.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

- un `else` après un bloc `if` qui se termine par `return` n'est pas utile
- la variable `next` n'est pas utilisée
- il y a de la redondance de code entre le cas `GRASS` et le cas `STAR`
- une fois le code simplifié, l'instruction `return null` ne servira plus

### Classe Spider

#### Généralités

##### Documentation

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

Le code est redondant avec la méthode `move` de `Snail`. Vois-tu une solution pour éviter cette redondance ?

### Classe Game

#### Généralités

##### Documentation

Javadoc de la classe et de l'interface incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, aucune description des fonctionnalités de la classe, seul l'auteur est mentionné.

##### Implémentation de l'interface

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Board getBoard()`

Aucune remarque.

##### Méthode `Animal[] getAnimals()`

Aucune remarque.

##### Méthode `void startLevel(int level)`

La construction des attributs `Board` et `Animal[]` doit être effectuée dans cette méthode, car la construction va dépendre du niveau. Dans cette première version il y a uniquement un niveau de disponible mais la logique de tes méthodes ne le suppose pas (le paramètre de `startLevel` peut prendre différentes valeurs).

##### Méthode `boolean levelIsOver()`

Aucune remarque.

##### Méthode `void move(Position position, Direction direction)`

Quelle est l'utilité des variables `check` et `checkMove` ?

La `position` donnée en paramètre n'est jamais utilisée. Comment l’animal est-il sélectionné ?

### Classe Controller

#### Généralités

##### Documentation

Javadoc de la classe et de l'interface incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits.

##### Gestion des exceptions

Si j'entre une direction incorrect, comme bonjour, je reçois une `java.lang.IllegalArgumentException` et le programme s'arrête.

#### Attributs et interfaces

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `startGame`

Au démarrage du jeu lorsque j’introduis la position `(0,1)` suivi de `EAST` au démarrage du jeu, l'escargot se déplace.

