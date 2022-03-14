package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vues.navigation.Fenetre;
import vues.navigation.Navigateur;

public class MenuPrincipal {
    private Navigateur navigateur;
    private Jeu jeu;

    @FXML
    private GridPane mainGrid;
    @FXML
    private Button solo;
    @FXML
    private Button param;
    @FXML
    private Button quitter;
    @FXML
    private GridPane paramPane;
    private FenetreDeJeu fenetreDeJeu;

    public MenuPrincipal(Navigateur navigateur) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        jeu = new Jeu(new GestionnaireDeTouchesFX(navigateur.getSceneCourante()));
        jeu.getGestionnaireDeTouches().echapProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!jeu.isPause()){
                    navigateur.naviguerVers(Fenetre.MENU_PAUSE, new MenuPause(navigateur, jeu, fenetreDeJeu));
                }
                jeu.setPause(!jeu.isPause());
            }
        });
    }

    @FXML
    public void initialize() {
        solo.setPrefHeight(40);
        param.setPrefHeight(40);
        quitter.setPrefHeight(40);
        solo.setMaxWidth(400);
        param.setMaxWidth(400);
        quitter.setMaxWidth(400);

        Stage myStage = navigateur.getStage();
        myStage.widthProperty().addListener((listener) -> {
            solo.setPrefWidth(myStage.getWidth()*0.70);
            param.setPrefWidth(myStage.getWidth()*0.70);
            quitter.setPrefWidth(myStage.getWidth()*0.70);
        });
    }

    @FXML
    public void startSolo() {
        fenetreDeJeu = new FenetreDeJeu(navigateur, jeu);
        navigateur.naviguerVers(fenetreDeJeu.getScene());
    }

    @FXML
    public void parametres() {
        navigateur.naviguerVers(Fenetre.PARAMETRES, new Parametres(navigateur, jeu));
    }

    @FXML
    public void quitter(ActionEvent bouttonQuitter) {
        jeu.setPause(true);
        navigateur.getStage().close();
    }
}