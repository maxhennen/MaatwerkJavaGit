package sample.Interfaces;

import sample.DomainClasses.OverigeProducten;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public interface IOverigeProductenUI {
    ArrayList<OverigeProducten> AlleOverige();
    void updateOverig(int id, String naam, float inkoop, float verkoop, boolean alcohol);
}
