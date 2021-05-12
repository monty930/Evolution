import java.util.Random;

public class Rob extends GatunekRob {
    private int numerInstrukcji = 0;
    private int kierunek; // 0 - północ, 1 - wschód, 2 - południe, 3 - zachód
    private int energia;
    private final String program;
    private boolean czyŻywy = true;
    private int współrzędnaX;
    private int współrzędnaY;

    public boolean czyŻywy() {
        return czyŻywy;
    }

    public String program() {
        return program;
    }

    // konstruktor pierwotnego praRoba
    public Rob(int kosztTury, int limitPowielania, int początkowaEnergia, double prPowielenia,
               double prUsunięciaInstrukcji, double prDodaniaInstrukcji, double prZmianyInstrukcji,
               double ułamekEnergiiRodzica, Plansza plansza, String spisInstrukcji, String początkowyProgram) {
        this.program = początkowyProgram;
        this.energia = początkowaEnergia;
        super.kosztTury(kosztTury);
        super.limitPowielania(limitPowielania);
        super.prPowielenia(prPowielenia);
        super.prUsunięciaInstrukcji(prUsunięciaInstrukcji);
        super.prDodaniaInstrukcji(prDodaniaInstrukcji);
        super.prZmianyInstrukcji(prZmianyInstrukcji);
        super.ułamekEnergiiRodzica(ułamekEnergiiRodzica);
        super.plansza(plansza);
        super.spisInstrukcji(spisInstrukcji);
        Random r = new Random();
        współrzędnaX = r.nextInt(super.plansza().liczbaKolumn());
        współrzędnaY = r.nextInt(super.plansza().liczbaWierszy());
        kierunek = r.nextInt(4);
    }

    public Rob(int energia, String program) {
        this.program = program;
        this.energia = energia;
        if (this.energia <= 0) {
            this.czyŻywy = false;
        }

        // położenie na planszy
        Random r = new Random();
        współrzędnaX = r.nextInt();
        współrzędnaY = r.nextInt();
        kierunek = r.nextInt(4);
    }

    /*public int kierunek() {
        return kierunek;
    }

    public int energia() {
        return energia;
    }*/

    private boolean wylosuj (double prawdopodobieństwo) {
        Random r = new Random();
        return r.nextDouble() <= prawdopodobieństwo;
    }

    public Rob powielSię () {
        //System.out.println("powielanie...");
        int energiaNowegoRoba = (int) ((double )this.energia * super.ułamekEnergiiRodzica());
        this.energia -= energiaNowegoRoba;
        String nowyProgram = program;
        if (wylosuj(super.prUsunięciaInstrukcji()) && nowyProgram.length() != 0 && super.spisInstrukcji().length() != 0) {
            nowyProgram = nowyProgram.substring(0, nowyProgram.length() - 1);
        }
        if (wylosuj(super.prDodaniaInstrukcji()) && super.spisInstrukcji().length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(super.spisInstrukcji().length());
            nowyProgram += Character.toString(super.spisInstrukcji().charAt(numerNowejInstrukcji));
        }
        if (wylosuj(super.prZmianyInstrukcji()) && nowyProgram.length() != 0 && super.spisInstrukcji().length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(super.spisInstrukcji().length());
            int numerStarejInstrukcji = r.nextInt(nowyProgram.length());
            nowyProgram = nowyProgram.substring(0, numerStarejInstrukcji) +
                    super.spisInstrukcji().charAt(numerNowejInstrukcji) +
                    nowyProgram.substring(numerStarejInstrukcji + 1);
        }
        return new Rob(kosztTury(), limitPowielania(), energiaNowegoRoba, prPowielenia(), prUsunięciaInstrukcji(),
                prDodaniaInstrukcji(), prZmianyInstrukcji(), ułamekEnergiiRodzica(), plansza(), spisInstrukcji(), nowyProgram);
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
        energia += super.plansza().pole(współrzędnaX, współrzędnaY).oddajJedzenie(numerTury);
        //System.out.println("en" + energia);
    }

