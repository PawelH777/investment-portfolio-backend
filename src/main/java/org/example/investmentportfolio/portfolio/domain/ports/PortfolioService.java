package org.example.investmentportfolio.portfolio.domain.ports;

import org.example.investmentportfolio.portfolio.adapters.portfoliodb.PortfolioRepository;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public final class PortfolioService {

    private final PortfolioRepository repository;

    public PortfolioService(final PortfolioRepository repository) {
        this.repository = repository;
    }

    public boolean save(final String name) {
        return repository.save(name);
    }

    public List<Portfolio> getPortfolios() {
        return repository.getPortfolios();
    }
}
