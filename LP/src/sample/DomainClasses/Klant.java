package sample.DomainClasses;

/**
 * Created by maxhe on 16-5-2017.
 */
public class Klant {
    private int Klantnummer;
    private String Naam;
    private String Adres;

    public void setKlantnummer(int klantnummer){Klantnummer = klantnummer;}
    public void setNaam(String naam){Naam = naam;}
    public void setAdres(String adres){Adres = adres;}

    public int getKlantnummer(){return Klantnummer;}
    public String getNaam(){return Naam;}
    public String getAdres(){return Adres;}

}
