
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felpi
 */
public class EditorLogic {
    public File actualFile;
    PrintWriter archive;
        
    public void NewArchive(JEditorPane EditorPanel){
        String archiveName = "NewText.txt";    
        actualFile = new File(archiveName);
        try 
        {
            if(actualFile.createNewFile())
            {
                System.out.println("file.txt File Created in Project root directory");
                EditorPanel.setEnabled(true);
            }
            else 
            {
                System.out.println("File file.txt already exists in the project root directory");
                EditorPanel.setEnabled(false);
            }           
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditorLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SaveArchive(JEditorPane EditorPanel){
        try {
            archive = new PrintWriter(actualFile.getName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditorLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        archive.print(EditorPanel.getText());
        archive.flush();  
        System.out.println("Archive Saved");
    }
    
    public void OpenArchive(JEditorPane EditorPanel){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        EditorPanel.setEnabled(true);
        
        actualFile = chooser.getSelectedFile();

        String line = null;

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(actualFile.getName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditorLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            while((line = bufferedReader.readLine()) != null) { 
                System.out.println(line);
                EditorPanel.setText(EditorPanel.getText() + line + "\n");
            }   
        } catch (IOException ex) {
            Logger.getLogger(EditorLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(EditorLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void CloseFile(JEditorPane EditorPanel){
        SaveArchive(EditorPanel);
        System.exit(0);
    }
    
    EditorLogic(JEditorPane EditorPanel){
        EditorPanel.setEnabled(false);
        System.out.println("Editor Started");
    }
}
