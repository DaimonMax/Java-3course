import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testReadTransactionsFromCsv() {
        List<Transaction> transactions = TransactionCSVReader.readTransactions("https://informer.com.ua/dut/java/pr2.csv");
        Assertions.assertNotNull(transactions);
        Assertions.assertFalse(transactions.isEmpty());
    }

    @Test
    public void testFindTop10Expenses() {
        List<Transaction> transactions = TransactionCSVReader.readTransactions("https://informer.com.ua/dut/java/pr2.csv");
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertNotNull(topExpenses);
        Assertions.assertTrue(topExpenses.size() <= 10);
        for (Transaction t : topExpenses) {
            Assertions.assertTrue(t.getAmount() < 0);
        }
    }

    @Test
    public void testPeriodExpensesExtremes() {
        Transaction t1 = new Transaction("05-01-2024", -500.0, "Кафе");
        Transaction t2 = new Transaction("12-01-2024", -2500.0, "Продукти");
        Transaction t3 = new Transaction("20-01-2024", -1200.0, "Транспорт");
        List<Transaction> sample = List.of(t1, t2, t3);

        Transaction largest = TransactionAnalyzer.findLargestExpenseInPeriod(sample, "01-01-2024", "31-01-2024");
        Transaction smallest = TransactionAnalyzer.findSmallestExpenseInPeriod(sample, "01-01-2024", "31-01-2024");

        Assertions.assertEquals(-2500.0, largest.getAmount());
        Assertions.assertEquals(-500.0, smallest.getAmount());
    }

    @Test
    public void testExpensesByCategoryAndMonth() {
        Transaction t1 = new Transaction("05-01-2024", -500.0, "Кафе");
        Transaction t2 = new Transaction("18-01-2024", -1500.0, "Кафе");
        Transaction t3 = new Transaction("10-02-2024", -800.0, "Аптека");
        List<Transaction> sample = List.of(t1, t2, t3);

        var byCategory = TransactionAnalyzer.getExpensesByCategory(sample);
        var byMonth = TransactionAnalyzer.getExpensesByMonth(sample);

        Assertions.assertEquals(2000.0, byCategory.get("Кафе"));
        Assertions.assertEquals(2000.0, byMonth.get("01-2024"));
        Assertions.assertEquals(800.0, byMonth.get("02-2024"));
    }
}