public class Pole {
    int ileRośnieJedzenie;
    int ileDajeJedzenie;
    boolean czyŻywieniowe;
    int kiedyZjedzone; // numer tury

    public boolean czyMaJedzenie(int numerTury) {
        return czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie <= numerTury;
        //System.out.println("czyŻyw " + czyŻywieniowe + " kiedyZj " + kiedyZjedzone + " ileR " + ileRośnieJedzenie);
    }

    // pole żywieniowe
    public Pole(int ileDajeJedzenie, int ileRośnieJedzenie) {
        //System.out.println("iled " + ileDajeJedzenie + " iler " + ileRośnieJedzenie);
        this.czyŻywieniowe = true;
        kiedyZjedzone = -1 * ileRośnieJedzenie;
        this.ileDajeJedzenie = ileDajeJedzenie;
        this.ileRośnieJedzenie = ileRośnieJedzenie;
    }

    // pole nieżywieniowe
    public Pole() {
        this.czyŻywieniowe = false;
        kiedyZjedzone = 0;
        this.ileDajeJedzenie = 0;
        this.ileRośnieJedzenie = 0;
    }

    // Funkcja zwraca ilość energii z zerwanego jedzenia.
    // Jeśli jedzenie jeszcze nie dojrzało lub na tym polu w ogóle
    // nie rośnie jedzenie - funkcja zwraca 0.
    int oddajJedzenie(int numerTury) {
        if (czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie <= numerTury) {
            kiedyZjedzone = numerTury;
            return ileDajeJedzenie;
        }
        return 0;
    }
}
