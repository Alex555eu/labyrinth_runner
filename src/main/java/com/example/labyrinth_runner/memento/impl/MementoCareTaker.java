package com.example.labyrinth_runner.memento.impl;

import com.example.labyrinth_runner.builder.IDirector;
import com.example.labyrinth_runner.labyrinth.ILabyrinth;
import com.example.labyrinth_runner.player_object.IPlayerObject;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class MementoCareTaker {

    private LabyrinthMemento labyrinthMemento;
    private PlayerObjectMemento playerObjectMemento;
    private DirectorMemento directorMemento;

    public void create(ILabyrinth labyrinth, IPlayerObject playerObject, IDirector director){
        labyrinthMemento = labyrinth.takeSnapshot();
        playerObjectMemento = playerObject.takeSnapshot();
        directorMemento = director.takeSnapshot();
    }

    public void restoreLabyrinth(ILabyrinth labyrinth) {
        labyrinth.restoreMemento(labyrinthMemento);
    }

    public void restorePlayerObject(IPlayerObject playerObject) {
        playerObject.restoreMemento(playerObjectMemento);
    }

    public void restoreDirector(IDirector director) {
        director.restoreMemento(directorMemento);
    }


    private final String SERIALIZED_LABYRINTH = "serialized_labyrinth.xml";
    private final String SERIALIZED_PLAYER_OBJECT = "serialized_player_object.xml";
    private final String SERIALIZED_DIRECTOR = "serialized_director.xml";

    public void encode() {
        XMLEncoder encoder = null;
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_LABYRINTH)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File " + SERIALIZED_LABYRINTH);
        }
        encoder.writeObject(labyrinthMemento);
        encoder.close();

        XMLEncoder encoder2 = null;
        try{
            encoder2 = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_PLAYER_OBJECT)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File " + SERIALIZED_PLAYER_OBJECT);
        }
        encoder2.writeObject(playerObjectMemento);
        encoder2.close();

        XMLEncoder encoder3 = null;
        try{
            encoder3 = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_DIRECTOR)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File " + SERIALIZED_DIRECTOR);
        }
        encoder3.writeObject(directorMemento);
        encoder3.close();
    }

    public void decode() {
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_LABYRINTH)));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File " + SERIALIZED_LABYRINTH + " not found");
        }
        labyrinthMemento = (LabyrinthMemento) decoder.readObject();

        XMLDecoder decoder2 = null;
        try {
            decoder2 = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_PLAYER_OBJECT)));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File " + SERIALIZED_PLAYER_OBJECT + " not found");
        }
        playerObjectMemento = (PlayerObjectMemento) decoder2.readObject();

        XMLDecoder decoder3 = null;
        try {
            decoder3 = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_DIRECTOR)));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File " + SERIALIZED_DIRECTOR + " not found");
        }
        directorMemento = (DirectorMemento) decoder3.readObject();

    }


}
