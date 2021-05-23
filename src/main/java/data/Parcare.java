package data;

public class Parcare
{
    private float x;
    private float y;
    private int locuri_libere;
    private int tarif;
    private String nume_parcare;
    private int id_parcare;

    Parcare(float x, float y, int locuri_libere, int tarif, String nume_parcare, int id_parcare) {
        this.x = x;
        this.y = y;
        this.locuri_libere = locuri_libere;
        this.tarif = tarif;
        this.nume_parcare = nume_parcare;
        this.id_parcare = id_parcare;
    }

    public float getLat() {
        return x;
    }

    public float getLng() {
        return y;
    }

    public int getID(){
        return id_parcare;
    }

    public int getLocuriLibere(){
        return locuri_libere;
    }

    public String getNume() {
        return nume_parcare;
    }
}
