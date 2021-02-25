package Window;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel{
    private JButton back = null;
    private JButton newSubject = null;
    private JButton newSection = null;
    private JButton newCard = null;
    private JButton next = null;
    private JButton chooseLanguage = null;
    private boolean backPressed = false;
    private boolean nextPressed = false;
    private boolean newSubjectPressed = false;
    private boolean newSectionPressed = false;
    private boolean newCardPressed = false;
    private boolean chooseLanguagePressed = false;
    
    public Options(){
        super();
        super.setBackground(Color.white);
        super.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        
        back = new JButton("Back");
        next = new JButton("Next");
        newSubject = new JButton("New Subject");
        newSection = new JButton("New Section");
        newCard = new JButton("New Flash Card");
        chooseLanguage = new JButton("Switch Language");
        
        back.addActionListener(new ButtonListener());
        next.addActionListener(new ButtonListener());
        newSubject.addActionListener(new ButtonListener());
        newSection.addActionListener(new ButtonListener());
        newCard.addActionListener(new ButtonListener());
        chooseLanguage.addActionListener(new ButtonListener()); 
        
        super.add(newSubject);
        super.add(newSection);
        super.add(newCard);
        super.add(chooseLanguage);
        super.add(next);
        super.add(back);
    }
    public void newQuestionSettings(){
        next.setText("Save");
        enableButton(chooseLanguage);
        disableButton(newSubject);
        disableButton(newSection);
        disableButton(newCard);
    }
    public void questionSettings(){
        next.setText("Next");
        enableButton(chooseLanguage);
        disableButton(newSubject);
        disableButton(newSection);
        disableButton(newCard);
    }
    public void selectionSettings(){
        next.setText("Next");
        disableButton(chooseLanguage);
        enableButton(newSubject);
        enableButton(newSection);
        enableButton(newCard);
    }
    public void disableButton(JButton b){
        b.setVisible(false);
        b.setEnabled(false);
    }
    public void enableButton(JButton b){
        b.setVisible(true);
        b.setEnabled(true);
    }
    public void goneBack(){
        backPressed = false;
    }
    public void setNextPressed(boolean b){
        nextPressed = b;
    }
    public void setNewSubjectPressed(boolean b){
        newSubjectPressed = b;
    }
    public void setNewSectionPressed(boolean b){
        newSectionPressed = b;
    }
    public void setNewCardPressed(boolean b){
        newCardPressed = b;
    }
    public void setChooseLanguagePressed(boolean b){
        chooseLanguagePressed = b;
    }
    public boolean getBackPressed(){
        return backPressed;
    }
    public boolean getNextPressed(){
        return nextPressed;
    }
    public boolean getNewSubjectPressed(){
        return newSubjectPressed;
    }
    public boolean getNewSectionPressed(){
        return newSectionPressed;
    }
    public boolean getNewCardPressed(){
        return newCardPressed;
    }
    public boolean getChooseLanguagePressed(){
        return chooseLanguagePressed;
    }
    
    private final class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object buttonPressed = e.getSource();
            
            if(buttonPressed == back){
                backPressed = true;
            }
            else if(buttonPressed == next){
                nextPressed = true;
            }
            else if(buttonPressed == newSubject){
                newSubjectPressed = true;
            }
            else if(buttonPressed == newSection){
                newSectionPressed = true;
            }
            else if(buttonPressed == newCard){
                newCardPressed = true;
            }
            else if(buttonPressed == chooseLanguage){
                chooseLanguagePressed = true;
            }
        }
    }
}