    public void wąchaj(int numerTury) {
        if (super.plansza().pole(współrzędneModulo(1, 'x'), współrzędnaY).czyMaJedzenie(numerTury)) {
            kierunek = 1;
        } else if (super.plansza().pole(współrzędneModulo(-1, 'x'), współrzędnaY).czyMaJedzenie(numerTury)) {
            kierunek = 3;
        } else if (super.plansza().pole(współrzędnaX, współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            kierunek = 0;
        } else if (super.plansza().pole(współrzędnaX, współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            kierunek = 2;
        }
    }

    private int współrzędneModulo(int liczba, char współrzędna) {
        if (współrzędna == 'x') {
            if ((współrzędnaX + liczba) % super.plansza().liczbaKolumn() < 0) {
                //System.out.println("tu " +(współrzędnaX + liczba) % super.plansza().liczbaKolumn() + super.plansza().liczbaKolumn());
                return (współrzędnaX + liczba) % super.plansza().liczbaKolumn() + super.plansza().liczbaKolumn();
            }
            //System.out.println("tu2 " +(współrzędnaX + liczba) % super.plansza().liczbaKolumn());
            return (współrzędnaX + liczba) % super.plansza().liczbaKolumn();
        }
        if (współrzędna == 'y') {
            if ((współrzędnaY + liczba) % super.plansza().liczbaWierszy() < 0) {
                //System.out.println((współrzędnaY + liczba) % super.plansza().liczbaWierszy() + super.plansza().liczbaWierszy());
                return (współrzędnaY + liczba) % super.plansza().liczbaWierszy() + super.plansza().liczbaWierszy();
            }
            //System.out.println("tu4 " + (współrzędnaY + liczba) % super.plansza().liczbaWierszy() );
            return (współrzędnaY + liczba) % super.plansza().liczbaWierszy();
        }
        System.out.println("NIEEEEEEEEEEEEEEEE");
        return współrzędnaX + liczba;
    }

    public void jedz(int numerTury) {
        //System.out.println("kol: " + super.plansza().liczbaKolumn() + " wier: " + super.plansza().liczbaWierszy());
        if (super.plansza().pole(współrzędneModulo(1, 'x'),
                współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX++;
            współrzędnaY++;
        } else if (super.plansza().pole(współrzędneModulo(1, 'x'),
                współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX++;
            współrzędnaY--;
        } else if (super.plansza().pole(współrzędneModulo(-1, 'x'),
                współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX--;
            współrzędnaY++;
        } else if (super.plansza().pole(współrzędneModulo(-1, 'x'),
                współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaX--;
            współrzędnaY--;
        } else if (super.plansza().pole(współrzędneModulo(1, 'x'),
                współrzędnaY).czyMaJedzenie(numerTury)) {
            współrzędnaX++;
        } else if (super.plansza().pole(współrzędnaX,
                współrzędneModulo(1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaY++;
        } else if (super.plansza().pole(współrzędneModulo(-1, 'x'),
                współrzędnaY).czyMaJedzenie(numerTury)) {
            współrzędnaX--;
        } else if (super.plansza().pole(współrzędnaX,
                współrzędneModulo(-1, 'y')).czyMaJedzenie(numerTury)) {
            współrzędnaY--;
        } else {
            return;
        }
        współrzędnaX = współrzędneModulo(0, 'x');
        współrzędnaY = współrzędneModulo(0, 'y');
        energia += super.plansza().pole(współrzędnaX, współrzędnaY).oddajJedzenie(numerTury);
        //System.out.println("en " + energia);
    }

    // metoda zwraca 1 jeśli powstał nowy rob, -1 jeśli obecny rob umarł,
    // 0 w przeciwnym wypadku
    public int ruch (int numerTury) {
        // spadek energii
        energia -= super.kosztTury();
        if (energia < 0) {
            czyŻywy = false;
            return -1;
        }

        // wykonanie instrukcji
        if (energia >= 1 && program.length() > 0) { // Rob ma wystarczająco energii na wykonanie instrukcji
            if (program.charAt(numerInstrukcji) == 'l') lewo();
            else if (program.charAt(numerInstrukcji) == 'p') prawo();
            else if (program.charAt(numerInstrukcji) == 'i') idź(numerTury);
            else if (program.charAt(numerInstrukcji) == 'w') wąchaj(numerTury);
            else if (program.charAt(numerInstrukcji) == 'j') jedz(numerTury);
            energia--;
        }
        if (program.length() != 0) numerInstrukcji = (numerInstrukcji + 1) % program.length();
        else {
            numerInstrukcji = 0;
        }

        // reprodukcja
        if (wylosuj(super.prPowielenia()) && energia > super.limitPowielania()) {
            return 1;
        }
        return 0;
    }
}
