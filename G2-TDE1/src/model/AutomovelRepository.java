package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AutomovelRepository {
	private List<Automovel> automoveis = new ArrayList<>();
	private static final String FILE_NAME = "automoveis.txt";

	public AutomovelRepository() { carregarArquivo(); }

	public boolean inserir(Automovel auto) {
	    if (buscarPorPlaca(auto.getPlaca()) != null) {
	        return false;
	    }
	    automoveis.add(auto);
	    return true;
	}

	public boolean remover(String placa) {
	    Automovel auto = buscarPorPlaca(placa);
	    if (auto != null) {
	        automoveis.remove(auto);
	        return true;
	    }
	    return false;
	}

	public Automovel buscarPorPlaca(String placa) {
	    for (Automovel auto : automoveis) {
	        if (auto.getPlaca().equalsIgnoreCase(placa)) {
	            return auto;
	        }
	    }
	    return null;
	}

	public boolean alterar(String placa, Automovel novoAuto) {
	    Automovel auto = buscarPorPlaca(placa);
	    if (auto != null) {
	        auto.setModelo(novoAuto.getModelo());
	        auto.setMarca(novoAuto.getMarca());
	        auto.setAno(novoAuto.getAno());
	        auto.setValor(novoAuto.getValor());
	        return true;
	    }
	    return false;
	}

	public List<Automovel> listarOrdenado(Comparator<Automovel> comparator) {
	    List<Automovel> copia = new ArrayList<>(automoveis);
	    copia.sort(comparator);
	    return copia;
	}

	public void salvarArquivo() {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
	        for (Automovel auto : automoveis) {
	            bw.write(auto.toString());
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        System.out.println("Erro ao salvar: " + e.getMessage());
	    }
	}

	private void carregarArquivo() {
	    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            String[] dados = linha.split(",");
	            if (dados.length == 5) {
	                Automovel auto = new Automovel(
	                    dados[0],
	                    dados[1],
	                    dados[2],
	                    Integer.parseInt(dados[3]),
	                    Double.parseDouble(dados[4])
	                );
	                automoveis.add(auto);
	            }
	        }
	    } catch (FileNotFoundException e) {
	    } catch (IOException e) {
	        System.out.println("Erro ao carregar: " + e.getMessage());
	    }
	}

}
