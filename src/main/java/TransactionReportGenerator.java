import java.util.List;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printPeriodExpensesExtremes(String startDate, String endDate, Transaction largest, Transaction smallest) {
        System.out.println("Витрати за період з " + startDate + " по " + endDate + ":");
        if (largest != null) {
            System.out.println("\nНайбільша витрата: " + largest.getDescription() + " (" + largest.getAmount() + ") [" + largest.getDate() + "]");
        } else {
            System.out.println("\nНайбільша витрата: відсутні");
        }

        if (smallest != null) {
            System.out.println("\nНайменша витрата: " + smallest.getDescription() + " (" + smallest.getAmount() + ") [" + smallest.getDate() + "]");
        } else {
            System.out.println("\nНайменша витрата: відсутні");
        }
    }

    public static void printExpensesVisualizationReport(
            java.util.Map<String, Double> byCategory,
            java.util.Map<String, Double> byMonth,
            double stepAmount) {

        System.out.println("\nВИТРАТИ ПО КАТЕГОРІЯХ:");
            byCategory.forEach((category, sum) ->
                System.out.printf("%-25s | %.2f%n", category, sum)
        );

        System.out.println("\nВИТРАТИ ПО МІСЯЦЯХ:");
            byMonth.forEach((month, sum) ->
                System.out.printf("%-10s | %.2f%n", month, sum)
            );
        }
}