import java.util.ArrayList;

public class Populacja {
    private int ileRobów;
    private ArrayList<Rob> roby = new ArrayList<>();

    public int ileRobów() {
        return ileRobów;
    }

    public Populacja(int ileRobów, int energia, String program, Plansza plansza,
                     int kosztTury, int limitPowielania, double prPowielenia) {
        this.ileRobów = ileRobów;
        for (int i = 0; i < ileRobów; i++) {
            roby.add(new Rob(energia, program, plansza, kosztTury, limitPowielania, prPowielenia));
        }
    }

    // metoda symuluje życie każdego Roba po kolei w danej turze
    public void Żyj() {
        for (Rob rob : roby) {
            rob.toString();
        }
    }
}
