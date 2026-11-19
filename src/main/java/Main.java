import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);
        
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);
        
        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
        
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        String startDate = "01-01-2024";
        String endDate = "31-01-2024";
        Transaction largest = TransactionAnalyzer.findLargestExpenseInPeriod(transactions, startDate, endDate);
        Transaction smallest = TransactionAnalyzer.findSmallestExpenseInPeriod(transactions, startDate, endDate);
        TransactionReportGenerator.printPeriodExpensesExtremes(startDate, endDate, largest, smallest);

        var expensesByCategory = TransactionAnalyzer.getExpensesByCategory(transactions);
        var expensesByMonth = TransactionAnalyzer.getExpensesByMonth(transactions);
        TransactionReportGenerator.printExpensesVisualizationReport(expensesByCategory, expensesByMonth, 1000.0);
    }
}