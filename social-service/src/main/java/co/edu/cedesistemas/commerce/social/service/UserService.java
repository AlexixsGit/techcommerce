package co.edu.cedesistemas.commerce.social.service;

import co.edu.cedesistemas.commerce.social.model.Product;
import co.edu.cedesistemas.commerce.social.model.Store;
import co.edu.cedesistemas.commerce.social.model.User;
import co.edu.cedesistemas.commerce.social.model.relation.FriendRelation;
import co.edu.cedesistemas.commerce.social.model.relation.ProductLikeRelation;
import co.edu.cedesistemas.commerce.social.model.relation.StoreLikeRelation;
import co.edu.cedesistemas.commerce.social.model.relation.StoreRateRelation;
import co.edu.cedesistemas.commerce.social.repository.UserRepository;
import co.edu.cedesistemas.common.event.SocialEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ProductService productService;
    private final StoreService storeService;
    private final EventPublisherService publisherService;

    public User createUser(String id) {
        User user = new User();
        user.setId(id);
        User userCreated = repository.save(user);
        this.publisherService.publishSocialUserEvent(user, SocialEvent.Status.CREATED);
        return userCreated;
    }

    public User update(User user) {
        return repository.save(user);
    }

    public User getById(final String id) {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            Set<Product> productsLiked = productService.getByUserLiked(user.getId());
            user.set_liked(productsLiked.stream().map(Product::getId).collect(Collectors.toSet()));

            Set<Store> storesLiked = storeService.getByUserLiked(user.getId());
            user.set_storesLiked(storesLiked.stream().map(Store::getId).collect(Collectors.toSet()));

            Set<User> friends = repository.findFriendsByUser(user.getId());
            User.UserFriendResult result = User.UserFriendResult.builder()
                    ._hints(friends.size())
                    .friends(friends.stream().map(User::getId).collect(Collectors.toSet()))
                    .build();
            user.set_friends(result);
        }
        return user;
    }


    public void rateStore(final String userId, final String storeId, float value) throws Exception {
        User foundUser = this.getById(userId);
        Store foundStore = this.storeService.getStoreById(storeId);
        foundUser.storeRates(StoreRateRelation.builder().rate(value).store(foundStore)
                .user(foundUser).build());
        this.repository.save(foundUser);
    }

    public void likeStore(final String userId, final String storeId) throws Exception {
        User foundUser = this.getById(userId);
        Store foundStore = this.storeService.getStoreById(storeId);
        foundUser.storeLikes(StoreLikeRelation.builder().user(foundUser).store(foundStore)
                .storeLikeDate(LocalDateTime.now()).build());
        this.repository.save(foundUser);
    }

    public void likeProduct(final String userId, final String productId) throws Exception {
        User foundUser = this.getById(userId);
        Product foundProduct = this.productService.getById(productId);
        foundUser.likes(ProductLikeRelation.builder().product(
                foundProduct).likeTime(LocalDateTime.now()).user(foundUser).build());
        this.repository.save(foundUser);
    }

    public void addFriend(final String userId, final String friendId) throws Exception {
        User foundUser = this.getById(userId);
        User foundFriend = this.getById(friendId);
        foundUser.addFriend(FriendRelation.builder()
                .friend(foundFriend).friendshipTime(LocalDateTime.now())
                .user(foundUser).build());
        this.repository.save(foundUser);
    }

    public void deleteUser(String id) {
        User userFound = this.getById(id);

        if (userFound != null) {
            this.repository.delete(userFound);
            this.publisherService.publishSocialUserEvent(userFound, SocialEvent.Status.DELETED);
        }
    }
}
