package FlashCards;
import FileIO.LanguageIO;
import FileIO.QuestionIO;
import java.util.ArrayList;
import java.util.HashMap;
import FlashCardsObjects.Subject;
import FlashCardsObjects.Language;
import Window.FlashCardsFrame;

public class Initialize {
    public static ArrayList<Language> initializeLanguages(){
        LanguageIO.initLanguageFiles();
        
        ArrayList<Language> languageSubstitutions = new ArrayList();
        int langCount = 0;
        Language lang = LanguageIO.readLanguageFromFile(langCount);

        
        while(!(lang.getName().isEmpty())){
            languageSubstitutions.add(lang);
            langCount++;
            lang = LanguageIO.readLanguageFromFile(langCount);
        }
        
        return languageSubstitutions;
    }
    public static ArrayList<Subject> initializeSubjects(){
        QuestionIO.initializeQuestionIO();
        ArrayList<Subject> subjects = new ArrayList();
        
        int subjectCount = 0;
        
        Subject newSubject = QuestionIO.readSubject(subjectCount);
        
        while(newSubject != null){
            subjects.add(newSubject);
            subjectCount++;
            newSubject = QuestionIO.readSubject(subjectCount);
        }
        
        return subjects;
    }
    public static void start(){
        ArrayList<Language> languages = initializeLanguages();
        ArrayList<Subject> subjects = initializeSubjects();
        
        FlashCardsFrame frame = new FlashCardsFrame(subjects, languages);
    }
}
