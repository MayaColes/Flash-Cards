package FlashCardsObjects;
import java.util.ArrayList;

public class Question {
    private ArrayList<Field> fields = null;
    
    public Question(){
        fields = new ArrayList();
    }
    public Question(ArrayList f){
        fields = f;
    }
    public Field getOneField(int index){
        return fields.get(index);
    }
    public int getFieldsLength(){
        return fields.size();
    }
}
