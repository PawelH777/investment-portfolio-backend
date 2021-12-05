package org.example.investmentportfolio.portfolio.adapters.api;

import org.example.investmentportfolio.portfolio.domain.ports.DummyService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyFacade {

    private final DummyService dummyService;

    DummyFacade(final DummyService dummyService) {
        this.dummyService = dummyService;
    }

    void save() {
        dummyService.save();
    }
}
