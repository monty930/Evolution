import java.util.ArrayList;

public class Populacja {
    private int ileRobów;
    private int ileUmarło = 0;
    private int ileSięUrodziło;
    private final ArrayList<Rob> roby = new ArrayList<>();
    private int minimalnaDługośćProgramu = -1;
    private int maksymalnaDługośćProgramu = -1;
    private int średniaDługośćProgramu = 0;
    private int minimalnaEnergia = -1;
    private int maksymalnaEnergia = -1;
    private int średniaEnergia = 0;
    private int minimalnyWiek = -1;
    private int maksymalnyWiek = -1;
    private int średniWiek = 0;
    private int liczbaPólZŻywnością;

    public int ileUmarło() {
        return ileUmarło;
    }

    public int ileSięUrodziło() {
        return ileSięUrodziło;
    }

    public int ileRobów() {
        return ileRobów;
    }

    public Populacja(int ileRobów, int kosztTury, int limitPowielania, int początkowaEnergia, double prPowielenia,
                     double prUsunięciaInstrukcji, double prDodaniaInstrukcji, double prZmianyInstrukcji,
                     double ułamekEnergiiRodzica, Plansza plansza, String spisInstrukcji, String początkowyProgram) {
        ileSięUrodziło = ileRobów;
        this.ileRobów = ileRobów;
        for (int i = 0; i < ileRobów; i++) {
            if (początkowaEnergia <= 0) i = ileRobów;
            roby.add(new Rob(kosztTury, limitPowielania, początkowaEnergia, prPowielenia,
                    prUsunięciaInstrukcji, prDodaniaInstrukcji, prZmianyInstrukcji,
                    ułamekEnergiiRodzica, plansza, spisInstrukcji, początkowyProgram, -1, -1, -1));
        }
        System.out.println("Raport przed rozpoczęciem Symulacji:");
        raport(0);
        System.out.println();
    }

    private void raport(int numerTury) {
        System.out.println(numerTury + ", rob: " + ileRobów + ", żyw: " + liczbaPólZŻywnością +
                ", prg: " + minimalnaDługośćProgramu + "/" + średniaDługośćProgramu + "/" +
                maksymalnaDługośćProgramu + ", energ: " + minimalnaEnergia + "/" + średniaEnergia +
                "/" + maksymalnaEnergia + ", wiek: " + minimalnyWiek + "/" + średniWiek +
                "/" + maksymalnyWiek);
        // 245, rob: 120, żyw: 340, prg: 3/4.56/78, energ: 1/4.34/26, wiek: 1/12.46/78
    }

    // metoda symuluje życie każdego Roba po kolei w danej turze
    public void tura(int numerTury) {
        int ileRobówSkończyło = 0;
        ArrayList<Rob> młodeRoby = new ArrayList<>();
        for (int i = 0; i < roby.size(); i++) {
            int wynikRuchu = roby.get(i).ruch();
            if (wynikRuchu == -1) {
                roby.remove(i);
                i--;
                ileRobów--;
                ileUmarło++;
            } else if (wynikRuchu == 1) {
                młodeRoby.add(roby.get(i).powielSię());
                ileRobów++;
                ileSięUrodziło++;
            }
        }
        for (int i = 0; ileRobówSkończyło < roby.size(); i = (i + 1) % roby.size()) {
            if (!roby.get(i).zakończonoProgram()) {
                roby.get(i).instrukcja(numerTury);
                if (roby.get(i).zakończonoProgram()) {
                    ileRobówSkończyło++;
                }
            }
        }

        roby.addAll(młodeRoby);
    }
}
