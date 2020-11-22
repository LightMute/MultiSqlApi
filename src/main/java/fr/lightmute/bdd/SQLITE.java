package fr.lightmute.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.lightmute.MultiSqlApi.DEBUG;

public class SQLITE {

	private static String SBASE = null;
	private Connection conn;
	private Statement stmt;
	DEBUG debug = new DEBUG();

	public SQLITE(String sBase) {

		super();
		this.conn = null;
		this.stmt = null;
		SBASE = sBase;
	}

	public void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:" + SQLITE.SBASE);
			this.conn.setAutoCommit(false);
			System.out.println("La base de données s'est connectée avec succés");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("SQLITE est connectée");
	}

	public void close(){
		try{
			this.conn.close();
			System.out.println("SQLITE est déconnectée");
		}
		catch( Exception e ){
			e.printStackTrace();
		}
	}

	public Object [] [] req01(){
		String Requ01 = "SELECT * FROM INTERVENANTS";

		Object [] [] dataPrim = null;

		int Compteur = 0;

		try {
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(Requ01);

			dataPrim = (Object[][]) new Object[getTableauSize()][5];
			
			while(rs.next()){

				dataPrim [Compteur] [0] = rs.getInt("ID_Intervenant");
				dataPrim [Compteur] [1] = rs.getString("NomI");
				dataPrim [Compteur] [2] = rs.getString("PrenomI");
				dataPrim [Compteur] [3] = rs.getInt("VisibleI");
				
				/*debug.DecoLigne();
				debug.DEBUGMessage("ID = " + rs.getInt("ID_Intervenant"));
				debug.DEBUGMessage("NAME = " + rs.getString("NomI"));
				debug.DEBUGMessage("AGE = " + rs.getString("PrenomI"));
				debug.DEBUGMessage("VISIBLE = " + rs.getInt("VisibleI"));
				debug.SautdeLigne();
				debug.DEBUGMessage("Ligne totales = " + Compteur);
				debug.DecoLigne();
				debug.SautdeLigne();*/

				Compteur++;

			}

			rs.close();
			stmt.close();

			return dataPrim;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return dataPrim;
	}

	public int getTableauSize(){
		try {
			stmt = this.conn.createStatement();
			String Requ01 = "SELECT COUNT(ID_Intervenant) AS total FROM INTERVENANTS";
			ResultSet rs = stmt.executeQuery(Requ01);
			int teamssize = 0;
			while(rs.next()){
				teamssize = rs.getInt("total");
			}
			rs.close();
			stmt.close();
			return teamssize;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}

