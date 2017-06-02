package sample.Logic;

import sample.DomainClasses.Ingredienten;
import sample.Interfaces.IIngredientenSQL;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class IngredientenRepository {
    private IIngredientenSQL Context;

    public IngredientenRepository(IIngredientenSQL context){
        Context = context;
    }

    public ArrayList<Ingredienten> AlleIngredienten(){
        return Context.AlleIngredienten();
    }
    }
