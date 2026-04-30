package Sxl07.budgeting.infrastructure.persistence.entity;

import java.util.UUID;

import Sxl07.budgeting.domain.Category;
import Sxl07.budgeting.domain.Transaction;
import Sxl07.budgeting.domain.TransactionId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private long amount;
    private Category category;

    public static TransactionEntity from(Transaction transaction) {
      return new TransactionEntity(transaction.getId().uuid(), 
      transaction.getDescription(), 
      transaction.getAmount(), 
      transaction.getCategory());
    }

    public Transaction toDomain() {
        return new Transaction(new TransactionId(this.id), this.description, this.amount, this.category);
    }
}
