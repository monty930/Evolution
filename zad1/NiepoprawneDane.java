public class NiepoprawneDane extends Exception {
    public NiepoprawneDane(String komunikat){
        super("Niepoprawne dane: " + komunikat);
    }
}
