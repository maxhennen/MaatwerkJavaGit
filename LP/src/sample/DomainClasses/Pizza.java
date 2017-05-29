package sample.DomainClasses;


import sample.Data.PizzaSQLContext;
import sample.Enums.Vorm;
import sample.Interfaces.IPizzaUI;
import sample.Logic.PizzaRepository;
import sample.ViewModel.PizzaUIRepo;

import javax.swing.*;
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
    private String BodemSoort;
    private boolean Gluten;
    private ArrayList<Ingredienten> Ingredienten;
    private PizzaRepository PizzaRepo;

    public void setFormaat(float formaat){Formaat = formaat;}
    public void setVorm (Vorm vorm){Vorm = vorm;}
    public void setSoort(String soort){BodemSoort = soort;}
    public void setGluten(boolean gluten){Gluten = gluten;}
    public void setIngredienten (ArrayList<Ingredienten> ingredienten){Ingredienten = ingredienten;}

    public float getFormaat(){return Formaat;}
    public Vorm getVorm(){return Vorm;}
    public String getSoort(){return BodemSoort;}
    public boolean getGluten(){return Gluten;}
    public ArrayList<Ingredienten> getIngredienten(){return Ingredienten;}


    public float prijsBepalen(ArrayList<Ingredienten> ingredienten, float formaat){
        float price = 0;
        for (Ingredienten i:ingredienten) {
            price = price +(i.getVerkoopPrijs() * formaat);
        }
        return price;
    }

    public float rondOppervlakBerekenen(float diameter){
        float berekening = (float) 0.25 * (float) Math.PI * diameter * diameter;
        if(checkFormaat(berekening)){
            return berekening;
        }

        return 0;
    }

    public float vierkantOppervlakBerekenen(float lengte, float breedte){
        float berekening = lengte * breedte;

        if(checkFormaat(berekening)){
            return berekening;
        }
        return 0;
    }

    public float driehoekOppervlakBerekenen(float lengte, float breedte, float diepte){
        float var = (lengte + breedte + diepte)/2;
        float berekening = var * (var - lengte) * (var - breedte) * (var - diepte);
        float uitkomst = (float) Math.sqrt(berekening);

        if(checkFormaat(uitkomst)) {
            return (float) Math.sqrt(berekening);
        }
        return 0;
    }

    public float formaatBerekenen(String vorm, float lengte, float breedte, float diepte){

        if(vorm.equals("Rond")){
            return rondOppervlakBerekenen(lengte);
        }
        else if(vorm.equals("Vierkant")){
            return vierkantOppervlakBerekenen(lengte,breedte);
        }
        else if(vorm.equals("Driehoek")){
            return driehoekOppervlakBerekenen(lengte,breedte,diepte);
        }
        return 0;
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
            JOptionPane.showMessageDialog(null,"Geen geldig formaat");
            return false;
        }
    }

    public ArrayList<Pizza> setComboBoxStandaarPizza(){
        PizzaRepo = new PizzaRepository(new PizzaSQLContext());
        ArrayList<Pizza> pizzas = PizzaRepo.AlleStandaardPizzas();
        for (Pizza p: pizzas) {
            p.setVerkoopPrijs((float)(prijsBepalen(PizzaRepo.IngredietenBijPizza(p.getID()),p.getFormaat())* 0.75));
        }
        return pizzas;
    }

    public ArrayList<Ingredienten> IngredientenBijPizza(int id){
        PizzaRepo = new PizzaRepository(new PizzaSQLContext());
        return PizzaRepo.IngredietenBijPizza(id);
    }

    public void UpdatePizza(int id, String naam, float formaat, String vorm, boolean gluten){
        Pizza pizza = new Pizza();
        pizza.setID(id);
        pizza.setNaam(naam);
        pizza.setFormaat(formaat);
        pizza.setVorm(Vorm.valueOf(vorm));
        pizza.setGluten(gluten);

        PizzaRepo = new PizzaRepository(new PizzaSQLContext());
        PizzaRepo.UpdatePizza(pizza);
    }

    public String ToString(){
        return getID() + " : " + getNaam() + " - " + getFormaat() + " - " + afrondenVerkoopprijs(getVerkoopPrijs());
    }
}
