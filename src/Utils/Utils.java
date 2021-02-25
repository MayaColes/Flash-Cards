package Utils;
import FlashCardsObjects.Question;
import FlashCardsObjects.Section;
import java.util.ArrayList;

public class Utils {
    public static String unicodeToString(String str){
        String[] unicode = str.split("-");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < unicode.length; i++){
            if(!unicode[i].isEmpty()){
                result.append(Character.toChars(Integer.parseInt(unicode[i])));
            }
        }
        
        return result.toString();
    }
    public static Question pickRandomQuestion(ArrayList<Section> sections){
        int totalQuestions = 0;
        for(int i = 0; i < sections.size(); i++){
            totalQuestions += sections.get(i).getNumberQuestions();
        }
        
        int questionIndex = (int)(Math.random() * totalQuestions);
        
        int currentIndex = 0;
        
        for(int i = 0; i < sections.size(); i++){
            for(int j = 0; j < sections.get(i).getNumberQuestions(); j++){
                if(currentIndex == questionIndex){
                    
                    return sections.get(i).getOneQuestion(j);
                }
                currentIndex++;
            }
        }
        return new Question();
    }
}
