public class CircularRobot extends Robot {
    private int sideLength;   // Longueur d'un côté de l'octogone
    private int diagonal;      // Longueur de la diagonale
    private int currentSide;   // Compteur pour le côté actuel
    private int steps;         // Compteur de pas pour chaque côté
    private int moveDistance;  // Distance à parcourir à chaque étape

    // Constructeur pour OctagonRobot
    public CircularRobot(String enterName, String color, int centerX, int centerY, int sideLength) {
        super(enterName, centerX, centerY, color);  // Appel du constructeur de Robot
        this.sideLength = sideLength;
        this.diagonal = 1;  // Longueur de la diagonale
        this.currentSide = 0;  // Commence avec le premier côté
        this.steps = 0;      // Compteur de pas initialisé à 0
        this.moveDistance = 2; // Distance à parcourir pour chaque côté

    }

    // Méthode pour déplacer le robot en suivant un octogone
    public void moveCircular() {
        // Vérifier si le robot a terminé le côté actuel
        if (steps < moveDistance) {
            // Déplacement vers l'avant
            moveForward();
            steps++;
        } else {
            // Changer de direction
            steps = 0; // Réinitialiser le compteur de pas
            currentSide = (currentSide + 1) % 8; // Passer au côté suivant
            // Pas besoin de tourner visuellement ici
        }
    }

    // Méthode pour faire avancer le robot
    private void moveForward() {
        int x = getXPosition();
        int y = getYPosition();
        
        // Déplacement en fonction du côté actuel
        switch (currentSide) {
            case 0: // Côté 1 (droite)
                setXPosition(x + 1);
                break;
            case 1: // Côté 2 (haut-droit)
                setXPosition(x + 1);
                setYPosition(y + 1);
                break;
            case 2: // Côté 3 (haut)
                setYPosition(y + 1);
                break;
            case 3: // Côté 4 (haut-gauche)
                setXPosition(x - 1);
                setYPosition(y + 1);
                break;
            case 4: // Côté 5 (gauche)
                setXPosition(x - 1);
                break;
            case 5: // Côté 6 (bas-gauche)
                setXPosition(x - 1);
                setYPosition(y - 1);
                break;
            case 6: // Côté 7 (bas)
                setYPosition(y - 1);
                break;
            case 7: // Côté 8 (bas-droit)
                setXPosition(x + 1);
                setYPosition(y - 1);
                break;
        }

        // Dessiner le robot à la nouvelle position
        move();
    }
}
