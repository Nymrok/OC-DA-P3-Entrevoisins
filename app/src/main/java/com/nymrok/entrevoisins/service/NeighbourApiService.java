package com.nymrok.entrevoisins.service;

import com.nymrok.entrevoisins.models.Neighbour;

import java.util.List;

public interface NeighbourApiService {

    List<Neighbour> getNeighbours();

    List<Neighbour> getFavorites();

    void createNeighbour(Neighbour neighbour);

    void deleteNeighbour(Neighbour neighbour);

    void switchFav(Neighbour neighbour);
}
