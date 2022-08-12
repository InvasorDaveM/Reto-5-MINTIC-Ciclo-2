package model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.PurchasesByPorjectDTO;
import util.JDBCUtilities;

public class PurchasesByProjectDAO {
    public ArrayList<PurchasesByPorjectDTO> toList() throws SQLException {
        ArrayList<PurchasesByPorjectDTO> result = new ArrayList<PurchasesByPorjectDTO>();
        Connection conn = JDBCUtilities.getConnection();
        try {
            String query = "SELECT"
            +    " ID_Compra, Constructora, Banco_Vinculado"
            + " FROM"
            +    " /*Compra JOIN Proyecto USING(ID_Proyecto)*/ Compra NATURAL JOIN Proyecto"
            + " WHERE" 
            +    " Proveedor = 'Homecenter' AND Ciudad = 'Salento'";
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
                /*
                PurchasesByPorjectDTO dto = new PurchasesByPorjectDTO();
                dto.setId(rset.getInt("ID_Compra"));
                dto.setBuilder(rset.getString("Constructora"));
                dto.setLinkedBank(rset.getString("Banco_Vinculado"));
                */
                PurchasesByPorjectDTO dto = new PurchasesByPorjectDTO(rset.getInt("ID_Compra"), rset.getString("Constructora"), rset.getString("Banco_Vinculado"));
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
            System.err.println("Error al leer la información de las compras y los proyectos");
        }
        finally {
            if(conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
