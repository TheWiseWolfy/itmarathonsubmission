package data;

public class User
{
    private String numar_telefon;
    private String nume;
    private int id_user;

    User(String numar_telefon, String nume, int id_user)
    {
        this.numar_telefon = numar_telefon;
        this.nume = nume;
        this.id_user = id_user;
    }

    public String getNumarTelefon() {
        return numar_telefon;
    }

    public String getNume() {
        return nume;
    }

    public int getID(){
        return id_user;
    }

}
