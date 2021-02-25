package FlashCardsObjects;

import java.util.HashMap;

public class Language {
    private String name = "";
    private HashMap associations;
    
    public Language(){
        name = "";
        associations = new HashMap();
    }
    public Language(String n, HashMap a){
        name = n;
        associations = a;
    }
    public String getName(){
        return name;
    }
    public HashMap getAssociation(){
        return associations;
    }
    public boolean inAssociation(String s){
        return associations.containsKey(s);
    }
    public String association(String s){
        return associations.get(s).toString();
    }
    public boolean inResults(String s){
        return associations.containsValue(s);
    }
}
