package FileIO;
import java.io.*;


public class LanguageFile{
    private InputStreamReader unicodeReader = null;
    private File file = null;
    
    public LanguageFile (String fileName) throws IOException{ 
        file = new File(System.getProperty("user.dir"), fileName);
        unicodeReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
    }
    public String readLine() throws IOException{
        int c = unicodeReader.read();
        StringBuffer line = new StringBuffer();
        
        if(c != '\n' && c != -1){
            line.append(c);
            c = unicodeReader.read();
        }
        while(c != '\n' && c != -1){
            line.append("-");
            line.append(c);
            
            c = unicodeReader.read();
        }
        return line.toString();
    }
    public void close() throws IOException{
        unicodeReader.close();
    }
}
