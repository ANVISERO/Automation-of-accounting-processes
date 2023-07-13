import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        YearlyReportManager yearlyReportManager = new YearlyReportManager(fileReader);
        MonthlyReportManager monthlyReportManager = new MonthlyReportManager(fileReader);
        Printer printer = new Printer(yearlyReportManager, monthlyReportManager);
        Checker checker = new Checker(monthlyReportManager, yearlyReportManager);

        boolean isReadMonth = false;
        boolean isReadYear = false;

        while (true) {
            printMenu();

            String command = scanner.nextLine();
            if (command.equals("q") || command.equals("quit")) {
                System.out.println("Пока!");
                scanner.close();
                return; // Выход из программы
            }

            int i = Integer.parseInt(command);
            if (i == 1) {
                int numberOfFiles = 3;
                System.out.println("\nСчитывание месячных отчётов...");

                for (int j = 1; j <= numberOfFiles; j++) {
                    monthlyReportManager.loadFile("m.20210" + j + ".csv", j);
                }

                isReadMonth = true;
                System.out.println("Все месячные отчёты успешно считаны!\n");
            } else if (i == 2) {
                System.out.println("\nСчитывание годового отчёта...");
                yearlyReportManager.loadFile("y.2021.csv", 2021);
                isReadYear = true;
                System.out.println("Годовой отчёт успешно считан!\n");
            } else if (i == 3) {
                if (!isReadMonth && !isReadYear) {
                    System.out.println("\nДанное действие не может быть выполнено, так как информация из месячных" +
                            " и годового отчётов ещё не была считана.\n" +
                            "Считайте информацию из месячных и годового отчётов, " +
                            "после чего повторите данное действие.\n");
                } else if (!isReadYear) {
                    System.out.println("\nДанное действие не может быть выполнено, так как информация из годового" +
                            " отчёта ещё не была считана.\n" +
                            "Считайте информацию из годового отчёта, после чего повторите данное действие.\n");
                } else if (!isReadMonth) {
                    System.out.println("\nДанное действие не может быть выполнено, так как информация из месячных" +
                            " отчётов ещё не была считана.\n" +
                            "Считайте информацию из месячных отчётов, после чего повторите данное действие.\n");
                } else {
                    if (checker.check()) {
                        System.out.println("\nСравнение успешно завершено!\n" +
                                "В ходе выполнения сравнения не было выявлено различий между годовым и месячными отчётами.\n");
                    } else {
                        System.out.println("\nПроверьте отчёты на корректность!\n");
                    }
                }
            } else if (i == 4) {
                if (!isReadMonth) {
                    System.out.println("\nДанное действие не может быть выполнено, так как информация из месячных" +
                            " отчётов ещё не была считана.\n" +
                            "Считайте информацию из месячных отчётов, после чего повторите данное действие.\n");
                } else {
                    printer.printMonthlyReportInfo();
                }
            } else if (i == 5) {
                if (!isReadYear) {
                    System.out.println("\nДанное действие не может быть выполнено, так как информация из годового" +
                            " отчёта ещё не была считана.\n" +
                            "Считайте информацию из годового отчёта, после чего повторите данное действие.\n");
                } else {
                    printer.printYearlyReportInfo();
                }
            } else {
                System.out.println("\nТакой команды нет!\n");
            }
        }
    }

    // Вывод меню в консоль
    static void printMenu() {
        System.out.println("Выберите одно из приведённых ниже действий:\n" +
                "(1) Считать все месячные отчёты\n" +
                "(2) Считать годовой отчёт\n" +
                "(3) Сверить отчёты\n" +
                "(4) Вывести информацию обо всех месячных отчётах\n" +
                "(5) Вывести информацию о годовом отчёте\n" +
                "Для выхода из программы введите quit (q)");
    }
}

