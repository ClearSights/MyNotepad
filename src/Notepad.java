import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Insight on 2016-12-31.
 */
public class Notepad {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UIManager.put("defaultFont", new Font("Arial", Font.PLAIN, 14));

        NotepadWindow notepad = new NotepadWindow();
        notepad.showUp();

//        // write keys and values
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter("key_value_pair.txt");
//
//            Enumeration keys = UIManager.getDefaults().keys();
//            while (keys.hasMoreElements()) {
//                Object key = keys.nextElement();
//                Object value = UIManager.get(key);
//
//                if (value != null) {
//                    writer.write(key.toString());
//                    writer.write(" = ");
//                    writer.write(value.toString());
//                    writer.write("\n");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                writer.close();
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
    }
}

class NotepadWindow extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu File, Edit, View, Organize, Help;
    JMenuItem File_Open, File_Save, File_Saveas, File_PageSetup, File_Print, File_Exit;
    JMenu File_New;
    JMenuItem File_New_File, File_New_Prj;

    JScrollPane scrollPane;
    JTextArea textArea;

    NotepadWindow() {
        // initialization
        menuBar = new JMenuBar();
        File = new JMenu("File(F)");
        File.setMnemonic('F');
        Edit = new JMenu("Edit(E)");
        Edit.setMnemonic('E');
        View = new JMenu("View(V)");
        View.setMnemonic('V');
        Organize = new JMenu("Organize(O)");
        Organize.setMnemonic('O');
        Help = new JMenu("Help(H)");
        Help.setMnemonic('H');

        File_New = new JMenu("New...");
        File_Open = new JMenuItem("Open");
        File_Save = new JMenuItem("Save");
        File_Saveas = new JMenuItem("Save as");
        File_PageSetup = new JMenuItem("Page setup");
        File_Print = new JMenuItem("Print");
        File_Exit = new JMenuItem("Exit");

        File_New_File = new JMenuItem("File");
        File_New_Prj = new JMenuItem("Project");

        // relations
        File_New.add(File_New_File);
        File_New.add(File_New_Prj);
        File.add(File_New);
        File.add(File_Open);
        File.add(File_Save);
        File.add(File_Saveas);
        File.addSeparator();
        File.add(File_PageSetup);
        File.add(File_Print);
        File.addSeparator();
        File.add(File_Exit);
        menuBar.add(File);
        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(Organize);
        menuBar.add(Help);

        // layout
        this.setJMenuBar(menuBar);

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);

        // window attributes
        this.setTitle("My Notepad");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // listeners
        File_Open.addActionListener(this);
        File_Saveas.addActionListener(this);
        File_Open.setActionCommand("File_Open");
        File_Saveas.setActionCommand("File_SaveAs");
    }

    void showUp() {
        this.setSize(800, 600);
        this.setLocationByPlatform(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "File_Open":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(this);
                try {
                    // get selected file content
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile != null) {
                        FileReader reader = new FileReader(selectedFile);
                        BufferedReader bfReader = new BufferedReader(reader);
                        String s, content = "";
                        while ((s = bfReader.readLine()) != null) {
                            content += s + "\n";
                        }
                        textArea.setText(content);

                        // close stream
                        try {
                            reader.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                break;
            case "File_SaveAs" :

                break;
        }
    }
}
