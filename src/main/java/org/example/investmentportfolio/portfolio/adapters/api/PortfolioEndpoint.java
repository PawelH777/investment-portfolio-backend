package org.example.investmentportfolio.portfolio.adapters.api;

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

    private final PortfolioFacade portfolioFacade;

    private final DummyFacade dummyFacade;

    public PortfolioEndpoint(final PortfolioFacade portfolioFacade, final DummyFacade dummyFacade) {
        this.portfolioFacade = portfolioFacade;
        this.dummyFacade = dummyFacade;
    }

    @POST
    public String save(final PortfolioRequest request) {
        portfolioFacade.save(request);
        return "";
    }

    @GET
    public List<PortfolioResponse> getPortfolios() {
        dummyFacade.save();
        return portfolioFacade.getPortfolios();
    }
}
