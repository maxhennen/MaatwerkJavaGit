package sample.Views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.FontWeight;
import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.OverigeProducten;
import sample.DomainClasses.Pizza;
import sample.Enums.Vorm;
import sample.ViewModel.IngredientenUIRepo;
import sample.ViewModel.OverigeUIRepo;
import sample.ViewModel.PizzaUIRepo;
import sun.plugin.com.AmbientProperty;

import javax.swing.text.html.*;

import static javafx.scene.text.FontWeight.BOLD;

/**
 * Created by maxhe on 16-5-2017.
 */
public class BestellingController {
    private PizzaUIRepo PizzaRepo;
    private IngredientenUIRepo IngredientenRepo;
    private OverigeUIRepo OverigeRepo;

    private AnchorPane AnchorPane;
    private Label lblBestelling;
    private ComboBox cbPizza;
    private ComboBox cbOverigeProducten;
    private Button btnToevoegenPizza;
    private Button btnToevoegenOverig;
    private Label lblEigenPizza;
    private Label lblKiesBodem;
    private ComboBox cbSoortBodem;
    private ComboBox cbVormBodem;
    private CheckBox chbGluten;
    private Label lblFormaat;
    private TextField tfFormaatX;
    private TextField tfFormaatY;
    private TextField tfFormaatZ;
    private ComboBox cbIngredienten;
    private Button btnToevoegenIngredienten;
    private Label lblIngredientenLst;
    private Label lblProductenLst;
    private ListView lstIngredienten;
    private ListView lstProducten;
    private Button btnEigenPizzaToevoegen;
    private TextField tfKlantnummer;
    private TextField tfNaamKlant;
    private TextField tfAdresKlant;
    private ComboBox cbOphalenBezorgen;
    private Button btnOpslaanBestelling;

    public void setAnchorPane(AnchorPane anchorPane){AnchorPane = anchorPane;}

