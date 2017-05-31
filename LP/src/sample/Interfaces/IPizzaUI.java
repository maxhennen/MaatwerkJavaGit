package sample.Interfaces;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public interface IPizzaUI {
    public ArrayList<Pizza> setComboBoxStandaarPizza();
    public ArrayList<Ingredienten> IngredientenBijPizza( int id);
    public void UpdatePizza(int id, String naam, float formaat, String vorm, boolean gluten,ArrayList<Ingredienten> ingredienten);
}
