package fr.naormute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApiSGBD {
	public Statement stmt = null;
	public ResultSet resultatSelect = null;
	private String iTypeBase ="";
	private String iSqliteCheminNom="";
	private Connection conn;

	
	// Setter
	
	public void setTypeBase(String TypeBase) {

		switch (TypeBase) {
		case "Sqlite":
			this.iTypeBase = "Sqlite";
			break;
		case "MySql":
			this.iTypeBase = "0";
			break;
		case "Access":
			this.iTypeBase = "0";
			break;
		case "SqlServer":
			this.iTypeBase = "0";
			break;
		case "Oracle":
			this.iTypeBase = "0";
			break;
		default:
			this.iTypeBase = "0";
			break;
		}		
		
	}

	public void setSqliteCheminNom(String Chemin) {

		this.iSqliteCheminNom = Chemin;
			
	}



	// Getter
	
	public String getTypeBase() {
		return this.iTypeBase;
	}

	public String getSqliteCheminNom() {
		return this.iSqliteCheminNom;
	} 
	

	
	// Procédures publiques
	
	
	public void OuvrirConnectSqlite() {
		
 		try {
 			Class.forName("org.sqlite.JDBC");
 			this.conn = DriverManager
 					.getConnection("jdbc:sqlite:" + iSqliteCheminNom);
 			this.conn.setAutoCommit(false);
 		} catch (Exception e) {
 			System.err.println(e.getClass().getName() + ": " + e.getMessage());
 			System.exit(0);
 		}
		
	}
	

	public void FermerConnectSqlite() {
		
        try{
        	this.conn.close();
        }
        catch( Exception e ){
            e.printStackTrace();
        }
		
	}
	

	public void LancerRequeteSelectSqlite(String Requete) {
		
		try {
			stmt = this.conn.createStatement();
			resultatSelect = stmt.executeQuery(Requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	// Procédures privées
	

	
	
	
	
	
//				GRENIER NE PAS EN TENIR COMPTE	
	
/*	
	public void ReqSelecxxxxxt(String RequeteSelect)  {

		this.iRequete=RequeteSelect;

		switch(iTypeBase)
		{
		case "Sqlite":
			this.ConnectSqlite();
			break;
		case "MySql":
			break;
		case "Access":
			break;
		case "SqlServer":
			break;
		case "Oracle":
			break;
		default:
			iTypeBase = "0";
			break;
		}			

	}	

*/
	
	/*public void setTypeBase0(String TypeBase) {

		if(TypeBase == "SQLLITE")  {
			this.iTypeBase = TypeBase;
		}else if (TypeBase == "MYSQL")  {
			this.iTypeBase = TypeBase;
		}else{	
			this.iTypeBase = null;
			System.out.println("ATTENTION ! Le type de la base doit être Sqlite et bientôt aussi MySql, MsAcces, SqlServeur " );
		}			
	}*/
}
