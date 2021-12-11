package org.example.investmentportfolio.portfolio.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Portfolio {

  private String name;

  private List<Asset> assets;
}
