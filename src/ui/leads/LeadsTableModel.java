package ui.leads;

import dto.*;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class LeadsTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = -5171313872366476315L;
    
    public List<Lead> leads;
    
    public LeadsTableModel(List<Lead> leads) {
        this.leads = leads;
    }

    @Override
    public int getRowCount() {
        return leads.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {

        if (columnIndex == 0) {
            return "Name";
        }
        if (columnIndex == 1) {
            return "Phone";
        }
        if (columnIndex == 2) {
            return "Email";
        }
        if (columnIndex == 3) {
            return "Contact Preference";
        }
        if (columnIndex == 4) {
            return "Contact Time";
        }
        if (columnIndex == 5) {
            return "Use Purpose";
        }
        if (columnIndex == 6) {
            return "Read";
        }
        if (columnIndex == 7) {
            return "Contacted";
        }
        return null;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lead f = leads.get(rowIndex);
        if (columnIndex == 0) {
            return f.getFirstName() + " " + f.getLastName();
        }
        if (columnIndex == 1) {
            return f.getPhoneNumber();
        }
        if (columnIndex == 2) {
            return f.getEmailAddress();
        }
        if (columnIndex == 3) {
            return f.getContactPreference();
        }
        if (columnIndex == 4) {
            return f.getContactTime();
        }
        if (columnIndex == 5) {
            return f.getUsePurpose();
        }
        if (columnIndex == 6) {
            return f.getRead() ? "Yes": "No";
        }
        if (columnIndex == 7) {
            return f.getContacted()? "Yes": "No";
        }

        return null;
    }

    public void removeRow(int index) {
        leads.remove(index);
        fireTableRowsDeleted(0, leads.size());
    }
    
    public void updateLeads(List<Lead> leads) {
        this.leads = leads;
    }
    

}