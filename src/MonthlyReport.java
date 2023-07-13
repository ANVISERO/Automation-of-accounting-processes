// Класс месячного отчёта
public class MonthlyReport {
    String itemName; // название товара
    boolean IsExpense; // обозначает, является ли запись тратой (TRUE) или доходом (FALSE)
    int quantity; // количество закупленного или проданного товара
    int unitPrice; // стоимость одной единицы товара
    int month; // Месяц

    public MonthlyReport(String itemName, boolean isExpense, int quantity, int unitPrice, int month) {
        this.itemName = itemName;
        IsExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.month = month;
    }
}
