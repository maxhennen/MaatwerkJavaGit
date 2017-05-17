package sample.ViewModel;

import sample.DomainClasses.Ingredienten;
import sample.Interfaces.IIngredientenUI;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class IngredientenUIRepo {
    private IIngredientenUI Context;
    public IngredientenUIRepo(IIngredientenUI context){
        Context = context;
    }

    public ArrayList<Ingredienten> AlleIngredienten(){
        return Context.AlleIngredienten();
    }
}
