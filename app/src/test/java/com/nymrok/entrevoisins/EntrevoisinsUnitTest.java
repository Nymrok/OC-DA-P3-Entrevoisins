package com.nymrok.entrevoisins;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.nymrok.entrevoisins.di.DI;
import com.nymrok.entrevoisins.models.Neighbour;
import com.nymrok.entrevoisins.service.DummyNeighbourGenerator;
import com.nymrok.entrevoisins.service.NeighbourApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EntrevoisinsUnitTest {

    private NeighbourApiService API;

    @Before
    public void setup() {
        API = DI.getNewInstanceApiService();
    }

    // Test : Set a neighbor as a favorite or remove it from favorites.
    @Test
    public void changeFavStateWithSuccess() {
        // Get neighbours
        List<Neighbour> neighbours = API.getNeighbours();

        // Take the first one from the list
        Neighbour neighbour = neighbours.get(0);

        // Save favorite status before changing it
        boolean stateBefore = neighbour.isFavorite();

        // Apply the modification to the state
        API.switchFav(neighbour);

        // Check if the state has been changed successfully
        assertTrue(neighbour.isFavorite() != stateBefore) ;
    }

    // Test : Recovery of the list of favorite neighbors and verification of its operation.
    @Test
    public void getFavoritesWithSuccess() {
        // Get neighbours
        List<Neighbour> neighbours = API.getNeighbours();

        // Get favorites
        List<Neighbour> favoritesBefore = API.getFavorites();

        // Favorites List should be empty
        assertEquals(0, favoritesBefore.size());

        // Take the first neighbour
        Neighbour neighbour = neighbours.get(0);

        // Set it to favorite
        neighbour.setFavorite(true);

        // Get again favorites
        List<Neighbour> favoritesAfter = API.getFavorites();

        // Check if the favorites list has increased
        assertEquals(1, favoritesAfter.size());

        // Check if the only one favorite is our guy
        assertSame(favoritesAfter.get(0), neighbour);
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = API.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = API.getNeighbours().get(0);
        API.deleteNeighbour(neighbourToDelete);
        assertFalse(API.getNeighbours().contains(neighbourToDelete));
    }
}