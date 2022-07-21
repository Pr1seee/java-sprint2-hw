import java.io.File;
import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YRecord> rows = new ArrayList<>();
    int year;

    public YearlyReport(int year, String path) {
        this.year = year;

        String filePath = path + File.separator + "y." + year + ".csv";
        String content = ReadFiles.readFileContentsOrNull(path);

        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");

            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YRecord record = new YRecord(month, amount, isExpense);
            rows.add(record);
        }
        System.out.println("Отчёт за " + year + " год, успешно считан!");
    }

    public void annualReport() {
        if (rows.size() == 0) {
            System.out.println("Годовой отчёт не считан.");
        } else {
            System.out.println("Отчёт за " + year + " год.");

            finalProfit();

            System.out.println("Средний доход за: " + year + " год, - " + avgIncomeOrExpenses(true));
            System.out.println("Средний расход в году: " + avgIncomeOrExpenses(false));
        }
    }
    private int avgIncomeOrExpenses(boolean isExpense) {
        int count = 0;
        int sum = 0;
        for (YRecord row : rows) {
            if (row.isExpense == isExpense) {
                count++;
                sum += row.amount;
            }
        }
        return sum/count;
    }

    private void finalProfit() {
        for (int i = 0; i < rows.size(); i += 2) {
            int month = rows.get(i).month;
            System.out.println("\t" + "За "+ month + " месяц: " + profitPerMonth(month));
        }


    }

    private int profitPerMonth(int month) {
        int expenses = 0;
        int income = 0;

        for (YRecord row : rows) {
            if (row.month == month) {
                if (row.isExpense) {
                    expenses += row.amount;
                } else {
                    income += row.amount;
                }
            }
        }

        return income - expenses;
    }
}