package com.jpmc.ledger.adapter.in.web.transaction;

import com.jpmc.ledger.application.port.in.PostTransactionUseCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Web slice — only the MVC layer is loaded; the use case is mocked. Verifies
 * controller wiring, validation, and error mapping without booting a database.
 *
 * <p>Suggested cases:
 *
 * <ul>
 *   <li>POST without {@code Idempotency-Key} header -> 400
 *   <li>POST with malformed body -> 400 with field errors
 *   <li>Use case throws {@code DomainException} -> 422 problem+json
 *   <li>Happy path -> 201 with response body
 * </ul>
 */
@WebMvcTest(TransactionController.class)
@Disabled("Re-enable once controller body and DTO mapping are implemented")
class TransactionControllerSliceTest {

    @Autowired MockMvc mockMvc;

    @MockBean PostTransactionUseCase postTransaction;

    @Test
    void todo_writeMe() {
        // TODO
    }
}
