package sample.Logic;

import sample.DomainClasses.OverigeProducten;

import sample.Interfaces.IOverigeProductenSQL;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class OverigeProductenRepository {
    private IOverigeProductenSQL Context;

    public OverigeProductenRepository(IOverigeProductenSQL context){
        Context = context;
    }

    public ArrayList<OverigeProducten> AlleOverige(){
        return Context.AlleOverige();
    }
}
