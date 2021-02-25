package FlashCardsObjects;
import java.util.ArrayList;
import java.util.HashMap;

public class Subject {
    private ArrayList<Section> sections;
    private String name = "";
    private HashMap language;
    
    public Subject(){
        sections = new ArrayList();
        name = "";
    }
    public Subject(String n, ArrayList<Section> s){
        sections = s;
        name = n;
    }
    public void addSection(Section s){
        sections.add(s);
    }
    public ArrayList<Section> getSections(){
        return sections;
    }
    public Section getOneSection(int i){
        return sections.get(i);
    }
    public String getName(){
        return name;
    }
    public int getSectionsLength(){
        return sections.size();
    }
}
