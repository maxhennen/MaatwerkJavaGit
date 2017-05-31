package sample.Views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.OverigeProducten;
import sample.DomainClasses.Pizza;
import sample.DomainClasses.Products;
import sample.Enums.Vorm;
import sample.ViewModel.IngredientenUIRepo;
import sample.ViewModel.OverigeUIRepo;
import sample.ViewModel.PizzaUIRepo;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by maxhe on 29-5-2017.
 */
public class ProductenController extends IController {
    private AnchorPane AnchorPane;
    private TextField tfPizzaX;
    private ComboBox cbPizzaVorm;
    private TextField tfPizzaNaam;
    private ComboBox cbPizza;
    private Button btnIngredientenOpslaan;
    private Button btnIngredientenWijzig;
    private ComboBox cbIngredientenVega;
    private ComboBox cbIngredientenHalal;
    private TextField tfIngredientenInkoop;
    private TextField tfIngredientenVerkoop;
    private TextField tfIngredientenNaam;
    private ComboBox cbIngredienten;
    private TextField tfPizzaY;
    private TextField tfPizzaZ;
    private ComboBox cbPizzaGluten;
    private Button btnPizzaWijzigen;
    private Button btnPizzaOpslaan;
    private ComboBox cbOverig;
    private TextField tfOverigNaam;
    private TextField tfOverigInkoop;
    private TextField tfOverigVerkoop;
    private ComboBox cbOverigAlcohol;
    private Button btnOverigWijzigen;
    private Button btnOverigOpslaan;
    private ListView lstIngredienten;
    private TextField tfPizzaFormaat;
    private ComboBox cbPizzaIngredienten;
    private Button btnIngredientToevoegen;
    private Button btnIngredientVerwijderen;

    private PizzaUIRepo PizzaRepo;
    private IngredientenUIRepo IngredientenRepo;
    private OverigeUIRepo OverigeRepo;

    private ArrayList<Ingredienten> Ingredienten;
    private ArrayList<Pizza> Pizzas;
    private ArrayList<OverigeProducten> Overigen;

    private int PizzaID;
    private int IngredientID;
    private int OverigID;

    public void setAnchorpane(AnchorPane anchorpane){AnchorPane = anchorpane;};

