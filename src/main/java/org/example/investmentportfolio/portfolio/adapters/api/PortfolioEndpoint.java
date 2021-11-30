package org.example.investmentportfolio.portfolio.adapters.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/portfolio")
@ApplicationScoped
public class PortfolioEndpoint {

    private final PortfolioFacade portfolioFacade;

    public PortfolioEndpoint(final PortfolioFacade portfolioFacade) {
        this.portfolioFacade = portfolioFacade;
    }

    @POST
    public String save(final PortfolioRequest request) {
        portfolioFacade.save(request);
        return "";
    }

    @GET
    public List<PortfolioResponse> getPortfolios() {
        return portfolioFacade.getPortfolios();
    }
}
