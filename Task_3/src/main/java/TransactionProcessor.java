import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TransactionProcessor {

    public void processTransactions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int total = 0;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int amount = Integer.parseInt(parts[1]);
            String type = parts[0];

            if (type.equals("deposit")) {
                total += amount;
            } else if (type.equals("withdrawal")) {
                total -= amount;
            }

            System.out.println("Processed: " + type + " of $" + amount);
        }

        System.out.println("Final Balance: $" + total);
    }
}
