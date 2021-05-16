public class Pole {
    private final int ileRośnieJedzenie;
    private final int ileDajeJedzenie;
    private final boolean czyŻywieniowe;
    private int kiedyZjedzone; // numer tury

    public boolean czyMaJedzenie(int numerTury) {
        return czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie <= numerTury;
    }

    // pole żywieniowe
    public Pole(int ileDajeJedzenie, int ileRośnieJedzenie) {
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

    // Funkcja zwraca ilość energii z zebranego jedzenia.
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
