package sample.DomainClasses;


import sample.Data.PizzaSQLContext;
import sample.Enums.Vorm;
import sample.Interfaces.IPizzaUI;
import sample.Logic.PizzaRepository;
import sample.ViewModel.PizzaUIRepo;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by maxhe on 16-5-2017.
 */
public class Pizza extends Products implements IPizzaUI {
    private float Formaat;
    private sample.Enums.Vorm Vorm;
    private String Soort;
    private boolean Gluten;
    private ArrayList<Ingredienten> Ingredienten;
    private PizzaRepository PizzaRepo;

    public void setFormaat(float formaat){Formaat = formaat;}
    public void setVorm (Vorm vorm){Vorm = vorm;}
    public void setSoort(String soort){Soort = soort;}
    public void setGluten(boolean gluten){Gluten = gluten;}
    public void setIngredienten (ArrayList<Ingredienten> ingredienten){Ingredienten = ingredienten;}

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
        float berekening = (float) 0.25 * (float) Math.PI * diameter * diameter;
        if(checkFormaat(berekening)){
            return berekening;
        }

        return 0;
    }

    public float vierkantOppervlakBerekenen(int lengte, int breedte){
        float berekening = lengte * breedte;

        if(checkFormaat(berekening)){
            return berekening;
        }
        return 0;
    }

    public float driehoekOppervlakBerekenen(int lengte, int breedte, int diepte){
        float var = (lengte + breedte + diepte)/2;
        float berekening = var * (var - lengte) * (var - breedte) * (var - diepte);
        float uitkomst = (float) Math.sqrt(berekening);

        if(checkFormaat(uitkomst)) {
            return (float) Math.sqrt(berekening);
        }
        return 0;
    }

    public void formaatBerekenen(String vorm, int lengte, int breedte, int diepte){

        if(vorm.equals("Rond")){
            setFormaat(rondOppervlakBerekenen(lengte));
        }
        else if(vorm.equals("Vierkant")){
            setFormaat(vierkantOppervlakBerekenen(lengte,breedte));
        }
        else if(vorm.equals("Driehoek")){
            setFormaat(driehoekOppervlakBerekenen(lengte,breedte,diepte));
        }

    }

    public boolean checkFormaat(float formaat){
        try {
            if (formaat > 7 && formaat < 500) {
                return true;
            }

            else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.print("Geen geldig formaat");
            return false;
        }
    }

    public ArrayList<Pizza> setComboBoxStandaarPizza(){
        PizzaRepo = new PizzaRepository(new PizzaSQLContext());
        ArrayList<Pizza> pizzas = PizzaRepo.AlleStandaardPizzas();
        for (Pizza p: pizzas) {
            p.setVerkoopPrijs(prijsBepalen(PizzaRepo.IngredietenBijPizza(p.getID()),p.getFormaat()));
        }
        return pizzas;
    }

    public String ToString(){
        return getID() + " : " + getNaam() + " - " + getFormaat() + " - " + afrondenVerkoopprijs(getVerkoopPrijs()/100);
    }
}
