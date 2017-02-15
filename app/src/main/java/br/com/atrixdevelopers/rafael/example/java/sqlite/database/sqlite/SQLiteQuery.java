package br.com.atrixdevelopers.rafael.example.java.sqlite.database.sqlite;

/**
 * Atrix Developers
 *
 * @author Rafael de Azeredo
 */
class SQLiteQuery {

    static final String CREATE_TABLE_CONTATO = "CREATE TABLE IF NOT EXISTS contato("
            + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT NOT NULL,"
            + "idade INTEGER NOT NULL"
            + ");";

    static final String SELECT_ALL_CONTATO = "SELECT * FROM contato;";
}
