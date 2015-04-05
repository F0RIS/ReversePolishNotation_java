package Calc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by F0RIS on 05.04.2015.
 */
public class ExpressionParser implements Operators {

    Stack<String> stack = new Stack<String>();
    String input;

    public  ExpressionParser(String _input) {


        if (_input.charAt(_input.length()-1) != '=')
            _input += '=';

        this.input = _input;
    }


    int PRIOR(char a) {
        switch (a) {
            case '^':
                return 4;

            case '*':
            case '/':
                return 3;

            case '-':
            case '+':
                return 2;

            case '(':
                return 1;

        }
        return -1;

//        throw new Exception("Неизвестный оператор");
    }

    public String makeReversePolishNotation() throws Exception {
        char a[] = input.toCharArray();
        String outstring = "";

        int k = 0;
        String temp = "";

        while (a[k] != '\0' && a[k] != '=') {


            /* Если очеpедной символ - '(' , то */
            if (a[k] == '(')/* заталкиваем её в стек */
                stack.push("(");


            // Если очеpедной символ - ')' то выталкиваем из стека в выходную стpоку
            if (a[k] == ')') {
                // все знаки опеpаций до ближайшей откpывающей скобки
                while (stack.lastElement().charAt(0) != '(')
                    outstring += stack.pop().charAt(0) + " ";

                stack.pop(); // Удаляем из стека саму откpывающую скобку
            }


            //Если очеpедной символ - цифра, читываем все символы цифры
            temp = "";
            while (Character.isDigit(a[k])) {
                temp += Character.toString(a[k]);

                if (Character.isDigit(a[k + 1]))
                    k++;
                else
                    break;


            }
            if (!temp.isEmpty())
                outstring += temp + " ";

            //буквы
            if (a[k] >= 'a' && a[k] <= 'z')
                outstring += a[k];    /* пеpеписываем её в выходную стpоку */


//            if(a[k]=='+'||a[k]=='-'||a[k]=='/'||a[k]=='*')
            if (operators.contains(Character.toString(a[k])))
			/* Если следующий символ - знак опеpации , то: */ {
                /* если стек пуст */
                if (stack.size() == 0)
                    stack.push(Character.toString(a[k]) + " ");/* записываем в него опеpацию */

                else    /* если не пуст */
				/* если пpиоpитет поступившей опеpации больше
				пpиоpитета опеpации на веpшине стека */
                    if (PRIOR(stack.lastElement().charAt(0)) < PRIOR(a[k]))
                        stack.push(Character.toString(a[k]));/* заталкиваем поступившую опеpацию на стек */
                    else    /* если пpиоpитет меньше */ {
                        while ((stack.size() > 0) && (PRIOR(stack.lastElement().charAt(0)) >= PRIOR(a[k])))
                            /* пеpеписываем в выходную стpоку все опеpации
                            с большим или pавным пpиоpитетом */
                            outstring += stack.pop().charAt(0) + " ";
                        /* записываем в стек поступившую  опеpацию */
                        stack.push(Character.toString(a[k]));
                    }
            }

            if (!operators.contains(Character.toString(a[k])) && (!Character.isDigit(a[k])) && a[k] != ' ' && a[k] != '(' && a[k] != ')')
                throw new Exception("Founded unknown operator");


		    //Пеpеход к следующему символу входной стpоки
            k++;
        }

        //выводим в строку оставшиеся символы
        while (stack.size() > 0)
            outstring += stack.pop().charAt(0) + " ";


        return outstring;
    }

}