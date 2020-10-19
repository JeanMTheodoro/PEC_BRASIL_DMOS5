package pecbrasil.com.br.exeption;

import java.sql.SQLException;

public class InsercaoDatabaseSqlite extends SQLException {
    public InsercaoDatabaseSqlite(String error){
        super(error);
    }
}
