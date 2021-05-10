import java.util.Random;

public class Rob {
    /*private int numerInstrukcji = 0;
    private int kierunek; // 0 - północ, 1 - wschód, 2 - południe, 3 - zachód
    private int energia;
    private String program;
    private boolean czyŻywy = true;
    private int współrzędnaX;
    private int współrzędnaY;
    private Plansza plansza;

    public boolean czyŻywy() {
        return czyŻywy;
    }

    public Rob(int energia, String program, Plansza plansza) {
        this.program = program;
        this.energia = energia;
        if (this.energia <= 0) {
            this.czyŻywy = false;
        }

        // położenie na planszy
        this.plansza = plansza;
        Random r = new Random();
        współrzędnaX = r.nextInt(plansza.liczbaKolumn());
        współrzędnaY = r.nextInt(plansza.liczbaWierszy());
    }

    public int kierunek() {
        return kierunek;
    }

    public int energia() {
        return energia;
    }

    private boolean wylosuj (double prawdopodobieństwo) {
        return true;
    }

    public Rob powielSię (double ułamekEnergiiRodzica, double prUsunięciaInstrukcji,
                          double prDodaniaInstrukcji, double prZmianyInstrukcji, String spis) {
        int energiaNowegoRoba = (int) ((double )this.energia * ułamekEnergiiRodzica);
        this.energia -= energiaNowegoRoba;
        String nowyProgram = program;
        if (wylosuj(prUsunięciaInstrukcji) && nowyProgram.length() != 0 && spis.length() != 0) {
            nowyProgram = nowyProgram.substring(0, nowyProgram.length() - 1);
        }
        if (wylosuj(prDodaniaInstrukcji) && spis.length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(spis.length());
            nowyProgram += Character.toString(spis.charAt(numerNowejInstrukcji));
        }
        if (wylosuj(prZmianyInstrukcji) && nowyProgram.length() != 0 && spis.length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(spis.length());
            int numerStarejInstrukcji = r.nextInt(nowyProgram.length());
            nowyProgram = nowyProgram.substring(0, numerStarejInstrukcji) +
                    spis.charAt(numerNowejInstrukcji) +
                    nowyProgram.substring(numerStarejInstrukcji + 1);
        }
        return new Rob(energiaNowegoRoba, nowyProgram, plansza);
    }

    public void lewo() {
        kierunek = (kierunek - 1) % 4;
    }

    public void prawo() {
        kierunek = (kierunek + 1) % 4;
    }

    public void idź() {
        if (kierunek == 0) { // północ
            współrzędnaY = (współrzędnaY + 1) % plansza.liczbaWierszy();
        }
        if (kierunek == 1) { // wschód
            współrzędnaX = (współrzędnaX + 1) % plansza.liczbaKolumn();
        }
        if (kierunek == 2) { // południe
            współrzędnaY = (współrzędnaY - 1) % plansza.liczbaWierszy();
        }
        if (kierunek == 3) { // zachód
            współrzędnaX = (współrzędnaX - 1) % plansza.liczbaKolumn();
        }
        //if (czyJedzenie(współrzędnaX, współrzędnaY)) {
        // jedz
        //}
    }

    public void wąchaj() {

    }

    public void jedz() {

    }

    // metoda zwraca 1 jeśli powstał nowy rob, -1 jeśli obecny rob umarł,
    // 0 w przeciwnym wypadku
    public int ruch (int kosztTury, int limitPowielania, double prPowielenia) {
        // spadek energii
        energia -= kosztTury;
        if (energia < 0) {
            czyŻywy = false;
            return -1;
        }

        // wykonanie instrukcji
        if (energia >= 1) { // Rob ma wystarczająco energii na wykonanie instrukcji
            if (program.charAt(numerInstrukcji) == 'l') lewo();
            else if (program.charAt(numerInstrukcji) == 'p') prawo();
            else if (program.charAt(numerInstrukcji) == 'i') idź();
            else if (program.charAt(numerInstrukcji) == 'w') wąchaj();
            else if (program.charAt(numerInstrukcji) == 'j') jedz();
            energia--;
        }
        numerInstrukcji = (numerInstrukcji + 1) % program.length();

        // reprodukcja
        if (wylosuj(prPowielenia) && energia > limitPowielania) {
            return 1;
        }
        return 0;
    }
    */
}
