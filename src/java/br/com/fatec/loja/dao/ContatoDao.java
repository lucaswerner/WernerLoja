
package br.com.fatec.loja.dao;

import br.com.fatec.loja.modelo.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lab3aluno
 */
@Repository
public class ContatoDao {
    private Connection connection;
    
    @Autowired    
    public ContatoDao(DataSource ds){
            try {
                this.connection = ds.getConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
    }
    
    public void adiciona(Contato c){
        
        String sql = "insert into contatos" + "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";
    
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,c.getNome());
            stmt.setString(2,c.getEmail());
            stmt.setString(3,c.getEndereco());
            stmt.setDate(4, c.getData());
                    
            stmt.execute();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getLista() {
    try {
        List<Contato> contatos = new ArrayList<Contato>();
        PreparedStatement stmt = this.connection.
        prepareStatement("select * from contatos");
        ResultSet rs = stmt.executeQuery();
 
        while (rs.next()) {
            // criando o objeto Contato
            Contato contato = new Contato();
            contato.setId(rs.getLong("id"));
            contato.setNome(rs.getString("nome"));
            contato.setEmail(rs.getString("email"));
            contato.setEndereco(rs.getString("endereco"));
            contato.setData(rs.getDate("dataNascimento"));
 
             
            contatos.add(contato);
        }
        rs.close();
        stmt.close();
        return contatos;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
 }
    
    public void altera(Contato contato) {
     String sql = "update contatos set nome=?, email=?,"+
             "endereco=?, dataNascimento=? where id=?";
 
     try {
         PreparedStatement stmt = connection.prepareStatement(sql);
         
         stmt.setString(1, contato.getNome());
         stmt.setString(2, contato.getEmail());
         stmt.setString(3, contato.getEndereco());
         stmt.setDate(4, contato.getData());
         stmt.setLong(5, contato.getId());
         stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
    public void remove(Contato contato) {
     try {
         PreparedStatement stmt = connection
                 .prepareStatement("delete from contatos where id=?");
         stmt.setLong(1, contato.getId());
         stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
}