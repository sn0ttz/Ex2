package com.ti2cc;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cod = 0;
		String log = "";
		String sen = "";
		char sex = ' ';
		int key = 0;
		DAO dao = new DAO();

		dao.conectar();

		Usuario[] usuarios = dao.getUsuariosMasculinos();
		Usuario usuario = new Usuario(cod, log, sen, sex);

		while (key != 5) {

			System.out.println("1- Listar todos os usuários");
			System.out.println("2- Inserir um usuário");
			System.out.println("3- Atualizar um usuário");
			System.out.println("4- Excluir um usuário");
			System.out.println("5- Sair");
			key = in.nextInt();
			switch (key) {
				case 1:
					usuarios = dao.getUsuarios();
					System.out.println("==== Mostrar usuários === ");
					for (int i = 0; i < usuarios.length; i++) {
						System.out.println(usuarios[i].toString());
					}
					break;
				case 2:
					System.out.println("Digite o código, login, senha e sexo do usuário: ");
					cod = in.nextInt();
					usuario.setCodigo(cod);
					in.nextLine();
					log = in.nextLine();
					usuario.setLogin(log);
					sen = in.nextLine();
					usuario.setSenha(sen);
					in.nextLine();
					sex = in.next().charAt(0);

					if (dao.inserirUsuario(usuario) == true) {
						System.out.println("Inserção com sucesso -> " + usuario.toString());
					}
					break;
				case 3:
					System.out.println("Informe o codigo do usuario a ser atualizado: ");
					cod = in.nextInt();
					in.nextLine();
					System.out.println("Digite o login do usuário: ");
					log = in.nextLine();
					System.out.println("Digite a senha do usuário: ");
					sen = in.nextLine();
					in.nextLine();
					System.out.println("Digite o sexo do usuário");
					sex = in.next().charAt(0);
					Usuario aux = new Usuario(cod, log, sen, sex);
					dao.atualizarUsuario(aux);
					break;
				case 4:
					System.out.println("Informe o codigo do usuario a ser excluido: ");
					cod = in.nextInt();
					dao.excluirUsuario(cod);
					break;
				case 5:
					key = 5;
					break;
				default:
					break;
			}
		}

		in.close();

		dao.close();
	}
}