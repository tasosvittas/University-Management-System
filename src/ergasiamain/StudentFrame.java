package ergasiamain;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.GridLayout;

public class StudentFrame extends JFrame {
    private ArrayList<Student>mstudents = new ArrayList<Student>();
    private ArrayList<Lesson>mlessons = new ArrayList<Lesson>();
    private ArrayList<Enroll>menrolls = new ArrayList<Enroll>();
    private JButton fileButton;
    private JTextArea infoArea;
    private JPanel areaPanel;
    
    JMenuBar menuBar;
    JMenu fileMenu, StudentMenu, LessonMenu;
    JMenuItem saveFile, loadFile, exitFile;
    JMenuItem newStudent, courseDeclaration, showStudent, deleteStudent;
    JMenuItem newLesson, studentPerformance, semesterChoose, deleteLesson;
    
public static boolean isNumeric(String str) 
{ 
  try {  
        Double.parseDouble(str);  
        return true;
       } catch(NumberFormatException e){  
    return false;  
    }  
}
    
 public StudentFrame(String title)
 {
      super(title);
      setSize(300,300);
      setResizable(false);
    
      
      menuBar=new JMenuBar();
      setJMenuBar(menuBar);
      fileMenu=new JMenu("Αρχείο");
      loadFile=new JMenuItem("Φόρτωση");
      loadFile.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {    
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout());
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setSize(600,300);
                newFrame.setTitle("Φόρτωση Αρχείου από τον Υπολογιστή");
                JPanel panel = new JPanel();
                newFrame.add(panel);
                panel.setVisible(true);
                panel.setLayout(new GridLayout(2,1));
                fileButton=new JButton("Φόρτωση Αρχείου");
                fileButton.addActionListener(new ActionListener()
                    {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                        {
                        JFileChooser chooser=new JFileChooser();
                        int returnVal = chooser.showOpenDialog(fileButton);
                        if (returnVal == JFileChooser.APPROVE_OPTION) 
                        {
                    
                            try {
                                String filename=chooser.getSelectedFile().getAbsolutePath();
                                FileReader rw=new FileReader(filename);
                                Scanner in=new Scanner(rw);
                                mstudents = new ArrayList<Student>();
                                while(in.hasNextLine())
                                {                                   
                                    String line=in.nextLine();
                                    String[] parts=line.split(",");
                                    if(parts.length == 4)
                                    {
                                        mstudents.add(new Student(parts[0],parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));
                                        infoArea.setText(infoArea.getText()+"\n"+line);
                                    }
                                    if(parts.length == 3 && !isNumeric(parts[0]))
                                    {   
                                        mlessons.add(new Lesson(parts[0],parts[1],Integer.parseInt(parts[2])));
                                        infoArea.setText(infoArea.getText()+"\n"+line);
                                    }
                                    else if(parts.length == 3 && isNumeric(parts[0]))
                                    {
                                        menrolls.add(new Enroll(Integer.parseInt(parts[0]),parts[1],Double.parseDouble(parts[2])));
                                        infoArea.setText(infoArea.getText()+"\n"+line);
                                    }
                                    
                                }
                                in.close();
                                } catch (FileNotFoundException ex) {
                                System.out.println("File not found");
                            }
                    
                        }
                    }
            });
            panel.add(fileButton);
            areaPanel=new JPanel();
            areaPanel.setLayout(new GridLayout());
            panel.add(areaPanel);
            infoArea=new JTextArea();
            infoArea.setRows(8);
            infoArea.setColumns(50);
            infoArea.setWrapStyleWord(true);
            infoArea.setEditable(false);
            JScrollPane sp = new JScrollPane(infoArea); 
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            areaPanel.add(sp);
            areaPanel.setVisible(true);
           }
      });
      
      saveFile=new JMenuItem("Αποθήκευση");
      saveFile.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent ae)
          {
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout());
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setSize(600,300);
                newFrame.setTitle("Αποθήκευση Αρχείου στον Υπολογιστή");
                JPanel panel = new JPanel();
                newFrame.add(panel);
                panel.setVisible(true);
                panel.setLayout(new GridLayout(2,1));
                fileButton=new JButton("Αποθήκευση Αρχείου");
                fileButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        JFileChooser chooser = new JFileChooser();
                        int ret = chooser.showSaveDialog(fileButton);
                        if (ret == JFileChooser.APPROVE_OPTION) 
                        {
                        String filename = chooser.getSelectedFile().getAbsolutePath();
                        try {
                        
                            FileWriter fw = new FileWriter(filename);
                            PrintWriter pw = new PrintWriter(fw);
                            for(Student x: mstudents)
                            {
                                pw.println(""+x);
                            }
                            for(Lesson l: mlessons)
                            {
                                pw.println(""+l);
                            }
                            for(Enroll e: menrolls)
                            {
                                pw.println(""+e);
                            }
                            fw.close();
                            } catch (IOException ex) {
                            Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                panel.add(fileButton);
                panel.add(areaPanel);
                panel.setVisible(true);
          } 
      });    
       
      exitFile=new JMenuItem("Έξοδος");
      exitFile.addActionListener(new ActionListener()
      {
        @Override
           public void actionPerformed(ActionEvent ae)
           {
               System.exit(0);
           }
      });
 
      fileMenu.add(loadFile);
      fileMenu.add(saveFile);
      fileMenu.add(exitFile);
      menuBar.add(fileMenu);
      
      StudentMenu=new JMenu("Μαθητής");
      newStudent=new JMenuItem("Νέος Μαθητής");
      newStudent.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {    
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout(5,1));
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setBounds(300,100,400,300);
                newFrame.setTitle("Νέος Μαθητής");
                JTextField nt1 = new JTextField("Όνομα Μαθητή",15);
                newFrame.add(nt1);
                JTextField nt2 = new JTextField("Επώνυμο Μαθητή",15);
                newFrame.add(nt2);
                JTextField nt3 = new JTextField("ΑΜ Μαθητή",15);
                newFrame.add(nt3);
                JTextField nt4 = new JTextField("Εξάμηνο Μαθητή",15);
                newFrame.add(nt4);
                JButton btn = new JButton("Προσθήκη");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        int am=Integer.parseInt(nt3.getText());
                        int sem=Integer.parseInt(nt4.getText());
                        Student s1 = new Student(nt1.getText(),nt2.getText(),am,sem);
                        mstudents.add(s1);
                    }
                });
                newFrame.add(btn);
           }
      });
      
      courseDeclaration =new JMenuItem("Δήλωση Μαθήματος");
      courseDeclaration.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {    
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout(5,1));
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setBounds(300,100,400,300);
                newFrame.setTitle("Νέος Μαθητής");
                JTextField nt1 = new JTextField("ΑΜ Μαθητή",15);
                newFrame.add(nt1);
                JTextField nt3 = new JTextField("Κωδικός Μαθήματος",15);
                newFrame.add(nt3);
                JTextField nt4 = new JTextField("Επιθυμητός Βαθμός",15);
                newFrame.add(nt4);
                JButton btn = new JButton("Προσθήκη");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        int am=Integer.parseInt(nt1.getText());
                        double sem=Double.parseDouble(nt4.getText());
                        for(Student x: mstudents)
                        {
                            for(Lesson l: mlessons)
                            {
                                if(x.getAm() == am && l.getLessonCode().equals(nt3.getText()))
                                {
                                    Enroll s1 = new Enroll(am,nt3.getText(),sem);
                                    menrolls.add(s1);
                                }
                            }
                        }
                    }
                });
                newFrame.add(btn);
           }
      });
      
      showStudent=new JMenuItem("Εμφάνιση Μαθητή");
      showStudent.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {    
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout());
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setSize(600,300);
                
                JPanel panel = new JPanel();
                newFrame.add(panel);
                newFrame.setTitle("Ευρέση Μαθητή");
                panel.setVisible(true);
                panel.setLayout(new GridLayout(2,1));
                JTextField nt1 = new JTextField("Εύρεση Μαθητή Μέσω ΑΜ",15);
                panel.add(nt1);
                panel.setLayout(new GridLayout(3,1));
                JButton btn = new JButton("Έυρεση");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        int count = 0;
                        double gradeSum = 0;
                        double average;
                        int ntInt = Integer.parseInt(nt1.getText());
                        for (Enroll e: menrolls)
                        {
                            if(e.getAmEnroll() == ntInt)
                            {
                                count++;
                                gradeSum = gradeSum + e.getStudentGrade();
                            }                            
                        }
                        average = gradeSum / count;
                        for (Student x: mstudents)
                        {   
                            if (x.getAm() == ntInt)
                            { 
                                infoArea.setText(x.toString()+", M.O Μαθημάτων: "+average+"\n");
                            }
                        }                     
                    }
                });
                panel.add(btn);
                areaPanel=new JPanel();
                areaPanel.setLayout(new GridLayout());
                panel.add(areaPanel);
                infoArea=new JTextArea();
                infoArea.setRows(8);
                infoArea.setColumns(50);
                infoArea.setWrapStyleWord(true);
                infoArea.setEditable(false);
                JScrollPane sp = new JScrollPane(infoArea); 
                sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                areaPanel.add(sp);
                areaPanel.setVisible(true);
           }
      });
      
      deleteStudent=new JMenuItem("Διαγραφή Μαθητή");
      deleteStudent.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {    
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout(5,1));
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setBounds(150,100,250,200);
                newFrame.setTitle("Διαγραφή Μαθητή");
                JTextField nt1 = new JTextField("ΑΜ Μαθητή",15);
                newFrame.add(nt1);
                JButton btn = new JButton("Διαγραφή");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        int ntInt = Integer.parseInt(nt1.getText());
                        for (Student x: mstudents)
                        {
                            if (x.getAm() == ntInt)
                            {
                                mstudents.remove(x);
                            }
                        }
                    }
                });
                newFrame.add(btn);
           }
      });
     
      StudentMenu.add(newStudent);
      StudentMenu.add(courseDeclaration);
      StudentMenu.add(showStudent);
      StudentMenu.add(deleteStudent);
      menuBar.add(StudentMenu);
      
      LessonMenu=new JMenu("Μάθημα");
      newLesson=new JMenuItem("Νέο Μάθημα");
      newLesson.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {    
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout(5,1));
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setBounds(300,100,400,300);
                newFrame.setTitle("Νέο Μάθημα");
                JTextField nt1 = new JTextField("Όνομα Μαθήματος",15);
                newFrame.add(nt1);
                JTextField nt2 = new JTextField("Κωδικός Μαθήματος",15);
                newFrame.add(nt2);
                JTextField nt3 = new JTextField("Εξάμηνο Μαθήματος",15);
                newFrame.add(nt3);
                JButton btn = new JButton("Προσθήκη");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        int sem=Integer.parseInt(nt3.getText());
                        Lesson l1 = new Lesson(nt1.getText(),nt2.getText(),sem);
                        mlessons.add(l1);
                    }
                });
                newFrame.add(btn);
           }
      });
      
      studentPerformance=new JMenuItem("Εμφάνιση Επιδόσεων");
      studentPerformance.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout());
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setSize(600,300);
                
                JPanel panel = new JPanel();
                newFrame.add(panel);
                panel.setVisible(true);
                panel.setLayout(new GridLayout(3,1));
                newFrame.setTitle("Εμφάνιση Επιδόσεων");
                JTextField nt1 = new JTextField("Κωδικός Μαθήματος",15);
                panel.add(nt1);
                JButton btn = new JButton("Έυρεση Μαθητών");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        for (Enroll e: menrolls)
                        {
                            if (e.getLessonCodeEnroll().equals(nt1.getText()))
                            {
                                infoArea.append(e.toString()+"\n");
                            }
                        }
                    }
                });
                panel.add(btn);
                areaPanel=new JPanel();
                areaPanel.setLayout(new GridLayout());
                panel.add(areaPanel);
                infoArea=new JTextArea();
                infoArea.setRows(8);
                infoArea.setColumns(50);
                infoArea.setWrapStyleWord(true);
                infoArea.setEditable(false);
                JScrollPane sp = new JScrollPane(infoArea); 
                sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                areaPanel.add(sp);
                areaPanel.setVisible(true);
           }
      });
      
      semesterChoose=new JMenuItem("Επιλογή Εξαμήνου");
      semesterChoose.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout());
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setSize(600,300);
                
                JPanel panel = new JPanel();
                newFrame.add(panel);
                panel.setVisible(true);
                panel.setLayout(new GridLayout(2,1));
                newFrame.setTitle("Επιλογή Εξαμήνου");
                JTextField nt1 = new JTextField("Επιθυμητό Εξάμηνο (Πχ. 3)",15);
                panel.add(nt1);
                JButton btn = new JButton("Έυρεση Μαθημάτων");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        int lessonSem = Integer.parseInt(nt1.getText());
                        for (Lesson l: mlessons)
                        {
                            if(l.getLessonSemester() == lessonSem)
                            {
                                infoArea.append(l.toString()+"\n"); 
                            }
                        }
                    }
                });
                panel.add(btn);
                areaPanel=new JPanel();
                areaPanel.setLayout(new GridLayout());
                panel.add(areaPanel);
                infoArea=new JTextArea();
                infoArea.setRows(8);
                infoArea.setColumns(50);
                infoArea.setWrapStyleWord(true);
                infoArea.setEditable(false);
                JScrollPane sp = new JScrollPane(infoArea); 
                sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                areaPanel.add(sp);
                areaPanel.setVisible(true);
           }
      });
      
      deleteLesson=new JMenuItem("Διαγραφή Μαθήματος");
      deleteLesson.addActionListener(new ActionListener()
      {
          @Override
           public void actionPerformed(ActionEvent ae)
           {
                JFrame newFrame = new JFrame();
                newFrame.setLayout(new GridLayout(5,1));
                newFrame.setVisible(true);
                newFrame.setResizable(false);
                newFrame.setBounds(150,50,250,200);
                newFrame.setTitle("Διαγραφή Μαθήματος");
                JTextField nt1 = new JTextField("Κωδικός Μαθήματος",15);
                newFrame.add(nt1);
                JButton btn = new JButton("Διαγραφή");
                btn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        for (Lesson le: mlessons)
                        {
                            if (le.getLessonCode().equals(nt1.getText()))
                            {
                                mlessons.remove(le);
                            }
                        }
                    }
                });
                newFrame.add(btn);
           }
      });
      
      LessonMenu.add(newLesson);
      LessonMenu.add(studentPerformance);
      LessonMenu.add(semesterChoose);
      LessonMenu.add(deleteLesson);
      menuBar.add(LessonMenu);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
 }
    
}
