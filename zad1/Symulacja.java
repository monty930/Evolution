// Krystyna Gasińska id: 429192, grupa 6.
// Symulacja ewolucji robów. Projekt 1, Programowanie Obiektowe.

// Uwagi i modyfikacje:
//
// Program zakłada, że wszystkie (liczbowe) parametry są nieujemne.
// Program początkowy robów i spis dostępnych programów powinien być słowem, w którym
// litery należą do zbioru {l, p, i, w, j}.
// Dodatkowo w spisie nie litery nie powinny się powtarzać.
// Litery w programie nie muszą występować w spisie.
// Prawdopodobieństwa i ułamek energii rodzica powinny być liczbą
// zmiennoprzecinkową z przedziału [0; 1], pisaną z kropką (np. 0.53).
// Pozostałe parametry są liczbami całkowitymi.
//
// Jeśli któryś z powyższych warunków, lub warunków zadania nie zostanie
// spełniony, lub nie zostanie podany prawidłowy plik z parametrami
// symulacja nie wykona się, a program zakończy się kodem 1.

// Uwaga! Jeśli nie zostanie podany prawidłowy plik z planszą lub
// odczytane z niego dane nie będą poprawne symulacja wykona się z
// planszą losową (tworzoną w klasie "Plansza"). Poprawność
// danych oznacza ich zgodność z treścią i warunkami zadania.

// Jeśli plik z planszą lub parametrami nie zostanie w ogóle podany
// jako argument przy kompilacji, program zakończy się kodem 1.

// Komunikaty o przyjęciu domyślnej
// planszy wypisywane są na początku,
// przed rozpoczęciem symulacji.

// Każdy Rob wykonuje cały swój program w każdej turze.
// Roby wykonują: najpierw wszystkie swoją pierwszą instrukcję,
// potem wszystkie drugą itd. Gdy Roby z krótszymi programami
// wykonają wszystkie swoje instrukcje nie robią nic, aż do końca
// tury. Tura kończy się gdy Roby z najdłuższymi instrukcjami
// skończą je wykonywać.

import java.io.File;

public class Symulacja {
    private static int numerTury = 0;
    private static int ileTur;
    private static int coIleWypisz;
    private static Populacja populacja;

    private static void uzupełnijDane(File planszaPlik, File parametryPlik) {
        Parametry parametry = new Parametry(parametryPlik);
        parametry.wypiszParametry();

        populacja = new Populacja(parametry.początkowaLiczbaRobów(), parametry.kosztTury(),
                parametry.limitPowielania(), parametry.początkowaEnergia(), parametry.prPowielenia(),
                parametry.prUsunięciaInstrukcji(), parametry.prDodaniaInstrukcji(), parametry.prZmianyInstrukcji(),
                parametry.ułamekEnergiiRodzica(), new Plansza(planszaPlik, parametry.ileDajeJedzenie(),
                parametry.ileRośnieJedzenie()), parametry.spisInstrukcji(), parametry.początkowyProgram());

        ileTur = parametry.ileTur();
        coIleWypisz = parametry.coIleWypisz();
    }

    public static void symuluj() {
        if (ileTur == 0) {
            System.out.println("Zerowa liczba tur. Symulacja nie wykonała się." +
                    "\nStan symulacji:\nLiczba robów: " + populacja.ileRobów());
            return;
        }
        while (numerTury < ileTur) {
            numerTury++;
            if (numerTury % coIleWypisz == 0) {
                System.out.println("wypisanie stanu symulacji... Tura " + numerTury + " liczba robów: " + populacja.ileRobów());
            }
            populacja.Żyj(numerTury);
        }
        System.out.println("W sumie urodziło się: " + populacja.ileSięUrodziło() + " robów.");
        System.out.println("W sumie umarło: " + populacja.ileUmarło() + " robów.");
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

        // symulacja
        symuluj();

        System.out.println("Powiadomienia o błędach podanych plików lub danych (jeśli wystąpiły)" +
                " zostały wypisane przed rozpoczęciem symulacji.");
    }
}
