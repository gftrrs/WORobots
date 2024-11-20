/**
 * Classe HorizontalRobot qui permet le déplacement d'un robot sur l'axe X.
 */
public class HorizontalRobot extends Robot {
    private int direction = 1;  // 1 pour droite, -1 pour gauche

    // Constructeur du robot horizontal
    public HorizontalRobot() {
        super("HorizontalRobot", 5, 3, "red");
    }

    // Méthode pour déplacer le robot horizontalement et changer de direction quand il atteint les bords
    public void moveHorizontal(int nb) {
        for (int i = 0; i < nb; i++) {
            // Si le robot atteint le bord gauche du canvas, changer la direction vers la droite
            if (getXPosition() <= 0) {
                direction = 1;
            } 
            // Si le robot atteint le bord droit du canvas, changer la direction vers la gauche
            else if (getXPosition() >= getMax()) {
                direction = -1;
            }

            // Déplacer le robot dans la direction actuelle
            setXPosition(getXPosition() + direction);

            move();  // Redessiner le robot

            try {
                Thread.sleep(600);  // Délai pour visualiser le mouvement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
