/**
 * Classe VerticalRobot qui permet le déplacement d'un robot sur l'axe Y.
 */
public class VerticalRobot extends Robot {
    private int direction = 1;  // 1 pour monter, -1 pour descendre

    // Constructeur du robot vertical
    public VerticalRobot() {
        super("VerticalRobot", 6, 7, "blue");
    }

    // Méthode pour déplacer le robot verticalement et changer de direction quand il atteint les bords
    public void moveVertical(int nb) {
        for (int i = 0; i < nb; i++) {
            // Si le robot atteint le haut du canvas, changer la direction vers le bas
            if (getYPosition() <= 0) {
                direction = 1;
            } 
            // Si le robot atteint le bas du canvas, changer la direction vers le haut
            else if (getYPosition() >= getMax()) {
                direction = -1;
            }

            // Déplacer le robot dans la direction actuelle
            setYPosition(getYPosition() + direction);

            move();  // Redessiner le robot

            try {
                Thread.sleep(600);  // Délai pour visualiser le mouvement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
