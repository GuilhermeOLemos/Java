package controller;

import model.Automovel;
import model.AutomovelRepository;

import java.util.Comparator;
import java.util.List;

public class AutomovelCtrl {
	private AutomovelRepository repo = new AutomovelRepository();
	
	public boolean incluirAutomovel(Automovel auto) { return repo.inserir(auto); }

	public boolean removerAutomovel(String placa) { return repo.remover(placa); }

	public Automovel consultarAutomovel(String placa) { return repo.buscarPorPlaca(placa); }

	public boolean alterarAutomovel(String placa, Automovel novoAuto) {
	    return repo.alterar(placa, novoAuto);
	}

	public List<Automovel> listarOrdenadoPor(String criterio) {
	    Comparator<Automovel> comparator;
	    switch (criterio.toLowerCase()) {
	        case "placa":
	            comparator = Comparator.comparing(Automovel::getPlaca);
	            break;
	        case "modelo":
	            comparator = Comparator.comparing(Automovel::getModelo);
	            break;
	        case "marca":
	            comparator = Comparator.comparing(Automovel::getMarca);
	            break;
	        default:
	            return null;
	    }
	    return repo.listarOrdenado(comparator);
	}

	public void salvarDados() { repo.salvarArquivo(); }

}
