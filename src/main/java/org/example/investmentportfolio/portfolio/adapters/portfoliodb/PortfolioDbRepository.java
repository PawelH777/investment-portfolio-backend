package org.example.investmentportfolio.portfolio.adapters.portfoliodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;
import org.example.investmentportfolio.portfolio.domain.ports.PortfolioRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PortfolioDbRepository implements PortfolioRepository {

  private final MongoClient mongoClient;

  public PortfolioDbRepository(final MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public boolean save(final Portfolio portfolio) {
    final Document preparedPortfolio =
        new Document().append("name", portfolio.getName()).append("assets", portfolio.getAssets());
    final MongoCollection<Document> portfolios =
        mongoClient.getDatabase("portfolio").getCollection("portfolio");
    portfolios.insertOne(preparedPortfolio);
    return true;
  }

  public List<Portfolio> getPortfolios() {
    final List<Portfolio> portfolios = new ArrayList<>();

    try (final MongoCursor<Document> cursor =
        mongoClient.getDatabase("portfolio").getCollection("portfolio").find().iterator()) {
      while (cursor.hasNext()) {
        final Document document = cursor.next();
        final Portfolio portfolio = Portfolio.builder().name(document.getString("name")).build();
        portfolios.add(portfolio);
      }
    }
    return portfolios;
  }
}
