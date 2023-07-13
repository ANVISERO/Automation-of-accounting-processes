import java.util.ArrayList;
import java.util.HashMap;

// Класс в котором собраны основные методы для работы с годовым отчётом
public class YearlyReportManager {
    HashMap<Integer, ArrayList<YearlyReport>> yearlyReports = new HashMap<>();
    FileReader fileReader;

    public YearlyReportManager(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    // Метод для считывания данных годового отчёта из файла
    public void loadFile(String fileName, int year) {
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        ArrayList<YearlyReport> yearlyReportArrayList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearlyReport yearlyReport = new YearlyReport(month, amount, isExpense, year);
            yearlyReportArrayList.add(yearlyReport);
        }
        yearlyReports.put(year, yearlyReportArrayList);
    }

    // Метод, возвращающий информацию о всех тратах в течение года
    public HashMap<Integer, ArrayList<YearlyReport>> getYearlyReports() {
        return yearlyReports;
    }

    // Метод, который считает прибыль по каждому месяцу
    public HashMap<Integer, Integer> getProfitForEachMonth(int year) {
        ArrayList<YearlyReport> yearlyReportArrayList =  yearlyReports.get(year);
        HashMap<Integer, Integer> profitForEachMonth = new HashMap<>();
        for (YearlyReport yearlyReport : yearlyReportArrayList) {
            if (!profitForEachMonth.containsKey(yearlyReport.month)) {
                if (!yearlyReport.isExpense) {
                    profitForEachMonth.put(yearlyReport.month, yearlyReport.amount);
                } else {
                    profitForEachMonth.put(yearlyReport.month, -yearlyReport.amount);
                }
            } else {
                if (!yearlyReport.isExpense) {
                    profitForEachMonth.put(yearlyReport.month, profitForEachMonth.get(yearlyReport.month) + yearlyReport.amount);
                } else {
                    profitForEachMonth.put(yearlyReport.month, profitForEachMonth.get(yearlyReport.month) - yearlyReport.amount);
                }
            }
        }
        return profitForEachMonth;
    }

    // Метод, который считает средний расход за все имеющиеся операции в году
    public double getAverageExpense(int year) {
        ArrayList<YearlyReport> yearlyReportArrayList =  yearlyReports.get(year);
        double averageExpense = 0.0;
        int n = 0;
        for (YearlyReport yearlyReport : yearlyReportArrayList) {
            if (yearlyReport.isExpense) {
                averageExpense += yearlyReport.amount;
                n++;
            }
        }
        return averageExpense/n;
    }

    // Метод, который считает средний доход за все имеющиеся операции в году
    public double getAverageIncome(int year) {
        ArrayList<YearlyReport> yearlyReportArrayList =  yearlyReports.get(year);
        double averageIncome = 0.0;
        int n = 0;
        for (YearlyReport yearlyReport : yearlyReportArrayList) {
            if (!yearlyReport.isExpense) {
                averageIncome += yearlyReport.amount;
                n++;
            }
        }
        return averageIncome/n;
    }

    // Метод, который возвращает все расходы за год
    public HashMap<Integer, Integer> getYearExpenses() {
        HashMap<Integer, Integer> yearExpenses = new HashMap<>();
        for (ArrayList<YearlyReport> yearlyReportsPerYear : yearlyReports.values()) {
            for (YearlyReport yearlyReport : yearlyReportsPerYear) {
                if (yearlyReport.isExpense) {
                    yearExpenses.put(yearlyReport.month, yearlyReport.amount);
                }
            }
        }
        return yearExpenses;
    }

    // Метод, который возвращает все доходы за год
    public HashMap<Integer, Integer> getYearIncome() {
        HashMap<Integer, Integer> yearIncome = new HashMap<>();
        for (ArrayList<YearlyReport> yearlyReportsPerYear : yearlyReports.values()) {
            for (YearlyReport yearlyReport : yearlyReportsPerYear) {
                if (!yearlyReport.isExpense) {
                    yearIncome.put(yearlyReport.month, yearlyReport.amount);
                }
            }
        }
        return yearIncome;
    }
}
