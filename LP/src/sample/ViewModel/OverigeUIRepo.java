package sample.ViewModel;

import sample.DomainClasses.OverigeProducten;
import sample.Interfaces.IOverigeProductenUI;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class OverigeUIRepo {
    private IOverigeProductenUI Context;

    public OverigeUIRepo(IOverigeProductenUI context){
        Context = context;
    }

    public ArrayList<OverigeProducten> AlleOverige(){
        return Context.AlleOverige();
    }

    public void updateOverig(int id, String naam, float inkoop, float verkoop, boolean alcohol){Context.updateOverig(id, naam,inkoop,verkoop,alcohol);}

}
