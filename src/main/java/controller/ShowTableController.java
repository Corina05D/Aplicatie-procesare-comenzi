package controller;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import view.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowTableController implements ActionListener {

    private List<String> tableHeader;
    private List<List<Object>> tableData;
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;
    private final String title;

    public ShowTableController(String title) {
        this.title = title;
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.orderBLL = new OrderBLL();
    }

    private String[] getHeaderArray() {
        String[] tableHeaderArray = new String[tableHeader.size()];
        int i = 0;
        for(String s : tableHeader){
            tableHeaderArray[i] = tableHeader.get(i++);
        }
        return tableHeaderArray;
    }

    private Object[][] getDataMatrix() {
        Object[][] tableDataMatrix = new Object[tableData.size()][];
        int i = 0;
        for (Object o : tableData) {
            tableDataMatrix[i] = tableData.get(i++).toArray();
        }
        return tableDataMatrix;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(title.equalsIgnoreCase("client")){
            this.tableHeader = clientBLL.createTableHeader();
            this.tableData = clientBLL.createTableData();
        } else if(title.equalsIgnoreCase("produs")){
            this.tableHeader = productBLL.createTableHeader();
            this.tableData = productBLL.createTableData();
        } else if(title.equalsIgnoreCase("comanda")){
            this.tableHeader = orderBLL.createTableHeader();
            this.tableData = orderBLL.createTableData();
        }
        TableView tableView = new TableView(getHeaderArray(), getDataMatrix(), title);
        tableView.setVisible(true);
    }
}
