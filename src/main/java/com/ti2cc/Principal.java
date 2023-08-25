package com.ti2cc;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// inicializando as variaveis

		// codigo
		int cod = 0;

		// login
		String log = "";

		// senha
		String sen = "";

		// sexo
		char sex = ' ';

		// criando um objeto DAO
		DAO dao = new DAO();

		// conexão com o bd
		dao.conectar();

		// criando um vetor de usuarios
		Usuario[] usuarios = dao.getUsuarios();

		// criando um usuario padrão
		Usuario usuario = new Usuario(cod, log, sen, sex);

		// inicializando a condição do while
		int key = 0;

		while (key != 5) {
			// menu
			System.out.println("1- Listar todos os usuários");
			System.out.println("2- Inserir um usuário");
			System.out.println("3- Atualizar um usuário");
			System.out.println("4- Excluir um usuário");
			System.out.println("5- Sair");

			// scan da key
			key = in.nextInt();

			switch (key) {

				case 1:
					// pegando todos os usuarios
					usuarios = dao.getUsuarios();
					// printando todos os usuarios
					System.out.println("==== Mostrar usuários === ");
					for (int i = 0; i < usuarios.length; i++) {
						System.out.println(usuarios[i].toString());
					}
					break;

				case 2:
					System.out.println("Digite o código, login, senha e sexo do usuário: ");
					// setando o codigo do usuario
					cod = in.nextInt();
					usuario.setCodigo(cod);

					// limpeza de buffer
					in.nextLine();

					// setando o login do usuario
					log = in.nextLine();
					usuario.setLogin(log);

					// setando a senha do usuario
					sen = in.nextLine();
					usuario.setSenha(sen);

					// limpeza de buffer
					in.nextLine();

					// setando o sexo do usuario
					sex = in.next().charAt(0);
					usuario.setSexo(sex);

					if (dao.inserirUsuario(usuario) == true) {
						System.out.println("Inserção com sucesso -> " + usuario.toString());
					}
					break;

				case 3:
					System.out.println("Informe o codigo do usuario a ser atualizado: ");

					cod = in.nextInt();
					// limpeza de buffer
					in.nextLine();
					System.out.println("Digite o login do usuário: ");

					log = in.nextLine();
					System.out.println("Digite a senha do usuário: ");

					sen = in.nextLine();
					// limpeza de buffer
					in.nextLine();
					System.out.println("Digite o sexo do usuário");

					sex = in.next().charAt(0);
					Usuario aux = new Usuario(cod, log, sen, sex);
					// substituindo as infos do user1 pelas infos do aux
					dao.atualizarUsuario(aux);
					break;

				case 4:
					System.out.println("Informe o codigo do usuario a ser excluido: ");
					cod = in.nextInt();
					// excluindo o user pela primary key (neste caso está na variável código)
					dao.excluirUsuario(cod);
					break;

				case 5:
					// transformando key em 5 para sair do while
					key = 5;
					break;

				default:
					break;
			}
		}

		// fechando o scanner e o dao
		in.close();
		dao.close();
	}
}