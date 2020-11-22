package fr.lightmute.MultiSqlApi;

import java.util.ArrayList;

public enum BaseSQL {
	SQLITE, MYSQL;
	
	private static ArrayList<String> array = new ArrayList<String>();
	public static ArrayList<String> getBasesDisponible() {
		for(BaseSQL sql : BaseSQL.values()) {
			array.add(sql.toString());
		}
		return array;
	}
}
