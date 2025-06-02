package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Aluno;


public class Main {
    static ArrayList<Aluno> alunos = new ArrayList<>();
    static final String NOME_ARQUIVO = "alunos.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDados();

        int opcao;
        do {
            System.out.println("\n1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Consultar por matrícula");
            System.out.println("4 - Salvar e sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunos();
                case 3 -> consultarAluno();
                case 4 -> salvarDados();
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    static void cadastrarAluno() {
        System.out.print("Matrícula: ");
        int matricula = sc.nextInt();
        sc.nextLine(); // limpar buffer
        if (buscarPorMatricula(matricula) != null) {
            System.out.println("Matrícula já cadastrada.");
            return;
        }
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Coeficiente de rendimento: ");
        double coeficiente = sc.nextDouble();
        sc.nextLine();

        alunos.add(new Aluno(matricula, nome, coeficiente));
        System.out.println("Aluno cadastrado.");
    }

    static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (Aluno a : alunos) {
            System.out.println(a.mostrar());
        }
    }

    static void consultarAluno() {
        System.out.print("Matrícula: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            System.out.println(aluno.mostrar());
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    static Aluno buscarPorMatricula(int matricula) {
        return alunos.stream()
                     .filter(a -> a.getMatricula() == matricula)
                     .findFirst()
                     .orElse(null);
    }

    static void carregarDados() {
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    int matricula = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    double coef = Double.parseDouble(dados[2]);
                    alunos.add(new Aluno(matricula, nome, coef));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado. Iniciando com lista vazia.");
        }
    }

    static void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Aluno a : alunos) {
                bw.write(a.toString());
                bw.newLine();
            }
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
