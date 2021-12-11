package org.example.investmentportfolio.portfolio.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Portfolio {

  public static final String PORTFOLIO_COLLECTION_PROPERTY = "portfolio";

  public static final String PORTFOLIO_NAME_PROPERTY = "name";

  public static final String PORTFOLIO_ASSETS_PROPERTY = "assets";

  private String name;

  private List<Asset> assets;
}
