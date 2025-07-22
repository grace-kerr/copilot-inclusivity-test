import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class TransactionProcessorTest {

  @Test
  public void testProcessTransactions() throws IOException {
    // Create a temporary file with transaction data
    Path tempFile = Files.createTempFile("transactions", ".csv");
    try (FileWriter writer = new FileWriter(tempFile.toFile())) {
      writer.write("deposit,100\n");
      writer.write("withdrawal,40\n");
      writer.write("deposit,60\n");
    }

    // Run the processor
    TransactionProcessor processor = new TransactionProcessor();
    processor.processTransactions(tempFile.toString());

    // No assertions here; this task focuses on manual observation of output
    // Participants may refactor and then write assertions in additional tests
  }
}
