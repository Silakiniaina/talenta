package model;

public class ReponseSimulationPossible {
    
    private int idQuestionSimulation;
    private int idReponseSimulationPossible;
    private String texteReponse;

    // CONSTRUCTOR
    public ReponseSimulationPossible(int idReponseSimulationPossible, int idQuestionSimulation, String txt){
        this.setIdQuestionSimulation(idQuestionSimulation);
        this.setIdReponseSimulationPossible(idReponseSimulationPossible);
        this.setTexteReponse(txt);
    }

    // GETTERS AND SETTERS
    public void setIdQuestionSimulation(int idQuestionSimulation) {
        this.idQuestionSimulation = idQuestionSimulation;
    }
    public void setIdReponseSimulationPossible(int idReponseSimulationPossible) {
        this.idReponseSimulationPossible = idReponseSimulationPossible;
    }
    public void setTexteReponse(String texteReponse) {
        this.texteReponse = texteReponse;
    }
    public int getIdQuestionSimulation() {
        return idQuestionSimulation;
    }
    public int getIdReponseSimulationPossible() {
        return idReponseSimulationPossible;
    }
    public String getTexteReponse() {
        return texteReponse;
    }

    
}
