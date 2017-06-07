package sample.Logic;

import sample.DomainClasses.Products;
import sample.Interfaces.IProducten;

/**
 * Created by maxhe on 2-6-2017.
 */
public class ProductenRepository {
    private IProducten Context;

    public ProductenRepository(IProducten context){
        Context = context;
    }

    public void opslaan(Products product){
        Context.opslaan(product);
    }

    public void update(Products product){
        Context.update(product);
    }
    public void verwijder(Products product){Context.verwijder(product);}
}
