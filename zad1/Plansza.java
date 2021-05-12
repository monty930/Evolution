import java.io.*;
import java.util.*;

public class Plansza {
    // plansza domyślna używana w przypadku błędnych danych lub braku danych w pliku
    static final ArrayList<Pole[]> planszaDomyślna = new ArrayList<>() {{
        add(new Pole[]{new Pole(), new Pole(), new Pole()});
        add(new Pole[]{new Pole(), new Pole(), new Pole()});
        add(new Pole[]{new Pole(), new Pole(), new Pole()});
    }};
    private ArrayList<Pole[]> plansza = new ArrayList<>();
    private int liczbaWierszy = 0;
    private int liczbaKolumn = -1;

    public int liczbaKolumn() {
        return liczbaKolumn;
    }

    public int liczbaWierszy() {
        return liczbaWierszy;
    }

    public Pole pole(int x, int y) {
        return plansza.get(y)[x];
    }

    public Plansza(File planszaPlik, int ileDajeJedzenie, int ileRośnieJedzenie) {
        try {
            Scanner czytajPlanszę = new Scanner(planszaPlik);

            while (czytajPlanszę.hasNextLine()) {
                liczbaWierszy++;
                String linia = czytajPlanszę.nextLine();

                if (liczbaKolumn != -1 && liczbaKolumn != linia.length()) {
                    throw new NiepoprawneDane("Niepoprawna liczba znakow w wierszu: " + liczbaWierszy + ".");
                }
                plansza.add(new Pole[linia.length()]);
                liczbaKolumn = linia.length();
                for (int i = 0; i < linia.length(); i++) {
                    char znak = linia.charAt(i);
                    if (znak != ' ' && znak != 'x' && znak != '\n') {
                        throw new NiepoprawneDane("Niedozwolony znak w pliku z planszą.");
                    }
                    if (znak == 'x') {
                        plansza.get(liczbaWierszy - 1)[i] = new Pole(ileDajeJedzenie, ileRośnieJedzenie);
                    }
                    if (znak == ' ') {
                        plansza.get(liczbaWierszy - 1)[i] = new Pole(ileDajeJedzenie, ileRośnieJedzenie);
                    }
                }
            }
            if (liczbaKolumn == 0 || liczbaWierszy == 0) {
                throw new NiepoprawneDane("Brak komórek na planszy.");
            }
            czytajPlanszę.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku z planszą. Wygląd planszy ustawiono na domyślny.");
            plansza = planszaDomyślna;
        } catch (NiepoprawneDane e) {
            System.out.println(e.getMessage() + " Wygląd planszy ustawiono na domyślny.");
            plansza = planszaDomyślna;
        }
    }

    public void wypiszPlanszę() {
        for (int i = 0; i < liczbaWierszy; i++) {
            for (int j = 0; j < liczbaKolumn; j++) {
                System.out.print(plansza.get(i)[j]);
            }
            System.out.println();
        }
    }
}
