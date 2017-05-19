package sample.Interfaces;

import sample.DomainClasses.Pizza;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public interface IPizzaUI {
    public ArrayList<Pizza> setComboBoxStandaarPizza();
    public void formaatBerekenen(String vorm,int lengte,int diepte,int breedte);
}
