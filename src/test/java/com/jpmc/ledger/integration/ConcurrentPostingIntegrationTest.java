package com.jpmc.ledger.integration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Concurrency stress test — the showcase test for this project.
 *
 * <p>Setup: two accounts. Spawn N threads, each posting a random transfer
 * between them. After all threads finish, assert:
 *
 * <ol>
 *   <li>The sum of the two balances equals the starting sum (no money
 *       created or destroyed).
 *   <li>The number of successfully-persisted transactions plus the number
 *       of expected-failures (optimistic-lock retries that gave up) equals
 *       N. No silent data loss.
 *   <li>A spot check of a random subset shows debits == credits per
 *       transaction.
 * </ol>
 *
 * <p>This is the test that demonstrates the locking strategy works. Run it
 * with both the optimistic and pessimistic adapter implementations and
 * compare throughput.
 */
@SpringBootTest
@Testcontainers
@Disabled("Re-enable once PostTransactionService is implemented")
class ConcurrentPostingIntegrationTest {

    @Container
    @SuppressWarnings("resource")
    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("ledger")
                    .withUsername("ledger")
                    .withPassword("ledger");

    @Test
    void noMoneyIsCreatedOrDestroyedUnderContention() throws Exception {
        // TODO: once PostTransactionService is implemented:
        //   1) seed two accounts via the repository directly.
        //   2) build a fixed thread pool of, say, 16 workers.
        //   3) submit ~1000 tasks, each randomly choosing direction & amount.
        //   4) await termination.
        //   5) assert invariants above with AssertJ.
        ExecutorService pool = Executors.newFixedThreadPool(16);
        pool.shutdown();
    }
}
