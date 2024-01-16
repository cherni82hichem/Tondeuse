package test;
import main.java.Tondeuse;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import main.java.TondeuseApp;
class TondeuseTest {

    @Test
    void testAvancer_North() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'N');
        tondeuse.avancer(5, 5);
        assertEquals("1 3 N", tondeuse.getPosition());
    }

    @Test
    void testAvancer_East() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'E');
        tondeuse.avancer(5, 5);
        assertEquals("2 2 E", tondeuse.getPosition());
    }

    @Test
    void testAvancer_South() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'S');
        tondeuse.avancer(5, 5);
        assertEquals("1 1 S", tondeuse.getPosition());
    }

    @Test
    void testAvancer_West() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'W');
        tondeuse.avancer(5, 5);
        assertEquals("0 2 W", tondeuse.getPosition());
    }

    @Test
    void testTournerDroite_North() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'N');
        tondeuse.tourner('D');
        assertEquals("1 2 E", tondeuse.getPosition());
    }

    @Test
    void testTournerDroite_East() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'E');
        tondeuse.tourner('D');
        assertEquals("1 2 S", tondeuse.getPosition());
    }

    @Test
    void testTournerGauche_North() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'N');
        tondeuse.tourner('G');
        assertEquals("1 2 W", tondeuse.getPosition());
    }

    @Test
    void testTournerGauche_West() {
        Tondeuse tondeuse = new Tondeuse(1, 2, 'W');
        tondeuse.tourner('G');
        assertEquals("1 2 S", tondeuse.getPosition());
    }

    @Test
    void testTondeuseApp() throws IOException {
        // Créez un fichier d'entrée temporaire pour le test
        String inputFileName = "resources/fichier.txt";
        String inputContent = "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA";
        Files.write(Paths.get(inputFileName), inputContent.getBytes());

        // Redirigez la sortie standard pour capturer la sortie
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Exécutez l'application avec le fichier temporaire comme entrée
        TondeuseApp.main(new String[]{inputFileName});

        // Comparez la sortie attendue avec la sortie réelle
        String expectedOutput = "1 3 N\r\n5 1 E\r\n";
        assertEquals(expectedOutput, outputStream.toString());


        // Restaurez la sortie standard
        System.setOut(System.out);
    }
}
