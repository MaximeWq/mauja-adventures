package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.interactions.evenements.Evenement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GestionnaireInteractions {
    private static GestionnaireInteractions gestionnaireInteractions;
    private static  List<Scenario> scenarios;
    private Queue<Evenement> fileCourante;
    private Queue<Evenement> fileSauvegarde;
    private Thread thread;
    private boolean enCours;

    private GestionnaireInteractions() {
        fileCourante = new LinkedList<>();
        fileSauvegarde = new LinkedList<>();
        enCours = false;
        initialisation();
    }

    public static GestionnaireInteractions getInstance(){
        if(gestionnaireInteractions == null){
            return new GestionnaireInteractions();
        }
        else{
            return gestionnaireInteractions;
        }
    }

    private void initialisation(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream("ressources/interactionsTest.xml");
            SAXParser parseur = factory.newSAXParser();

            InteractionHandler handler = new InteractionHandler();

            parseur.parse(inputStream, handler);
            scenarios = handler.getListeScenarios();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouter(Evenement evenement) {
        if (!enCours) {
            fileSauvegarde.add(evenement);
            if(thread != null) {
                thread.interrupt();
            }
            thread = new Thread(this::traitement);
            thread.start();
        }
        else{
            fileSauvegarde.add(evenement);
        }
    }

    private void traitement() {
        enCours = true;
        while(true){
            if(fileCourante.size() != 0){
                Evenement evenement = fileCourante.poll();
                evenement.traitement(scenarios);
            }
            else{
                if(fileSauvegarde.size() != 0){
                    fileCourante = new LinkedList<>(fileSauvegarde);
                    fileSauvegarde = new LinkedList<>();
                }
                else
                    break;
            }
        }
        enCours = false;
    }
}
