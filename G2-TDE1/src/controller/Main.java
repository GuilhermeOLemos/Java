package controller;

import view.AutomovelView;

public class Main {

	public static void main(String[] args) {
		AutomovelCtrl ctrl = new AutomovelCtrl();
		AutomovelView view = new AutomovelView(ctrl);
		view.exibirMenu();
	}

}
