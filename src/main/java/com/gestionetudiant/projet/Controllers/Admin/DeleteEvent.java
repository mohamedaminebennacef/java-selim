package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.event.Event;
import javafx.event.EventType;

public class DeleteEvent extends Event {
    public static final EventType<DeleteEvent> DELETE_EVENT_TYPE = new EventType<>("DELETE_EVENT");

    private final Utilisateur utilisateur;

    public DeleteEvent(Utilisateur utilisateur) {
        super(DELETE_EVENT_TYPE);
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}

