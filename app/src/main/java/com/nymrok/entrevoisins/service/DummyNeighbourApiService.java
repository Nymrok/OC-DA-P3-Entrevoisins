package com.nymrok.entrevoisins.service;

import com.nymrok.entrevoisins.models.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class DummyNeighbourApiService implements NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    @Override // Method to create a list of normal neighbors.
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    // Method to remove a particular neighbor from the list of normal neighbors.
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override // Method to retrieve normal neighbors and send them to a list.
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override // Method to retrieve favorite neighbors and send them to another list.
    public List<Neighbour> getFavorites() {
        List<Neighbour> favorites = new ArrayList<>();
        for (Neighbour list : neighbours) {
            if (list.isFavorite()) {
                favorites.add(list);
            }
        }
        return favorites;
    }

    // Method to change a particular neighbor’s favorite status from the Details page.
    public void switchFav(Neighbour neighbour) {
        boolean newFavState = !neighbour.isFavorite();
        neighbours.get(neighbours.indexOf(neighbour)).setFavorite(newFavState);
        neighbour.setFavorite(newFavState);
    }
}
