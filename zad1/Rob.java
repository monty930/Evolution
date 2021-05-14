import java.util.Random;

public class Rob {
    private final String spisInstrukcji;
    private final Plansza plansza;
    private final double ułamekEnergiiRodzica;
    private final double prZmianyInstrukcji;
    private final double prDodaniaInstrukcji;
    private final double prUsunięciaInstrukcji;
    private final int kosztTury;
    private final int limitPowielania;
    private final double prPowielenia;
    private int numerInstrukcji = 0;
    private int kierunek; // 0 - północ (góra), 1 - wschód (prawo),
                          // 2 - południe (dół), 3 - zachód (lewo)
    private int energia;
    private final String program;
    private int współrzędnaX;
    private int współrzędnaY;
    private boolean zakończonoProgram = false;
    private int wiek = 0;

    public int wiek() {
        return wiek;
    }

    public boolean zakończonoProgram() {
        return zakończonoProgram;
    }

    public Rob(int kosztTury, int limitPowielania, int początkowaEnergia, double prPowielenia,
               double prUsunięciaInstrukcji, double prDodaniaInstrukcji, double prZmianyInstrukcji,
               double ułamekEnergiiRodzica, Plansza plansza, String spisInstrukcji, String początkowyProgram,
               int współrzędnaX, int współrzędnaY, int kierunek) {
        this.program = początkowyProgram;
        this.energia = początkowaEnergia;
        this.kosztTury = kosztTury;
        this.limitPowielania = limitPowielania;
        this.prPowielenia = prPowielenia;
        this.prUsunięciaInstrukcji = prUsunięciaInstrukcji;
        this.prDodaniaInstrukcji = prDodaniaInstrukcji;
        this.prZmianyInstrukcji = prZmianyInstrukcji;
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
        this.plansza = plansza;
        this.spisInstrukcji = spisInstrukcji;

        // ustalenie losowej pozycji początkowej
        if (współrzędnaX == -1 || współrzędnaY == -1 || kierunek == -1){
            Random r = new Random();
            this.współrzędnaX = r.nextInt(plansza.liczbaKolumn());
            this.współrzędnaY = r.nextInt(plansza.liczbaWierszy());
            this.kierunek = r.nextInt(4);
        } else {
            this.współrzędnaX = współrzędnaX;
            this.współrzędnaY = współrzędnaY;
            this.kierunek = kierunek;
        }
    }

    // Funkcja z podanym prawdopodobieństwem zwróci wartość true.
    private boolean wylosuj (double prawdopodobieństwo) {
        Random r = new Random();
        return r.nextDouble() <= prawdopodobieństwo;
    }

    // Funkcja tworzy nowego Roba, z nowym programem i energią, zwróconego przeciwnie
    // niż rodzic, na tym samym polu co on.
    public Rob powielSię () {
        int energiaNowegoRoba = (int) ((double )this.energia * ułamekEnergiiRodzica);
        this.energia -= energiaNowegoRoba;
        String nowyProgram = program;
        if (wylosuj(prUsunięciaInstrukcji) && nowyProgram.length() != 0 && spisInstrukcji.length() != 0) {
            nowyProgram = nowyProgram.substring(0, nowyProgram.length() - 1);
        }
        if (wylosuj(prDodaniaInstrukcji) && spisInstrukcji.length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(spisInstrukcji.length());
            nowyProgram += Character.toString(spisInstrukcji.charAt(numerNowejInstrukcji));
        }
        if (wylosuj(prZmianyInstrukcji) && nowyProgram.length() != 0 && spisInstrukcji.length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(spisInstrukcji.length());
            int numerStarejInstrukcji = r.nextInt(nowyProgram.length());
            nowyProgram = nowyProgram.substring(0, numerStarejInstrukcji) +
                    spisInstrukcji.charAt(numerNowejInstrukcji) +
                    nowyProgram.substring(numerStarejInstrukcji + 1);
        }
        return new Rob(kosztTury, limitPowielania, energiaNowegoRoba, prPowielenia, prUsunięciaInstrukcji,
                prDodaniaInstrukcji, prZmianyInstrukcji, ułamekEnergiiRodzica, plansza, spisInstrukcji, nowyProgram,
                współrzędnaX, współrzędnaY, (kierunek + 2) % 4);
    }

    public void lewo() {
        kierunek = (kierunek - 1) % 4;
    }

    public void prawo() {
        kierunek = (kierunek + 1) % 4;
    }

