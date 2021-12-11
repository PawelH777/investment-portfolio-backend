package org.example.investmentportfolio.portfolio.adapters.common.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto {

  private String name;

  private List<AssetDto> assets;
}
