public class Sportolo {
    int helyezes;
    int eredmeny;
    String sportolo;
    String orszagkod;
    String helyszin;
    String datum;

    public int getHelyezes() {
        return helyezes;
    }

    public int getEredmeny() {
        return eredmeny;
    }

    public String getSportolo() {
        return sportolo;
    }

    public String getOrszagkod() {
        return orszagkod;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public String getDatum() {
        return datum;
    }

    public void setHelyezes(int helyezes) {
        this.helyezes = helyezes;
    }

    public void setEredmeny(int eredmeny) {
        this.eredmeny = eredmeny;
    }

    public void setSportolo(String sportolo) {
        this.sportolo = sportolo;
    }

    public void setOrszagkod(String orszagkod) {
        this.orszagkod = orszagkod;
    }

    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Sportolo (int helyezes, int eredmeny, String sportolo, String orszagkod, String helyszin, String datum) {
        this.setHelyezes(helyezes);
        this.setEredmeny(eredmeny);
        this.setSportolo(sportolo);
        this.setOrszagkod(orszagkod);
        this.setHelyszin(helyszin);
        this.setDatum(datum);
    }
}

