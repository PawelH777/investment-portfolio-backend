package org.example.investmentportfolio.portfolio.adapters.api;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioResponse {

    private String name;

    static PortfolioResponse of(final Portfolio portfolio) {
        final PortfolioResponse response = new PortfolioResponse();
        response.setName(portfolio.getName());
        return response;
    }
}
