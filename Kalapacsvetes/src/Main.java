import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Sportolo> filereader(String filename) throws FileNotFoundException {
        ArrayList<Sportolo> sportolok = new ArrayList<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        //Első sor kihagyása:
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            int helyezes = Integer.parseInt(parts[0].trim());
            int eredmeny = Integer.parseInt(parts[1].trim());
            String sportolo = parts[2].trim();
            String orszagkod = parts[3].trim();
            String helyszin = parts[5].trim();
            String datum = parts[6].trim();

            sportolok.add(new Sportolo(helyezes, eredmeny, sportolo, orszagkod, helyszin, datum));
        }

        return sportolok;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Sportolo> sporolok = filereader("kalapacsvetes");

    }
}