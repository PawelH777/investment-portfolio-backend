package org.example.investmentportfolio.portfolio.adapters.api;

import org.example.investmentportfolio.portfolio.domain.model.Portfolio;
import org.example.investmentportfolio.portfolio.domain.ports.PortfolioService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PortfolioFacade {

    private final PortfolioService portfolioService;

    public PortfolioFacade(final PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    public boolean save(final PortfolioRequest request) {
        return portfolioService.save(request.getName());
    }

    public List<PortfolioResponse> getPortfolios() {
        final List<Portfolio> portfolios = portfolioService.getPortfolios();
        return portfolios.stream().map(PortfolioResponse::of).collect(Collectors.toList());
    }
}
