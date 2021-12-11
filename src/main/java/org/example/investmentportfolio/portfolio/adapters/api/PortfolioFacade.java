package org.example.investmentportfolio.portfolio.adapters.api;

import org.example.investmentportfolio.portfolio.adapters.common.converters.PortfolioConverter;
import org.example.investmentportfolio.portfolio.adapters.common.model.PortfolioDto;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;
import org.example.investmentportfolio.portfolio.domain.ports.PortfolioRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PortfolioFacade {

  private final PortfolioRepository portfolioRepository;

  private final PortfolioConverter portfolioConverter;

  @Inject
  public PortfolioFacade(
      final PortfolioRepository portfolioRepository, final PortfolioConverter portfolioConverter) {
    this.portfolioRepository = portfolioRepository;
    this.portfolioConverter = portfolioConverter;
  }

  public boolean save(final PortfolioDto portfolio) {
    return portfolioRepository.save(portfolioConverter.convertPortfolioDtoToPortfolio(portfolio));
  }

  public List<PortfolioDto> getPortfolios() {
    final List<Portfolio> portfolios = portfolioRepository.getPortfolios();
    return portfolios.stream()
        .map(portfolioConverter::convertPortfolioToPortfolioDto)
        .collect(Collectors.toList());
  }
}
