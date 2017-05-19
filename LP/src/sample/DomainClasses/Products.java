package sample.DomainClasses;

import sun.misc.Compare;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;

/**
 * Created by maxhe on 16-5-2017.
 */
public abstract class Products {
    private String Naam;
    private float VerkoopPrijs;
    private int ID;

    public void setNaam(String naam){Naam = naam;}
    public void setVerkoopPrijs (float verkoopPrijs){VerkoopPrijs = verkoopPrijs;}
    public void setID (int id){ID = id;}

    public String getNaam(){return Naam;}
    public float getVerkoopPrijs(){return VerkoopPrijs;}
    public int getID(){return ID;}

    public abstract class SortName implements Comparable<Products>{
        public int compareTo(Products x){
            return Naam.compareTo(x.Naam);
        }
    }

    public abstract class SortPrice implements Comparable<Products> {
        public int compareTo(Products x) {
            if (VerkoopPrijs > x.VerkoopPrijs) {
                return 1;
            } else if (VerkoopPrijs == x.VerkoopPrijs) {
                return 0;
            } else if (VerkoopPrijs < x.VerkoopPrijs) {
                return -1;
            }
            return 0;
        }
    }

    public String afrondenVerkoopprijs(float verkoopPrijs){
        NumberFormat nf = NumberFormat.getNumberInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyLocalizedPattern("€00,00");
        String output = df.format(verkoopPrijs);
        return output;
    }
    public abstract String ToString();
}
