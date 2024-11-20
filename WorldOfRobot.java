import java.util.ArrayList;
import java.util.Random;

/**
 * Classe WorldOfRobot qui gère l'environnement des robots
 */
public class WorldOfRobot {
    // Variables d'instance
    private static ArrayList<Robot> robots;
    private Canvas canvas;

    /**
     * Constructeur d'objets de classe WorldOfRobot
     */
    public WorldOfRobot() {
        robots = new ArrayList<Robot>();
    }

    // Méthode pour récupérer la liste de robots
    public static ArrayList<Robot> listRobot() {
        return robots;
    }

    // Méthode pour ajouter des robots à la liste
    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    // Méthode pour générer le monde avec des robots spécifiques
    public void generateWorld() {
        // Ajout de robots spécifiques
        VerticalRobot verticalBot = new VerticalRobot();
        addRobot(verticalBot);

        HorizontalRobot horizontalBot = new HorizontalRobot();
        addRobot(horizontalBot);

        RobotZigZag zigZagBot = new RobotZigZag();
        addRobot(zigZagBot);

        DiagonalRobot diagonalBot = new DiagonalRobot();
        addRobot(diagonalBot);

        RobotRandom randomBot = new RobotRandom();
        addRobot(randomBot);

        CircularRobot circularBot = new CircularRobot("CircularBot", "purple", 5, 5, 2);
        addRobot(circularBot);
    }

    // Méthode pour vérifier si un robot est à la même position qu'un autre
    public boolean checkCollision(Robot robot) {
        for (Robot otherRobot : robots) {
            if (otherRobot != robot) {  
                if (otherRobot.getXPosition() == robot.getXPosition() &&
                    otherRobot.getYPosition() == robot.getYPosition()) {
                    return true;  // Collision 
                }
            }
        }
        return false;  // Pas de collision
    }

    // Méthode pour éviter la collision
    public void avoidCollision(Robot robot) {
        Random random = new Random();
        boolean moved = false;

        while (!moved) {
            // Essayer une nouvelle direction aléatoire pour le robot
            int randomX = random.nextInt(3) - 1;  // Valeur entre -1 et 1
            int randomY = random.nextInt(3) - 1;

            // Calculer la nouvelle position
            int newX = robot.getXPosition() + randomX;
            int newY = robot.getYPosition() + randomY;

            // Vérifier que la nouvelle position n'entraîne pas une nouvelle collision
            boolean collision = false;
            for (Robot otherRobot : robots) {
                if (otherRobot != robot &&
                    otherRobot.getXPosition() == newX &&
                    otherRobot.getYPosition() == newY) {
                    collision = true;  // Collision détectée à la nouvelle position
                    break;
                }
            }

            // Si pas de collision, on met à jour la position
            if (!collision) {
                robot.setXPosition(newX);
                robot.setYPosition(newY);
                moved = true;
            }
        }
    }

    // Méthode pour déplacer tous les robots selon leur type
    public void moveAllRobots(int nb) throws InterruptedException {
        int i = 0;
        ArrayList<Robot> robots = WorldOfRobot.listRobot();

        if (robots == null || robots.isEmpty()) {
            System.out.println("Aucun robot disponible pour se déplacer.");
            return;
        }

        // Boucle pour faire 'nb' mouvements pour chaque robot
        while (i < nb) {
            i++;
            for (Robot robot : robots) {
                // Déplacement du robot selon son type
                if (robot instanceof VerticalRobot) {
                    ((VerticalRobot) robot).moveVertical(1);
                } else if (robot instanceof HorizontalRobot) {
                    ((HorizontalRobot) robot).moveHorizontal(1);
                } else if (robot instanceof RobotZigZag) {
                    ((RobotZigZag) robot).moveZigZag(1);
                } else if (robot instanceof DiagonalRobot) {
                    ((DiagonalRobot) robot).moveDiagonalRobot(1);
                } else if (robot instanceof RobotRandom) {
                    ((RobotRandom) robot).moveRandom();
                } else if (robot instanceof CircularRobot) {
                    ((CircularRobot) robot).moveCircular();
                }

                // Vérifier la collision après le mouvement
                if (checkCollision(robot)) {
                    System.out.println(robot.getName() + " a rencontré un autre robot. Evitement...");
                    avoidCollision(robot);  // Eviter la collision
                }
            }

            // Pause de 500ms avant de déplacer le robot suivant
            Thread.sleep(500);
        }
    }
}
