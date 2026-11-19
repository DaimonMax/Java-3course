import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Transaction findLargestExpenseInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate end = LocalDate.parse(endDateStr, dateFormatter);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), dateFormatter);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .min(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findSmallestExpenseInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate end = LocalDate.parse(endDateStr, dateFormatter);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), dateFormatter);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .max(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
    }

    public static java.util.Map<String, Double> getExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }

    public static java.util.Map<String, Double> getExpensesByMonth(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), dateFormatter).format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }
}