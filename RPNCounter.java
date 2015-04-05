package Calc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by F0RIS on 05.04.2015.
 */
public class RPNCounter implements Operators {

    private String input;



    public RPNCounter(String input) {
        this.input = input;

        loadOperator();

    }

    public int Count(){

        int res = 0;

        Stack<Integer> stack = new Stack<Integer>();

        String all[] = input.split(" ");

        for (String item : all){

            if (Character.isDigit(item.charAt(0))){
                stack.push(Integer.parseInt(item));
                continue;
            }

//            if (item.charAt(0) == '+' || item.charAt(0) == '-' || item.charAt(0) == '*' || item.charAt(0) == '/' ){
            if (set.contains(item.charAt(0))){
                //достаем 2 числа

                int x1 = stack.pop();
                int x2 = stack.pop();

                switch (item.charAt(0)){
                    case '+':
                        res = x1 + x2;
                        break;
                    case '-':
                        res = x2 - x1;
                        break;
                    case '*':
                        res = x1 * x2;
                        break;
                    case '/':
                        res = x2 / x1;
                        break;

                    case '^':
                        res = (int) Math.pow((double)x2, (double)x1);
                        break;

                }

                stack.push(res);
            }
        }

//        System.out.println("Ответ: " + stack.lastElement());
        return stack.pop();
    }

    @Override
    public void loadOperator() {
        for (char c : operators) {
            set.add(c);
        }
    }
}
