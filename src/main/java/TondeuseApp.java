package main.java;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class Tondeuse {
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

public class TondeuseApp {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.lines(Paths.get("resources/fichier.txt"))
                    .collect(Collectors.toList());

            String[] dimensions = lines.get(0).split(" ");
            int maxX = Integer.parseInt(dimensions[0]);
            int maxY = Integer.parseInt(dimensions[1]);

            for (int i = 1; i < lines.size(); i += 2) {
                String[] initialPosition = lines.get(i).split(" ");
                int x = Integer.parseInt(initialPosition[0]);
                int y = Integer.parseInt(initialPosition[1]);
                char orientation = initialPosition[2].charAt(0);

                Tondeuse tondeuse = new Tondeuse(x, y, orientation);

                for (char instruction : lines.get(i + 1).toCharArray()) {
                    if (instruction == 'A') {
                        tondeuse.avancer(maxX, maxY);
                    } else {
                        tondeuse.tourner(instruction);
                    }
                }

                System.out.println(tondeuse.getPosition());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

