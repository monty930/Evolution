import java.util.ArrayList;

public class Populacja {
    private int ileRobów;
    private int ileUmarło = 0;
    private int ileSięUrodziło;
    private final ArrayList<Rob> roby = new ArrayList<>();

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
                    ułamekEnergiiRodzica, plansza, spisInstrukcji, początkowyProgram));
        }
    }

    // metoda symuluje życie każdego Roba po kolei w danej turze
    public void Żyj(int numerTury) {
        int licz = 0;
        for (int i = 0; i < roby.size(); i++) {
            int wynikRuchu = roby.get(i).ruch();
            if (wynikRuchu == -1) {
                roby.remove(i);
                i--;
                ileRobów--;
                ileUmarło++;
            } else if (wynikRuchu == 1) {
                roby.add(roby.get(i).powielSię());
                ileRobów++;
                ileSięUrodziło++;
            }
        }
        for (int i = 0; licz < roby.size(); i = (i + 1) % roby.size()) {
            //System.out.println("i: " + i);
            if (!roby.get(i).zakończonoProgram()) {
                roby.get(i).instrukcja(numerTury);
                if (roby.get(i).zakończonoProgram()) {
                    licz++;
                }
            }
        }
    }
}
