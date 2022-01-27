package com.nymrok.entrevoisins.events;

import com.nymrok.entrevoisins.models.Neighbour;

public class EventRemoveFavorite {

    public Neighbour neighbour;

    public EventRemoveFavorite(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
