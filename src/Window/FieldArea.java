package Window;
import FlashCardsObjects.Field;
import FlashCardsObjects.Language;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class FieldArea{
    private final Color incorrect = new Color(207, 34, 0);
    private final Color correct = new Color(0, 201, 30);
    private final int defaultQuestionHeight = 3;
    private final int defaultQuestionWidth = 15;
    private final int defaultAnswerHeight = 5;
    private final int defaultAnswerWidth = 15;
    private Field info;
    private JTextArea answer;
    private JTextArea question;
    private JButton submit;
    private Language language;
    private boolean newField = false;
    
    public FieldArea(){
        info = new Field();
        answer = new JTextArea();
        question = new JTextArea();
        language = new Language();
        
        answer.setColumns(defaultAnswerWidth);
        answer.setRows(defaultAnswerHeight);
        question.setColumns(defaultQuestionWidth);
        question.setRows(defaultQuestionHeight);
        
        answer.setBorder(BorderFactory.createLineBorder(Color.gray));
        question.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        question.addKeyListener(new KeySubstitution());
        answer.addKeyListener(new KeySubstitution());
        answer.setFocusable(true);
        answer.requestFocus();
        answer.setLineWrap(true);
        newField = true;
    }
    public FieldArea(Field i, Language l, int ansHeight, int ansWidth){
        info = i;
        language = l;
        answer = new JTextArea(ansWidth, ansHeight);
        question = new JTextArea(info.getQuestion());
        submit = new JButton("Submit");
        
        submit.addActionListener(new FieldButtonListener());
        
        answer.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        question.setEditable(false);
        
        answer.addKeyListener(new KeySubstitution());
        answer.setFocusable(true);
        answer.requestFocus();
        answer.setLineWrap(true);
    }
    public void substitute(JTextArea ta){
        
        String unsubstituted = ta.getText();
        int pos = unsubstituted.length() - 1;
        
        StringBuffer substitution = new StringBuffer();
        while(pos > -1){
            if(language.inResults(unsubstituted.charAt(pos) + "")){
                break;
            }
            
            substitution.insert(0, unsubstituted.charAt(pos));
            pos--;
        }

        if(language.inAssociation(substitution + "")){
            
            ta.setText(unsubstituted.substring(0, pos + 1) + language.association(substitution.toString()));
        }
        
    }
    public void checkAnswer(){
        answer.setEditable(false);
        if(answer.getText().equals(info.getAnswer())){
            answer.setForeground(correct);
        }
        else{
            answer.setForeground(incorrect);
        }
        
    }
    public Field getField(){
        return (new Field(question.getText(), answer.getText()));
    }
    public JTextArea getAnswer(){
        return answer;
    }
    public JTextArea getQuestion(){
        return question;
    }
    public JButton getSubmit(){
        return submit;
    }
    public Language getLanguage(){
        return language;
    }
    public void setLanguage(Language l){
        language = l;
    }
    public boolean getNewField(){
        return newField;
    }
    
    private final class KeySubstitution implements KeyListener{
        public void keyReleased(KeyEvent event){
            substitute(answer);
            if(newField){
                substitute(question);
            }
            
        }
        public void keyPressed(KeyEvent event){
        
        }
        public void keyTyped(KeyEvent event){
            
        }
    }
    private final class FieldButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object source = e.getSource();
            
            if(source == submit){
                checkAnswer();
            }
        }
    }
}
