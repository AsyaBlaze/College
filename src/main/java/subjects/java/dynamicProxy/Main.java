package subjects.java.dynamicProxy;

import java.lang.reflect.Proxy;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User newUser = new User("Elena189", "Uru", "Elena");
        newUser.addMoney(50000);
        ClassLoader userClassLoader = newUser.getClass().getClassLoader();
        Class[] interfaces = newUser.getClass().getInterfaces();
        UserInterface proxyUser = (UserInterface) Proxy.newProxyInstance(userClassLoader, interfaces, new UserInvocationHandler(newUser));
        proxyUser.spendMoney(700);
        proxyUser.addMoney(100);
        System.out.println("Деньги на счету: " + proxyUser.getMoneyInAccount());
        List<Integer> historyOfIncomeMoney = proxyUser.getIncomesHistory();
        System.out.println("История пополнений:");
        historyOfIncomeMoney.stream().forEach(System.out::println);

        /*//Создаем оригинальный объект
        Man vasia = new Man("Вася", 30, "Санкт-Петербург", "Россия");

        //Получаем загрузчик класса у оригинального объекта
        ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

        //Получаем все интерфейсы, которые реализует оригинальный объект
        Class[] interfaces = vasia.getClass().getInterfaces();

        //Создаем прокси нашего объекта vasia
        Person proxyVasia = (Person) Proxy.newProxyInstance(vasiaClassLoader, interfaces, new PersonInvocationHandler(vasia));

        //Вызываем у прокси объекта один из методов нашего оригинального объекта
        proxyVasia.introduce(vasia.getName());
        proxyVasia.sayFrom(vasia.getCity(), vasia.getCountry());*/
    }
}
