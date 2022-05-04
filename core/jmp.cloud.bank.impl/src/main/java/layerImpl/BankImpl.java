package layerImpl;

import layer.Bank;
import models.*;
import repository.BankCardRepositoryLike;

public class BankImpl implements Bank {

    BankCardRepositoryLike bankCardRepository = new BankCardRepositoryLike();

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        if (bankCardType.equals(BankCardType.CREDIT)) {
            return bankCardRepository.createBankCard(new CreditBankCard(user));
        } else if (bankCardType.equals(BankCardType.DEBIT)) {
            return bankCardRepository.createBankCard(new DebitBankCard(user));
        }
        return null;
    }

}
