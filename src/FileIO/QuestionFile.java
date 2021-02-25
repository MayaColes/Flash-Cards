package FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import FlashCardsObjects.Field;
import FlashCardsObjects.Question;
import Utils.Utils;

public class QuestionFile {
    private final String questionStart = "Question: ";
    private final String answerStart = "Answer: ";
    private InputStreamReader unicodeReader = null;
    private File file = null;
    private BufferedWriter unicodeWriter = null;
    
    public QuestionFile(File path) throws IOException{
        file = path;
        if(!file.exists()){
            file.createNewFile();
        }

        unicodeReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
    }
    public Field readField() throws IOException{
        String question = readLine();
        
        if (question.equals("EOF")){
            return (new Field());
        }
        
        question = Utils.unicodeToString(question);
        
        
        while (!question.startsWith(questionStart)){
            question = readLine();
            if (question.equals("EOF")){
                return (new Field());
            }

            question = Utils.unicodeToString(question);
            
        }
        question = question.replaceFirst(questionStart, "");
        
        String answer = readLine();
        if (answer.equals("EOF")){
            return (new Field());
        }
        answer = Utils.unicodeToString(answer);
        
        while (!answer.startsWith(answerStart)){
            answer = readLine();
            if (answer.equals("EOF")){
                return (new Field());
            }
            answer = Utils.unicodeToString(answer);
        }
        answer = answer.replaceFirst(answerStart, "");
        return new Field(question, answer);
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
        if(c == -1){
            return "EOF";
        }
        return line.toString();
    }
    public void writeFileFromQuestion(Question q) throws IOException{
        unicodeWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        for(int i = 0; i < q.getFieldsLength(); i++){
            Field f = q.getOneField(i);
            unicodeWriter.write("{");
            unicodeWriter.newLine();
            unicodeWriter.write(questionStart + f.getQuestion());
            unicodeWriter.newLine();
            unicodeWriter.write(answerStart + f.getAnswer());
            unicodeWriter.newLine();
            unicodeWriter.write("}");
            unicodeWriter.newLine();
            unicodeWriter.newLine();
        }
        
    }
    public void close() throws IOException{
        if(unicodeWriter != null){
            unicodeWriter.close();
        }
        unicodeReader.close();
    }
    public File getFile(){
        return file;
    }
}
