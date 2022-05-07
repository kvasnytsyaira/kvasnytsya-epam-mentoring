package utilities;

import model.CurrencyTransaction;
import model.UserAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserAccountWriter {
    UserAccountScanner userAccountScanner = new UserAccountScanner();

    public void addTransaction(CurrencyTransaction transaction) throws IOException {
        updateFile(transaction, transaction.getUserAccount());
    }

    private void updateFile(CurrencyTransaction transaction, UserAccount userAccount) throws IOException {
        var filePath = ("C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\multithreading\\Task-5\\src\\main\\resources\\"
                + userAccount.getName() + ".txt");
        var accountToUpdate = userAccountScanner.readUserAccount(userAccount.getName());

        var accountStatus = userAccount.toString();
        var transactionHistory = accountToUpdate.split(";")[1];
        transactionHistory = String.join(transaction.toString());

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
        writer.append(accountStatus.join(transactionHistory));

        writer.close();
    }
}
