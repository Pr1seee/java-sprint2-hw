import java.util.ArrayList;


public class MonthlyReport {
    ArrayList<MRecord> rows = new ArrayList<>();
    ReadFiles readFiles = new ReadFiles();

    public MonthlyReport(String path) {
        String content = readFiles.readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MRecord record = new MRecord(itemName, isExpense, quantity, sumOfOne);
            rows.add(record);
        }
    }
}