package main.java;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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

