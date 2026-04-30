package Sxl07.budgeting.infrastructure.http.request;

import Sxl07.budgeting.application.input.PersistTransactionInput;
import Sxl07.budgeting.domain.Category;

public record TransactionRequest (String description, Category category, Long amount) {

    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);   
}
}