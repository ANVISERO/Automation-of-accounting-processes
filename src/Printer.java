import java.util.ArrayList;
import java.util.HashMap;

// Класс для вывода информации в консоль
public class Printer {
    YearlyReportManager yearlyReportManager;
    MonthlyReportManager monthlyReportManager;
    static String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public Printer(YearlyReportManager yearlyReportManager, MonthlyReportManager monthlyReportManager) {
        this.yearlyReportManager = yearlyReportManager;
        this.monthlyReportManager = monthlyReportManager;
    }

    // Метод, который выводит в консоль информацию из месячных отчётов
    public void printMonthlyReportInfo() {
        System.out.println("\nИнформация из месячных отчётов:");
        HashMap<Integer, ArrayList<MonthlyReport>> monthlyReports = monthlyReportManager.getMonthlyReports();
        for (Integer month : monthlyReports.keySet()) {
            System.out.println(months[month - 1]);
            String[] mostProfitableProduct = monthlyReportManager.getMostProfitableProduct(month);
            System.out.println("Самый прибыльный товар – \"" + mostProfitableProduct[0]
            + "\", был продан на сумму – " + mostProfitableProduct[1]);
            String[] biggestExpense = monthlyReportManager.getBiggestExpense(month);
            System.out.println("Самая большая трата – \"" + biggestExpense[0]
                    + "\", было потрачено – " + biggestExpense[1] + "\n");
        }
    }

    // Метод, который выводит в консоль информацию из годового отчёта
    public void printYearlyReportInfo() {
        System.out.println("\nИнформация из годового отчёта:");
        HashMap<Integer, ArrayList<YearlyReport>> yearlyReports = yearlyReportManager.getYearlyReports();
        for (Integer year : yearlyReports.keySet()) {
            System.out.println(year + " год");
            HashMap<Integer, Integer> incomesPerMonth = yearlyReportManager.getProfitForEachMonth(year);
            for (Integer month : incomesPerMonth.keySet()) {
                System.out.println("За месяц " + months[month - 1] +
                        " прибыль составила: " + incomesPerMonth.get(month));
            }
            System.out.println("\nСредний расход за все имеющиеся операции в " + year + " году составил: "
                    + yearlyReportManager.getAverageExpense(year));
            System.out.println("\nСредний доход за все имеющиеся операции в " + year + " году составил: "
                    + yearlyReportManager.getAverageIncome(year) + "\n");
        }
    }
}
