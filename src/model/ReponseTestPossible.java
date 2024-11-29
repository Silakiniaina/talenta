package model;

public class ReponseTestPossible {
    
    private int idQuestionTest;
    private int idReponseTestPossible;
    private String texteReponse;

    // CONSTRUCTOR
    public ReponseTestPossible(int idReponseTestPossible, int idQuestionTest, String txt){
        this.setIdQuestionTest(idQuestionTest);
        this.setIdReponseTestPossible(idReponseTestPossible);
        this.setTexteReponse(txt);
    }

    // GETTERS AND SETTERS
    public void setIdQuestionTest(int idQuestionTest) {
        this.idQuestionTest = idQuestionTest;
    }
    public void setIdReponseTestPossible(int idReponseTestPossible) {
        this.idReponseTestPossible = idReponseTestPossible;
    }
    public void setTexteReponse(String texteReponse) {
        this.texteReponse = texteReponse;
    }
    public int getIdQuestionTest() {
        return idQuestionTest;
    }
    public int getIdReponseTestPossible() {
        return idReponseTestPossible;
    }
    public String getTexteReponse() {
        return texteReponse;
    }

    
}
