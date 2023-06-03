
package agenda.utils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private Connection conn;
    private Statement stmt;
    
    public void createContactsDatabase(){
    
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            this.conn = DriverManager.getConnection("jdbc:hsqldb:file:./database/dbagenda");
            this.stmt = this.conn.createStatement();
            this.stmt.executeUpdate("CREATE TABLE contatos (id IDENTITY PRIMARY KEY,"+ 
                    " cpf VARCHAR(11),"+
                    " nome VARCHAR(50),"+
                    " email VARCHAR(50),"+
                    " telefone VARCHAR(50));");
            this.stmt.close();
            this.conn.close();
            System.out.println("Tabela criada com sucesso");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada!");
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]){
        DatabaseUtil util = new DatabaseUtil();
        util.createContactsDatabase();
    }
    
    
    
    
    }
    

