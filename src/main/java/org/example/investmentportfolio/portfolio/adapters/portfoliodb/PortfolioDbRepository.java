package org.example.investmentportfolio.portfolio.adapters.portfoliodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.Decimal128;
import org.example.investmentportfolio.portfolio.domain.model.Asset;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;
import org.example.investmentportfolio.portfolio.domain.ports.PortfolioRepository;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.investmentportfolio.portfolio.domain.model.Asset.*;
import static org.example.investmentportfolio.portfolio.domain.model.Portfolio.*;

@ApplicationScoped
public class PortfolioDbRepository implements PortfolioRepository {

  private final MongoClient mongoClient;

  public PortfolioDbRepository(final MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public boolean save(final Portfolio portfolio) {
    final List<Document> preparedAssets =
        portfolio.getAssets().stream().map(this::buildAssetDocument).collect(Collectors.toList());
    final Document preparedPortfolio =
        new Document()
            .append(PORTFOLIO_NAME_PROPERTY, portfolio.getName())
            .append(PORTFOLIO_ASSETS_PROPERTY, preparedAssets);
    final MongoCollection<Document> portfolios =
        mongoClient.getDatabase("portfolio").getCollection(PORTFOLIO_COLLECTION_PROPERTY);
    portfolios.insertOne(preparedPortfolio);
    return true;
  }

  public List<Portfolio> getPortfolios() {
    final List<Portfolio> portfolios = new ArrayList<>();

    try (final MongoCursor<Document> cursor =
        mongoClient
            .getDatabase("portfolio")
            .getCollection(PORTFOLIO_COLLECTION_PROPERTY)
            .find()
            .iterator()) {
      while (cursor.hasNext()) {
        portfolios.add(buildPortfolio(cursor.next()));
      }
    }
    return portfolios;
  }

  private Portfolio buildPortfolio(final Document portfolioDocument) {
    final List<Document> assetDocuments =
        portfolioDocument.getList(PORTFOLIO_ASSETS_PROPERTY, Document.class);
    final String portfolioName = portfolioDocument.getString(PORTFOLIO_NAME_PROPERTY);
    final List<Asset> assets = buildAssets(assetDocuments);
    return buildPortfolio(portfolioName, assets);
  }

  private Portfolio buildPortfolio(final String name, final List<Asset> assets) {
    return Portfolio.builder().name(name).assets(assets).build();
  }

  private Document buildAssetDocument(final Asset asset) {
    return new Document()
        .append(ASSET_NAME_PROPERTY, asset.getName())
        .append(ASSET_CASH_CONTRIBUTION_PROPERTY, asset.getCashContribution())
        .append(
            ASSET_DEVIATION_FROM_CASH_CONTRIBUTION_PROPERTY,
            asset.getDeviationFromCashContribution());
  }

  private List<Asset> buildAssets(final List<Document> assetDocuments) {
    return assetDocuments.stream().map(this::buildAsset).collect(Collectors.toList());
  }

  private Asset buildAsset(final Document assetDocument) {
    final String assetName = assetDocument.getString(ASSET_NAME_PROPERTY);
    final BigDecimal cashContribution =
        assetDocument.get(ASSET_CASH_CONTRIBUTION_PROPERTY, Decimal128.class).bigDecimalValue();
    final BigDecimal deviationFromCashContribution =
        assetDocument
            .get(ASSET_DEVIATION_FROM_CASH_CONTRIBUTION_PROPERTY, Decimal128.class)
            .bigDecimalValue();
    return Asset.builder()
        .name(assetName)
        .cashContribution(cashContribution)
        .deviationFromCashContribution(deviationFromCashContribution)
        .build();
  }
}
