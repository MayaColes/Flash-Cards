package FileIO;
import java.io.*;
import java.util.HashMap;
import Utils.Utils;
import FlashCardsObjects.Language;

public class LanguageIO {
    private static final String languagesFolder = "Languages";
    private static String[] fileNames = null;
    private static final String split = "-61-";
    
    private static LanguageFile openLanguageFile(String fileName){
        LanguageFile f = null; 
        try{
            f = new LanguageFile(languagesFolder + "/" + fileName);
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        return f;
    }
    private static void closeLanguageFile(LanguageFile f){
        try{
            f.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    private static String langReadLine(LanguageFile f){
        try{
            return f.readLine();
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        return "";
    }
    public static void initLanguageFiles(){
        fileNames = new File(System.getProperty("user.dir"), languagesFolder).list();
    }
    public static Language readLanguageFromFile(int langCount){
        HashMap lang = new HashMap();
        if(langCount >= fileNames.length){
            return new Language();
        }
        
        LanguageFile f = openLanguageFile(fileNames[langCount]);
        
        String line = langReadLine(f);
        
        
        while(!line.isEmpty()){
            String key = line.split(split)[0];
            String result = line.split(split)[1];
            
            key = Utils.unicodeToString(key);
            result = Utils.unicodeToString(result);
            
            lang.put(key, result);
            line = langReadLine(f);
        }
        closeLanguageFile(f);
        
        return new Language(fileNames[langCount].replaceAll(".dat", ""), lang);
    }
}
