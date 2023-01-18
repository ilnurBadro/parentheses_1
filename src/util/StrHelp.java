package util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StrHelp {
    private static final Character OPEN = '(';
    private static final Character CLOSE = ')';
    private static final String REPL_PARENT_REGEXP = "[^\\(\\)]";
    private static String ZERO_RESP = "0";

    public static String getCountAndValidParent(String input) {
        Stack<AbstractMap.SimpleEntry<Integer, Character>> validStack = new Stack<>();
        Stack<AbstractMap.SimpleEntry<Integer, Character>> charsWithIndexesStack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        //Валидация входных символов
        input = input.replaceAll(REPL_PARENT_REGEXP, "");
        if (input.isEmpty()) {
            return ZERO_RESP;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == OPEN && i != input.length() - 1) {
                charsWithIndexesStack.push(new AbstractMap.SimpleEntry<>(i, c));
                validStack.push(new AbstractMap.SimpleEntry<>(i, c));
            } else if (c == CLOSE && !validStack.isEmpty()) {
                validStack.pop();
                charsWithIndexesStack.push(new AbstractMap.SimpleEntry<>(i, c));
            }
        }

        //Исключение непарных скобок
        List<Integer> exceptIndex = new ArrayList<>();
        if (!validStack.isEmpty()) {
            validStack
                    .elements()
                    .asIterator()
                    .forEachRemaining(el -> exceptIndex.add(el.getKey()));
        }

        //Сборка итоговой строки валидных пар скобок.
        charsWithIndexesStack
                .elements()
                .asIterator()
                .forEachRemaining(elem -> {
                    if (!exceptIndex.contains(elem.getKey())) {
                        stringBuilder.append(elem.getValue());
                    }
                });

        String validParent = stringBuilder.toString();
        return !validParent.isEmpty() ? validParent.length() + " - " + validParent : ZERO_RESP;
    }
}

