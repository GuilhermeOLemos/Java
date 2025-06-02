package view;

import java.util.List;
import java.util.Scanner;

import controller.AutomovelCtrl;
import model.Automovel;

public class AutomovelView {
	private AutomovelCtrl ctrl;
	private Scanner sc = new Scanner(System.in);
	
	public AutomovelView(AutomovelCtrl ctrl) { this.ctrl = ctrl; }

	public void exibirMenu() {
	    int opcao;
	    do {
	        System.out.println("1 - Incluir automóvel");  
	        System.out.println("2 - Remover automóvel");  
	        System.out.println("3 - Alterar dados de automóvel");  
	        System.out.println("4 - Consultar automóvel por placa");  
	        System.out.println("5 - Listar automóveis (ordenado)");  
	        System.out.println("6 - Salvar e sair");  
	        System.out.print("Escolha: ");  
	        opcao = sc.nextInt();  
	        sc.nextLine();

	        switch (opcao) {
	            case 1: incluir(); break;
	            case 2: remover(); break;
	            case 3: alterar(); break;
	            case 4: consultar(); break;
	            case 5: listar(); break;
	            case 6: ctrl.salvarDados(); System.out.println("Dados salvos. Saindo..."); break;
	            default: System.out.println("Opção inválida.");
	        }
	    } while (opcao != 6);
	}

	private void incluir() {
	    System.out.print("Placa: ");
	    String placa = sc.nextLine();
	    System.out.print("Modelo: ");
	    String modelo = sc.nextLine();
	    System.out.print("Marca: ");
	    String marca = sc.nextLine();
	    System.out.print("Ano: ");
	    int ano = sc.nextInt();
	    System.out.print("Valor: ");
	    double valor = sc.nextDouble();
	    sc.nextLine();

	    Automovel auto = new Automovel(placa, modelo, marca, ano, valor);
	    if (ctrl.incluirAutomovel(auto)) {
	        System.out.println("Automóvel incluído com sucesso.");
	    } else {
	        System.out.println("Placa já existente.");
	    }
	}

	private void remover() {
	    System.out.print("Placa a remover: ");
	    String placa = sc.nextLine();
	    if (ctrl.removerAutomovel(placa)) {
	        System.out.println("Automóvel removido.");
	    } else {
	        System.out.println("Placa não encontrada.");
	    }
	}

	private void alterar() {
	    System.out.print("Placa a alterar: ");
	    String placa = sc.nextLine();
	    Automovel existente = ctrl.consultarAutomovel(placa);
	    if (existente != null) {
	        System.out.print("Novo modelo: ");
	        String modelo = sc.nextLine();
	        System.out.print("Nova marca: ");
	        String marca = sc.nextLine();
	        System.out.print("Novo ano: ");
	        int ano = sc.nextInt();
	        System.out.print("Novo valor: ");
	        double valor = sc.nextDouble();
	        sc.nextLine();

	        Automovel novoAuto = new Automovel(placa, modelo, marca, ano, valor);
	        ctrl.alterarAutomovel(placa, novoAuto);
	        System.out.println("Automóvel alterado.");
	    } else {
	        System.out.println("Placa não encontrada.");
	    }
	}

	private void consultar() {
	    System.out.print("Placa a consultar: ");
	    String placa = sc.nextLine();
	    Automovel auto = ctrl.consultarAutomovel(placa);
	    if (auto != null) {
	        System.out.println(auto);
	    } else {
	        System.out.println("Automóvel não encontrado.");
	    }
	}

	private void listar() {
	    System.out.print("Ordenar por (placa/modelo/marca): ");
	    String criterio = sc.nextLine();
	    List<Automovel> lista = ctrl.listarOrdenadoPor(criterio);
	    if (lista != null) {
	        for (Automovel auto : lista) {
	            System.out.println(auto);
	        }
	    } else {
	        System.out.println("Criterio inválido.");
	    }
	}

}
