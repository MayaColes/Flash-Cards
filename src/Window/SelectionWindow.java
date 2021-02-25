package Window;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Dimension;
import FlashCardsObjects.Subject;
import FlashCardsObjects.Section;
import java.awt.Color;
import java.awt.event.*;

public class SelectionWindow extends JPanel implements ActionListener{
    private final Dimension panelSize = new Dimension(275, 300);
    private final Dimension scrollSize = new Dimension(285, 310);
    private JPanel sectionsPanel = null;
    private JPanel subjectsPanel = null;
    private JScrollPane sectionsScroll = null;
    private JScrollPane subjectsScroll = null;
    private ArrayList<SubjectButton> subjectButtons = null;
    private ArrayList<JCheckBox> sections = null;
    private ArrayList<Subject>  subjects = null;
    private ButtonGroup subjectGroup = null;
    private int selectedSubject = -1;
    
    public SelectionWindow(ArrayList<Subject> s){
        super();
        
        subjectsPanel = new JPanel();
        sectionsPanel = new JPanel();
        subjectButtons = new ArrayList();
        sections = new ArrayList();
        subjectGroup = new ButtonGroup();
        sectionsScroll = new JScrollPane(sectionsPanel);
        subjectsScroll = new JScrollPane(subjectsPanel);
        
        sectionsPanel.setAutoscrolls(true);
        subjectsPanel.setAutoscrolls(true);
        
        subjects = s;
        
        subjectsPanel.setBackground(Color.white);
        sectionsPanel.setBackground(Color.white);
        
        subjectsPanel.setLayout(new FlowLayout (FlowLayout.LEFT, 0, -10));
        sectionsPanel.setLayout(new FlowLayout (FlowLayout.LEFT, 0, -10));
        
        subjectsPanel.setPreferredSize(panelSize);
        sectionsPanel.setPreferredSize(panelSize);
        
        subjectsScroll.setPreferredSize(scrollSize);
        sectionsScroll.setPreferredSize(scrollSize);
        
        super.add(subjectsScroll, BorderLayout.WEST);
        super.add(sectionsScroll, BorderLayout.EAST);
        
        for(int i = 0; i < s.size(); i++){
            addSubject(s.get(i));
        }
        
        
    }
    public void addSubjectToPanel(SubjectButton b){
        subjectsPanel.add(b);
        subjectsPanel.repaint();
    }
    public void addSectionToPanel(JCheckBox section){
        sectionsPanel.add(section);
        sectionsPanel.repaint();
    }
    public void addSubject(Subject s){
        SubjectButton newSubject = new SubjectButton(s);
        newSubject.addActionListener(this);
        
        subjectGroup.add(newSubject);
        subjectButtons.add(newSubject); 
        addSubjectToPanel(newSubject);
    }
    public void addNewSubject(Subject s){
        subjects.add(s);
        SubjectButton newSubject = new SubjectButton(s);
        newSubject.addActionListener(this);
        
        subjectGroup.add(newSubject);
        subjectButtons.add(newSubject); 
        addSubjectToPanel(newSubject);
    }
    public void addSection(Section s, String subjectName){
        int i = 0;
        for(; i < subjects.size(); i++){
            if(subjects.get(i).getName().equals(subjectName)){
                subjects.get(i).addSection(s);
                break;
            }
        }
        Section[] newSections = new Section[subjects.get(i).getSections().size()];
        addSubjectSections(subjects.get(i).getSections().toArray(newSections));
    }
    public void removeAllSections(){
        sectionsPanel.removeAll();
        sections = new ArrayList();
        sectionsPanel.repaint();
    }
    public void addSubjectSections(Section[] s){
        removeAllSections();
        
        for(int i = 0; i < s.length; i++){
            JCheckBox newSection = new JCheckBox(s[i].getName());
            newSection.setPreferredSize(new Dimension(200, 50));
            sections.add(newSection);
            addSectionToPanel(sections.get(i));
            
        }
        sectionsPanel.repaint();
    }
    public ArrayList<Section> getSelectedSections(){
        ArrayList<Section> selectedSections = new ArrayList();
        
        for(int i = 0; i < sections.size(); i++){
            if(sections.get(i).isSelected()){
                selectedSections.add(subjectButtons.get(selectedSubject).getOneSection(i));
            }
        }
        
        return selectedSections;
    }
    public String getSelectedSubject(){
        if(selectedSubject != -1){
            return subjectButtons.get(selectedSubject).getName();
        }
        return "";
    }
    
    public void actionPerformed(ActionEvent e){
        Object subject = e.getSource();
        
        int subjectNumber = subjectButtons.indexOf((SubjectButton)subject);
        
        selectedSubject = subjectNumber;
        addSubjectSections(subjectButtons.get(subjectNumber).getSections());
        
    }
}
