package org.example.investmentportfolio.portfolio.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Asset {

  private String name;

  private BigDecimal cashContribution;

  private BigDecimal deviationFromCashContribution;
}
