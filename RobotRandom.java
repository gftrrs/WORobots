import java.util.Random;

/**
 * Décrivez votre classe RobotRandom ici.
 */
public class RobotRandom extends Robot {
    private Random random;

    /**
     * Constructeur d'objets de classe RobotRandom
     */
    public RobotRandom() {
        super("RobotRandom", 6, 6, "yellow");
        random = new Random();
    }

    // Mouvement aléatoire
    public void moveRandom() {
        int randomX = random.nextInt(21) - 10;  // Mouvement aléatoire entre -10 et 10
        int randomY = random.nextInt(21) - 10;  // Mouvement aléatoire entre -10 et 10

        // Mettre à jour les positions X et Y du robot avec les mouvements aléatoires
        setXPosition(getXPosition() + randomX);
        setYPosition(getYPosition() + randomY);

        // S'assurer que le robot reste dans les limites du canvas
        if (getXPosition() < 0) setXPosition(0);
        if (getXPosition() > getMax()) setXPosition(getMax());
        if (getYPosition() < 0) setYPosition(0);
        if (getYPosition() > getMax()) setYPosition(getMax());

        // Appeler la méthode move() de la classe mère pour redessiner le robot à la nouvelle position
        move();

        System.out.println("Le robot random se déplace de (" + randomX + ", " + randomY + ")");
    }
}
