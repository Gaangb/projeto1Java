
package agenda.dao;

import agenda.model.Contato;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class ContatoDAO {
    public static List <Contato> listaContatos = new ArrayList<>();
    public static int codigo = 1;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    
    private void conectar() throws ClassNotFoundException, SQLException{
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            this.conn = DriverManager.getConnection("jdbc:hsqldb:file:./database/dbagenda");
    }
    
    
    private void criarStatement() throws SQLException{
        this.stmt = this.conn.createStatement();
    }
    
    private void criarPrepareStatement(String query) throws SQLException{
        this.pstmt = this.conn.prepareStatement(query);
    }
    
    private void desconectar() throws SQLException{
        if(this.pstmt != null)
            this.pstmt.close();
        if(this.stmt != null)
            this.stmt.close();
        if(this.conn != null)
            this.conn.close();
    }
    
    private Contato buscaContato(int id){
    for(Contato contato: ContatoDAO.listaContatos){
        if(contato.getId()== id){
            return contato;
        }
    
    }
    return null;
}
    public void atualizarContato(Contato contato) throws ClassNotFoundException, SQLException{
        this.conectar();
        String query = "UPDATE contatos"
                + "set cpf=?, nome=?, email=?, telefone=?"
                + "WHERE id = ?";
        this.criarPrepareStatement(query);
        this.pstmt.setString(1, contato.getCpf());
        this.pstmt.setString(2, contato.getNome());
        this.pstmt.setString(3, contato.getEmail());
        this.pstmt.setString(4, contato.getTelefone());
        this.pstmt.setInt(5, contato.getId());
        this.pstmt.execute();
    }

    public void salvar(Contato contact) throws ClassNotFoundException, SQLException{
        this.conectar();
        String query = "INSERT INTO contatos"
                + "(cpf, nome, email, telefone)"
                +"VALUES(?,?,?,?)";
        this.criarPrepareStatement(query);
        this.pstmt.setString(1, contact.getCpf());
        this.pstmt.setString(2, contact.getNome());
        this.pstmt.setString(3, contact.getEmail());
        this.pstmt.setString(4, contact.getTelefone());
        this.pstmt.execute();
    }
    
    
    public void remover(int id) throws SQLException, ClassNotFoundException{
        this.conectar();
        String query = "DELETE FROM contatos"
                + "WHERE id = ?";
        this.criarPrepareStatement(query);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
    }
    
    public List<Contato> obterTodosOsContatos() throws ClassNotFoundException, SQLException{
        this.conectar();
        this.criarStatement();
        List<Contato> contatos = new ArrayList();
        String query = "SELECT * FROM contatos;";
        ResultSet rs = this.stmt.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("id");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String telefone = rs.getString("telefone");
            Contato contato = new Contato(id, cpf, nome, email, telefone);
            contatos.add(contato);
        }
        return null;
    }
    
}

