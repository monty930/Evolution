public class Pole {
    int ileRośnieJedzenie;
    int ileDajeJedzenie;
    boolean czyŻywieniowe;
    int kiedyZjedzone; // numer tury

    public boolean czyMaJedzenie(int numerTury) {
        if (czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie >= numerTury) {
            kiedyZjedzone = numerTury;
            return true;
        }
        return false;
    }

    public Pole(int ileDajeJedzenie, int ileRośnieJedzenie, boolean czyŻywieniowe) {
        this.czyŻywieniowe = czyŻywieniowe;
        kiedyZjedzone = -1 * ileRośnieJedzenie;
        this.ileDajeJedzenie = ileDajeJedzenie;
        this.ileRośnieJedzenie = ileRośnieJedzenie;
    }

    // Funkcja zwraca ilość energii z zerwanego jedzenia.
    // Jeśli jedzenie jeszcze nie dojrzało lub na tym polu w ogóle
    // nie rośnie jedzenie - funkcja zwraca 0.
    int oddajJedzenie(int numerTury) {
        if (czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie >= numerTury) {
            kiedyZjedzone = numerTury;
            return ileDajeJedzenie;
        }
        return 0;
    }
}
