package com.nymrok.entrevoisins.events;

import com.nymrok.entrevoisins.models.Neighbour;

public class EventDeleteNeighbour {

    public Neighbour neighbour;

    public EventDeleteNeighbour(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
