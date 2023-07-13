import java.util.HashMap;

// Класс для сравнения отчётов
public class Checker {
    MonthlyReportManager monthlyReportManager;
    YearlyReportManager yearlyReportManager;
    static String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public Checker(MonthlyReportManager monthlyReportManager, YearlyReportManager yearlyReportManager) {
        this.monthlyReportManager = monthlyReportManager;
        this.yearlyReportManager = yearlyReportManager;
    }

    // Метод, который проверяет, сходятся ли отчёты за месяцы и за год
    public boolean check() {
        boolean check = true;
        HashMap<Integer, Integer> yearIncome = yearlyReportManager.getYearIncome();
        HashMap<Integer, Integer> sumOfIncomeForAllMonth = monthlyReportManager.getSumOfIncome();

        for (Integer month : sumOfIncomeForAllMonth.keySet()) {
            Integer incomeMonth = sumOfIncomeForAllMonth.get(month);
            Integer incomeMonthByYear = yearIncome.get(month);

            if (incomeMonthByYear == null) {
                check = false;
                System.out.println("Доходы за месяц " + months[month - 1] + " отсутствует в годовом отчёте!");
                continue;
            }

            if (incomeMonth.intValue() != incomeMonthByYear.intValue()) {
                System.out.println("Обнаружено несоответствие доходов в месяце " + months[month - 1]);
                check = false;
            }
        }

        HashMap<Integer, Integer> yearExpense = yearlyReportManager.getYearExpenses();
        HashMap<Integer, Integer> sumOfExpensesForAllMonth = monthlyReportManager.getSumOfExpenses();

        for (Integer month : sumOfExpensesForAllMonth.keySet()) {
            Integer expensesMonth = sumOfExpensesForAllMonth.get(month);
            Integer expensesMonthByYear = yearExpense.get(month);

            if (expensesMonthByYear == null) {
                check = false;
                System.out.println("Расходы за месяц " + months[month - 1] + " отсутствует в годовом отчёте!");
                continue;
            }

            if (expensesMonth.intValue() != expensesMonthByYear.intValue()) {
                System.out.println("Обнаружено несоответствие расходов в месяце " + months[month - 1]);
                check = false;
            }
        }
        return check;
    }
}
