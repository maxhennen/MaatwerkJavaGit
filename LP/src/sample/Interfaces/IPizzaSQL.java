package sample.Interfaces;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public interface IPizzaSQL {
   ArrayList<Pizza> AlleStandaardPizzas();
   ArrayList<Ingredienten> IngredientenBijPizza(int id);
}
