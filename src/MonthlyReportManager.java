import java.util.ArrayList;
import java.util.HashMap;

// Класс в котором собраны основные методы для работы с месячными отчётами
public class MonthlyReportManager {
    HashMap<Integer, ArrayList<MonthlyReport>> monthlyReports = new HashMap<>();
    FileReader fileReader;

    public MonthlyReportManager(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    // Метод для считывания данных месячного отчёта из файла
    public void loadFile(String fileName, int month) {
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        ArrayList<MonthlyReport> monthlyReportArrayList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int unitPrice = Integer.parseInt(parts[3]);

            MonthlyReport monthlyReport = new MonthlyReport(itemName, isExpense, quantity, unitPrice, month);
            monthlyReportArrayList.add(monthlyReport);
        }
        monthlyReports.put(month, monthlyReportArrayList);
    }

    // Метод, возвращающий данные о доходах и расходах в рамках одного календарного месяца.
    public HashMap<Integer, ArrayList<MonthlyReport>> getMonthlyReports() {
        return monthlyReports;
    }

    // Метод, для подсчёта суммы доходов за каждый месяц
    public HashMap<Integer, Integer> getSumOfIncome() {
        HashMap<Integer, Integer> sumOfIncomeForEachOfTheMonths = new HashMap<>();

        for (Integer month : monthlyReports.keySet()) {
            int sumOfIncome = 0;
            for (MonthlyReport monthlyReport : monthlyReports.get(month)) {
                if (!monthlyReport.IsExpense) {
                    sumOfIncome += monthlyReport.quantity * monthlyReport.unitPrice;
                }
            }
            sumOfIncomeForEachOfTheMonths.put(month, sumOfIncome);
        }
        return sumOfIncomeForEachOfTheMonths;
    }

    // Метод, для подсчёта суммы расходов за каждый месяц
    public HashMap<Integer, Integer> getSumOfExpenses() {
        HashMap<Integer, Integer> sumOfExpensesForEachOfTheMonths = new HashMap<>();

        for (Integer month : monthlyReports.keySet()) {
            int sumOfIncome = 0;
            for (MonthlyReport monthlyReport : monthlyReports.get(month)) {
                if (monthlyReport.IsExpense) {
                    sumOfIncome += monthlyReport.quantity * monthlyReport.unitPrice;
                }
            }
            sumOfExpensesForEachOfTheMonths.put(month, sumOfIncome);
        }
        return sumOfExpensesForEachOfTheMonths;
    }

    // Метод, который находит самый прибыльный товар за месяц
    public String[] getMostProfitableProduct(int month) {
        ArrayList<MonthlyReport> monthlyReportArrayList = monthlyReports.get(month);

        String mostProfitableProduct = null;
        int mostProfitableProductSum = 0;
        for (MonthlyReport monthlyReport : monthlyReportArrayList) {
            if (!monthlyReport.IsExpense && monthlyReport.quantity * monthlyReport.unitPrice > mostProfitableProductSum) {
                mostProfitableProduct = monthlyReport.itemName;
                mostProfitableProductSum = monthlyReport.quantity * monthlyReport.unitPrice;
            }
        }
        return new String[] {mostProfitableProduct, String.valueOf(mostProfitableProductSum)};
    }

    // Метод, который находит самую большую трату за месяц
    public String[] getBiggestExpense(int month) {
        ArrayList<MonthlyReport> monthlyReportArrayList = monthlyReports.get(month);

        String biggestExpense = null;
        int biggestExpenseSum = 0;
        for (MonthlyReport monthlyReport : monthlyReportArrayList) {
            if (monthlyReport.IsExpense && monthlyReport.quantity * monthlyReport.unitPrice > biggestExpenseSum) {
                biggestExpense = monthlyReport.itemName;
                biggestExpenseSum = monthlyReport.quantity * monthlyReport.unitPrice;
            }
        }
        return new String[] {biggestExpense, String.valueOf(biggestExpenseSum)};
    }
}
