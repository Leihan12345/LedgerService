package com.jpmc.ledger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Smoke test — does the Spring context start at all?
 *
 * <p>Disabled in the scaffold because the use case beans throw
 * {@link UnsupportedOperationException} on construction-free wiring is fine
 * but the application won't pass health checks until the TODOs are filled
 * in. Re-enable once you've implemented enough of the app to load cleanly.
 */
@SpringBootTest
@Testcontainers
@Disabled("Re-enable once domain/services are implemented")
class LedgerApplicationTests {

    @Container
    @SuppressWarnings("resource")
    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("ledger")
                    .withUsername("ledger")
                    .withPassword("ledger");

    @Test
    void contextLoads() {
        // intentionally empty
    }
}
