import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parametry {
    private final String[] parametrySpis =
            {"ile_tur", "pocz_ile_robów", "pocz_energia", "ile_daje_jedzenie",
                    "ile_rośnie_jedzenie", "koszt_tury", "pr_powielenia",
                    "limit_powielania", "co_ile_wypisz", "pocz_progr",
                    "spis_progr", "ułamek_energii_rodzica", "pr_usunięcia_instr",
                    "pr_dodania_instr", "pr_zmiany_instr"};

    private int wyszukaj(String parametr) {
        for (int i = 0; i < parametrySpis.length; i++) {
            if (parametrySpis[i].equals(parametr)) {
                return i;
            }
        }
        return -1;
    }

    private final int[] parametryCałkowite = new int[9];
    private final double[] parametryNiecałkowite = new double[4];
    private String program;
    private String spis;
    private int długośćSpisu = -1;
    private int długośćProgramu = -1;

    public int ileTur() { return parametryCałkowite[0]; }
    public int początkowaLiczbaRobów() { return parametryCałkowite[1]; }
    public int początkowaEnergia() { return parametryCałkowite[2]; }
    public int ileEnergiiDajeJedzenie() { return parametryCałkowite[3]; }
    public int ile_rośnie_jedzenie() { return parametryCałkowite[4]; }
    public int koszt_tury() { return parametryCałkowite[5]; }
    public int pr_powielenia() { return parametryCałkowite[6]; }
    public int limitPowielania() { return parametryCałkowite[7]; }
    public int coIleWypisz() { return parametryCałkowite[8]; }
    public String początkowyProgram() { return program; }
    public String spis_instr() { return spis; }
    public double prUsunięciaInstrukcji() { return parametryNiecałkowite[1]; }
    public double prDodaniaInstrukcji() { return parametryNiecałkowite[2]; }
    public double prZmianyInstrukcji() { return parametryNiecałkowite[3]; }
    public double ułamekEnergiiRodzica() { return parametryNiecałkowite[0]; }

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
                    throw new NiepoprawneDane("Nie podano jednego z parametrów: linia " + liczbaWierszy + ".");
                }
                int numerParametru = wyszukaj(parametr);
                if (numerParametru == -1) {
                    throw new NiepoprawneDane("Błędna nazwa parametru.");
                }
                if (numerParametru <= 8) {
                    int wartość = czytajLinię.nextInt();
                    if (parametryCałkowite[numerParametru] != -1) {
                        throw new NiepoprawneDane("Parametr powtarza się.");
                    }
                    parametryCałkowite[numerParametru] = wartość;
                }
                else if (numerParametru <= 10) {
                    String wartość = czytajLinię.next();
                    if (numerParametru == 9) {
                        if (długośćProgramu != -1) {
                            throw new NiepoprawneDane("Parametr powtarza się.");
                        }
                        program = wartość;
                        długośćProgramu = wartość.length();
                    }
                    else {
                        if (długośćSpisu != -1) {
                            throw new NiepoprawneDane("Parametr powtarza się.");
                        }
                        spis = wartość;
                        długośćSpisu = wartość.length();
                    }
                }
                else { // numerParametru <= 14
                    double wartość = czytajLinię.nextDouble();
                    if (parametryNiecałkowite[numerParametru - 11] != -1.) {
                        throw new NiepoprawneDane("Parametr powtarza się.");
                    }
                    parametryNiecałkowite[numerParametru - 11] = wartość;
                }
                czytajLinię.close();
            }
            czytajParametry.close();
            if (liczbaWierszy != parametrySpis.length) {
                throw new NiepoprawneDane("Niepoprawna liczba parametrów.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku z parametrami. " +
                    "Liczba tur ustawiona na 0. Symulacja nie wykona się.");
            parametryCałkowite[0] = 0;
        } catch (NiepoprawneDane e) {
            System.out.println(e.getMessage() + " Liczba tur ustawiona na 0. " +
                    "Symulacja nie wykona się.");
            parametryCałkowite[0] = 0;
        } catch (InputMismatchException e) {
        System.out.println("Niepoprawny typ parametru. Liczba tur ustawiona na 0. " +
                "Symulacja nie wykona się.");
        parametryCałkowite[0] = 0;
    }
    }

    public void wypiszParametry() {
        for (int i = 0; i < parametryCałkowite.length; i++) {
            System.out.println(parametrySpis[i] + " " + parametryCałkowite[i]);
        }
        for (int j = 0; j < parametryNiecałkowite.length; j++) {
            System.out.println(parametrySpis[j + 11] + " " + parametryNiecałkowite[j]);
        }
        System.out.println("program: " + program);
        System.out.println("spis: " + spis);
    }
}
