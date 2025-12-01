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
            double eredmeny = Double.parseDouble(parts[1].replace(",", "."));
            String sportolo = parts[2].trim();
            String orszagkod = parts[3].trim();
            String helyszin = parts[4].trim();
            String datum = parts[5].trim();

            sportolok.add(new Sportolo(helyezes, eredmeny, sportolo, orszagkod, helyszin, datum));
        }

        return sportolok;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Sportolo> sporolok = filereader("kalapacsvetes.txt");
        System.out.println("4. feladat: " + sporolok.size() + " dobás eredménye található.");

        double magyarEredmenyOsszeg = 0.0;
        int magyarEredmenyDarab = 0;
        for (Sportolo sportolo : sporolok) {
            if (sportolo.getOrszagkod().equalsIgnoreCase("HUN")) {
                magyarEredmenyDarab++;
                magyarEredmenyOsszeg += sportolo.getEredmeny();
            }
        }

        double magyarAtlagEredmeny = magyarEredmenyOsszeg / magyarEredmenyDarab;

        System.out.println("5. feladat: A magyar sportolók átlagosan " + String.format("%.2f", magyarAtlagEredmeny) + " métert dobtak.");
    }
}