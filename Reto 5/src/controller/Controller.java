package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.LeaderInfoDAO;
import model.dao.ProjectsByClassDAO;
import model.dao.PurchasesByProjectDAO;
import model.dto.LeaderInfoDTO;
import model.dto.ProjectsByClassDTO;
import model.dto.PurchasesByPorjectDTO;

public class Controller {
    private LeaderInfoDAO leaderInfoDAO;
    private ProjectsByClassDAO projectsByClassDAO;
    private PurchasesByProjectDAO purchasesByProjectDAO;

    public Controller() {
        this.leaderInfoDAO = new LeaderInfoDAO();
        this.projectsByClassDAO = new ProjectsByClassDAO();
        this.purchasesByProjectDAO = new PurchasesByProjectDAO();
    } 

    public ArrayList<LeaderInfoDTO> toQueryLeaderInfo() throws SQLException {
        return leaderInfoDAO.toList();
    }

    public ArrayList<ProjectsByClassDTO> toQueryProjectsByClass() throws SQLException {
        return projectsByClassDAO.toList();
    }

    public ArrayList<PurchasesByPorjectDTO> toQueryPurchasesByProject() throws SQLException {
        return purchasesByProjectDAO.toList();
    }
}
