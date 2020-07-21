package co.edu.cedesistemas.commerce.service;

import co.edu.cedesistemas.commerce.model.Order;
import co.edu.cedesistemas.commerce.repository.OrderRepository;
import co.edu.cedesistemas.common.SpringProfile;
import co.edu.cedesistemas.common.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Profile("!" + SpringProfile.SANDBOX)
@Service
@AllArgsConstructor
@Slf4j
public class OrderService implements IOrderService {
    private final OrderRepository repository;

    @Override
    public Order createOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(Order.Status.CREATED);
        order.calculateValue();
        log.info("order created: {}", order.getId());
        return repository.save(order);
    }

    @Override
    public Order updateOrder(final String id, final Order order) {
        Order found = getById(id);
        if (found == null) {
            log.warn("order not found: {}", id);
            return null;
        }
        BeanUtils.copyProperties(order, found, Utils.getNullPropertyNames(order));
        return repository.save(found);
    }

    @Override
    public Order getById(String id) {
        return repository.findById(id).orElse(null);
    }
}
