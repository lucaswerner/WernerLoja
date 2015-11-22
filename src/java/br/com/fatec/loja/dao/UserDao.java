/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.dao;

import br.com.fatec.loja.ConnectionFactory;
import br.com.fatec.loja.modelo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas
 */
@Repository
public class UserDao {

    private Connection connection;

    @Autowired
    public UserDao(DataSource ds) {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User validaUser(User usuario) {
        String sql = "select * from usuarios where login=(?) and senha=(?) limit 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getLong("id"));
                usuario.setSenha(null);
                usuario.setNome(rs.getString("nome"));
                return usuario;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User registra(User usuario) {
        String sql = "insert into usuarios" + "(login,senha,nome)" + " values (?,?,?)";
        try {

            if (validaUser(usuario) == null) {
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, usuario.getLogin());
                stmt.setString(2, usuario.getSenha());
                stmt.setString(3, usuario.getNome());

                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                usuario.setId(rs.getLong(1));
                stmt.close();
                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean existeUser(User usuario) {
        String sql = "select * from usuarios where login=(?) limit 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String novaSenha(User usuario) {
        SimpleDateFormat horaformatada = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String senha = horaformatada.format(cal.getTime());
        senha = usuario.md5(senha).substring(0, 8);
        System.out.println(senha);

        String sql = "update usuarios set senha=? where login=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, senha);
            stmt.setString(2, usuario.getLogin());

            stmt.execute();
            stmt.close();
            return senha;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void altera(User user) {
        String sql = "update usuarios set login=?, senha=?, nome=? "
                + "where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getSenha());
            stmt.setString(3, user.getNome());
            stmt.setLong(4, user.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean senhasIguais(User usuario) {
        String sql = "select senha from usuarios where id=(?) limit 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, usuario.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("senha"));
                System.out.println(usuario.getSenha());
                if (usuario.getSenha().equals(rs.getString("senha"))) {
                    System.out.println("retornou verdadeiro");
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaLoginId(User usuario) {
        if (this.existeUser(usuario)) {
            String sql = "select id from usuarios where login=(?) limit 1";
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, usuario.getLogin());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    if (rs.getLong("id") == usuario.getId()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return true;
        }
    }

}
