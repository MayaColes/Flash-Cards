package Window;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.Color;
import FlashCardsObjects.Language;
import FlashCardsObjects.Field;
import FlashCardsObjects.Question;

public class QuestionWindow extends JPanel implements ActionListener{
    private GridBagConstraints gbc = null;
    private boolean newQuestion = false;
    private ArrayList<FieldArea> fieldAreas = null;
    private JButton newField = null;
    
    public QuestionWindow(){
        super();
        super.setForeground(Color.white);
        super.setBackground(Color.white);
        super.setLayout(new GridBagLayout());
        
        fieldAreas = new ArrayList();
        gbc = new GridBagConstraints(); 
        gbc.gridy = 0;
        gbc.gridx = 0;
    }
    public void createFieldAreas(Question q){
        for(int i = 0; i < q.getFieldsLength(); i++){
            FieldArea newFA = new FieldArea(q.getOneField(i), new Language(), 20, 7);
            fieldAreas.add(newFA);
            addField(newFA);
        }
    }
    public void addField(FieldArea fa){
        gbc.insets = (new Insets(15, 0, 10, 0));
        super.add(fa.getQuestion(), gbc);
        gbc.gridy++;
        
        gbc.insets = (new Insets(0, 0, 20, 0));
        super.add(fa.getAnswer(), gbc);
        gbc.gridy++;
        gbc.insets = (new Insets(0, 0, 0, 0));
        
        if(!fa.getNewField()){
            super.add(fa.getSubmit(), gbc);
            gbc.gridy++;
        }
    }
    public void setFieldsLanguage(Language l){
        for(int i = 0; i < fieldAreas.size(); i++){
            fieldAreas.get(i).setLanguage(l);
        }
    }
    public void createNewQuestion(){
        newQuestion = true;
        fieldAreas = new ArrayList();
        newField = new JButton("New Field");
        newField.addActionListener(this);
        
        FieldArea f = new FieldArea();
        fieldAreas.add(f);
        
        addField(f);
        super.add(newField, gbc);
    }
    public void newField(){
        FieldArea f = new FieldArea();
        fieldAreas.add(f);
        super.remove(newField);
        
        addField(f);
        super.add(newField, gbc);
    }
    
    public ArrayList<Field> getFields(){
        ArrayList<Field> fields = new ArrayList();
        
        for(int i = 0; i < fieldAreas.size(); i++){
            fields.add(fieldAreas.get(i).getField());
        }
        return fields;
    }
    public void resetWindow(){
        super.removeAll();
        gbc.gridy = 0;
        newQuestion = false;
        super.repaint();
    }
    
    public void actionPerformed(ActionEvent e){
        newField();
    }
}
