package org.example.investmentportfolio.portfolio.config;

import org.example.investmentportfolio.portfolio.domain.ports.DummyService;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class PortfolioConfig {

    @Produces
    public DummyService dummyService() {
        return new DummyService();
    }
}
