package Window;
import javax.swing.JRadioButton;
import FlashCardsObjects.Subject;
import FlashCardsObjects.Section;
import java.awt.Dimension;

public class SubjectButton extends JRadioButton{
    private Subject subject = null;
    
    public SubjectButton(){
        super();
        subject = new Subject();
    }
    public SubjectButton(Subject s){
        super(s.getName());
        super.setPreferredSize(new Dimension(200, 50));
        subject = s;
    }
    public String getName(){
        return subject.getName();
    }
    public Section[] getSections(){
        Section[] a = new Section[subject.getSections().size()];
        subject.getSections().toArray(a);
        return a;
    }
    public void addSection(Section s){
        subject.addSection(s);
    }
    public Section getOneSection(int index){
        return subject.getOneSection(index);
    }
}
