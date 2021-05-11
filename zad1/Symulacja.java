// Krystyna Gasińska id: 429192, grupa 6.
// Symulacja ewolucji robów. Projekt 1, Programowanie Obiektowe.
// Uwagi i modyfikacje:

// Program zakłada, że wszystkie (liczbowe) parametry są nieujemne.
// Jeśli tak nie jest, symulacja nie wykona się. Jeśli nie zostanie
// podany prawidłowy plik z parametrami, lub odczytane z niego dane
// nie będą poprawne, symulacja nie wykona się (liczba tur zostanie
// ustawiona na 0). Program początkowy robów i spis dostępnych
// programów powinien być słowem, w którym litery należą do zbioru
// {l, p, i, w, j}. Dodatkowo w spisie nie litery nie powinny się
// powtarzać. Prawdopodobieństwa i ułamek energii rodzica powinny być
// liczbą zmiennoprzecinkową z przedziału [0; 1], pisaną z kropką
// (np. 0.53). Jeśli któryś z powyższych warunków nie zostanie
// spełniony symulacja nie wykona się (liczba tur zostanie ustawiona
// na 0)

// Uwaga! Jeśli nie zostanie podany prawidłowy plik z planszą lub
// odczytane z niego dane nie będą poprawne symulacja wykona się z
// planszą domyślną (umieszczoną w klasie "Plansza"). Poprawność
// danych oznacza ich zgodność z treścią i warunkami zadania,a także
// podaną wyżej interpretacją.

// Jeśli plik z planszą lub parametrami nie zostanie w ogóle podany
// jako argument przy kompilacji, program zakończy się kodem 1.

// Komunikaty o wszelkich wyjątkach i błędach, przyjęciu domyślnej
// planszy lub ustawieniu liczby tur na 0, wypisywane są na początku,
// przed rozpoczęciem symulacji.

import java.io.File;

public class Symulacja {
    private static int numerTury = 0;
    private static int ileTur;
    private static Plansza plansza;

    private static void uzupełnijDane(File planszaPlik, File parametryPlik) {
        Parametry parametry = new Parametry(parametryPlik);
        parametry.wypiszParametry();

        int ileDajeJedzenie = parametry.ileDajeJedzenie();
        int ileRośnieJedzenie = parametry.ileRośnieJedzenie();
        plansza = new Plansza(planszaPlik, ileDajeJedzenie, ileRośnieJedzenie);
        plansza.wypiszPlanszę();

        ileTur = parametry.ileTur();
    }

    public static void main(String[] args) {
        // wczytywanie danych
        try {
            File planszaPlik = new File(args[0]);
            File parametryPlik = new File(args[1]);
            uzupełnijDane(planszaPlik, parametryPlik);

            if (args.length > 2) {
                throw new NiepoprawneDane("Wprowadzono więcej niż 2 argumenty kompilacji." +
                        " Program zignoruje nadmiar.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie wprowadzono nazwy pliku z planszą lub z parametrami." +
                    " Przerwano działanie programu.");
            System.exit(1);
        } catch (NiepoprawneDane e) {
            System.out.println(e.getMessage());
        }
    }
}
