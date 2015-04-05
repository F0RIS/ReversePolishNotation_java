package Calc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by F0RIS on 05.04.2015.
 */


public class Main {

    public static void main(String[] args) {

        
        ExpressionParser parser = new ExpressionParser("5^2+3=");

        String s = null;
        try {
            s = parser.makeReversePolandNotation();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Обратная польская запись: ");
        System.out.println(s);

        RPNCounter counter = new RPNCounter(s);

        System.out.println("Ответ: " + counter.Count());

    }

}
