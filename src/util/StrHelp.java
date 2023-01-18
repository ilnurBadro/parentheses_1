package util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StrHelp {
    private static final Character OPEN_PARENT = '(';
    private static final Character CLOSE_PARENT = ')';
    private static final String REPLACING_PARENTHESIS_REGEXP = "[^\\(\\)]";
    private static String ZERO_RESPONSE = "0";

    public static String getCountAndValidParent(String input) {
        Stack<AbstractMap.SimpleEntry<Integer, Character>> validationStack = new Stack<>();
        Stack<AbstractMap.SimpleEntry<Integer, Character>> charsWithIndexesStack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        //Валидация входных символов
        input = input.replaceAll(REPLACING_PARENTHESIS_REGEXP, "");
        if (input.isEmpty()) {
            return ZERO_RESPONSE;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == OPEN_PARENT && i != input.length() - 1) {
                charsWithIndexesStack.push(new AbstractMap.SimpleEntry<>(i, c));
                validationStack.push(new AbstractMap.SimpleEntry<>(i, c));
            } else if (c == CLOSE_PARENT && !validationStack.isEmpty()) {
                validationStack.pop();
                charsWithIndexesStack.push(new AbstractMap.SimpleEntry<>(i, c));
            }
        }

        //Исключение открывающих скобок, для которых не нашлось закрывающих
        List<Integer> exceptionIndexes = new ArrayList<>();
        if (!validationStack.isEmpty()) {
            validationStack
                    .elements()
                    .asIterator()
                    .forEachRemaining(el -> exceptionIndexes.add(el.getKey()));
        }

        //Сборка итоговой строки валидных пар скобок.
        charsWithIndexesStack
                .elements()
                .asIterator()
                .forEachRemaining(elem -> {
                    if (!exceptionIndexes.contains(elem.getKey())) {
                        stringBuilder.append(elem.getValue());
                    }
                });

        String validParentheses = stringBuilder.toString();
        return !validParentheses.isEmpty() ? validParentheses.length() + " - " + validParentheses : ZERO_RESPONSE;
    }
}

