package org.example.investmentportfolio.portfolio.domain.ports;

import org.example.investmentportfolio.portfolio.domain.model.Portfolio;

import java.util.List;

public interface PortfolioRepository {

    boolean save(final Portfolio portfolio);

    List<Portfolio> getPortfolios();
}
