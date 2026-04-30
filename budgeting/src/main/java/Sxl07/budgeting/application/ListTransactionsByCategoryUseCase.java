package Sxl07.budgeting.application;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import Sxl07.budgeting.application.output.TransactionOutput;
import Sxl07.budgeting.domain.Category;
import Sxl07.budgeting.domain.TransactionRepository;

@Service
public class ListTransactionsByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Tool(name = "list-transactions-by-category", description = "lista todas as transações de uma categoria específica")
    public List<TransactionOutput> execute(@ToolParam(description = "a categoria de uma transação") Category category) {
        return transactionRepository.findAllByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
