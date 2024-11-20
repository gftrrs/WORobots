
/**
 * Classe DiagonalRobot qui permet le déplacement 
 */
public class DiagonalRobot extends Robot {

    // Constructeur d'objets de classe DiagonalRobot
    public DiagonalRobot() {
        super("DiagonalRobot", 7, 5, "magenta");  // Exemple de position initiale
    }

    /**
     * 
     *
     * @param nb Le nombre de déplacements.
     */
    public void moveDiagonalRobot(int nb) {
        // Répéter le mouvement zigzag 'nb' fois
        for (int i = 0; i < nb; i++) {
            // Déplacer le robot vers le Sud s'il est dans les limites
            if (getYPosition() < getMax()) {
                moveSouth();
            } else {
                // Si on atteint la limite Sud, inverser et remonter
                moveNorth();
            }

            // Déplacer le robot vers l'Est s'il est dans les limites
            if (getXPosition() < getMax()) {
                moveEast();
            } else {
                // Si on atteint la limite Est, inverser et aller vers l'Ouest
                moveWest();
            }

            // Optionnel : Pause pour observer le mouvement
            try {
                Thread.sleep(500);  // Pause de 500ms entre chaque mouvement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
