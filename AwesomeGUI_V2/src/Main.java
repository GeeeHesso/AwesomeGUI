import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
    private JPanel panel_T;
    private JComboBox yearSpin;
    private JComboBox subjectSpin;
    private JComboBox axisSpin;
    private JButton fileSelect;
    private JTextArea textRef;
    private JTextArea textAbtract;
    private JTextArea textKeyWord;
    private JTextArea textLink;
    private JButton doneBtn;
    private JCheckBox checkBox_reference;
    private JCheckBox checkBox_abstract;
    private JCheckBox checkBox_keyWords;
    private JCheckBox checkBox_link;
    private JButton checkBtn;
    private File file;

    //File chooser
    final JFileChooser fc = new JFileChooser();

    //Bool to check if everything is ok
    boolean b_year = false;
    boolean b_subject = false;
    boolean b_axis = false;
    boolean b_file = false;
    boolean b_ref = false;
    boolean b_abstract = false;
    boolean b_kw = false;
    boolean b_link = false;

    private String[] subjectFolder = {"_","In_the_News","Presentations","Papers"};
    private String[] axisFolder = {"_","smartgrid","energy_transition","network_stability","network_robustness"};

    private String[] yearString = {"---Choose year", "2015", "2016", "2017", "2018", "2019", "2020"};
    private String[] subjectString ={"--Choose subject","In the News","Presentations","Papers"};
    private String[] axisString = {"--Choose axis","Smartgrid","Energy Transition","Network Stability","Network Robustness"};

    private String folderName;
    boolean b_folderName = false;


    public Main() {

        init_GUI();

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("AwesomeGUI");

        frame.setContentPane(new Main().panel_T);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setVisible(true);

    }

    public void init_GUI(){
//        yearSpin.insertItemAt("wow",0);

        for (int i = 0; i<yearString.length;i++)
            yearSpin.insertItemAt(yearString[i],i);
        yearSpin.setSelectedIndex(0);
//        ---

        for (int i = 0; i<subjectString.length;i++)
            subjectSpin.insertItemAt(subjectString[i],i);
        subjectSpin.setSelectedIndex(0);
//        ---

        for (int i = 0; i<axisString.length;i++)
            axisSpin.insertItemAt(axisString[i],i);
        axisSpin.setSelectedIndex(0);
//        ---

        fileSelect.addActionListener(new FileBtnListener());

        doneBtn.addActionListener(new DoneBtnListener());
        doneBtn.setEnabled(false);

        checkBtn.addActionListener(new CheckBtnListener());

        yearSpin.addActionListener(new YearSpinListener());
        subjectSpin.addActionListener(new SubjectSpinListener());
        axisSpin.addActionListener(new AxisSpinListener());

        checkBox_reference.addActionListener(new RefCBListener());
        checkBox_abstract.addActionListener(new AbsCBListener());
        checkBox_keyWords.addActionListener(new KWCBListener());
        checkBox_link.addActionListener(new LinkCBListener());


        textRef.setText("*  [<--Authors-->, *<--Title-->*, <--others-->]");
        textAbtract.setText("# <--Title--> \n Dated : <--date--> \n <--Abstract text--> \n **Author :** <--Authors<sup>1</sup>--> \n 1) <--Author's institute--> \n Also available online there : [Arxiv.org](<--Links to the archive-->) \n ");
    }

    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    private class RefCBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            b_ref = checkBox_reference.isSelected();
        }
    }

    private class AbsCBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            b_abstract = checkBox_abstract.isSelected();
        }
    }

    private class KWCBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            b_kw = checkBox_keyWords.isSelected();
        }
    }

    private class LinkCBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            b_link = checkBox_link.isSelected();
        }
    }

    private class YearSpinListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(yearSpin.getSelectedIndex()!=0)
                b_year=true;
            else
                b_year=false;
        }
    }

    private class SubjectSpinListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(subjectSpin.getSelectedIndex()!=0)
                b_subject=true;
            else
                b_subject=false;
        }
    }

    private class AxisSpinListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(axisSpin.getSelectedIndex()!=0)
                b_axis=true;
            else
                b_axis=false;
        }
    }

    private class CheckBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!b_kw)
                checkBox_keyWords.setBackground(Color.YELLOW);
            else
                checkBox_keyWords.setBackground(Color.GREEN);

            if(!b_link)
                checkBox_link.setBackground(Color.YELLOW);
            else
                checkBox_link.setBackground(Color.GREEN);

            if (!b_year){
                yearSpin.setEditable(true);
                yearSpin.getEditor().getEditorComponent().setBackground(Color.RED);
                yearSpin.setEditable(false);
            }
            else{
                yearSpin.setEditable(true);
                yearSpin.getEditor().getEditorComponent().setBackground(Color.GREEN);
                yearSpin.setEditable(false);
            }

            if(!b_subject){
                subjectSpin.setEditable(true);
                subjectSpin.getEditor().getEditorComponent().setBackground(Color.RED);
                subjectSpin.setEditable(false);
            }
            else{
                subjectSpin.setEditable(true);
                subjectSpin.getEditor().getEditorComponent().setBackground(Color.GREEN);
                subjectSpin.setEditable(false);
            }

            if(!b_axis){
                axisSpin.setEditable(true);
                axisSpin.getEditor().getEditorComponent().setBackground(Color.RED);
                axisSpin.setEditable(false);
            }
            else{
                axisSpin.setEditable(true);
                axisSpin.getEditor().getEditorComponent().setBackground(Color.GREEN);
                axisSpin.setEditable(false);
            }

            if(!b_file)
                fileSelect.setBackground(Color.RED);
            else
                fileSelect.setBackground(Color.GREEN);

            if(!b_ref)
                checkBox_reference.setBackground(Color.RED);
            else
                checkBox_reference.setBackground(Color.GREEN);

            if(!b_abstract)
                checkBox_abstract.setBackground(Color.RED);
            else
                checkBox_abstract.setBackground(Color.GREEN);

            if (b_year && b_subject && b_axis && b_ref && b_abstract && b_file){
                doneBtn.setEnabled(true);
            }

        }
    }

    private class FileBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (b_year && b_subject) {
                int returnVal = fc.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    this.movefile(file, file.getName());
                    b_file = true;
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }

            if (!b_year){
                yearSpin.setEditable(true);
                yearSpin.getEditor().getEditorComponent().setBackground(Color.RED);
                yearSpin.setEditable(false);
            }
            else{
                yearSpin.setEditable(true);
                yearSpin.getEditor().getEditorComponent().setBackground(Color.GREEN);
                yearSpin.setEditable(false);
            }

            if(!b_subject){
                subjectSpin.setEditable(true);
                subjectSpin.getEditor().getEditorComponent().setBackground(Color.RED);
                subjectSpin.setEditable(false);
            }
            else{
                subjectSpin.setEditable(true);
                subjectSpin.getEditor().getEditorComponent().setBackground(Color.GREEN);
                subjectSpin.setEditable(false);
            }

        }
        public void movefile(File file_source, String filename){

            if(!b_folderName){//Create folder
                folderName = filename.split("[.]")[0];
                new File(yearString[yearSpin.getSelectedIndex()]+File.separator+subjectFolder[subjectSpin.getSelectedIndex()]+File.separator+ folderName +File.separator).mkdirs();
                b_folderName = true;
            }

            //now copy file
            File dest_file = new File(yearString[yearSpin.getSelectedIndex()]+File.separator+subjectFolder[subjectSpin.getSelectedIndex()]+File.separator+ folderName +File.separator + filename);
            try {
                copyFileUsingJava7Files(file_source,dest_file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class DoneBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            boolean to_write_ref = false;
            boolean b_readme2 = true;

            //----------------------------------------------------------------
            // Writing file specific README
            //----------------------------------------------------------------
            String rm_string1 = textAbtract.getText();
            String rm_string2 = textKeyWord.getText();
            String rm_string3 = textLink.getText();

            String to_write_1 = rm_string1+ "\n<!-- keywords: " + axisSpin.getSelectedItem().toString() +", " + rm_string2+ "-->\n<!-- link: " + rm_string3 + "-->";

            try {
                Files.write(Paths.get(yearString[yearSpin.getSelectedIndex()]+File.separator+subjectFolder[subjectSpin.getSelectedIndex()]+File.separator+ folderName +File.separator + "README.md"), to_write_1.getBytes()); //THIS IS THE ONE TO USE TO WRITE!
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            //----------------------------------------------------------------
            // Writing branch specific README
            //----------------------------------------------------------------

            String to_write="";
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(yearString[yearSpin.getSelectedIndex()]+File.separator+subjectFolder[subjectSpin.getSelectedIndex()]+File.separator+"README.md"));
                String line = reader.readLine();
                while (line != null) {

                    if(!to_write_ref && line.length()>0 && line.charAt(0)=='*') {
//                        System.out.println("WOW");
                        to_write += textRef.getText() + "\n\n";
                        to_write_ref = true;
                    }
                    to_write += line + "\n";
                    // read next line
                    line = reader.readLine();

                }
                reader.close();
                if(!to_write_ref)
                    to_write += textRef.getText() + "\n\n";

                try {
                    Files.write(Paths.get(yearString[yearSpin.getSelectedIndex()]+File.separator+subjectFolder[subjectSpin.getSelectedIndex()]+File.separator+"README2.md"), to_write.getBytes());
                } catch (IOException e1) {
//                    e1.printStackTrace();
                    b_readme2 = false;
                }
            } catch (IOException er) {
//                System.out.println("Error writing files (README2)");
                b_readme2 = false;
            }

            if(b_readme2) {//Rename the file and delete the other one. Might have to do some checks to avoid breaking things
                File f1 = new File(yearString[yearSpin.getSelectedIndex()] + File.separator + subjectFolder[subjectSpin.getSelectedIndex()] +File.separator + "README2.md");
                File f2 = new File(yearString[yearSpin.getSelectedIndex()] + File.separator + subjectFolder[subjectSpin.getSelectedIndex()] + File.separator + "README.md");
                f1.renameTo(f2);
            }

        }
    }
}