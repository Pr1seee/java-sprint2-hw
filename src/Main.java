import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = null;
        MonthlyReport monthlyReport = null;

        String username = hello(scanner);
        while (true) {
            menu(username);
            int command = scanner.nextInt();
            if (command == 1) {
                for (int i = 1; i <= 3 ; i++) {
                    monthlyReport = new MonthlyReport("resources/m.20210" + i + ".csv");
                }
                System.out.println("Все месячные отчёты - успешно считаны!");
            } else if (command == 2) {
                System.out.println("За какой год хотите считать отчёт?");
                int year = scanner.nextInt();
                yearlyReport = new YearlyReport(year,"resources/y." + year + ".csv");

            } else if (command == 3) {
            } else if (command == 4) {
                monthlyReport.annualReport();
            } else if (command == 5) {
                yearlyReport.annualReport();
            } else if (command == 0) {
                System.out.println("Хорошего вечера, " + username + ".");
                break;
            } else {
                System.out.println("Такого пункта меню не существует, попробуйте снова.");
            }
        }
    }
         static void menu(String username) {
            System.out.println("Что вы хотите сделать, " + username + "?");
            System.out.println("1. Считать все месячные отчёты.");
            System.out.println("2. Считать годовой отчёт.");
            System.out.println("3. Сверить отчёты.");
            System.out.println("4. Вывести информацию о всех месячных отчётах.");
            System.out.println("5. Вывести информацию о годовом отчёте.");
            System.out.println("0. Выйти из приложения.");
        }
        static String hello(Scanner scanner) {
            System.out.println("Добро пожаловать в новое приложение для бухгалтерии!");
            System.out.println("Подкажите, как Вас зовут?");
            String name = scanner.nextLine();
            System.out.println("Очень приятно!");
            return name;
        }

    }

