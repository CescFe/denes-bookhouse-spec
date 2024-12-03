package com.onebox.oneboxchallenge.cart.infrastructure.tasks;

import com.onebox.oneboxchallenge.cart.infrastructure.repository.SpringDataCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CartCleanupTask {

    private final SpringDataCartRepository cartRepository;

    @Scheduled(fixedRate = 60000)
    public void cleanOldCarts() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);
        cartRepository.deleteByUpdatedAtBefore(threshold);
    }
}
