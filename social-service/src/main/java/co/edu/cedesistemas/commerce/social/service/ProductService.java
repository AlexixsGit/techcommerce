package co.edu.cedesistemas.commerce.social.service;

import co.edu.cedesistemas.commerce.social.model.Product;
import co.edu.cedesistemas.commerce.social.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;

    public Product createProduct(final String id) {
        Product product = new Product();
        product.setId(id);
        return repository.save(product);
    }

    public Product update(final Product product) {
        return repository.save(product);
    }

    public Product getById(final String id) {
        return repository.findById(id).orElse(null);
    }

    public Set<Product> getByUserLiked(final String userId) {
        return repository.findByUserLiked(userId);
    }

    public Product getProduct(String id) {
        Product product = getById(id);
        if (product == null) {
            product = new Product();
            product.setId(id);
            product = update(product);
        }
        return product;
    }
}
