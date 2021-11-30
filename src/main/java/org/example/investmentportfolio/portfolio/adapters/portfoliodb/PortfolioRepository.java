package org.example.investmentportfolio.portfolio.adapters.portfoliodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.investmentportfolio.portfolio.domain.model.Portfolio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PortfolioRepository {

    @Inject
    private MongoClient mongoClient;

    public boolean save(final String name) {
        final Document portfolio = new Document()
                .append("name", name);
        final MongoCollection<Document> portfolios = mongoClient.getDatabase("portfolio").getCollection("portfolio");
        portfolios.insertOne(portfolio);
        return true;
    }

    public List<Portfolio> getPortfolios() {
        final List<Portfolio> portfolios = new ArrayList<>();

        try (final MongoCursor<Document> cursor = mongoClient.getDatabase("portfolio").getCollection("portfolio").find().iterator()) {
            while (cursor.hasNext()) {
                final Document document = cursor.next();
                final Portfolio portfolio = Portfolio.builder().name(document.getString("name")).build();
                portfolios.add(portfolio);
            }
        }
        return portfolios;
    }
}
