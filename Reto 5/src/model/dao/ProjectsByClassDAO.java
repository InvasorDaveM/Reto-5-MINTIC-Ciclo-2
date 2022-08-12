package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.ProjectsByClassDTO;
import util.JDBCUtilities;

public class ProjectsByClassDAO {
    public ArrayList<ProjectsByClassDTO> toList() throws SQLException {
        ArrayList<ProjectsByClassDTO> result = new ArrayList<ProjectsByClassDTO>();
        Connection conn = JDBCUtilities.getConnection();
        try {
            String query = "SELECT"
            +    " ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad"
            + " FROM"
            +    " Proyecto"
            + " WHERE"
            +    " Clasificacion = 'Casa Campestre' AND Ciudad IN('Santa Marta', 'Cartagena', 'Barranquilla')";
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
                /*
                ProjectsByClassDTO dto = new ProjectsByClassDTO();
                dto.setId(rset.getInt("ID_Proyecto"));
                dto.setBuilder(rset.getString("Constructora"));
                dto.setRoomsNumber(rset.getInt("Numero_Habitaciones"));
                dto.setCity(rset.getString("Ciudad")); 
                */
                ProjectsByClassDTO dto = new ProjectsByClassDTO(rset.getInt("ID_Proyecto"), rset.getString("Constructora"), rset.getInt("Numero_Habitaciones"), rset.getString("Ciudad"));
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
            System.err.println("Error al leer la información de los proyectos");
        }
        finally {
            if(conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
