import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    public LoginForm(JFrame owner) {
        this.owner = owner;
        owner.setSize(new Dimension(400,150));
        lblUsername.setForeground(Color.white);
        labelPassword.setForeground(Color.white);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    System.out.println("Am apasat butonul de login");
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));
                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.TEACHER){
                            mainPanel.setVisible(false);
                            owner.setContentPane(new TeacherForm(owner,Application.getInstance().currentUser).getPanel1());
                        }else if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.STUDENT){
                            mainPanel.setVisible(false);
                            owner.setContentPane(new StudentForm(Application.getInstance().currentUser, owner).mainPanel);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                owner.setContentPane(new RegisterForm(owner).getMainPanel());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton regButton;
    private JLabel labelPassword;
    private JFrame owner;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
