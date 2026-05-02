package com.jpmc.ledger.domain.transaction;

/**
 * Direction of an entry. We carry the sign on the type rather than on the
 * amount so that {@link com.jpmc.ledger.domain.shared.Money} can stay a pure
 * "magnitude" — amounts on entries are always strictly positive.
 */
public enum EntryType {
    DEBIT,
    CREDIT
}
