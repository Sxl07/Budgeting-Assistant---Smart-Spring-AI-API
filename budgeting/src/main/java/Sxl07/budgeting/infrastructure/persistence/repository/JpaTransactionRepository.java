package Sxl07.budgeting.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import Sxl07.budgeting.domain.Category;
import Sxl07.budgeting.domain.Transaction;
import Sxl07.budgeting.domain.TransactionRepository;
import Sxl07.budgeting.infrastructure.persistence.entity.TransactionEntity;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
        }    
    
    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return transactionEntityRepository.findAllByCategory(category).stream().map(TransactionEntity::toDomain).toList();
    }

    public static TransactionEntity from(Transaction transaction){
        return new TransactionEntity(transaction.getId().uuid(), 
        transaction.getDescription(),
        transaction.getAmount(),
        transaction.getCategory());
    }
}
