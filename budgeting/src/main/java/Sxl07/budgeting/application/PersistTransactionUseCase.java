package Sxl07.budgeting.application;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import Sxl07.budgeting.application.input.PersistTransactionInput;
import Sxl07.budgeting.application.output.TransactionOutput;
import Sxl07.budgeting.domain.Transaction;
import Sxl07.budgeting.domain.TransactionRepository;

@Service
public class PersistTransactionUseCase {
    
    private final TransactionRepository transactionRepository;
    
    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    @Tool(name = "persist-transaction", description = "Registra uma nova transação financeira no banco de dados. Use sempre que o usuário informar uma despesa ou receita com valor e descrição.")
    public TransactionOutput execute(@ToolParam(description = "Dados da transação: descrição, valor e categoria") PersistTransactionInput input) {
        var transaction = transactionRepository.save(new Transaction(input.description(),input.amount(), input.category()));

        return TransactionOutput.from(transaction);
    }
    
}
