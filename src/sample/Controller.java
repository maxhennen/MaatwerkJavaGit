package sample;

import javafx.event.ActionEvent;

import javax.swing.*;

public class Controller {
    public void Min(int Getal1,int Getal2){
        int uitkomst = Getal1 - Getal2;
        JOptionPane.showMessageDialog(null,"Uitkomst is: " + String.valueOf(uitkomst));
    }

    public void Plus(int Getal1,int Getal2){
        int uitkomst = Getal1 - Getal2;
        JOptionPane.showMessageDialog(null,"Uitkomst is: " + String.valueOf(uitkomst));
    }

    public void Devide(int Getal1,int Getal2){
        int uitkomst = Getal1 / Getal2;
        JOptionPane.showMessageDialog(null,"Uitkomst is: " + String.valueOf(uitkomst));
    }

    public void Multiply(int Getal1,int Getal2){
        int uitkomst = Getal1 * Getal2;
        JOptionPane.showMessageDialog(null,"Uitkomst is: " + String.valueOf(uitkomst));
    }

    public void Plus(ActionEvent actionEvent,int getal1, int getal2) {

    }
}
