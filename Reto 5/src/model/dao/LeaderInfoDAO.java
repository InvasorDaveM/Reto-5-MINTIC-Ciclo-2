package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.LeaderInfoDTO;
import util.JDBCUtilities;

public class LeaderInfoDAO {
    public ArrayList<LeaderInfoDTO> toList() throws SQLException {
        ArrayList<LeaderInfoDTO> result = new ArrayList<LeaderInfoDTO>();
        Connection conn = JDBCUtilities.getConnection();
        try {
            String query = "SELECT"
            +    " ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia"
            + " FROM"
            +    " Lider"
            + " ORDER BY"
            +    " Ciudad_Residencia /*ASC*/";

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
                /*
                LeaderInfoDTO dto = new LeaderInfoDTO();
                dto.setId(rset.getInt("ID_Lider"));
                dto.setName(rset.getString("Nombre"));
                dto.setFirstLastName(rset.getString("Nombre"));
                dto.setResidence(rset.getString("Nombre"));
                */
                LeaderInfoDTO dto = new LeaderInfoDTO(rset.getInt("ID_Lider"), rset.getString("Nombre"), rset.getString("Nombre"), rset.getString("Nombre"));
                result.add(dto);
            }

            if(rset != null) {
                rset.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
        catch(SQLException e) {
            System.err.println("Error al leer la información de los líderes");
        }
        finally {
            if(conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
