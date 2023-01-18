import util.StrHelp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите любок количество кр. скобок");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = StrHelp.getCountAndValidParent(input);
        System.out.println("Вы ввели: " + input);
        System.out.println(output);
    }
}