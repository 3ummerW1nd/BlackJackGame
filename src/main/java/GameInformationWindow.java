import javax.swing.*;

public class GameInformationWindow {
    private JPanel panel1;
    private JTextField statusTextField;
    private JButton button1;

    public void showEnable(String title, String status) {
        JFrame jFrame = new JFrame(title);
        jFrame.setContentPane(panel1);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statusTextField.setText(status);
        jFrame.setSize(200, 100);
    }

    public void show(String title, String status) {
        JFrame jFrame = new JFrame(title);
        jFrame.setContentPane(panel1);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statusTextField.setText(status);
        statusTextField.setEnabled(false);
        button1.setVisible(false);
        jFrame.setSize(200, 100);
        jFrame.setLocation(500, 400);
    }

}
