package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.dto.LeaderInfoDTO;
import model.dto.ProjectsByClassDTO;
import model.dto.PurchasesByPorjectDTO;

public class View extends JFrame {
    private Controller controller = new Controller();
    private JLabel lblBusqueda;
    private JTable table;
    private DefaultTableModel tableModel;

    public View() {
        setTitle("Reto 5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(300, 300, 900, 500);
        setSize(900, 500);
        setLayout(null);

        lblBusqueda = new JLabel("Seleccione una consulta");
        lblBusqueda.setBounds(20, 10, 600, 20);
        getContentPane().add(lblBusqueda);

        table = new JTable();
        table.setBackground(Color.YELLOW);
        
        DefaultTableCellRenderer centeredTable = new DefaultTableCellRenderer();
        centeredTable.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centeredTable);
        //txtTabla.setBounds(20, 30, 600, 400);;
        //getContentPane().add(txtTabla);

        //JScrollPane spOrganizador = new JScrollPane(txtTabla);
        //spOrganizador.setViewportView(txtTabla);
        JScrollPane spOrganizador = new JScrollPane(table);
        spOrganizador.setBounds(20, 30, 600, 400);
        getContentPane().add(spOrganizador);
        
        JButton btnLI = new JButton("Consulta de líderes");
        btnLI.setBounds(650, 100, 200, 30);
        btnLI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toViewLI();
                
            }
            
        });
        getContentPane().add(btnLI);

        JButton btnPBC = new JButton("Consulta de proyectos");
        btnPBC.setBounds(650, 150, 200, 30);
        btnPBC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toViewPBC();
                
            }
            
        });
        getContentPane().add(btnPBC);

        JButton btnPBP = new JButton("Consulta de compras");
        btnPBP.setBounds(650, 200, 200, 30);
        btnPBP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toViewPBP();
                
            }
            
        });
        getContentPane().add(btnPBP);
    }
    
    public void toViewLI() {
        try {
            ArrayList<LeaderInfoDTO> leaderInfoList = controller.toQueryLeaderInfo();
            lblBusqueda.setText("Informe de las identificaciones de los líderes, sus nombre, sus primeros apellidos y sus residencias");
            //String output = "\t\t\tInformación disponible de los líderes\n\tID de Líder\t\tNombre de Líder\t\tPrimer Apellido de Líder\tResidencia de Líder";
            tableModel = new DefaultTableModel(new String[] {"ID de Líder", "Nombre de Líder", "Primer Apellido de Líder", "Residencia de Líder"}, 0);
            //String[] columns = {"ID de Líder", "Nombre de Líder", "Primer Apellido de Líder", "Residencia de Líder"};
            //tableModel.setColumnIdentifiers(columns);
            for(LeaderInfoDTO leaderInfo : leaderInfoList) {
                //output += "\t" + leaderInfo.getId() + "\t\t" + leaderInfo.getName() + "\t\t" + leaderInfo.getFirstLastName() + "\t" + leaderInfo.getResidence() + "\n";
                tableModel.addRow(new Object[] {leaderInfo.getId(), leaderInfo.getName(), leaderInfo.getFirstLastName(), leaderInfo.getResidence()});
            }
            //txtTabla.setText(output);
            DefaultTableCellRenderer centeredTable = new DefaultTableCellRenderer();
        centeredTable.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centeredTable);
            table.setModel(tableModel);
        }
        catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void toViewPBC() {
        try {
            ArrayList<ProjectsByClassDTO> projectsByClassDTOList = controller.toQueryProjectsByClass();
            lblBusqueda.setText("<html>Informe de las identificaciónes de los proyectos, sus constructoras, sus números de habitaciones y sus ciudades de los proyectos cuya "
                                + "clasificación es <i>Casa Campestre</i> y sean de las ciudades de <i>Santa Marta</i>, <i>Barranquilla</i> y <i>Cartagena</i></html>");
            lblBusqueda.setBounds(20, 0, 700, 30);
            tableModel = new DefaultTableModel(new String[] {"ID de Proyecto", "Constructora", "N° de Habitatciones", "Ciudad"}, 0);
            for(ProjectsByClassDTO projectsByClassDTO : projectsByClassDTOList) {
                tableModel.addRow(new Object[] {projectsByClassDTO.getId(), projectsByClassDTO.getBuilder(), projectsByClassDTO.getRoomsNumber(), projectsByClassDTO.getCity()});
            }
            table.setModel(tableModel);
        }
        catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void toViewPBP() {
        try {
            ArrayList<PurchasesByPorjectDTO> purchasesByPorjectDTOList = controller.toQueryPurchasesByProject();
            lblBusqueda.setText("<html>Informe de las identificaciónes de las compras, sus constructoras y sus bancos vinculados cuyo proveedor es <i>Homecenter</i> y sean "
            + "de la ciudad de <i>Salento</i></html>");
            lblBusqueda.setBounds(20, 0, 700, 30);
            tableModel = new DefaultTableModel(new String[] {"ID de Compra", "Constructora", "Bancos Vinculados"}, 0);
            for(PurchasesByPorjectDTO purchasesByPorjectDTO : purchasesByPorjectDTOList) {
                tableModel.addRow(new Object[] {purchasesByPorjectDTO.getId(), purchasesByPorjectDTO.getBuilder(), purchasesByPorjectDTO.getLinkedBank()});
            }
            table.setModel(tableModel);
        }
        catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
