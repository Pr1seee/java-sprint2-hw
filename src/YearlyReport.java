import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YRecord> rows = new ArrayList<>();
    public YearlyReport(int year,String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YRecord record = new YRecord(month,amount,isExpense);
            rows.add(record);
        }
        System.out.println("Отчёт за " + year + " год, успешно считан!");
    }
    public void printYearlyReports(int year) {
        System.out.println("Отчёт за " + year + " год.");
        profitPerMonth(rows);




        }

    public void profitPerMonth(ArrayList<YRecord> rows) {
        for (YRecord row : rows) {
            Integer[] profit = new Integer[3];
            for (int i = 0; i < 4; i++) {
                if (row.month == i) {
                    profit[i] += row.amount;
                    System.out.println("Прибль за " + row.month + ", " + profit[i] + " рублей.");
                }
            }
        }
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

}
