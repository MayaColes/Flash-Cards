package Window;
import FlashCardsObjects.Language;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameWindow{
    private static JButton ok = null;
    private static JButton cancel = null;
    private static ArrayList<JRadioButton> langButtons = null;
    private static ArrayList<Language> languages = null;
    private static Language currentLanguage = new Language();
    
    public static String subjectNameDialog(JFrame f){
        return (String)JOptionPane.showInputDialog(f, "Subject Name:", "New Subject", JOptionPane.PLAIN_MESSAGE);
    }
    public static String sectionNameDialog(JFrame f, String subject){
        if(!subject.isEmpty()){
            return (String)JOptionPane.showInputDialog(f, "Section Name:","New Section - " + subject, JOptionPane.PLAIN_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(f, "Please select a subject.", "Subject Error", JOptionPane.WARNING_MESSAGE);
        
        return "";
    }
    public static String questionNameDialog(JFrame f){
        return (String)JOptionPane.showInputDialog(f, "Question Name:", "New Card", JOptionPane.PLAIN_MESSAGE);
    }
    public static void selectSectionWarning(JFrame f){
        JOptionPane.showMessageDialog(f, "Please select one section", "Section Error", JOptionPane.WARNING_MESSAGE);
    }
    public static Language languageSelection(JFrame f, ArrayList<Language> l){
        JPanel panel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        
        languages = l;
        
        langButtons = new ArrayList();
        
        for(int i = 0; i < l.size(); i++){
            String name = l.get(i).getName();
            JRadioButton lang = new JRadioButton(name);
            lang.addActionListener(new ButtonListener());
            group.add(lang);
            panel.add(lang);
            langButtons.add(lang);
        }
 
        int choice = JOptionPane.showConfirmDialog(f, panel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        return currentLanguage;
    }
    
    public static class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object buttonPressed = e.getSource();
            
            for(int i = 0; i < langButtons.size(); i++){
                if(buttonPressed == langButtons.get(i)){
                    currentLanguage = languages.get(i);
                }
            }
        }
    }
}
