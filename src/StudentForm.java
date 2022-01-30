import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;
public class StudentForm {
    public JPanel mainPanel;
    private JPanel topPanel;
    private JTabbedPane tabbedPane1;
    private JEditorPane preview_pane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JTabbedPane tabbedPane5;
    private JButton printCoursesButton;
    private JButton verificaRestanteButton;
    private JButton calculeazaMediaButton;
    private JButton printGradesButton;
    private JLabel welcomeLabel;
    private JEditorPane preview_pane;
    private JEditorPane preview_panel3;
    private JTabbedPane tabbedPane2;
    private JEditorPane preview_pane4;
    private JButton backToCommandsButton;
    private JButton btc0;
    private JButton btc1;
    private JButton btc2;
    private JButton backToLoginButton;
    private JFrame owner;
    public StudentForm(User u,JFrame owner) {
        this.owner=owner;
        owner.setResizable(false);
        owner.setSize(new Dimension(400,450));
        preview_pane.setEditable(false);
        preview_pane2.setEditable(false);
        preview_panel3.setEditable(false);
        preview_pane4.setEditable(false);
        FileDataManager fileDataManager = new FileDataManager();
        Curs[] curses=fileDataManager.createCoursesData();
        String userFirstName = new String();
        String userLastName = new String();
//        FileDisplay fd = new FileDisplay();
//        fd.displayCourses(curses);
        for(Map.Entry<String,String>user:u.menuStrategy.getAccountHolderInformation().entrySet()){
            userFirstName = user.getKey();
            userLastName = user.getValue();
        }
        welcomeLabel.setText("\r\rWelcome, "+ userFirstName +" "+ userLastName+"!");
        String finalUserFirstName = userFirstName;
        String finalUserLastName = userLastName;
        printCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(1);
                StringBuilder courseText= new StringBuilder();
                boolean studFound = false;
                for(Curs c:curses) {
                    //System.out.println(c);
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                            studFound = true;
                        }
                    }
                    if (studFound) {
                        courseText.append(c.nume);
                        courseText.append("\n");
                    }
                }
                preview_pane.setText(courseText.toString());
            }

        });
        printGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(2);
                StringBuilder courseText= new StringBuilder();
                boolean studFound = false;
                int nota=0;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                           nota=c.nota.get(s);
                        }
                    }
                    if (nota != 0) {

                        courseText.append("Aveti nota " + nota + " la cursul " + c.nume);

                        courseText.append("\n");
                    }else {
                        courseText.append("Nu sunteti inscris la cursul " + c.nume);

                        courseText.append("\n");
                    }
                }
                preview_pane2.setText(courseText.toString());
            }
        });
        calculeazaMediaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(3);
                StringBuilder courseText= new StringBuilder();
                int nota=0;
                int sum=0;
                int numNote=0;
                float medie=0.f;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                            nota=c.nota.get(s);
                        }
                    }
                    if (nota != 0) {

                        sum+=nota;
                        numNote++;
                    }

                }
                medie=(float)sum/(float) numNote;
                courseText.append("Media este: "+ medie);
                preview_panel3.setText(String.valueOf(courseText));
            }

        });
        verificaRestanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(4);
                StringBuilder courseText= new StringBuilder();
                int nota=0;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                            nota=c.nota.get(s);
                        }
                    }
                    if (nota != 0) {
                        if(nota<5) {
                            courseText.append("Aveti restanta la " + c.nume);

                            courseText.append("\n");
                        }
                    }
                }
                preview_pane4.setText(courseText.toString());

            }
        });
        backToCommandsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
        btc0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
        btc1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
        btc2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
    }
}
