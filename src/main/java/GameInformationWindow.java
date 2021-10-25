import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class GameInformationWindow {
  private JPanel panel1;
  private JTextField statusTextField;
  private JButton button1;
  private JDialog jFrame;

  public JButton getButton1() {
    return button1;
  }

  public JTextField getStatusTextField() {
    return statusTextField;
  }

  public void showEnable(String title, String status) {
    jFrame = new JDialog();
    jFrame.setTitle(title);
    jFrame.setContentPane(panel1);
    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    statusTextField.setText(status);
    jFrame.setLocation(500, 400);
    jFrame.setSize(300, 100);
    jFrame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    jFrame.setVisible(true);
  }

  public void show(String title, String status) {
    jFrame = new JDialog();
    jFrame.setTitle(title);
    jFrame.setContentPane(panel1);
    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    statusTextField.setText(status);
    statusTextField.setEnabled(false);
    button1.setVisible(false);
    jFrame.setSize(300, 100);
    jFrame.setLocation(500, 400);
    jFrame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    jFrame.setVisible(true);
  }

  public void close() {
    jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
  }
}
