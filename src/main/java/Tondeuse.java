package main.java;

public class Tondeuse {
    int x, y;
    char orientation;

    public Tondeuse(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void avancer(int maxX, int maxY) {
        switch (orientation) {
            case 'N':
                if (y < maxY) y++;
                break;
            case 'E':
                if (x < maxX) x++;
                break;
            case 'S':
                if (y > 0) y--;
                break;
            case 'W':
                if (x > 0) x--;
                break;
        }
    }

    public void tourner(char direction) {
        if (direction == 'D') {
            // Tourner à droite
            orientation = switch (orientation) {
                case 'N' -> 'E';
                case 'E' -> 'S';
                case 'S' -> 'W';
                case 'W' -> 'N';
                default -> throw new IllegalArgumentException("Orientation invalide");
            };
        } else if (direction == 'G') {
            // Tourner à gauche
            orientation = switch (orientation) {
                case 'N' -> 'W';
                case 'E' -> 'N';
                case 'S' -> 'E';
                case 'W' -> 'S';
                default -> throw new IllegalArgumentException("Orientation invalide");
            };
        }
    }

    public String getPosition() {
        return x + " " + y + " " + orientation;
    }
}
