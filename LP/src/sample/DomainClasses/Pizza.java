package sample.DomainClasses;


import sample.Data.PizzaSQLContext;
import sample.Enums.Vorm;
import sample.Interfaces.IPizzaUI;
import sample.Logic.PizzaRepository;
import sample.ViewModel.PizzaUIRepo;

import java.io.FileInputStream;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Created by maxhe on 16-5-2017.
 */
public class Pizza extends Products implements IPizzaUI {
    private int ID;
    private float Formaat;
    private sample.Enums.Vorm Vorm;
    private String Soort;
    private boolean Gluten;
    private ArrayList<Ingredienten> Ingredienten;
    private PizzaRepository PizzaRepo;

    public void setID(int id){ID = id;}
    public void setFormaat(float formaat){Formaat = formaat;}
    public void setVorm (Vorm vorm){Vorm = vorm;}
    public void setSoort(String soort){Soort = soort;}
    public void setGluten(boolean gluten){Gluten = gluten;}
    public void setIngredienten (ArrayList<Ingredienten> ingredienten){Ingredienten = ingredienten;}

    public int getID(){return ID;}
    public float getFormaat(){return Formaat;}
    public Vorm getVorm(){return Vorm;}
    public String getSoort(){return Soort;}
    public boolean getGluten(){return Gluten;}
    public ArrayList<Ingredienten> getIngredienten(){return Ingredienten;}

    public float prijsBepalen(ArrayList<Ingredienten> ingredienten, float formaat){
        float price = 0;
        for (Ingredienten i:ingredienten) {
            price = price +(i.getVerkoopPrijs() * formaat);
        }
        return price;
    }

    public float rondOppervlakBerekenen(int diameter){
        return (float) 0.25 * (float) Math.PI * diameter * diameter;
    }

    public float vierkantOppervlakBerekenen(int lengte, int breedte){
        return lengte * breedte;
    }

    public float driehoekOppervlakBerekenen(int lengte, int breedte, int diepte){
        float var = (lengte + breedte + diepte)/2;
        float berekening = var * (var - lengte) * (var - breedte) * (var - diepte);
        return (float) Math.sqrt(berekening);
    }

    public ArrayList<Pizza> setComboBoxStandaarPizza(){
        PizzaRepo = new PizzaRepository(new PizzaSQLContext());
        return PizzaRepo.AlleStandaardPizzas();
    }
}
