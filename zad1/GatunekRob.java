public abstract class GatunekRob {
    private int kosztTury;
    private int limitPowielania;
    private double prPowielenia;
    private double prUsunięciaInstrukcji;
    private double prDodaniaInstrukcji;
    private double prZmianyInstrukcji;
    private double ułamekEnergiiRodzica;
    private Plansza plansza;
    private String spisInstrukcji;

    public void kosztTury(int kosztTury) {
        this.kosztTury = kosztTury;
    }

    public void limitPowielania(int limitPowielania) {
        this.limitPowielania = limitPowielania;
    }

    public void prPowielenia(double prPowielenia) {
        this.prPowielenia = prPowielenia;
    }

    public void prUsunięciaInstrukcji(double prUsunięciaInstrukcji) {
        this.prUsunięciaInstrukcji = prUsunięciaInstrukcji;
    }

    public void prDodaniaInstrukcji(double prDodaniaInstrukcji) {
        this.prDodaniaInstrukcji = prDodaniaInstrukcji;
    }

    public void prZmianyInstrukcji(double prZmianyInstrukcji) {
        this.prZmianyInstrukcji = prZmianyInstrukcji;
    }

    public void ułamekEnergiiRodzica(double ułamekEnergiiRodzica) {
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
    }

    public void plansza(Plansza plansza) {
        this.plansza = plansza;
    }

    public void spisInstrukcji(String spisInstrukcji) {
        this.spisInstrukcji = spisInstrukcji;
    }

    public int kosztTury() {
        return kosztTury;
    }

    public int limitPowielania() {
        return limitPowielania;
    }

    public double prPowielenia() {
        return prPowielenia;
    }

    public double prUsunięciaInstrukcji() {
        return prUsunięciaInstrukcji;
    }

    public double prDodaniaInstrukcji() {
        return prDodaniaInstrukcji;
    }

    public double prZmianyInstrukcji() {
        return prZmianyInstrukcji;
    }

    public double ułamekEnergiiRodzica() {
        return ułamekEnergiiRodzica;
    }

    public Plansza plansza() {
        return plansza;
    }

    public String spisInstrukcji() {
        return spisInstrukcji;
    }
}
