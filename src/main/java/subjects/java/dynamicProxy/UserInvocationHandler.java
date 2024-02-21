package subjects.java.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Scanner;

public class UserInvocationHandler implements InvocationHandler {
    private User user;

    public UserInvocationHandler(User user) {
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print("Введите пароль: ");
        Scanner scanner = new Scanner(System.in);
        String passwordIn = scanner.nextLine();
        if (user.getPassword().equals(passwordIn)) {
            System.out.println("Вы успешно зашли!");
            return method.invoke(user, args);
        } else {
            System.out.println("Пароль введен неверно!");
            return null;
        }
    }
}
