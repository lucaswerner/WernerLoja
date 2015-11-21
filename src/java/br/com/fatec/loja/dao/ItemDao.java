package br.com.fatec.loja.dao;

import br.com.fatec.loja.modelo.Item;
import br.com.fatec.loja.modelo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas
 */
@Repository
public class ItemDao {

    private Connection connection;

    @Autowired
    public ItemDao(DataSource ds) {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void adiciona(Item i, User u) {

        String sql = "insert into item" + "(nome, valor,quant,descricao, usuarios_id, img, categoria_id)" + " values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, i.getNome());
            stmt.setDouble(2, i.getValor());
            stmt.setShort(3, i.getQuant());
            stmt.setString(4, i.getDescricao());
            stmt.setLong(5, u.getId());
            stmt.setString(6, i.getImg());
            stmt.setLong(7, i.getCategoria_id());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> getLista(User u) {
        try {
            List<Item> itens = new ArrayList<Item>();
            String sql = "select A.ID, A.VALOR, A.QUANT, A.DESCRICAO, A.NOME, A.IMG, B.NOME_CAT"
                    + " FROM ITEM A, CATEGORIA B "
                    + "WHERE A.USUARIOS_ID=(?) AND A.CATEGORIA_ID = B.ID;";

            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, u.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Item i = new Item();
                i.setNome(rs.getString("nome"));
                i.setId(rs.getLong("id"));
                i.setValor(rs.getDouble("valor"));
                i.setQuant(rs.getShort("quant"));
                i.setDescricao(rs.getString("descricao"));
                i.setImg(rs.getString("img"));
                i.setCategoria(rs.getString("nome_cat"));

                itens.add(i);
            }
            rs.close();
            stmt.close();
            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    

    public void altera(Item i) {
        String sql = "update item set nome=?, valor=?, quant=?,"
                + "descricao=?, img=?, categoria_id=? where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, i.getNome());
            stmt.setDouble(2, i.getValor());
            stmt.setShort(3, i.getQuant());
            stmt.setString(4, i.getDescricao());
            stmt.setString(5, i.getImg());
            stmt.setLong(6, i.getCategoria_id());
            stmt.setLong(7, i.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Item i) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from item where id=?");
            stmt.setLong(1, i.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Item carregaItem(Item item, User u) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from item where id=? and usuarios_id=? limit 1");
            stmt.setLong(1, item.getId());
            stmt.setLong(2, u.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Item i = new Item();
                i.setNome(rs.getString("nome"));
                i.setId(rs.getLong("id"));
                i.setValor(rs.getDouble("valor"));
                i.setQuant(rs.getShort("quant"));
                i.setDescricao(rs.getString("descricao"));
                i.setImg(rs.getString("img"));
                i.setCategoria_id(rs.getLong("categoria_id"));
                return i;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public Item carregaItem(Item item) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from item where id=? limit 1");
            stmt.setLong(1, item.getId());
            ResultSet rs = stmt.executeQuery();
            short teste = 1;
            if (rs.next()) {
                Item i = new Item();
                i.setNome(rs.getString("nome"));
                i.setId(rs.getLong("id"));
                i.setValor(rs.getDouble("valor"));
                i.setQuant(teste);
                i.setDescricao(rs.getString("descricao"));
                i.setImg(rs.getString("img"));
                return i;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public List<Item> tudo() {
        try {
            List<Item> itens = new ArrayList<Item>();
            String sql = "select * from item";

            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Item i = new Item();
                i.setNome(rs.getString("nome"));
                i.setId(rs.getLong("id"));
                i.setValor(rs.getDouble("valor"));
                i.setQuant(rs.getShort("quant"));
                i.setDescricao(rs.getString("descricao"));
                i.setImg(rs.getString("img"));

                itens.add(i);
            }
            rs.close();
            stmt.close();
            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Item> listaCategoria(int id) {
        try {
            List<Item> itens = new ArrayList<Item>();
            String sql = "select * from item where categoria_id =(?)";
             
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Item i = new Item();
                i.setNome(rs.getString("nome"));
                i.setId(rs.getLong("id"));
                i.setValor(rs.getDouble("valor"));
                i.setQuant(rs.getShort("quant"));
                i.setDescricao(rs.getString("descricao"));
                i.setImg(rs.getString("img"));

                itens.add(i);
            }
            rs.close();
            stmt.close();
            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