    public void setLayout(){

        lblBestelling = new Label();
        lblBestelling.setLayoutY(14);
        lblBestelling.setLayoutX(14);
        lblBestelling.setPrefWidth(100);
        lblBestelling.setMinHeight(20);
        lblBestelling.setText("Bestelling");
        lblBestelling.setStyle("-fx-font-weight: bold");
        AnchorPane.getChildren().add(lblBestelling);

        cbPizza = new ComboBox();
        cbPizza.setLayoutX(14);
        cbPizza.setLayoutY(35);
        cbPizza.setMinHeight(25);
        cbPizza.setPrefWidth(147);
        cbPizza.setPromptText("Pizza");
        AnchorPane.getChildren().add(cbPizza);

        cbOverigeProducten = new ComboBox();
        cbOverigeProducten.setLayoutY(72);
        cbOverigeProducten.setLayoutX(14);
        cbOverigeProducten.setPrefWidth(147);
        cbOverigeProducten.setMinHeight(25);
        cbOverigeProducten.setPromptText("Overige Producten");
        AnchorPane.getChildren().add(cbOverigeProducten);

        btnToevoegenPizza = new Button();
        btnToevoegenPizza.setLayoutX(170);
        btnToevoegenPizza.setLayoutY(35);
        btnToevoegenPizza.setPrefWidth(71);
        btnToevoegenPizza.setMinHeight(27);
        btnToevoegenPizza.setText("Toevoegen");
        AnchorPane.getChildren().add(btnToevoegenPizza);

        btnToevoegenOverig = new Button();
        btnToevoegenOverig.setLayoutY(71);
        btnToevoegenOverig.setLayoutX(170);
        btnToevoegenOverig.setMinHeight(27);
        btnToevoegenOverig.setPrefWidth(71);
        btnToevoegenOverig.setText("Toevoegen");
        AnchorPane.getChildren().add(btnToevoegenOverig);

        lblEigenPizza = new Label();
        lblEigenPizza.setLayoutX(14);
        lblEigenPizza.setLayoutY(108);
        lblEigenPizza.setMinHeight(20);
        lblEigenPizza.setPrefWidth(200);
        lblEigenPizza.setText("Eigen pizza samenstellen");
        lblEigenPizza.setStyle("-fx-font-weight: bold");
        AnchorPane.getChildren().add(lblEigenPizza);

        lblKiesBodem = new Label();
        lblKiesBodem.setLayoutY(128);
        lblKiesBodem.setLayoutX(14);
        lblKiesBodem.setPrefWidth(200);
        lblKiesBodem.setMinHeight(20);
        lblKiesBodem.setText("Kies bodem");
        AnchorPane.getChildren().add(lblKiesBodem);

        cbSoortBodem = new ComboBox();
        cbSoortBodem.setLayoutY(163);
        cbSoortBodem.setLayoutX(14);
        cbSoortBodem.setPrefWidth(100);
        cbSoortBodem.setMinHeight(25);
        cbSoortBodem.setPromptText("Soort");
        AnchorPane.getChildren().add(cbSoortBodem);

        cbVormBodem = new ComboBox();
        cbVormBodem.setLayoutX(130);
        cbVormBodem.setLayoutY(164);
        cbVormBodem.setMinHeight(25);
        cbVormBodem.setPrefWidth(111);
        cbVormBodem.setPromptText("Vorm");
        cbVormBodem.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                setFormaatFields(cbVormBodem.getSelectionModel().getSelectedItem().toString());
            }
        });
        AnchorPane.getChildren().add(cbVormBodem);

        chbGluten = new CheckBox();
        chbGluten.setLayoutX(14);
        chbGluten.setLayoutY(200);
        chbGluten.setMinHeight(20);
        chbGluten.setPrefWidth(89);
        chbGluten.setText("Gluten");
        AnchorPane.getChildren().add(chbGluten);

        lblFormaat = new Label();
        lblFormaat.setLayoutX(14);
        lblFormaat.setLayoutY(233);
        lblFormaat.setMinHeight(20);
        lblFormaat.setPrefWidth(100);
        lblFormaat.setText("Formaat");
        AnchorPane.getChildren().add(lblFormaat);

        tfFormaatX = new TextField();
        tfFormaatX.setLayoutY(263);
        tfFormaatX.setLayoutX(14);
        tfFormaatX.setMinHeight(26);
        tfFormaatX.setPrefWidth(35);
        tfFormaatX.setMaxWidth(35);
        AnchorPane.getChildren().add(tfFormaatX);

        tfFormaatY = new TextField();
        tfFormaatY.setLayoutX(65);
        tfFormaatY.setLayoutY(263);
        tfFormaatY.setPrefWidth(35);
        tfFormaatY.setMaxWidth(35);
        tfFormaatY.setMinHeight(26);
        AnchorPane.getChildren().add(tfFormaatY);

        tfFormaatZ = new TextField();
        tfFormaatZ.setLayoutY(263);
        tfFormaatZ.setLayoutX(111);
        tfFormaatZ.setMinHeight(26);
        tfFormaatZ.setPrefWidth(35);
        tfFormaatZ.setMaxWidth(35);
        AnchorPane.getChildren().add(tfFormaatZ);

        cbIngredienten = new ComboBox();
        cbIngredienten.setLayoutY(300);
        cbIngredienten.setLayoutX(14);
        cbIngredienten.setPrefWidth(132);
        cbIngredienten.setMinHeight(25);
        cbIngredienten.setPromptText("Ingredienten");
        AnchorPane.getChildren().add(cbIngredienten);

        btnToevoegenIngredienten = new Button();
        btnToevoegenIngredienten.setLayoutX(158);
        btnToevoegenIngredienten.setLayoutY(299);
        btnToevoegenIngredienten.setPrefWidth(83);
        btnToevoegenIngredienten.setMinHeight(27);
        btnToevoegenIngredienten.setText("Toevoegen");
        AnchorPane.getChildren().add(btnToevoegenIngredienten);

        lblIngredientenLst = new Label();
        lblIngredientenLst.setLayoutY(15);
        lblIngredientenLst.setLayoutX(255);
        lblIngredientenLst.setPrefWidth(135);
        lblIngredientenLst.setMaxHeight(20);
        lblIngredientenLst.setText("Ingredienten");
        AnchorPane.getChildren().add(lblIngredientenLst);

        lblProductenLst = new Label();
        lblProductenLst.setLayoutX(422);
        lblProductenLst.setLayoutY(15);
        lblProductenLst.setPrefWidth(135);
        lblProductenLst.setMinHeight(20);
        lblProductenLst.setText("Producten");
        AnchorPane.getChildren().add(lblProductenLst);

        lstIngredienten = new ListView();
        lstIngredienten.setLayoutY(45);
        lstIngredienten.setLayoutX(254);
        lstIngredienten.setPrefHeight(200);
        lstIngredienten.setPrefWidth(150);
        lstIngredienten.setMinWidth(157);
        AnchorPane.getChildren().add(lstIngredienten);

        lstProducten = new ListView();
        lstProducten.setLayoutX(422);
        lstProducten.setLayoutY(45);
        lstProducten.setPrefWidth(150);
        lstProducten.setPrefHeight(200);
        AnchorPane.getChildren().add(lstProducten);

        btnEigenPizzaToevoegen = new Button();
        btnEigenPizzaToevoegen.setLayoutY(255);
        btnEigenPizzaToevoegen.setLayoutX(254);
        btnEigenPizzaToevoegen.setMinHeight(27);
        btnEigenPizzaToevoegen.setMinWidth(71);
        btnEigenPizzaToevoegen.setText("Toevoegen");
        AnchorPane.getChildren().add(btnEigenPizzaToevoegen);

        tfKlantnummer = new TextField();
        tfKlantnummer.setLayoutX(254);
        tfKlantnummer.setLayoutY(292);
        tfKlantnummer.setMinWidth(200);
        tfKlantnummer.setMinHeight(26);
        tfKlantnummer.setPromptText("Klantnummer");
        AnchorPane.getChildren().add(tfKlantnummer);

        tfNaamKlant = new TextField();
        tfNaamKlant.setLayoutY(327);
        tfNaamKlant.setLayoutX(254);
        tfNaamKlant.setMinHeight(26);
        tfNaamKlant.setMinWidth(200);
        tfNaamKlant.setPromptText("Naam");
        AnchorPane.getChildren().add(tfNaamKlant);

        tfAdresKlant = new TextField();
        tfAdresKlant.setLayoutX(254);
        tfAdresKlant.setLayoutY(359);
        tfAdresKlant.setMinWidth(200);
        tfAdresKlant.setMinHeight(26);
        tfAdresKlant.setPromptText("Adres");
        AnchorPane.getChildren().add(tfAdresKlant);

        cbOphalenBezorgen = new ComboBox();
        cbOphalenBezorgen.setLayoutX(464);
        cbOphalenBezorgen.setLayoutY(292);
        cbOphalenBezorgen.setMinHeight(25);
        cbOphalenBezorgen.setMinWidth(120);
        cbOphalenBezorgen.setPromptText("Ophalen/Bezorgem");
        AnchorPane.getChildren().add(cbOphalenBezorgen);

        btnOpslaanBestelling = new Button();
        btnOpslaanBestelling.setLayoutX(464);
        btnOpslaanBestelling.setLayoutY(359);
        btnOpslaanBestelling.setMinWidth(120);
        btnOpslaanBestelling.setMinHeight(27);
        btnOpslaanBestelling.setText("Opslaan");
        AnchorPane.getChildren().add(btnOpslaanBestelling);

        setValuesUI();
    }

    public void setValuesUI(){
        PizzaRepo = new PizzaUIRepo(new Pizza());
        IngredientenRepo = new IngredientenUIRepo(new Ingredienten());
        OverigeRepo = new OverigeUIRepo(new OverigeProducten());

        for (Pizza p:PizzaRepo.setComboBoxStandaardPizza()) {
            cbPizza.getItems().add(p.getNaam() + " - " + p.getFormaat());
        }

        for (Ingredienten I:IngredientenRepo.AlleIngredienten()){
            cbIngredienten.getItems().add(I.getNaam());
        }

        for (OverigeProducten O:OverigeRepo.AlleOverige()){
            cbOverigeProducten.getItems().add(O.getNaam());
        }

        cbVormBodem.setItems(FXCollections.observableArrayList(Vorm.values()));

        cbSoortBodem.getItems().add("Dun");
        cbSoortBodem.getItems().add("Dik");
    }

    public void setFormaatFields(String vorm){
        if(vorm.equals("Rond")){
            tfFormaatX.visibleProperty().setValue(true);
            tfFormaatY.visibleProperty().setValue(false);
            tfFormaatZ.visibleProperty().setValue(false);
        }

        else if(vorm.equals("Vierkant")){
            tfFormaatX.visibleProperty().setValue(true);
            tfFormaatY.visibleProperty().setValue(true);
            tfFormaatZ.visibleProperty().setValue(false);
        }

        else if(vorm.equals("Driehoek")){
            tfFormaatX.visibleProperty().setValue(true);
            tfFormaatY.visibleProperty().setValue(true);
            tfFormaatZ.visibleProperty().setValue(true);
        }
    }
}
