package com.nymrok.entrevoisins.di;

import com.nymrok.entrevoisins.service.DummyNeighbourApiService;
import com.nymrok.entrevoisins.service.NeighbourApiService;

// Dependency Injector : to get instances of services
public class DI {

    private static NeighbourApiService service = new DummyNeighbourApiService();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}
