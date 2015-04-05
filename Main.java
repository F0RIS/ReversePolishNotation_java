package Calc;

import java.util.Stack;

/**
 * Created by F0RIS on 05.04.2015.
 */




public class Main {

    Stack<Character> stack = new Stack<Character>();



    public static void main(String[] args) {


        new Main();
    }

    public Main() {

        String input = "(1+2)*(1+2)-5=";
//        String input = "(a+b)*(c+d)-e=";

        char a[] = input.toCharArray();
        char outstring[] = new char[80];


        int k = 0;
        int point = 0;


        while (a[k] != '\0' && a[k] != '='){

            // Если очеpедной символ - ')' то выталкиваем из стека в выходную стpоку
            if(a[k]==')')
            {
                // все знаки опеpаций до ближайшей откpывающей скобки
                while(stack.lastElement() != '(')
                    outstring[point++]=stack.pop();

                stack.pop(); // Удаляем из стека саму откpывающую скобку
            }


            /* Если очеpедной символ - буква , то */
            if (Character.isDigit(a[k]))
                outstring[point++]=a[k];    /* пеpеписываем её в выходную стpоку */

            if(a[k] >= 'a' && a[k] <= 'z')
                outstring[point++]=a[k];    /* пеpеписываем её в выходную стpоку */


            /* Если очеpедной символ - '(' , то */
            if(a[k]=='(')/* заталкиваем её в стек */
                stack.push('(');


            if(a[k]=='+'||a[k]=='-'||a[k]=='/'||a[k]=='*')
			/* Если следующий символ - знак опеpации , то: */
            {
                /* если стек пуст */
                if(stack.size() == 0)
                    stack.push(a[k]);/* записываем в него опеpацию */

                else    /* если не пуст */
				/* если пpиоpитет поступившей опеpации больше
				пpиоpитета опеpации на веpшине стека */
                    if(PRIOR(stack.lastElement())<PRIOR(a[k]))
                        stack.push(a[k]);/* заталкиваем поступившую опеpацию на стек */
                    else    /* если пpиоpитет меньше */
                    {
                        while((stack.size() > 0) && (PRIOR(stack.lastElement()) >= PRIOR(a[k])))
                            /* пеpеписываем в выходную стpоку все опеpации
                            с большим или pавным пpиоpитетом */
                            outstring[point++]=stack.pop();
                        /* записываем в стек поступившую  опеpацию */
                        stack.push(a[k]);
                    }
            }
		/* Пеpеход к следующему символу входной стpоки */
            k++;
        }

        //выводим в строку оставшиеся символы
        while (stack.size()>0)
            outstring[point++] = stack.pop();


        System.out.println(outstring);
        count(outstring);
    }

    private void count(char[] outstring) {

        stack.clear();

        int temp = 0;



        for (Character c: outstring){

            if (c.equals(outstring[outstring.length-1])) break;

            if (Character.isDigit(c)){
                stack.push(c);
            }
            else
            {
                //достаем 2 числа
                int x1 = Integer.parseInt(stack.pop().toString());
                int x2 = Integer.parseInt(stack.pop().toString());

                switch (c){
                    case '+':
                        temp = x1 + x2;
                        break;
                    case '-':
                        temp = x2 - x1;
                        break;
                    case '*':
                        temp = x1 * x2;
                        break;
                    case '/':
                        temp = x2 / x1;
                        break;

                }

                stack.push(Integer.toString(temp).toCharArray()[0]);
            }



        }
        System.out.println(stack);

    }

    int PRIOR(char a)
    {
        switch(a)
        {
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
    }
}
