package subjects.java.dynamicProxy;

import java.util.ArrayList;
import java.util.List;

public class User implements UserInterface {
    private String login;
    private String password;
    private String name;
    private final List<Integer> incomeHistory = new ArrayList<>();
    private final List<Integer> spendingHistory = new ArrayList<>();
    private int money;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    @Override
    public List<Integer> getIncomesHistory() {
        return incomeHistory;
    }

    @Override
    public List<Integer> getSpendingHistory() {
        return spendingHistory;
    }

    @Override
    public int getMoneyInAccount() {
        return money;
    }

    @Override
    public void addMoney(int amount) {
        System.out.println(amount + " добавлена на счет");
        incomeHistory.add(amount);
        money += amount;
    }

    @Override
    public void spendMoney(int amount) {
        if (money - amount < 0) {
            throw new ArithmeticException("Не достаточно денег на счету");
        }
        spendingHistory.add(amount);
        money -= amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
