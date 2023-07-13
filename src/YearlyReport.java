// Класс годового отчёта
public class YearlyReport {
    int month; // месяц
    int amount; // сумма
    boolean isExpense; // Обозначает, является ли запись тратой (true) или доходом (false)
    int year; // год

    public YearlyReport(int month, int amount, boolean isExpense, int year) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
        this.year = year;
    }
}