    public void setLayout(){
        cbIngredienten = new ComboBox();
        cbIngredienten.setLayoutY(63);
        cbIngredienten.setLayoutX(14);
        cbIngredienten.setPrefWidth(212);
        cbIngredienten.setPrefHeight(25);
        cbIngredienten.setPromptText("Ingredienten");
        cbIngredienten.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                wijzigProduct(StringToProduct(cbIngredienten.getSelectionModel().getSelectedItem().toString(),"Ingredienten"));
                getIDs(cbIngredienten.getSelectionModel().getSelectedItem().toString(),"Ingredient");
            }
        });
        AnchorPane.getChildren().add(cbIngredienten);

        tfIngredientenNaam = new TextField();
        tfIngredientenNaam.setLayoutY(125);
        tfIngredientenNaam.setLayoutX(14);
        tfIngredientenNaam.setPrefWidth(200);
        tfIngredientenNaam.setPrefHeight(26);
        tfIngredientenNaam.setPromptText("Naam");
        AnchorPane.getChildren().add(tfIngredientenNaam);

        tfIngredientenVerkoop = new TextField();
        tfIngredientenVerkoop.setLayoutX(14);
        tfIngredientenVerkoop.setLayoutY(175);
        tfIngredientenVerkoop.setPrefHeight(26);
        tfIngredientenVerkoop.setPrefWidth(200);
        tfIngredientenVerkoop.setPromptText("Verkoop");
        AnchorPane.getChildren().add(tfIngredientenVerkoop);

        tfIngredientenInkoop = new TextField();
        tfIngredientenInkoop.setLayoutY(221);
        tfIngredientenInkoop.setLayoutX(14);
        tfIngredientenInkoop.setPrefWidth(200);
        tfIngredientenInkoop.setPrefHeight(26);
        tfIngredientenInkoop.setPromptText("Inkoop");
        AnchorPane.getChildren().add(tfIngredientenInkoop);

        cbIngredientenHalal = new ComboBox();
        cbIngredientenHalal.setLayoutY(283);
        cbIngredientenHalal.setLayoutX(14);
        cbIngredientenHalal.setPrefHeight(25);
        cbIngredientenHalal.setPrefWidth(200);
        cbIngredientenHalal.setPromptText("Halal");
        cbIngredientenHalal.getItems().add("Ja");
        cbIngredientenHalal.getItems().add("Nee");
        AnchorPane.getChildren().add(cbIngredientenHalal);

        cbIngredientenVega = new ComboBox();
        cbIngredientenVega.setLayoutX(14);
        cbIngredientenVega.setLayoutY(335);
        cbIngredientenVega.setPrefWidth(200);
        cbIngredientenVega.setPrefHeight(25);
        cbIngredientenVega.setPromptText("Vega");
        cbIngredientenVega.getItems().add("Ja");
        cbIngredientenVega.getItems().add("Nee");
        AnchorPane.getChildren().add(cbIngredientenVega);

        btnIngredientenWijzig = new Button();
        btnIngredientenWijzig.setLayoutY(396);
        btnIngredientenWijzig.setLayoutX(14);
        btnIngredientenWijzig.setPrefWidth(100);
        btnIngredientenWijzig.setPrefHeight(27);
        btnIngredientenWijzig.setText("Wijzig");
        AnchorPane.getChildren().add(btnIngredientenWijzig);

        btnIngredientenOpslaan = new Button();
        btnIngredientenOpslaan.setLayoutY(397);
        btnIngredientenOpslaan.setLayoutX(114);
        btnIngredientenOpslaan.setPrefHeight(27);
        btnIngredientenOpslaan.setPrefWidth(100);
        btnIngredientenOpslaan.setText("Opslaan");
        AnchorPane.getChildren().add(btnIngredientenOpslaan);

        cbPizza = new ComboBox();
        cbPizza.setLayoutY(63);
        cbPizza.setLayoutX(258);
        cbPizza.setLayoutY(63);
        cbPizza.setPrefHeight(25);
        cbPizza.setPrefWidth(212);
        cbPizza.setPromptText("Pizza");
        cbPizza.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                lstIngredienten.getItems().clear();
                wijzigProduct(StringToProduct(cbPizza.getSelectionModel().getSelectedItem().toString(),"Pizza"));
                getIDs(cbPizza.getSelectionModel().getSelectedItem().toString(),"Pizza");
            }
        });
        AnchorPane.getChildren().add(cbPizza);

        tfPizzaNaam = new TextField();
        tfPizzaNaam.setLayoutX(258);
        tfPizzaNaam.setLayoutY(125);
        tfPizzaNaam.setPrefHeight(26);
        tfPizzaNaam.setPrefWidth(200);
        tfPizzaNaam.setPromptText("Naam");
        AnchorPane.getChildren().add(tfPizzaNaam);

        cbPizzaVorm = new ComboBox();
        cbPizzaVorm.setLayoutY(177);
        cbPizzaVorm.setLayoutX(258);
        cbPizzaVorm.setPrefWidth(200);
        cbPizzaVorm.setPrefHeight(25);
        cbPizzaVorm.setPromptText("Vorm");
        cbPizzaVorm.getItems().setAll(Vorm.values());
        AnchorPane.getChildren().add(cbPizzaVorm);

        tfPizzaFormaat = new TextField();
        tfPizzaFormaat.setLayoutX(258);
        tfPizzaFormaat.setLayoutY(221);
        tfPizzaFormaat.setPrefWidth(200);
        tfPizzaFormaat.setPrefHeight(26);
        tfPizzaFormaat.setPromptText("Formaat");
        AnchorPane.getChildren().add(tfPizzaFormaat);

        cbPizzaGluten = new ComboBox();
        cbPizzaGluten.setLayoutX(252);
        cbPizzaGluten.setLayoutY(284);
        cbPizzaGluten.setPrefHeight(25);
        cbPizzaGluten.setPrefWidth(80);
        cbPizzaGluten.setPromptText("Gluten");
        cbPizzaGluten.getItems().add("Ja");
        cbPizzaGluten.getItems().add("Nee");
        AnchorPane.getChildren().add(cbPizzaGluten);

        cbPizzaIngredienten = new ComboBox();
        cbPizzaIngredienten.setLayoutY(334);
        cbPizzaIngredienten.setLayoutX(252);
        cbPizzaIngredienten.setPrefWidth(212);
        cbPizzaIngredienten.setPrefHeight(25);
        cbPizzaIngredienten.setPromptText("Ingredienten");
        AnchorPane.getChildren().add(cbPizzaIngredienten);

        btnIngredientToevoegen = new Button();
        btnIngredientToevoegen.setLayoutY(384);
        btnIngredientToevoegen.setLayoutX(252);
        btnIngredientToevoegen.setPrefWidth(100);
        btnIngredientToevoegen.setPrefHeight(27);
        btnIngredientToevoegen.setText("Toevoegen");
        btnIngredientToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ingredientToevoegen(cbPizzaIngredienten.getSelectionModel().getSelectedItem().toString());
            }
        });
        AnchorPane.getChildren().add(btnIngredientToevoegen);

        btnIngredientVerwijderen = new Button();
        btnIngredientVerwijderen.setLayoutY(384);
        btnIngredientVerwijderen.setLayoutX(352);
        btnIngredientVerwijderen.setPrefHeight(27);
        btnIngredientVerwijderen.setPrefWidth(100);
        btnIngredientVerwijderen.setText("Verwijderen");
        btnIngredientVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lstIngredienten.getItems().remove(lstIngredienten.getSelectionModel().getSelectedItem());
            }
        });
        AnchorPane.getChildren().add(btnIngredientVerwijderen);

        lstIngredienten = new ListView();
        lstIngredienten.setLayoutY(432);
        lstIngredienten.setLayoutX(252);
        lstIngredienten.setPrefHeight(150);
        lstIngredienten.setPrefWidth(200);
        AnchorPane.getChildren().add(lstIngredienten);

        btnPizzaWijzigen = new Button();
        btnPizzaWijzigen.setLayoutX(252);
        btnPizzaWijzigen.setLayoutY(592);
        btnPizzaWijzigen.setPrefWidth(100);
        btnPizzaWijzigen.setPrefHeight(27);
        btnPizzaWijzigen.setText("Wijzigen");
        btnPizzaWijzigen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Update("Pizza");
            }
        });
        AnchorPane.getChildren().add(btnPizzaWijzigen);

        btnPizzaOpslaan = new Button();
        btnPizzaOpslaan.setLayoutY(592);
        btnPizzaOpslaan.setLayoutX(352);
        btnPizzaOpslaan.setPrefHeight(27);
        btnPizzaOpslaan.setPrefWidth(100);
        btnPizzaOpslaan.setText("Opslaan");
        AnchorPane.getChildren().add(btnPizzaOpslaan);

        cbOverig = new ComboBox();
        cbOverig.setLayoutY(63);
        cbOverig.setLayoutX(508);
        cbOverig.setPrefWidth(212);
        cbOverig.setPrefHeight(25);
        cbOverig.setPromptText("Overige Producten");
        cbOverig.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                wijzigProduct(StringToProduct(cbOverig.getSelectionModel().getSelectedItem().toString(),"Overige"));
                getIDs(cbOverig.getSelectionModel().getSelectedItem().toString(),"Overig");
            }
        });
        AnchorPane.getChildren().add(cbOverig);

        tfOverigNaam = new TextField();
        tfOverigNaam.setLayoutY(125);
        tfOverigNaam.setLayoutX(508);
        tfOverigNaam.setPrefHeight(26);
        tfOverigNaam.setPrefWidth(200);
        tfOverigNaam.setPromptText("Naam");
        AnchorPane.getChildren().add(tfOverigNaam);

        tfOverigVerkoop = new TextField();
        tfOverigVerkoop.setLayoutX(508);
        tfOverigVerkoop.setLayoutY(175);
        tfOverigVerkoop.setPrefWidth(200);
        tfOverigVerkoop.setPrefHeight(26);
        tfOverigVerkoop.setPromptText("Verkoop");
        AnchorPane.getChildren().add(tfOverigVerkoop);

        tfOverigInkoop = new TextField();
        tfOverigInkoop.setLayoutY(221);
        tfOverigInkoop.setLayoutX(508);
        tfOverigInkoop.setPrefHeight(26);
        tfOverigInkoop.setPrefWidth(200);
        tfOverigInkoop.setPromptText("Inkoop");
        AnchorPane.getChildren().add(tfOverigInkoop);

        cbOverigAlcohol = new ComboBox();
        cbOverigAlcohol.setLayoutX(508);
        cbOverigAlcohol.setLayoutY(284);
        cbOverigAlcohol.setPrefHeight(25);
        cbOverigAlcohol.setPrefWidth(80);
        cbOverigAlcohol.setPromptText("Alcohol");
        cbOverigAlcohol.getItems().add("Ja");
        cbOverigAlcohol.getItems().add("Nee");
        AnchorPane.getChildren().add(cbOverigAlcohol);

        btnOverigWijzigen = new Button();
        btnOverigWijzigen.setLayoutX(508);
        btnOverigWijzigen.setLayoutY(332);
        btnOverigWijzigen.setPrefWidth(100);
        btnOverigWijzigen.setPrefHeight(27);
        btnOverigWijzigen.setText("Wijzigen");
        AnchorPane.getChildren().add(btnOverigWijzigen);

        btnOverigOpslaan = new Button();
        btnOverigOpslaan.setLayoutX(608);
        btnOverigOpslaan.setLayoutY(333);
        btnOverigOpslaan.setPrefHeight(27);
        btnOverigOpslaan.setPrefWidth(100);
        btnOverigOpslaan.setText("Opslaan");
        AnchorPane.getChildren().add(btnOverigOpslaan);

        vulComboBoxen();
    }

    public void vulComboBoxen(){
        PizzaRepo = new PizzaUIRepo(new Pizza());
        IngredientenRepo = new IngredientenUIRepo(new Ingredienten());
        OverigeRepo = new OverigeUIRepo(new OverigeProducten());

        Ingredienten = new ArrayList<>();
        Pizzas = new ArrayList<>();
        Overigen = new ArrayList<>();

        for (Pizza p:PizzaRepo.setComboBoxStandaardPizza()){
            cbPizza.getItems().add(p.ToString());
            Pizzas.add(p);
        }

        for (Ingredienten i:IngredientenRepo.AlleIngredienten()){
            cbIngredienten.getItems().add(i.ToString());
            Ingredienten.add(i);
        }

        for (OverigeProducten o:OverigeRepo.AlleOverige()){
            cbOverig.getItems().add(o.ToString());
            Overigen.add(o);
        }

        for (Ingredienten i: Ingredienten){
            cbPizzaIngredienten.getItems().add(i.ToString());
        }
    }

    public void wijzigProduct(Products product){
        if(product instanceof Pizza){
            PizzaRepo = new PizzaUIRepo(new Pizza());
            tfPizzaNaam.setText(product.getNaam());
            cbPizzaGluten.setPromptText(boolToString(((Pizza) product).getGluten()));
            cbPizzaVorm.setPromptText(((Pizza) product).getVorm().toString());
            cbPizzaVorm.setValue(((Pizza) product).getVorm());
            cbPizzaGluten.setValue(boolToString(((Pizza) product).getGluten()));
            tfPizzaFormaat.setText(String.valueOf(((Pizza) product).getFormaat()));

            for (Ingredienten i:PizzaRepo.IngredientenBijPizza(product.getID())) {
                lstIngredienten.getItems().add(i.ToString());
            }
        }

        else if(product instanceof Ingredienten){
            tfIngredientenNaam.setText(product.getNaam());
            tfIngredientenInkoop.setText(String.valueOf(((Ingredienten) product).getInkoop()));
            tfIngredientenVerkoop.setText(String.valueOf(product.getVerkoopPrijs()));
            cbIngredientenVega.setPromptText(boolToString(((Ingredienten) product).getVega()));
            cbIngredientenHalal.setPromptText(boolToString(((Ingredienten) product).getHalal()));
        }

        else if(product instanceof OverigeProducten){
            tfOverigNaam.setText(product.getNaam());
            tfOverigInkoop.setText(String.valueOf(((OverigeProducten) product).getInkoop()));
            tfOverigVerkoop.setText(String.valueOf(product.getVerkoopPrijs()));
            cbOverigAlcohol.setPromptText(boolToString(((OverigeProducten) product).getAlcohol()));
        }
    }

    public Products StringToProduct(String string, String soortProduct){
        String[] getID = string.split(" :");
        if(soortProduct.equals("Pizza")){
            for (Pizza p:Pizzas){
                if(Integer.parseInt(getID[0]) == p.getID()){
                    return p;
                }
            }
        }

        else if (soortProduct.equals("Overige")){
            for (OverigeProducten o: Overigen){
                if(Integer.parseInt(getID[0]) == o.getID()){
                    return o;
                }
            }
        }

        else if(soortProduct.equals("Ingredienten")){
            for (Ingredienten i:Ingredienten){
                if(Integer.parseInt(getID[0]) == i.getID()){
                    return i;
                }
            }
        }
        return null;
    }

    public String boolToString(boolean bool){
        if(bool){
            return "Ja";
        }

        else {
            return "Nee";
        }
    }

    public boolean StringtoBool(String string){
        if(string.equals("Ja")){
            return true;
        }
        else {
            return false;
        }
    }

    public void Update(String product) {
        if (product.equals("Pizza")) {
            PizzaRepo = new PizzaUIRepo(new Pizza());

            String naam = tfPizzaNaam.getText();
            float formaat = Float.parseFloat(tfPizzaFormaat.getText());
            String vorm = cbPizzaVorm.getSelectionModel().getSelectedItem().toString();
            boolean gluten = StringtoBool(cbPizzaGluten.getSelectionModel().getSelectedItem().toString());
            ArrayList<Ingredienten> ingredienten = new ArrayList<>();
            for (Object s : lstIngredienten.getItems()) {
                String string = (String) s;
                String[] getID = string.split(" :");

                for (Ingredienten i : Ingredienten) {
                    if (i.getID() == Integer.parseInt(getID[0])) {
                        ingredienten.add(i);
                    }
                }
            }
            PizzaRepo.UpdatePizza(PizzaID, naam, formaat, vorm, gluten,ingredienten);
            JOptionPane.showMessageDialog(null,"Product is gewijzigd");
        }

        else if (product.equals("Ingredient")) {
                IngredientenRepo = new IngredientenUIRepo(new Ingredienten());
                String naam = tfIngredientenNaam.getText();
            }
        }

    public void getIDs(String product, String soort){
        String getID[] = product.split(" :");
        switch (soort){
            case "Pizza": PizzaID = Integer.parseInt(getID[0]);
            case "Overig" : OverigID = Integer.parseInt(getID[0]);
            case "Ingredient" : IngredientID = Integer.parseInt(getID[0]);
            default:
        }
    }

    public void ingredientToevoegen(String ingredient){
        try {
            lstIngredienten.getItems().add(ingredient);
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,"Geen ingredient geselecteerd");
        }
    }
}
