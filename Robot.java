import java.util.ArrayList;

/**
 * Décrivez votre classe Robot ici.
 *
 * @author (Group 7)
 * @version (18/10/24)
 */
public class Robot
{
    
    private String name;
    private int xPosition;
    private int yPosition;
    
    // Constantes qui définissent les contraintes du robot
    private int MIN_NAME_LENGTH = 3; // Longueur minimale du nom du robot
    private int MIN_POSITION = 0;    // Position minimale sur les axes X et Y
    private int MAX_POSITION = 11;   // Position maximale sur les axes X et Y
    
    // Compteur pour générer des noms de robots si l'utilisateur ne les fournit pas
    private static int numberOfUnnamedRobots = 0;
    
    
    private CanvasRobot canvasRobot;
    
    
    private Colour colour;
    
    
    private String colourBody;
    
    /**
     * Constructeur de la classe Robot.
     * Initialise le nom, les positions et la couleur du robot, tout en dessinant ce dernier sur le canvas.
     */
    public Robot(String enterName, int xPos, int yPos, String color){
        setName(enterName);             // Définit le nom du robot
        setXPosition(xPos);             // Définit la position en X
        setYPosition(yPos);             // Définit la position en Y
        setColourBody(color.toUpperCase());  // Définit la couleur (convertie en majuscules)
        canvasRobot = new CanvasRobot(color); // Initialise l'objet graphique
        canvasRobot.drawRobot(xPosition, yPosition); // Dessine le robot à la position spécifiée
    }

    // Méthode pour définir la couleur du corps du robot
    public void setColourBody(String colourBody) {
        // Vérifie si la couleur donnée est valide en utilisant la méthode contains
        if (contains(colourBody)) {    
            this.colourBody = colourBody;  // Si valide, définit la couleur
        } else {
            this.colourBody = "BLUE";      // Sinon, par défaut, la couleur est BLEU
        }
    }    
    
    // Méthode statique qui vérifie si une couleur fait partie des valeurs valides de l'enum Colour
    public static boolean contains(String test) {
        for (Colour c : Colour.values()) { 
            if (c.name().equals(test)) { 
                return true; // Si la couleur est trouvée dans l'enum, renvoie vrai
            } 
        } 
        return false; // Sinon, renvoie faux
    }
    
    // Méthode getter pour obtenir le nom du robot
    public String getName(){
        return this.name;
    }
  
    // Méthode getter pour obtenir la position X du robot
    public int getXPosition(){
        return xPosition;
    }

    // Méthode getter pour obtenir la position Y du robot
    public int getYPosition(){
        return yPosition;
    }

    // Méthode getter pour obtenir la position maximale permise
    public int getMax(){
        return MAX_POSITION;
    }

    // Méthode getter pour obtenir la position minimale permise
    public int getMin(){
        return MIN_POSITION;
    }
    
    // Méthode setter pour définir le nom du robot
    public void setName(String enterName){
        enterName = enterName.trim(); // Supprime les espaces avant et après le nom
        if (enterName.length() >= MIN_NAME_LENGTH){
            this.name = enterName; // Si le nom est assez long, l'attribuer au robot
        } else {
            numberOfUnnamedRobots++;
            name = "iRobot" + numberOfUnnamedRobots; // Sinon, donner un nom par défaut avec un numéro unique
        }
    }

    // Méthode setter pour définir la position X du robot avec vérification
    public void setXPosition(int Xpos){
        if (Xpos >= MIN_POSITION && Xpos <= MAX_POSITION){
            xPosition = Xpos; // Si X est dans la plage valide, l'attribuer
        } else {
            xPosition = 0; // Sinon, positionner à 0
        }
    }

    // Méthode setter pour définir la position Y du robot avec vérification
    public void setYPosition(int Ypos){
        if (Ypos >= MIN_POSITION && Ypos <= MAX_POSITION){
            yPosition = Ypos; // Si Y est dans la plage valide, l'attribuer
        } else {
            yPosition = 0; // Sinon, positionner à 0
        }
    }
    
    // Méthode getter pour obtenir la couleur du corps du robot
    public String getColourBody(){
        return this.colourBody;
    }
    
    // Méthode getter pour obtenir l'objet CanvasRobot associé au robot
    public CanvasRobot getCanvasRobot(){
        return canvasRobot;
    }
    
    // Méthode pour dessiner le robot à sa position actuelle
    public void move(){
        getCanvasRobot().drawRobot(getXPosition(), getYPosition());
    }
    
    // Méthodes de mouvement pour gérer les déplacements du robot sur la grille :
    
    // Mouvement vers le Nord (décrémentation de y)
    public void moveNorth() {
        if (yPosition > MIN_POSITION) {
            yPosition--; // Le robot monte d'une case
            canvasRobot.drawRobot(xPosition, yPosition); // Redessine le robot après mouvement
        } else {
            System.out.println("Le robot est déjà à la limite Nord.");
        }
    }

    // Mouvement vers le Sud (incrémentation de y)
    public void moveSouth() {
        if (yPosition < MAX_POSITION) {
            yPosition++; // Le robot descend d'une case
            canvasRobot.drawRobot(xPosition, yPosition); // Redessine le robot après mouvement
        } else {
            System.out.println("Le robot est déjà à la limite Sud.");
        }
    }

    // Mouvement vers l'Est (incrémentation de x)
    public void moveEast() {                 
        if (xPosition < MAX_POSITION) {
            xPosition++; // Le robot se déplace à droite
            canvasRobot.drawRobot(xPosition, yPosition); // Redessine le robot après mouvement
        } else {
            System.out.println("Le robot est déjà à la limite Est.");
        }
    }

    // Mouvement vers l'Ouest (décrémentation de x)
    public void moveWest() {
        if (xPosition > MIN_POSITION) {
            xPosition--; // Le robot se déplace à gauche
            canvasRobot.drawRobot(xPosition, yPosition); // Redessine le robot après mouvement
        } else {
            System.out.println("Le robot est déjà à la limite Ouest.");
        }
    }

}
