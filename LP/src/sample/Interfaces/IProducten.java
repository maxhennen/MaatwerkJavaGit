package sample.Interfaces;

import sample.DomainClasses.Products;
import sun.dc.pr.PRError;

/**
 * Created by maxhe on 2-6-2017.
 */
public interface IProducten {
    void update(Products product);
    void opslaan(Products product);
    void verwijder(Products product);
}
