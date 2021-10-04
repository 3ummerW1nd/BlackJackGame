import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow {
    private JTextArea temp, temp1;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton noButton;
    private JButton yesButton;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextField textField1;
    private JButton sureButton;

    public JTextArea getTemp1() {
        return temp1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JButton getNoButton() {
        return noButton;
    }

    public JButton getYesButton() {
        return yesButton;
    }

    public JTextArea getTextArea2() {
        return textArea2;
    }

    public JTextArea getTextArea3() {
        return textArea3;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JButton getSureButton() {
        return sureButton;
    }

    public JTextArea getTemp() {
        return temp;
    }

    public GameWindow(JFrame jFrame) {
        temp1 = new JTextArea();
        temp1.setText("");
        temp1.setVisible(true);
        temp = new JTextArea();
        temp.setText("false");
        temp.setVisible(false);
        initLayout();
        jFrame.setContentPane(panel1);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setSize(800, 800);
    }

    public void initLayout() {
        textArea1.setText("庄家：\n");
        textArea3.setText("玩家：\n");
        textArea2.setText("游戏信息：\n");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp1.setText("");
                temp1.append("1");
                System.out.println(temp1.getText());
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp1.setText("");
                temp1.append("0");
                System.out.println(temp1.getText());
            }
        });
        sureButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                temp.setText("true");
            }
        });
    }

    public int scanIntFromTextArea() {
        System.out.println(temp1.getText());
        while (temp1.getText().equals(""));
        int answer = Integer.parseInt(temp1.getText());
        temp1.setText("");
        return answer;
    }

    public String scanFromTextField() {
        while (temp.getText().equals("false"));
        temp.setText("false");
        String answer = textField1.getText();
        textField1.setText("");
        return answer;
    }

    public void clearWindows() {
        getTextArea1().setText("");
        getTextArea3().setText("");
    }


}
