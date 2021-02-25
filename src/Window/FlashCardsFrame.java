package Window;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import FlashCardsObjects.Subject;
import FileIO.QuestionIO;
import java.util.ArrayList;
import FlashCardsObjects.*;
import Utils.Utils;

public class FlashCardsFrame {
    private JFrame frame = null;
    private QuestionWindow qw = null;
    private SelectionWindow sw = null;
    private JScrollPane questionScroll = null;
    private JScrollPane selectionScroll = null;
    private Options options = null;
    private final Dimension frameSize = new Dimension(600, 400);
    private ArrayList languages = null;
    
    public FlashCardsFrame(ArrayList<Subject> subjects, ArrayList<Language> langs){
        qw = new QuestionWindow();
        sw = new SelectionWindow(subjects);
        options = new Options();
        questionScroll = new JScrollPane(qw);
        selectionScroll = new JScrollPane(sw);
        
        languages = langs;
        
        qw.setAutoscrolls(true);
        questionScroll.setPreferredSize(frameSize);
        
        sw.setAutoscrolls(true);
        selectionScroll.setPreferredSize(frameSize);
        
        frame = new JFrame();
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(frameSize);
        
        frame.add(options);
        frame.setVisible(true);
        
        subjectSelect();
    }
    public void subjectSelect(){
        subjectSelectSettings();
        
        while(true){
            frame.pack();
            if(options.getBackPressed()){
                options.goneBack();
                break;
            }
            else if(options.getNextPressed()){
                options.setNextPressed(false);
                questions(sw.getSelectedSections());
                subjectSelectSettings();
            }
            else if(options.getNewSubjectPressed()){
                String name = NameWindow.subjectNameDialog(frame);
                
                if(name != null){
                    QuestionIO.createNewSubject(name);
                    sw.addNewSubject(new Subject(name, new ArrayList()));
                    sw.repaint();
                }
                
                options.setNewSubjectPressed(false);
            }
            else if(options.getNewSectionPressed()){
                String name = NameWindow.sectionNameDialog(frame, sw.getSelectedSubject());
                if(name != null){
                    if(!name.isEmpty()){
                        QuestionIO.createNewSection(sw.getSelectedSubject(), name);
                        sw.addSection(new Section(name, new ArrayList()), sw.getSelectedSubject());
                        sw.repaint();
                    }
                }
                
                options.setNewSectionPressed(false);
            }
            else if(options.getNewCardPressed()){
                if(sw.getSelectedSections().size() != 1){
                    NameWindow.selectSectionWarning(frame);
                }
                else{
                    newQuestion(sw.getSelectedSubject(), sw.getSelectedSections().get(0).getName());
                    subjectSelectSettings();
                }
                options.setNewCardPressed(false);
            }
            sleep(100);
        }
        System.out.println("HERE");
    }
    public void questions(ArrayList sections){
        frame.remove(selectionScroll);
        frame.repaint();
        
        options.questionSettings();
        qw.resetWindow();
        qw.createFieldAreas(Utils.pickRandomQuestion(sections));
        
        frame.add(questionScroll);
        frame.pack();

        while(true){
            frame.pack();
            if(options.getBackPressed()){
                qw.resetWindow();
                options.goneBack();
                break;
            }
            else if(options.getNextPressed()){
                qw.resetWindow();
                qw.createFieldAreas(Utils.pickRandomQuestion(sections));
                options.setNextPressed(false);
            }
            else if(options.getChooseLanguagePressed()){
                Language l = NameWindow.languageSelection(frame, languages);
                qw.setFieldsLanguage(l);
                options.setChooseLanguagePressed(false);
            }
            sleep(100);
        }
    }
    public void newQuestion(String subjectName, String sectionName){
        frame.remove(selectionScroll);
        frame.repaint();
        
        options.newQuestionSettings();
        
        frame.add(questionScroll);
        
        qw.createNewQuestion();
        
        while(true){
            frame.pack();
            
            if (options.getBackPressed()){
                options.goneBack();
                qw.resetWindow();
                break;
            }
            else if (options.getNextPressed()){
                String questionName = NameWindow.questionNameDialog(frame);
                options.setNextPressed(false);
                
                Question newQuestion = new Question(qw.getFields());
                
                QuestionIO.writeQuestion(newQuestion, subjectName, sectionName, questionName);
                options.setNextPressed(false);
                break;
            }
            else if(options.getChooseLanguagePressed()){
                Language l = NameWindow.languageSelection(frame, languages);
                qw.setFieldsLanguage(l);
                options.setChooseLanguagePressed(false);
            }
            sleep(100);
        }
        qw.resetWindow();
    }
    public void subjectSelectSettings(){
        frame.remove(questionScroll);
        
        options.selectionSettings();
        
        frame.add(selectionScroll);
        frame.repaint();
    }
    private void sleep(int time){
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
    }
}
