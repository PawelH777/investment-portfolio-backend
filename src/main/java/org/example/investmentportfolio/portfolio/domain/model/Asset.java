package org.example.investmentportfolio.portfolio.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Asset {

  public static final String ASSET_NAME_PROPERTY = "name";

  public static final String ASSET_CASH_CONTRIBUTION_PROPERTY = "cashContribution";

  public static final String ASSET_DEVIATION_FROM_CASH_CONTRIBUTION_PROPERTY =
      "deviationFromCashContribution";

  private String name;

  private BigDecimal cashContribution;

  private BigDecimal deviationFromCashContribution;
}
