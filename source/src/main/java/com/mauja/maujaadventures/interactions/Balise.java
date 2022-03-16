package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.jeu.Observable;

public abstract class Balise {

    private Balise baliseParente;

    public abstract void ajouter(Balise balise);

    public Balise getBaliseParente() {
        return baliseParente;
    }

    public void setBaliseParente(Balise baliseParente) {
        this.baliseParente = baliseParente;
    }
}