package FileIO;
import FlashCardsObjects.*;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class QuestionIO {
    private static final String basePath = System.getProperty("user.dir") + "/Notes";
    private static String[] fileNames = null;
    
    public static QuestionFile openQuestionFile(File path){
        QuestionFile f = null;
        
        try{
            f = new QuestionFile(path);
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        return f;
    }
    public static void closeQuestionFile(QuestionFile f){
        try{
            f.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    } 
    public static Field readField(QuestionFile f) {
        try{
            return f.readField();
        }
        catch(IOException e){
            System.out.println(e);
        }
        return new Field();
    }
    public static void writeQuestionToFile(QuestionFile qFile, Question q){
        try{
            qFile.writeFileFromQuestion(q);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static Question readQuestion(File questionFile){
        ArrayList<Field> fields = new ArrayList();
        QuestionFile qFile = openQuestionFile(questionFile);
        
        Field f = readField(qFile);
        
        while(!f.getAnswer().isEmpty() && !f.getQuestion().isEmpty()){
            fields.add(f);
           
            f = readField(qFile);
        }
        closeQuestionFile(qFile);
        
        return new Question(fields);
    }
    public static void writeQuestion(Question q, String subject, String section, String name){
        File path = new File(basePath + "/" + subject + "/" + section + "/" + name);
        
        QuestionFile qFile = openQuestionFile(path);
        
        writeQuestionToFile(qFile, q);
        
        closeQuestionFile(qFile);
    }
    public static void createNewSubject(String name){
        File path = new File(basePath + "/" + name);
        path.mkdir();
    }
    public static void createNewSection(String subjectName, String name){
        File path = new File(basePath + "/" + subjectName + "/" + name);
        path.mkdir();
    }
    public static void initializeQuestionIO(){
        (new File(basePath)).mkdir();
        fileNames = new File(basePath).list();
    }
    public static Subject readSubject(int index){
        if(index >= fileNames.length){
            return null;
        }
        String subjectName = fileNames[index];
        File subjectFile = new File(basePath + "/" + subjectName);
        
        String[] sectionNames = subjectFile.list();
        ArrayList<Section> sections = new ArrayList();
        
        for(int i = 0; i < sectionNames.length; i++){
            File sectionFile = new File(subjectFile, sectionNames[i]);
            sections.add(readSection(i, sectionFile));
        }
        
        return new Subject(subjectName, sections);
    }
    public static Section readSection(int index, File sectionFile){ 
        String sectionName = sectionFile.getName();
        ArrayList<Question> questions = new ArrayList();
        
        for(int i = 0; i < sectionFile.list().length; i++){
            String questionName = sectionFile.list()[i];
            File questFile = new File(sectionFile, questionName);
            questions.add(readQuestion(questFile));
        }
        return new Section(sectionName, questions);
    }
}
