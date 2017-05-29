package sample.ViewModel;

import com.sun.org.apache.regexp.internal.RE;
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

    public ArrayList<Ingredienten> IngredientenBijPizza(int id){return Context.IngredientenBijPizza(id);}
    public void UpdatePizza(int id, String naam, float formaat, String vorm, boolean gluten){Context.UpdatePizza(id, naam,formaat,vorm,gluten);}
}
