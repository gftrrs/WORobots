public class RobotZigZag extends Robot {

    private int directionX = 1;  // 1 pour droite, -1 pour gauche
    private int directionY = 1;  // 1 pour bas, -1 pour haut
    private boolean hasTouchedBottom = false;  // Vérifier si le robot a atteint le bas
    private boolean movingUp = false;  // Indique si le robot est en train de remonter

    // Constructeur d'objets de classe RobotZigZag
    public RobotZigZag() {
        super("RobotZigZag", 4, 1, "green");  // Position initiale
    }

    /**
     * Méthode pour déplacer le robot en zigzag.
     * Le robot alterne entre droite-bas, gauche-bas, et une fois le bas atteint, il remonte.
     *
     * @param nb Le nombre de déplacements.
     */
    public void moveZigZag(int nb) {
        for (int i = 0; i < nb; i++) {

            // 1. Déplacement horizontal (droite ou gauche)
            if (!movingUp) {
                // Si le robot n'est pas en train de remonter, il se déplace horizontalement
                if (getXPosition() >= getMax()) {
                    directionX = -1;  // Si limite droite atteinte, aller vers la gauche
                } else if (getXPosition() <= 0) {
                    directionX = 1;  // Si limite gauche atteinte, aller vers la droite
                }

                // Déplacer horizontalement
                setXPosition(getXPosition() + directionX);
                move();  // Met à jour la position à l'écran pour rendre visible le mouvement horizontal
                try {
                    Thread.sleep(500);  // Pause pour observer le mouvement horizontal
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 2. Déplacement vertical vers le bas si possible, seulement si le bas n'est pas encore atteint
                if (!hasTouchedBottom && getYPosition() < getMax()) {
                    setYPosition(getYPosition() + directionY);  // Déplacement vertical vers le bas
                    move();  // Met à jour la position à l'écran pour rendre visible le mouvement vertical
                    try {
                        Thread.sleep(500);  // Pause pour observer le mouvement vertical
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 3. Si le robot atteint le bas pour la première fois, il doit remonter
                if (getYPosition() == getMax() && !hasTouchedBottom) {
                    hasTouchedBottom = true;  // Marque que le bas a été atteint
                    directionY = -1;  // Changer la direction verticale pour remonter
                    movingUp = true;  // Activer le mouvement vers le haut
                }

            } else {
                // 4. Si le robot est en train de remonter, il fait des mouvements haut-droite ou haut-gauche
                // Déplacer horizontalement en remontant
                setXPosition(getXPosition() + directionX);  // Alternance droite-gauche
                setYPosition(getYPosition() + directionY);  // Remonter

                move();  // Met à jour la position à l'écran pour rendre visible le mouvement de remontée
                try {
                    Thread.sleep(500);  // Pause pour observer le mouvement de remontée
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Inverser la direction horizontale après chaque mouvement vertical
                if (getXPosition() >= getMax()) {
                    directionX = -1;  // Si limite droite atteinte, aller vers la gauche
                } else if (getXPosition() <= 0) {
                    directionX = 1;  // Si limite gauche atteinte, aller vers la droite
                }

                // Alterner la direction horizontale pour simuler le "zigzag" haut-gauche ou haut-droite
                if (getXPosition() >= getMax() || getXPosition() <= 0) {
                    directionX = (directionX == 1) ? -1 : 1;  // Inverser la direction horizontale
                }

                // Arrêter le mouvement lorsque le robot a atteint le haut
                if (getYPosition() <= 0) {
                    movingUp = false;  // Arrêter le mouvement vertical
                    break;  // Sortir de la boucle principale
                }
            }
        }
    }
}
