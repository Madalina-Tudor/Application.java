import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel welcomeLabel;
    private JTextField usernameF;
    private JTextField prenumeF;
    private JTextField numeF;
    private JPasswordField passwordF;
    private JLabel uNlabel;
    private JLabel passLabel;
    private JLabel numeLabel;
    private JLabel prenumeLabel;
    private JButton backToLoginButton;
    private JButton regButtton;
    private JLabel imageLabel;
    private JPanel buttonPane;
    private JFrame owner;

    public JPanel getMainPanel(){return mainPanel;}
    public RegisterForm(JFrame owner) {
        this.owner=owner;
        this.guiAspect();
        owner.setSize(new Dimension(400,300));
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
        regButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==regButtton){
                    int res = Application.getInstance().register(usernameF.getText(), new String(passwordF.getPassword()),numeF.getText(),prenumeF.getText());
                    if(res==0){
                        JOptionPane.showMessageDialog(null,"Error");
                    }else if(res==-1){
                        JOptionPane.showMessageDialog(null,"User already exists");
                    }else if(res == -2 || res == -3){
                        usernameF.setText("");
                        prenumeF.setText("");
                        numeF.setText("");
                        prenumeF.setText("");
                    }
                }
            }

        });
    }
    private void guiAspect(){
        ImageIcon ico = new ImageIcon("icons/unitbv.png");
        imageLabel.setIcon(scaleImg(ico,50,50));
        Border border = imageLabel.getBorder();
        Border margin = new EmptyBorder(0,0,0,100);
        imageLabel.setBorder(new CompoundBorder(border, margin));
    }
    private ImageIcon scaleImg(ImageIcon im, int weight,int height){
        Image newImg = im.getImage();
        newImg= newImg.getScaledInstance(weight,height, Image.SCALE_AREA_AVERAGING);
        ImageIcon newIco = new ImageIcon(newImg);
        return newIco;
    }
}