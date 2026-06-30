package dao;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Alumno;
import modelos.Alumnos;

public class DaoAlumno {

    private String ultimoError;

    public String getUltimoError() {
        return ultimoError;
    }

    public Alumnos listar() {

        Alumnos lista = new Alumnos();

        String sql = "SELECT NL, Nombre, ApPaterno, ApMaterno, DDI, DWI, ECBD FROM alumno ORDER BY NL";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Alumno a = new Alumno();

                a.setNL(rs.getInt("NL"));
                a.setNombre(rs.getString("Nombre"));
                a.setApPaterno(rs.getString("ApPaterno"));
                a.setApMaterno(rs.getString("ApMaterno"));
                a.setDDI(rs.getDouble("DDI"));
                a.setDWI(rs.getDouble("DWI"));
                a.setECBD(rs.getDouble("ECBD"));

                lista.add(a);

            }

        } catch (SQLException e) {

            ultimoError = "Error al LISTAR: " + e.getMessage();
            e.printStackTrace();

        }

        return lista;

    }

    public boolean agregar(Alumno a) {

        String sql = "INSERT INTO alumno(NL,Nombre,ApPaterno,ApMaterno,DDI,DWI,ECBD) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getNL());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApPaterno());
            ps.setString(4, a.getApMaterno());
            ps.setDouble(5, a.getDDI());
            ps.setDouble(6, a.getDWI());
            ps.setDouble(7, a.getECBD());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            ultimoError = "Error al AGREGAR: " + e.getMessage();
            e.printStackTrace();

            return false;

        }

    }

    public boolean modificar(int nlOld, Alumno a) {

        String sql = "UPDATE alumno SET NL=?, Nombre=?, ApPaterno=?, ApMaterno=?, DDI=?, DWI=?, ECBD=? WHERE NL=?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getNL());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApPaterno());
            ps.setString(4, a.getApMaterno());
            ps.setDouble(5, a.getDDI());
            ps.setDouble(6, a.getDWI());
            ps.setDouble(7, a.getECBD());
            ps.setInt(8, nlOld);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            ultimoError = "Error al MODIFICAR: " + e.getMessage();
            e.printStackTrace();

            return false;

        }

    }

    public boolean eliminar(int nl) {

        String sql = "DELETE FROM alumno WHERE NL=?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nl);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            ultimoError = "Error al ELIMINAR: " + e.getMessage();
            e.printStackTrace();

            return false;

        }

    }

    public Alumno buscar(int nl) {

        String sql = "SELECT * FROM alumno WHERE NL=?";

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nl);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Alumno a = new Alumno();

                a.setNL(rs.getInt("NL"));
                a.setNombre(rs.getString("Nombre"));
                a.setApPaterno(rs.getString("ApPaterno"));
                a.setApMaterno(rs.getString("ApMaterno"));
                a.setDDI(rs.getDouble("DDI"));
                a.setDWI(rs.getDouble("DWI"));
                a.setECBD(rs.getDouble("ECBD"));

                return a;

            }

        } catch (SQLException e) {

            ultimoError = "Error al BUSCAR: " + e.getMessage();
            e.printStackTrace();

        }

        return null;

    }

}