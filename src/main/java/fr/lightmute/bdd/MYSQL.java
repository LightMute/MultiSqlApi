package fr.lightmute.bdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MYSQL  {
	private Connection connexion;
	private String urlbase,host,database,user,pass;

	public MYSQL(String urlbase, String host, String database, String user, String pass) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
	}

	public void connect(){
		if(!isConnect()){
			try {
				try {
					Class.forName ( "com.mysql.jdbc.Driver" );
				} catch (ClassNotFoundException e) {
					System.out.println("La classe com.mysql.jdbc.Driver n'est pas connue !");
				}
				connexion = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
				System.out.println("Base de données Teams connecté au plugin !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(){
		if(isConnect()){
			try {
				connexion.close();
				System.out.println("Base de données Teams déconnecté du plugin !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * INSERT
	 * UPDATE
	 * DELETE
	 * SELECT
	 *
	 * PREPARER ?,?
	 * REMPLACER LES ? PAR DES VALEURS
	 * EXECUTE
	 *
	 */

	public boolean isConnect(){
		return connexion != null;
	}

	public void createTeam(String ID_team, String prefixteam, String owner, Integer money, Date datecreation){
		try {
			PreparedStatement q = connexion.prepareStatement("INSERT INTO teams(ID_team,prefixteam,owner,money,datecreation) VALUES (?,?,?,?,?)");
			q.setString(1, ID_team);
			q.setString(2, prefixteam);
			q.setString(3, owner);
			q.setInt(4, money);
			q.setDate(5, datecreation);
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
