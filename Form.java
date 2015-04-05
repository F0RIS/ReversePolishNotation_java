package Calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by F0RIS on 05.04.2015.
 */
public class Form extends JFrame{
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton countButton;
    private JPanel root;

    public Form() throws HeadlessException {
        super("Expression calculator");

        setContentPane(root);

        pack();
        setSize(500,300);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(countButton);

        setVisible(true);



        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textArea1.setText("");
                String input = textField1.getText();
                if (input.isEmpty())
                    return;

                ExpressionParser parser = new ExpressionParser(input);

                String s = null;
                try {
                    s = parser.makeReversePolishNotation();



                    textArea1.append("Reverse Polish Notation: \n");

                    textArea1.append(s + "\n");

                    RPNCounter counter = new RPNCounter(s);

                    textArea1.append("Answer: " + counter.Count());

                } catch (Exception e1) {
                    textArea1.append(e1.getMessage());
                }
            }
        });

    }


}
