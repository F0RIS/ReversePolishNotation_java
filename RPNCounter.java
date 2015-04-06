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

    }

    public double Count() throws Exception {

        double res = 0.0;

        Stack<Double> stack = new Stack<Double>();

        String all[] = input.split(" ");

        for (String item : all){

            if (Character.isDigit(item.charAt(0))){
                stack.push(Double.parseDouble(item));
                continue;
            }


            if (operators.contains(Character.toString(item.charAt(0)))){

                //достаем 2 числа
                /*if (stack.size()<1)
                    throw new Exception("Expression have some error, counting stopped");
*/
                double x1 = 0,x2 =0;

                if (item.charAt(0) != 'p') //для Пи ничего доставать не нужно, оно просто заменяется
                     x1 = stack.pop();

                //первые 5 оператора бинарные, остальные унарные
                if (operators.indexOf(item.charAt(0))<5)
                    x2 = stack.pop();// = stack.pop();

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
                        res =  Math.pow((double)x2, (double)x1);
                        break;
                    case 's':
                        res =  Math.sin(x1);
                        break;

                    case 'c':
                        res = Math.cos(x1);
                        break;
                    case 't':
                        res = Math.tan(x1);
                        break;

                    case 'e':
                        res = Math.exp(x1);
                        break;

                    case 'p':
                        res = Math.PI;
                        break;

                    case 'g':
                        res = Math.log10(x1);
                        break;

                    case 'l':
                        res = Math.log(x1);
                        break;


                }

                stack.push(res);
            }
        }

//        System.out.println("Ответ: " + stack.lastElement());
        return stack.pop();
    }

}
