package model;


public class Aluno {
    private int matricula;
    private String nome;
    private double coeficiente;

    public Aluno(int matricula, String nome, double coeficiente) {
        this.matricula = matricula;
        this.nome = nome;
        this.coeficiente = coeficiente;
    }

    // Getters
    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public double getCoeficiente() { return coeficiente; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCoeficiente(double coeficiente) { this.coeficiente = coeficiente; }

    @Override
    public String toString() {
        return matricula + "," + nome + "," + coeficiente;
    }

    public String mostrar() {
        return "Matrícula: " + matricula + ", Nome: " + nome + ", Coeficiente: " + coeficiente;
    }
}
