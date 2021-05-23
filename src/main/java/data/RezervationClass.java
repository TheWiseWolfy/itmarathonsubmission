package data;

public class RezervationClass {

    private String numeParcare;
    private String masina;
    private int id;
    private static int lastId;

    public RezervationClass(String parcare, String masina, int id) {
        numeParcare = parcare;
        this.masina = masina;
        this.id = id;
        lastId = id;
    }

    public String getNumeParcare() {
        return numeParcare;
    }

    public String getMasina() {
        return masina;
    }

    public int getId() {
        return id;
    }

    public static int getLastId() {
        return lastId;
    }
}
