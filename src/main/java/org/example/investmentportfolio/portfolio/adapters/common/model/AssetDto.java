package org.example.investmentportfolio.portfolio.adapters.common.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

  private String name;

  private BigDecimal cashContribution;

  private BigDecimal deviationFromCashContribution;
}
