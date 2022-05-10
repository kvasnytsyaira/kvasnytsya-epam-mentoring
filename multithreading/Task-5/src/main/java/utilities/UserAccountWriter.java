package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.CurrencyTransaction;
import model.UserAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserAccountWriter {
    UserAccountScanner userAccountScanner = new UserAccountScanner();
    ObjectMapper objectMapper = new ObjectMapper();

    public void addTransaction(CurrencyTransaction transaction) throws IOException {
        updateFile(transaction, transaction.getUserAccount());
    }

    private void updateFile(CurrencyTransaction transaction, UserAccount userAccount) throws IOException {
        var filePath = ("C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\multithreading\\Task-5\\src\\main\\resources\\"
                + userAccount.getName() + ".txt");
        var accountToUpdate = userAccountScanner.readUserAccountFromFile(userAccount.getName());

        var accountStatus = objectMapper.writeValueAsString(userAccount);
        var transactionHistory = accountToUpdate.split(";")[1];
        transactionHistory = String.join("", transactionHistory, transaction.toString());
        String newFile = String.join("", accountStatus+";", transactionHistory);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
        writer.append(newFile);

        writer.close();
    }
}