    public void idź(int numerTury) {
        if (kierunek == 0) { // północ
            współrzędnaY = współrzędneModulo(1, 'y');
        }
        if (kierunek == 1) { // wschód
            współrzędnaX = współrzędneModulo(1, 'x');
        }
        if (kierunek == 2) { // południe
            współrzędnaY = współrzędneModulo(-1, 'y');
        }
        if (kierunek == 3) { // zachód
            współrzędnaX = współrzędneModulo(-1, 'x');
        }
        energia += plansza.pole(współrzędnaX, współrzędnaY).oddajJedzenie(numerTury);
        //System.out.println("en" + energia);
    }

    public void wąchaj(int numerTury) {
        if (plansza.pole(współrzędneModulo(1, 'x'), współrzędnaY).czyMaJedzenie(numerTury)) {
            kierunek = 1;
        } else if (plansza.pole(współrzędneModulo(-1, 'x'), współrzędnaY).czyMaJedzenie(numerTury)) {
            kierunek = 3;
        } else if (plansza.pole(współrzędnaX, współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            kierunek = 0;
        } else if (plansza.pole(współrzędnaX, współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            kierunek = 2;
        }
    }

    private int współrzędneModulo(int liczba, char współrzędna) {
        if (współrzędna == 'x') {
            if ((współrzędnaX + liczba) % plansza.liczbaKolumn() < 0) {
                //System.out.println("tu " +(współrzędnaX + liczba) % plansza().liczbaKolumn() + plansza().liczbaKolumn());
                return (współrzędnaX + liczba) % plansza.liczbaKolumn() + plansza.liczbaKolumn();
            }
            //System.out.println("tu2 " +(współrzędnaX + liczba) % plansza().liczbaKolumn());
            return (współrzędnaX + liczba) % plansza.liczbaKolumn();
        }
        if (współrzędna == 'y') {
            if ((współrzędnaY + liczba) % plansza.liczbaWierszy() < 0) {
                //System.out.println((współrzędnaY + liczba) % plansza().liczbaWierszy() + plansza().liczbaWierszy());
                return (współrzędnaY + liczba) % plansza.liczbaWierszy() + plansza.liczbaWierszy();
            }
            //System.out.println("tu4 " + (współrzędnaY + liczba) % plansza().liczbaWierszy() );
            return (współrzędnaY + liczba) % plansza.liczbaWierszy();
        }
        System.out.println("NIEEEEEEEEEEEEEEEE");
        return współrzędnaX + liczba;
    }

    public void jedz(int numerTury) {
        //System.out.println("kol: " + plansza().liczbaKolumn() + " wier: " + plansza().liczbaWierszy());
        if (plansza.pole(współrzędneModulo(1, 'x'),
                współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX++;
            współrzędnaY++;
        } else if (plansza.pole(współrzędneModulo(1, 'x'),
                współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX++;
            współrzędnaY--;
        } else if (plansza.pole(współrzędneModulo(-1, 'x'),
                współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX--;
            współrzędnaY++;
        } else if (plansza.pole(współrzędneModulo(-1, 'x'),
                współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX--;
            współrzędnaY--;
        } else if (plansza.pole(współrzędneModulo(1, 'x'),
                współrzędnaY).czyMaJedzenie(numerTury)) {
            współrzędnaX++;
        } else if (plansza.pole(współrzędnaX,
                współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaY++;
        } else if (plansza.pole(współrzędneModulo(-1, 'x'),
                współrzędnaY).czyMaJedzenie(numerTury)) {
            współrzędnaX--;
        } else if (plansza.pole(współrzędnaX,
                współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaY--;
        } else {
            return;
        }
        współrzędnaX = współrzędneModulo(0, 'x');
        współrzędnaY = współrzędneModulo(0, 'y');
        energia += plansza.pole(współrzędnaX, współrzędnaY).oddajJedzenie(numerTury);
        //System.out.println("en " + energia);
    }

    public void instrukcja(int numerTury) {
        if (program.length() == 0 || energia < 1 || numerInstrukcji >= program.length()) {
            zakończonoProgram = true;
            numerInstrukcji = 0;
            return;
        }

        if (program.charAt(numerInstrukcji) == 'l') lewo();
        else if (program.charAt(numerInstrukcji) == 'p') prawo();
        else if (program.charAt(numerInstrukcji) == 'i') idź(numerTury);
        else if (program.charAt(numerInstrukcji) == 'w') wąchaj(numerTury);
        else if (program.charAt(numerInstrukcji) == 'j') jedz(numerTury);
        energia--;
        numerInstrukcji++;
    }

    // metoda zwraca 1 jeśli powstał nowy rob, -1 jeśli obecny rob umarł,
    // 0 w przeciwnym wypadku
    public int ruch () {
        wiek++;
        zakończonoProgram = false;
        // spadek energii
        energia -= kosztTury;
        if (energia < 0) {
            return -1;
        }

        // reprodukcja
        if (wylosuj(prPowielenia) && energia > limitPowielania) {
            return 1;
        }
        return 0;
    }
}
