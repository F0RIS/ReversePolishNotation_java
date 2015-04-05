package Calc;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by F0RIS on 05.04.2015.
 */


public class Main {

    public static void main(String[] args) throws IOException {

        //text mode
        if (args.length>0) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Введите выражние: ");
            ExpressionParser parser = new ExpressionParser(reader.readLine());

            String s = null;
            try {
                s = parser.makeReversePolishNotation();


                System.out.println("Обратная польская запись: ");
                System.out.println(s);

                RPNCounter counter = new RPNCounter(s);

                System.out.println("Ответ: " + counter.Count());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }
        else{

                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // If Nimbus is not available, fall back to cross-platform
                    try {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    } catch (Exception ex) {
                        // not worth my time
                    }
                }


            new Form();

        }


    }

}
