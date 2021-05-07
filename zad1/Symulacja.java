import java.io.*;
import java.util.*;

public class Symulacja {
    public static void main(String[] args) throws IOException {
        File planszaPlik = new File(args[0]);
        Plansza plansza = new Plansza();
        plansza.pobierzDane(planszaPlik);

    }
}
