package Sxl07.budgeting.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

import Sxl07.budgeting.domain.Category;
public record PersistTransactionInput(@ToolParam(description = "a descrição da transação") String description, @ToolParam(description = "o valor da transação") long amount, @ToolParam(description = "a categoria da transação") Category category) {
        
}
