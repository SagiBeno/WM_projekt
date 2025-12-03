import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    public static void filewriter(String filename, StringBuilder output) throws IOException {
        File file = new File(filename);
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(output));
        writer.close();
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("6. feladat: Adjon meg egy évszámot:");
        int evszam = scanner.nextInt();

        int evszamDarab = 0;
        ArrayList<String> nevek = new ArrayList<>();

        for (Sportolo sportolo : sporolok) {

            if (sportolo.getDatum().contains(String.valueOf(evszam))) {
                evszamDarab++;
                nevek.add(sportolo.getSportolo());
            }
        }

        if (evszamDarab > 0) {
            System.out.println("\t" + evszamDarab + " darab dobás került be ebben az évben.");
            for (String nev : nevek) {
                System.out.println("\t" + nev);

            }
        } else {
            System.out.println("\tEgy dobás sem került be ebben az évben.");
        }

        ArrayList<String> orszagkodok = new ArrayList<>();

        for (Sportolo sportolo : sporolok) {
            if (!orszagkodok.contains(sportolo.getOrszagkod())) orszagkodok.add(sportolo.getOrszagkod());
        }

        Map<String, Integer> statisztika = new HashMap<>();
        for (String orszagkod : orszagkodok) {

            int darab = 0;

            for (Sportolo sportolo : sporolok) {
                if (sportolo.getOrszagkod().equalsIgnoreCase(orszagkod)) {
                    darab++;
                }
            }

            statisztika.put(orszagkod, darab);
        }

        System.out.println("7. feladat: Statisztika");
        for (String key : statisztika.keySet()) {
            System.out.println("\t" + key + " - " + statisztika.get(key) + " dobás");
        }

        StringBuilder output = new StringBuilder();
        output.append("Helyezés;Eredmény;Sportoló;Országkód;Helyszín;Dátum");
        for (Sportolo sportolo : sporolok) {
            if (sportolo.getOrszagkod().equalsIgnoreCase("hun")) {
                output.append("\n" + sportolo.getHelyezes() + ";" + sportolo.getEredmeny() + ";" + sportolo.getSportolo() + ";" + sportolo.getOrszagkod() + ";" + sportolo.getHelyszin() + ";" + sportolo.getDatum());
            }
        }

        try {
            filewriter("magyarok.txt", output);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}