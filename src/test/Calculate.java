package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import util.Utils;

/**
 * created by suxinwei at 2020/2/23
 * description:
 */
class Calculate {
    public static final String OPERATOR = "+-*/()";

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String expression = scanner.nextLine();
        String expression = "9+(3-1)*3+10/2";
//        String suffixExpression = "9 3 1-3*+10 2/+";
        List<String> list = parseData(expression);
        List<String> suffixList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (OPERATOR.contains(s)) {
                if (stack.contains("(")) {
                    if (")".equals(s)) {
                        // 匹配括号
                        String pop = stack.pop();
                        suffixList.add(pop);
                        stack.pop();
                    } else {
                        stack.push(s);
                    }
                } else {

                    stack.push(s);
                }
            } else {
                suffixList.add(s);
            }
        }
        while (!stack.isEmpty()) {
            suffixList.add(stack.pop());
        }
        Utils.printList(suffixList);
    }

    private static List<String> parseData(String expression) {
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < expression.length(); i++) {
            String s = expression.substring(i, i + 1);
            if (OPERATOR.contains(s)) {
                if (temp.length() > 0) {
                    list.add(temp);
                }
                list.add(s);
                temp = "";
            } else {
                temp += s;
            }
            if (i == expression.length() - 1) {
                list.add(temp);
            }
        }
        return list;
    }


}
