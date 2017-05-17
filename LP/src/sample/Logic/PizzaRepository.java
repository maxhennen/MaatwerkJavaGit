package sample.Logic;

import sample.DomainClasses.Pizza;
import sample.Interfaces.IPizzaSQL;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class PizzaRepository {
    private IPizzaSQL Context;
    public PizzaRepository(IPizzaSQL context){
        Context = context;
    }

    public ArrayList<Pizza> AlleStandaardPizzas(){
        return Context.AlleStandaardPizzas();
    }
}
