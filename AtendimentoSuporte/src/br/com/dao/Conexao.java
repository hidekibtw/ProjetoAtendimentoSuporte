package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Conexao {
	protected Connection con = null; // Classe para estabelecer uma conexão com o servidor do Banco de Dados
	protected PreparedStatement pst = null; // Classe para fazer execuções de SQL
	protected ResultSet rs = null;// Guarda o resultado da consulta select

	public void abrirConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			con = DriverManager.getConnection("jdbc:mysql://10.26.44.226:6020/suportedb?useTimeZone=true", "root",
					"123@senac");

		} catch (SQLException se) {
			se.printStackTrace();//Mostrar um erro SQL
			;
		} catch (Exception e) {
			e.printStackTrace(); //Mostrar erro inesperado
		}
	}

	public void fecharConexao() {
		try {
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}