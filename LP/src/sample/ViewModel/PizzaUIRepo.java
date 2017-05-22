package sample.ViewModel;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;
import sample.Interfaces.IPizzaUI;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class PizzaUIRepo {
    private IPizzaUI Context;
    public PizzaUIRepo(IPizzaUI context){
        Context = context;
    }

    public ArrayList<Pizza> setComboBoxStandaardPizza(){
        return Context.setComboBoxStandaarPizza();
    }


}
