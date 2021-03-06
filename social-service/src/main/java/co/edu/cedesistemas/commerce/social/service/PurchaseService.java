package co.edu.cedesistemas.commerce.social.service;

import co.edu.cedesistemas.commerce.social.model.Product;
import co.edu.cedesistemas.commerce.social.model.User;
import co.edu.cedesistemas.commerce.social.model.relation.PurchaseRelation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseService {
    private final UserService userService;
    private final ProductService productService;

    public PurchaseRelation purchase(final String userId, final String productId) throws Exception {
        User user = userService.getById(userId);
        if (user == null) {
            throw new Exception("user not found");
        }

        Product product = productService.getById(productId);
        if (product == null) {
            throw new Exception("product not found");
        }

        PurchaseRelation purchase = PurchaseRelation.builder()
                .purchaseTime(LocalDateTime.now())
                .product(product)
                .user(user)
                .build();
        user.purchases(purchase);
        userService.update(user);
        return purchase;
    }

    public Set<PurchaseRelation> purchase(final String userId, final Set<String> productIds) throws Exception {
        User user = userService.getById(userId);
        if (user == null) {
            throw new Exception("user not found");
        }
        Set<PurchaseRelation> purchases = productIds.stream()
                .map(productService::getProduct)
                .map(p -> PurchaseRelation.builder()
                        .product(p)
                        .user(user)
                        .purchaseTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toSet());
        user.purchases(purchases);
        userService.update(user);
        return purchases;
    }
}
