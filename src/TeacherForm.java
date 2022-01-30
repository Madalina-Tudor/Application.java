import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TeacherForm {
    private JPanel panel1;
    private JPanel upPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JTabbedPane tabbedPane5;
    private JButton printCoursesButton;
    private JButton backToLoginButton;
    private JButton printStudentsButton;
    private JButton gradeStudentsButton;
    private JLabel welcomeLabel;
    private JTextPane textPane1;
    private JButton backToMenuButton;
    private JTextPane textPane2;
    private JButton backToMenuButton1;
    private JTextPane textPane3;
    private JButton backToMenuButton2;
    private JButton gradeStudentButton;
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField notaField;
    private JFrame owner;


    public JPanel getPanel1() {
        return panel1;
    }

    public void initUI() {
        owner.setSize(new Dimension(400, 300));
        owner.setResizable(false);
        textPane1.setEditable(false);
        textPane2.setEditable(false);
    }

    public TeacherForm(JFrame owner, User u) {
        this.owner = owner;
        this.initUI();
        FileDataManager fileDataManager = new FileDataManager();
        Curs[] curses = fileDataManager.createCoursesData();
        this.owner = owner;
        String userFirstName = new String();
        String userLastName = new String();
        for (Map.Entry<String, String> user : u.menuStrategy.getAccountHolderInformation().entrySet()) {
            userFirstName = user.getKey();
            userLastName = user.getValue();
        }
        StringBuffer text = new StringBuffer();
        text.append("Hello, " + "\n" + userFirstName + " " + userLastName);
        welcomeLabel.setText(text.toString());
        String finalUserFirstName = userFirstName;
        String finalUserLastName = userLastName;
        printCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder data = new StringBuilder();
                boolean teacherFound = false;
                tabbedPane1.setSelectedIndex(1);
                for (Curs c : curses) {
                    if (c.profu.equals(new Profesor(finalUserFirstName, finalUserLastName))) {
                        data.append(c.nume);
                        data.append("\n");
                        teacherFound = true;
                    }

                }
                if (!teacherFound) {
                    data.append("Nu participati la niciun curs");
                }
                textPane1.setText(data.toString());
            }
        });
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
        printStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(2);
                StringBuilder data = new StringBuilder();
                boolean teacherFound = false;
                for (Curs c : curses) {
                    if (c.profu.equals(new Profesor(finalUserFirstName, finalUserLastName))) {
                        for (Student s : c.studenti) {
                            data.append(s.nume + " " + s.prenume);
                            data.append("\n");
                        }
                        teacherFound = true;
                    }
                }
                if (!teacherFound) {
                    data.append("Nu sunt studenti inscrisi la acest curs");
                }
                textPane2.setText(data.toString());
            }
        });
        gradeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDisplay fd = new FileDisplay();
                boolean studFound = false;
                for (Curs c : curses) {
                    if (c.profu.equals(new Profesor(finalUserFirstName, finalUserLastName))) {
                        for (Student s : c.studenti) {
                            //System.out.println(s);
                            if (s.nume.equals(numeField.getText()) && s.prenume.equals(prenumeField.getText())) {
                                c.nota.put(s, Integer.parseInt((String) notaField.getText()));
                                studFound = true;
                            }
                        }
                    }
                }
                if (studFound) {
                    fd.displayCourses(curses);
                    numeField.setText("");
                    prenumeField.setText("");
                    notaField.setText("");
                } else JOptionPane.showMessageDialog(null, "Nu s a putut gasi studentul");
            }

        });
        gradeStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(3);
            }
        });
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
        backToMenuButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
    }
}