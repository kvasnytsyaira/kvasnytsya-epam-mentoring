package layer;

import model.BankCardType;
import model.BankCard;
import model.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType bankCardType);
}
