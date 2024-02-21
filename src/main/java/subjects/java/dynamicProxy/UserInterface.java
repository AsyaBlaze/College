package subjects.java.dynamicProxy;

import java.util.List;

public interface UserInterface {
    List<Integer> getIncomesHistory();
    List<Integer> getSpendingHistory();
    int getMoneyInAccount();
    void addMoney(int amount);
    void spendMoney(int amount);
}
