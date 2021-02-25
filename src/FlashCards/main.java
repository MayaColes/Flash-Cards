package FlashCards;
import Window.FlashCardsFrame;
import FileIO.QuestionIO;
import FlashCardsObjects.Question;
import FlashCardsObjects.Field;
import java.util.ArrayList;


public class main {

    public static void main(String[] args) {
        //FlashCardsFrame f = new FlashCardsFrame();
        //ArrayList<Field> fields = new ArrayList();
        //fields.add(new Field("question1", "answer1"));
        //fields.add(new Field("question2", "answer2"));
        //QuestionIO.writeFileFromQuestion(new Question(fields), System.getProperty("user.dir")+ "/TestSubject/Test", "TestQuestion.dat");
        //System.out.println(QuestionIO.readQuestionFromFile(System.getProperty("user.dir")+ "/TestSubject/Test", "TestQuestion.dat").getFieldsLength());
        Initialize.start();
    }
    
}
