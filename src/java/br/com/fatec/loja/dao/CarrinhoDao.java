/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.dao;

import br.com.fatec.loja.modelo.Carrinho;
import br.com.fatec.loja.modelo.Item;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas
 */
@Repository
public class CarrinhoDao {

    private Connection connection;

    @Autowired
    public CarrinhoDao(DataSource ds) {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void finalizarCompra(Carrinho carrinho) {
        String sql = "insert into carrinho" + " (data_expiracao, usuarios_id, total)" + " values (?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new Date(System.currentTimeMillis()));
            stmt.setLong(2, carrinho.getUsuarios_id());
            stmt.setDouble(3, carrinho.getTotal());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();

            rs.next();

            int idCarrinho = rs.getInt(1);

            sql = "insert into itens_carrinho" + " (relacao_item_id, relacao_id_carrinho)" + " values (?,?)";

            for (Item item : carrinho.getItens()) {

                stmt = connection.prepareStatement(sql);
                stmt.setLong(1, item.getId());
                stmt.setLong(2, idCarrinho);

                stmt.execute();
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Carrinho> historicoCompras(Carrinho carrinho) throws ParseException {
        String sql = "select C.id_carrinho, C.data_expiracao, C.usuarios_id, C.total, I.id, I.nome, I.descricao, I.valor, I.quant, I.img"
                + " from carrinho C"
                + " inner join"
                + " itens_carrinho IC"
                + " on (C.usuarios_id = ? and C.id_carrinho = IC.relacao_id_carrinho)"
                + " inner join item I"
                + " on (I.id = IC.relacao_item_id)"
                + " order by C.Id_carrinho";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, carrinho.getUsuarios_id());

            ResultSet rs = stmt.executeQuery();
            List<Carrinho> compras = new ArrayList<Carrinho>();

            Long teste;
            teste = 0L;

            Carrinho meuCarrinho = new Carrinho();

            while (rs.next()) {
                if (meuCarrinho.getItens().isEmpty()) {
                    teste = rs.getLong("id_carrinho");
                }

                if (teste != rs.getLong("id_carrinho")) {

                    compras.add(meuCarrinho);
                    meuCarrinho = new Carrinho();
                    teste = rs.getLong("id_carrinho");
                }

                meuCarrinho.setId(rs.getLong("id_carrinho"));
                meuCarrinho.setData_expiracao(rs.getDate("data_expiracao"));
                meuCarrinho.setTotal(rs.getDouble("total"));

                Item i = new Item();
                i.setId(rs.getLong("id"));
                i.setNome(rs.getString("nome"));
                i.setValor(rs.getDouble("valor"));
                //i.setQuant(rs.getShort("quant")); quantidade sempre será 1 , ver depois
                i.setDescricao(rs.getString("descricao"));
                i.setImg(rs.getString("img"));

                meuCarrinho.getItens().add(i);

            }
            compras.add(meuCarrinho);

            rs.close();
            stmt.close();
            return compras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
