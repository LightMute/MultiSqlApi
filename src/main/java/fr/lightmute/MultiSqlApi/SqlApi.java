package fr.lightmute.MultiSqlApi;

import fr.lightmute.bdd.MYSQL;
import fr.lightmute.bdd.SQLITE;

public class SqlApi {

	private String Type;

	private SQLITE sqlite;
	private MYSQL mysql;

	private String cheminSqlite;
	private String database;

	private String urlbase;
	private String host;
	private String user;
	private String pass;

	public void setSqlType(BaseSQL BaseSQL) {
		this.Type = BaseSQL.toString();
	}

	public String getType() {
		return this.Type;
	}	

	public void connect(){
		if(this.Type.equals(SQLITE.class.getSimpleName())) {
			if(ischeminset()) {
				SQLITE sql = new SQLITE(cheminSqlite);
				sqlite = sql;
				sql.connect();
			}
		}else if(this.Type.equals(MYSQL.class.getSimpleName())) {
			MYSQL sql = new MYSQL(urlbase, host, database, user, pass);
			mysql = sql;
			sql.connect();
		}
	}

	public void close() {
		if(this.Type.equals(SQLITE.class.getSimpleName())) {
			sqlite.close();
		}else if(this.Type.equals(MYSQL.class.getSimpleName())) {
			mysql.close();
		}
	}

	public boolean ischeminset() {
		if(!cheminSqlite.isEmpty()) {
			return true;
		}
		return false;
	}

	public void seturlbase(String urlbase) {
		this.urlbase = urlbase;
	}

	public String geturlbase() {
		return urlbase;
	}

	public void sethost(String host) {
		this.host = host;
	}

	public String gethost() {
		return host;
	}

	public void setuser(String user) {
		this.user = user;
	}

	public String getuser() {
		return user;
	}

	public void setpass(String pass) {
		this.pass = pass;
	}

	public String getpass() {
		return pass;
	}

	public void setdatabase(String database) {
		this.database = database;
	}

	public String getdatabase() {
		return database;
	}

	public void setchemin(String chemin) {
		cheminSqlite = chemin;
	}

	public String getchemin() {
		return cheminSqlite;
	}

	public SQLITE returnSqlite() {
		return sqlite;
	}

	public MYSQL returnMysql() {
		return mysql;
	}

}
