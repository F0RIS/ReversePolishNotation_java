package Calc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by F0RIS on 05.04.2015.
 */


public class Main {

    Stack<String> stack = new Stack<String>();
    Set<Character> set = new HashSet<Character>();




    public static void main(String[] args) {


        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() throws Exception {

        set.add('+');
        set.add('-');
        set.add('*');
        set.add('/');
        set.add('^');



//        String input = "(1+2)*(3+4)-5=";
        String input = "2+2*2=";
//        String input = "(a+b)*(c+d)-e=";

        char a[] = input.toCharArray();
        String outstring ="";


        int k = 0;
        int point = 0;
        String temp = "";

        while (a[k] != '\0' && a[k] != '='){

            // Если очеpедной символ - ')' то выталкиваем из стека в выходную стpоку
            if(a[k]==')')
            {
                // все знаки опеpаций до ближайшей откpывающей скобки
                while(stack.lastElement().charAt(0) != '(')
                    outstring += stack.pop().charAt(0)+" ";

                stack.pop(); // Удаляем из стека саму откpывающую скобку
            }


//             Если очеpедной символ - цифра

            temp ="";
            while (Character.isDigit(a[k])){
                temp += Character.toString(a[k]);

                if (Character.isDigit(a[k+1]))
                    k++;
                else
                    break;


            }
            if (!temp.isEmpty())
                outstring += temp + " ";


            if(a[k] >= 'a' && a[k] <= 'z')
                outstring += a[k];    /* пеpеписываем её в выходную стpоку */


            /* Если очеpедной символ - '(' , то */
            if(a[k]=='(')/* заталкиваем её в стек */
                stack.push("(");


//            if(a[k]=='+'||a[k]=='-'||a[k]=='/'||a[k]=='*')
            if(set.contains(a[k]))
			/* Если следующий символ - знак опеpации , то: */
            {
                /* если стек пуст */
                if(stack.size() == 0)
                    stack.push(Character.toString(a[k]) + " ");/* записываем в него опеpацию */

                else    /* если не пуст */
				/* если пpиоpитет поступившей опеpации больше
				пpиоpитета опеpации на веpшине стека */
                    if(PRIOR(stack.lastElement().charAt(0))<PRIOR(a[k]))
                        stack.push(Character.toString(a[k]));/* заталкиваем поступившую опеpацию на стек */
                    else    /* если пpиоpитет меньше */
                    {
                        while((stack.size() > 0) && (PRIOR(stack.lastElement().charAt(0)) >= PRIOR(a[k])))
                            /* пеpеписываем в выходную стpоку все опеpации
                            с большим или pавным пpиоpитетом */
                            outstring += stack.pop().charAt(0) + " ";
                        /* записываем в стек поступившую  опеpацию */
                        stack.push(Character.toString(a[k]));
                    }
            }
		/* Пеpеход к следующему символу входной стpоки */
            k++;
        }

        //выводим в строку оставшиеся символы
        while (stack.size()>0)
            outstring += stack.pop().charAt(0)+" ";


        System.out.println("Обратная польская запись: ");
        System.out.println(outstring);
        count(outstring);
    }

    private void count(String input) {

        stack.clear();




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

        System.out.println("Ответ: " + stack.pop());

    }

    int PRIOR(char a) throws Exception {
        switch(a)
        {
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


        throw new Exception("Неизвестный оператор");
    }

    public boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
