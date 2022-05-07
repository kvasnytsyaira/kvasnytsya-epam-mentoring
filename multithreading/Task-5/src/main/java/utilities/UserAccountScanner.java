package utilities;

import model.UserAccount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserAccountScanner {
    public UserAccountScanner() {
    }

    public String readUserAccount(String accountName) {
        try {
            File file = new File("C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\multithreading\\Task-5\\src\\main\\resources\\" + accountName + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readUserAccountStatus(String accountName) {
        return readUserAccount(accountName).split(";")[0];

    }

    public String readUserAccountTransactionHistory(String accountName) {
        return readUserAccount(accountName).split(";")[1];
    }
}

