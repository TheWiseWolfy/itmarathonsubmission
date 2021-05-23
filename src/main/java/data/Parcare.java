package data;

public class Parcare
{
    private int x;
    private int y;
    private int locuri_libere;
    private int tarif;
    private String nume_parcare;
    private int id_parcare;

    Parcare(int x, int y, int locuri_libere, int tarif, String nume_parcare, int id_parcare)
    {
        this.x = x;
        this.y = y;
        this.locuri_libere = locuri_libere;
        this.tarif = tarif;
        this.nume_parcare = nume_parcare;
        this.id_parcare = id_parcare;
    }

}
