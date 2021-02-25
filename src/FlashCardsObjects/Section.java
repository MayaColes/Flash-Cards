package FlashCardsObjects;
import java.util.ArrayList;

public class Section {
    private String name = "";
    private ArrayList<Question> question;
    
    public Section(String n, ArrayList<Question> f){
        name = n;
        question = f;
    }
    public String getName(){
        return name;
    }
    public Question getOneQuestion(int index){
        return question.get(index);
    }
    public int getNumberQuestions(){
        return question.size();
    }
}   
