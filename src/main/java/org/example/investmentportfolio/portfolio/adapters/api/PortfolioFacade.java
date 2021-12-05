package org.example.investmentportfolio.portfolio.adapters.api;

import org.example.investmentportfolio.portfolio.domain.model.Portfolio;
import org.example.investmentportfolio.portfolio.domain.ports.PortfolioRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PortfolioFacade {

    private final PortfolioRepository portfolioRepository;

    public PortfolioFacade(final PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public boolean save(final PortfolioRequest request) {
        return portfolioRepository.save(request.getName());
    }

    public List<PortfolioResponse> getPortfolios() {
        final List<Portfolio> portfolios = portfolioRepository.getPortfolios();
        return portfolios.stream().map(PortfolioResponse::of).collect(Collectors.toList());
    }
}
