package sample.Interfaces;

import sample.DomainClasses.Ingredienten;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public interface IIngredientenUI {
    ArrayList<Ingredienten> AlleIngredienten();
    void updateIngredient(int id, String naam, float inkoop,float verkoop,boolean halal, boolean vega);
    void opslaanIngredient(String naam, float inkoop,float verkoop,boolean halal, boolean vega);
}
