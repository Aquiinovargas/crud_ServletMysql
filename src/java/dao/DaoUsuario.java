package dao;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Usuario;
import modelos.Usuarios;
import util.Seguridad;

public class DaoUsuario {

    private String ultimoError;

    public String getUltimoError() {
        return ultimoError;
    }

    public Usuarios listar() {

        Usuarios lista = new Usuarios();

        String sql = "SELECT idUsuario, nombre, apPaterno, apMaterno, correo, password, estado FROM usuario ORDER BY idUsuario";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApPaterno(rs.getString("apPaterno"));
                u.setApMaterno(rs.getString("apMaterno"));
                u.setCorreo(rs.getString("correo"));
                u.setPassword(rs.getString("password"));
                u.setEstado(rs.getString("estado"));

                lista.add(u);

            }

        } catch (SQLException e) {

            ultimoError = "Error al LISTAR: " + e.getMessage();
            e.printStackTrace();

        }

        return lista;

    }

    public boolean correoExiste(String correo) {

        String sql = "SELECT idUsuario FROM usuario WHERE correo = ?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {

            ultimoError = "Error al validar correo: " + e.getMessage();
            e.printStackTrace();
            return false;

        }

    }

    public boolean registrar(Usuario u) {

        String sql = "INSERT INTO usuario(nombre,apPaterno,apMaterno,correo,password,estado) VALUES(?,?,?,?,?,'activo')";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApPaterno());
            ps.setString(3, u.getApMaterno());
            ps.setString(4, u.getCorreo());
            ps.setString(5, Seguridad.hashPassword(u.getPassword()));

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            ultimoError = "Error al REGISTRAR: " + e.getMessage();
            e.printStackTrace();

            return false;

        }

    }

    public boolean modificar(int idUsuario, Usuario u) {

        String sql = "UPDATE usuario SET nombre=?, apPaterno=?, apMaterno=?, correo=? WHERE idUsuario=?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApPaterno());
            ps.setString(3, u.getApMaterno());
            ps.setString(4, u.getCorreo());
            ps.setInt(5, idUsuario);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            ultimoError = "Error al MODIFICAR: " + e.getMessage();
            e.printStackTrace();

            return false;

        }

    }

    public boolean cambiarEstado(int idUsuario) {

        String sql = "UPDATE usuario SET estado = IF(estado='activo','inactivo','activo') WHERE idUsuario=?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            ultimoError = "Error al CAMBIAR ESTADO: " + e.getMessage();
            e.printStackTrace();

            return false;

        }

    }

    public Usuario buscar(int idUsuario) {

        String sql = "SELECT * FROM usuario WHERE idUsuario=?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuario u = new Usuario();

                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApPaterno(rs.getString("apPaterno"));
                    u.setApMaterno(rs.getString("apMaterno"));
                    u.setCorreo(rs.getString("correo"));
                    u.setPassword(rs.getString("password"));
                    u.setEstado(rs.getString("estado"));

                    return u;

                }

            }

        } catch (SQLException e) {

            ultimoError = "Error al BUSCAR: " + e.getMessage();
            e.printStackTrace();

        }

        return null;

    }

    // Para el Login: solo deja pasar si existe el correo, la password coincide y el estado es activo
    public Usuario autenticar(String correo, String password) {

        String sql = "SELECT * FROM usuario WHERE correo=? AND password=? AND estado='activo'";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, Seguridad.hashPassword(password));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuario u = new Usuario();

                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApPaterno(rs.getString("apPaterno"));
                    u.setApMaterno(rs.getString("apMaterno"));
                    u.setCorreo(rs.getString("correo"));
                    u.setEstado(rs.getString("estado"));

                    return u;

                }

            }

        } catch (SQLException e) {

            ultimoError = "Error al AUTENTICAR: " + e.getMessage();
            e.printStackTrace();

        }

        return null;

    }

}
