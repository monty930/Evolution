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
            roby.add(new Rob(kosztTury, limitPowielania, początkowaEnergia, prPowielenia,
                    prUsunięciaInstrukcji, prDodaniaInstrukcji, prZmianyInstrukcji,
                    ułamekEnergiiRodzica, plansza, spisInstrukcji, początkowyProgram));
        }
    }

    // metoda symuluje życie każdego Roba po kolei w danej turze
    public void Żyj(int numerTury) {
        for (int i = 0; i < roby.size(); i++) {
            if (numerTury == 300) {
                System.out.println("rob numer " + i + " program " + roby.get(i).program());
            }
            int wynikRuchu = roby.get(i).ruch(numerTury);
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
    }
}
