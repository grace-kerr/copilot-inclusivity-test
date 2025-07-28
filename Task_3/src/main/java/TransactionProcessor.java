import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TransactionProcessor {

  public void processTransactions(String filePath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;
    int total = 0;
    int depositCount = 0;
    int withdrawalCount = 0;
    int maxDeposit = Integer.MIN_VALUE;
    int minWithdrawal = Integer.MAX_VALUE;
    int transactionCount = 0;
    int suspiciousCount = 0;

    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(",");
      if (parts.length < 2) {
        System.out.println("Skipping malformed line: " + line);
        continue;
      }
      int amount;
      try {
        amount = Integer.parseInt(parts[1]);
      } catch (NumberFormatException e) {
        System.out.println("Invalid amount in line: " + line);
        continue;
      }
      String type = parts[0];
      transactionCount++;

      if (type.equals("deposit")) {
        total += amount;
        depositCount++;
        if (amount > maxDeposit) maxDeposit = amount;
      } else if (type.equals("withdrawal")) {
        total -= amount;
        withdrawalCount++;
        if (amount < minWithdrawal) minWithdrawal = amount;
        if (amount > 1000) {
          System.out.println("Suspicious withdrawal detected: $" + amount);
          suspiciousCount++;
        }
      } else if (type.equals("fee")) {
        total -= amount;
        System.out.println("Fee applied: $" + amount);
      } else {
        System.out.println("Unknown transaction type: " + type);
      }

      System.out.println("Processed: " + type + " of $" + amount);
    }

    System.out.println("Final Balance: $" + total);
    System.out.println("Total transactions: " + transactionCount);
    System.out.println(
        "Deposits: "
            + depositCount
            + ", Max deposit: $"
            + (maxDeposit == Integer.MIN_VALUE ? 0 : maxDeposit));
    System.out.println(
        "Withdrawals: "
            + withdrawalCount
            + ", Min withdrawal: $"
            + (minWithdrawal == Integer.MAX_VALUE ? 0 : minWithdrawal));
    System.out.println("Suspicious withdrawals: " + suspiciousCount);
  }
}
