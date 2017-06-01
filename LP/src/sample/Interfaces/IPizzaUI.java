package sample.Interfaces;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public interface IPizzaUI {
    ArrayList<Pizza> setComboBoxStandaarPizza();
    ArrayList<Ingredienten> IngredientenBijPizza( int id);
    void UpdatePizza(int id, String naam, float formaat, String vorm, boolean gluten,ArrayList<Ingredienten> ingredienten);
    void OpslaanPizza(String naam, float formaat, String vorm, boolean gluten,ArrayList<Ingredienten> ingredienten);
}
