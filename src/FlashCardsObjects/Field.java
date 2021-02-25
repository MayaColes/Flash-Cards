package FlashCardsObjects;

public class Field {
    private String question;
    private String answer;
    private int answerType = 0;
    private boolean exactAnswer = false;
    private int questionLength = 0;
    
    public Field(){
        question = "";
        answer = "";
        answerType = 0;
        exactAnswer = false;
        questionLength = 0;
    }
    public Field(String q, String a){
        question = q;
        answer = a;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
}
