package org.example.investmentportfolio.portfolio.adapters.api;

import org.example.investmentportfolio.portfolio.adapters.common.model.PortfolioDto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/portfolio")
@Produces("application/json")
@ApplicationScoped
public class PortfolioEndpoint {

  private static final String SAVE_PORTFOLIO_SUCCESS_MESSAGE =
      "Portfolio has been saved in database";
  private static final String SAVE_PORTFOLIO_ERROR_MESSAGE = "Portfolio hasn't been saved :(";

  private final PortfolioFacade portfolioFacade;

  private final DummyFacade dummyFacade;

  public PortfolioEndpoint(final PortfolioFacade portfolioFacade, final DummyFacade dummyFacade) {
    this.portfolioFacade = portfolioFacade;
    this.dummyFacade = dummyFacade;
  }

  @POST
  public String save(final PortfolioDto request) {
    final boolean isSaved = portfolioFacade.save(request);
    return isSaved ? SAVE_PORTFOLIO_SUCCESS_MESSAGE : SAVE_PORTFOLIO_ERROR_MESSAGE;
  }

  @GET
  public List<PortfolioDto> getPortfolios() {
    dummyFacade.save();
    return portfolioFacade.getPortfolios();
  }
}
