package org.example.investmentportfolio.portfolio.adapters.common.converters;

import org.example.investmentportfolio.portfolio.adapters.common.model.AssetDto;
import org.example.investmentportfolio.portfolio.adapters.common.model.PortfolioDto;
import org.example.investmentportfolio.portfolio.domain.model.Asset;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PortfolioConverter {

    public Portfolio convertPortfolioDtoToPortfolio(final PortfolioDto portfolioDto) {
        return Portfolio.builder()
                .name(portfolioDto.getName())
                .assets(buildAssets(portfolioDto.getAssets()))
                .build();
    }

    public PortfolioDto convertPortfolioToPortfolioDto(final Portfolio portfolio) {
        return PortfolioDto.builder()
                .name(portfolio.getName())
                .assets(buildAssetDtos(portfolio.getAssets()))
                .build();
    }

    private List<Asset> buildAssets(final List<AssetDto> assets) {
        return assets.stream().map(this::buildAsset).collect(Collectors.toList());
    }

    private Asset buildAsset(final AssetDto asset) {
        return Asset.builder()
                .name(asset.getName())
                .cashContribution(asset.getCashContribution())
                .deviationFromCashContribution(asset.getDeviationFromCashContribution())
                .build();
    }

    private List<AssetDto> buildAssetDtos(final List<Asset> assets) {
        return assets.stream().map(this::buildAssetDto).collect(Collectors.toList());
    }

    private AssetDto buildAssetDto(final Asset asset) {
        return AssetDto.builder()
                .name(asset.getName())
                .cashContribution(asset.getCashContribution())
                .deviationFromCashContribution(asset.getDeviationFromCashContribution())
                .build();
    }
}
