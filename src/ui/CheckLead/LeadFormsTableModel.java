package ui.CheckLead;

import dto.*;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class LeadFormsTableModel extends AbstractTableModel {
    public List<Lead> forms;
    public LeadFormsTableModel(List forms) {
        this.forms = forms;
    }

    @Override
    public int getRowCount() {
        return forms.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {

        if (columnIndex == 0) {
            return "Name";
        }
        if (columnIndex == 1) {
            return "Phone Number";
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
            return "Contacted";
        }
        return null;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lead f = forms.get(rowIndex);
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
            return f.getContacted()? "Yes": "No";
        }

        return null;
    }

    public void removeRow(int index) {
        forms.remove(index);
        fireTableRowsDeleted(0, forms.size());
    }


}