import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Klasa obsługująca podane w pliku parametry. Sprawdza ich poprawność i
// przechowuje ich wartości.
public class Parametry {
    private final String[] parametrySpis =
            {"ile_tur", "pocz_ile_robów", "pocz_energia", "ile_daje_jedzenie",
                    "ile_rośnie_jedzenie", "koszt_tury",
                    "limit_powielania", "co_ile_wypisz", "pocz_progr",
                    "spis_instr", "ułamek_energii_rodzica", "pr_usunięcia_instr",
                    "pr_dodania_instr", "pr_zmiany_instr", "pr_powielenia"};

    private int wyszukaj(String parametr) {
        for (int i = 0; i < parametrySpis.length; i++) {
            if (parametrySpis[i].equals(parametr)) {
                return i;
            }
        }
        return -1;
    }

    private final int[] parametryCałkowite = new int[8];
    private final double[] parametryNiecałkowite = new double[5];
    private String program;
    private String spis;
    private int długośćSpisu = -1;
    private int długośćProgramu = -1;

    public int ileTur() { return parametryCałkowite[0]; }
    public int początkowaLiczbaRobów() { return parametryCałkowite[1]; }
    public int początkowaEnergia() { return parametryCałkowite[2]; }
    public int ileDajeJedzenie() { return parametryCałkowite[3]; }
    public int ileRośnieJedzenie() { return parametryCałkowite[4]; }
    public int kosztTury() { return parametryCałkowite[5]; }
    public int limitPowielania() { return parametryCałkowite[6]; }
    public int coIleWypisz() { return parametryCałkowite[7]; }
    public String początkowyProgram() { return program; }
    public String spisInstrukcji() { return spis; }
    public double prUsunięciaInstrukcji() { return parametryNiecałkowite[1]; }
    public double prDodaniaInstrukcji() { return parametryNiecałkowite[2]; }
    public double prZmianyInstrukcji() { return parametryNiecałkowite[3]; }
    public double prPowielenia() { return parametryNiecałkowite[4]; }
    public double ułamekEnergiiRodzica() { return parametryNiecałkowite[0]; }

    // funkcja sprawdza, czy litery w spisie nie powtarzają się oraz czy litery w
    // spisie i początkowym programie należą do zbioru {l, p, i, w, j}.
    private boolean czyPoprawneInstrukcje() {
        for (int i = 0; i < długośćProgramu; i++) {
            if (program.charAt(i) != 'l' && program.charAt(i) != 'p' && program.charAt(i) != 'i' &&
                    program.charAt(i) != 'w' && program.charAt(i) != 'j') {
                return false;
            }
        }
        if (długośćSpisu > 5) {
            return false;
        }
        boolean[] czyWystąpiło = {false, false, false, false, false}; // odpowiednio litery: l, p, i, w, j
        for (int i = 0; i < długośćSpisu; i++) {
            if (spis.charAt(i) == 'l') {
                if (czyWystąpiło[0]) {
                    return false;
                }
                czyWystąpiło[0] = true;
            }
            if (spis.charAt(i) == 'p') {
                if (czyWystąpiło[1]) {
                    return false;
                }
                czyWystąpiło[1] = true;
            }
            if (spis.charAt(i) == 'i') {
                if (czyWystąpiło[2]) {
                    return false;
                }
                czyWystąpiło[2] = true;
            }
            if (spis.charAt(i) == 'w') {
                if (czyWystąpiło[3]) {
                    return false;
                }
                czyWystąpiło[3] = true;
            }
            if (spis.charAt(i) == 'j') {
                if (czyWystąpiło[4]) {
                    return false;
                }
                czyWystąpiło[4] = true;
            }
        }
        return true;
    }

    // konstruktor. pobieranie danych z pliku
    public Parametry(File parametryCałkowitePlik) {
        Arrays.fill(parametryCałkowite, -1);
        Arrays.fill(parametryNiecałkowite, -1.);

        try {
            int liczbaWierszy = 0;
            Scanner czytajParametry = new Scanner(parametryCałkowitePlik);
            while (czytajParametry.hasNextLine()) {
                liczbaWierszy++;
                String linia = czytajParametry.nextLine();
                Scanner czytajLinię = new Scanner(linia);
                czytajLinię.useLocale(Locale.US);
                if (!czytajLinię.hasNext()) {
                    throw new NiepoprawneDane("Jeden z wierszy w pliku z parametrami jest pusty: linia " + liczbaWierszy + ".");
                }
                String parametr = czytajLinię.next();
                if (!czytajLinię.hasNext()) {
                    throw new NiepoprawneDane("Nie podano jednego z parametrów: linia "
                            + liczbaWierszy + ".");
                }
                int numerParametru = wyszukaj(parametr);
                if (numerParametru == -1) {
                    throw new NiepoprawneDane("Błędna nazwa parametru: linia " + liczbaWierszy + ".");
                }
                if (numerParametru <= 7) {
                    int wartość = czytajLinię.nextInt();
                    if (wartość < 0) {
                        throw new NiepoprawneDane("Ujemna wartość parametru: linia " + liczbaWierszy + ".");
                    }
                    if (parametryCałkowite[numerParametru] != -1) {
                        throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                    }
                    parametryCałkowite[numerParametru] = wartość;
                }
                else if (numerParametru <= 9) {
                    String wartość = czytajLinię.next();
                    if (numerParametru == 8) {
                        if (długośćProgramu != -1) {
                            throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                        }
                        program = wartość;
                        długośćProgramu = wartość.length();
                    }
                    else {
                        if (długośćSpisu != -1) {
                            throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                        }
                        spis = wartość;
                        długośćSpisu = wartość.length();
                    }
                }
                else { // numerParametru <= 14
                    double wartość = czytajLinię.nextDouble();
                    if (wartość < 0 || wartość > 1) {
                        throw new NiepoprawneDane("Niepoprawna wartość prawdopodobieństwa" +
                                " lub ułamku energii rodzica: : linia " + liczbaWierszy + ".\nNależy podać wartość z przedziału [0; 1].");
                    }
                    if (parametryNiecałkowite[numerParametru - 10] != -1.) {
                        throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                    }
                    parametryNiecałkowite[numerParametru - 10] = wartość;
                }
                czytajLinię.close();
            }
            czytajParametry.close();
            if (liczbaWierszy != parametrySpis.length) {
                throw new NiepoprawneDane("Niepoprawna liczba parametrów.");
            }
            if (!czyPoprawneInstrukcje()) {
                throw new NiepoprawneDane("Niepoprawna litera w początkowym" +
                        "programie robów, w spisie, lub litery w spisie powtarzają się.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku z parametrami.\n" +
                    "Symulacja nie wykona się.");
            System.exit(1);
        } catch (NiepoprawneDane e) {
            System.out.println(e.getMessage() +
                    "Symulacja nie wykona się.");
            System.exit(1);
        } catch (InputMismatchException e) {
            System.out.println("Niepoprawny typ parametru. Upewnij się, że parametry" +
                    " zmiennoprzecinkowe (prawdopodobiństwa i ułamki)\nzapisane są w notacji" +
                    " z kropką, np 0.55.\nSymulacja nie wykona się.");
            System.exit(1);
        }
    }

    public void wypiszParametry() {
        for (int i = 0; i < parametryCałkowite.length; i++) {
            System.out.println(parametrySpis[i] + " " + parametryCałkowite[i]);
        }
        for (int j = 0; j < parametryNiecałkowite.length; j++) {
            System.out.println(parametrySpis[j + 10] + " " + parametryNiecałkowite[j]);
        }
        System.out.println("program: " + program);
        System.out.println("spis: " + spis);
    }
}
