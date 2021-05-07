import java.io.*;
import java.util.*;

public class Plansza {
    public void pobierzDane (File plansza) {
        try {
            Scanner czytajPlanszę = new Scanner(plansza);

            int liczbaWierszy = 0;
            while (czytajPlanszę.hasNextLine()) {
                liczbaWierszy++;
                System.out.println("nowa linia");
                String linia = czytajPlanszę.nextLine();
                System.out.println(linia);

                int liczbaKolumn = 0;
                for (int i = 0; i < linia.length(); i++) {
                    char znak = linia.charAt(i);
                    if (znak != ' ' && znak != 'x' && znak != '\n') {
                        throw new NiepoprawneDane("Niedozwolony znak w pliku \"Plansza.txt\".");
                    }

                }
            }
            czytajPlanszę.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Nie znaleziono pliku \"Plansza.txt\".");
        } catch (NiepoprawneDane e) {
            System.out.print(e.getMessage());
        }
    }
}
