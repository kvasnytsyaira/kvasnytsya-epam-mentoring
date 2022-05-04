package layer;

import models.BankCardType;
import models.BankCard;
import models.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType bankCardType);
}
