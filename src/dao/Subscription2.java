package dao;

import dto.DataPersistence;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.*;

public class Subscription2 extends JFrame implements ActionListener, TableModelListener {
	private JButton notification;
	private JButton vehicleBtn;
	private JButton dealerBtn;
	private JButton subbtn;
	private JButton vehicle2;
	private JButton dealer2;
	private JButton sub_v, sub_d;
	private JButton unsub_v, unsub_d;
	private JPanel ButtonsPnl, Content, updatePnl, userPnl, userPnl2, vPnl, vPnl1, dPnl, sub_vPnl, sub_dPnl;
	private static String[] usercolumn = { "vehicleID", "dealerID", "Year", "Brand", "Model", "Miles", "Price" };
	private static String[] usercolumn2 = { "DealerID", "Name", "Address" };
	private Object[][] userdata;
	private Object[][] userdata2;
	private JTable usertable;
	private JScrollPane userscroll;
	private JTable usertable2;
	private JScrollPane userscroll2;
	private JLabel[] usercolumnlabel = new JLabel[usercolumn.length];
	private JTextField[] usercolumntex = new JTextField[usercolumn.length];
	private JLabel[] usercolumn2label = new JLabel[usercolumn2.length];
	private JTextField[] usercolumn2tex = new JTextField[usercolumn2.length];
	private Object[] selecteduser = new Object[usercolumn.length];
	private Object[] selecteduser2 = new Object[usercolumn2.length];
	private JTable sub_vtable, sub_dtable;
	private JScrollPane sub_vscroll, sub_dscroll;
	private DefaultTableModel model, model2;
	private Object[][] sub_vdata;
	private Object[][] sub_ddata;
	private Customer customer;
	private CarDaoImpl car = new CarDaoImpl();
	private DealerDaoImpl dealer = new DealerDaoImpl();
	private Object[] selected_subv = new Object[usercolumn.length];
	private Object[] selected_subd = new Object[usercolumn2.length];
	private static String[] usercolumn_sub = { "vehicleID", "dealerID" };
	private static String[] usercolumn2_sub = { "DealerID", "Name" };

	public Subscription2(Customer x) {

		customer = x;

		createUI();
		setVisible(true);
		setSize(1500, 1200);

	}

	private void createUI() {
		vehicleBtn = new JButton("vehicle list");
		dealerBtn = new JButton("dealer list");
		subbtn = new JButton("my subscription");
		vehicle2 = new JButton("vehicles");
		dealer2 = new JButton("dealers");
		sub_v = new JButton("subscribe");
		sub_d = new JButton("subscribe");
		unsub_v = new JButton("unsubscribe");
		unsub_d = new JButton("unsubscribe");
		ButtonsPnl = new JPanel();
		Content = new JPanel();
		unsub_v.addActionListener(this);
		unsub_d.addActionListener(this);
		sub_v.addActionListener(this);
		sub_d.addActionListener(this);
		vehicleBtn.addActionListener(this);
		dealerBtn.addActionListener(this);
		subbtn.addActionListener(this);
		ButtonsPnl.add(vehicleBtn);
		ButtonsPnl.add(dealerBtn);
		ButtonsPnl.add(subbtn);
		add(ButtonsPnl, BorderLayout.NORTH);
		updatePnl = new JPanel();
		updatePnl.setLayout(new GridLayout(6, 0));
		JPanel pan1 = new JPanel();
		vehicle2.addActionListener(this);
		dealer2.addActionListener(this);
		pan1.add(vehicle2);
		pan1.add(dealer2);
		updatePnl.add(pan1);
		add(Content, BorderLayout.CENTER);

		sub_vPnl = new JPanel(); // subscription of vehicle
		model = new DefaultTableModel();

		for (int i = 0; i < usercolumn_sub.length; i++) { // add name of each column
			model.addColumn(usercolumn_sub[i]);
		}
		sub_vtable = new JTable(model); // table of subscription
		List<Vehicle> carList;
		try {
			carList = car.findAllVehicle(customer); // list of subscribed vehicle
			sub_vdata = new Object[carList.size()][usercolumn_sub.length];
			for (int i = 0; i < carList.size(); i++) { // data in row

				sub_vdata[i][0] = carList.get(i).getVehicleId();
				sub_vdata[i][1] = carList.get(i).getDealerId();

			}
			for (int i = 0; i < carList.size(); i++) {// add row by each data
				model.addRow(sub_vdata[i]);
			}

		} catch (Exception e) {
			System.out.print("null customer or exception");
		}

		sub_vscroll = new JScrollPane(sub_vtable);
		sub_vscroll.setPreferredSize(new Dimension(750, 700));
		sub_vPnl.add(sub_vscroll);
		sub_vtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {// add action to table to
																								// get selected data
			public void valueChanged(ListSelectionEvent event) {

				selected_subv[0] = sub_vtable.getValueAt(sub_vtable.getSelectedRow(), 0).toString();
				selected_subv[1] = sub_vtable.getValueAt(sub_vtable.getSelectedRow(), 1).toString();

			}
		});

		sub_dPnl = new JPanel();// subscription of dealer
		model2 = new DefaultTableModel();
		for (int i = 0; i < usercolumn2_sub.length; i++) {// add name of each column
			model2.addColumn(usercolumn2_sub[i]);
		}
		sub_dtable = new JTable(model2);

		List<Dealer> dealerList;
		try {
			dealerList = dealer.findAllInventory(customer);// list of subscribed dealer
			sub_ddata = new Object[dealerList.size()][usercolumn2_sub.length];
			for (int i = 0; i < dealerList.size(); i++) {// data in row

				sub_ddata[i][0] = dealerList.get(i).getDealerId();
				sub_ddata[i][1] = dealerList.get(i).getDealerName();

			}
			for (int i = 0; i < dealerList.size(); i++) {
				model2.addRow(sub_ddata[i]);
			}

		} catch (Exception e) {
			System.out.print("null customer or exception(d)");
		}
		sub_dscroll = new JScrollPane(sub_dtable);
		sub_dscroll.setPreferredSize(new Dimension(750, 700));
		sub_dPnl.add(sub_dscroll);
		sub_dtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {// add action to table to
																								// get selected data
			public void valueChanged(ListSelectionEvent event) {

				selected_subd[0] = sub_dtable.getValueAt(sub_dtable.getSelectedRow(), 0).toString();
				selected_subd[1] = sub_dtable.getValueAt(sub_dtable.getSelectedRow(), 1).toString();

			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == subbtn) { // my subscription button
			Content.removeAll();
			Content.add(updatePnl);// show subscription history buttons

			Content.revalidate();
			this.repaint();

		} else if (e.getSource() == vehicle2) {// subscription history of vehicle button
			Content.removeAll();

			Content.add(sub_vPnl);
			Content.add(unsub_v); // unsubscribe vehicle button
			Content.revalidate();

			this.repaint();

		} else if (e.getSource() == dealer2) {// subscription history of dealer button
			Content.removeAll();

			Content.add(sub_dPnl);
			Content.add(unsub_d);// unsubscribe dealer button
			Content.revalidate();
			this.repaint();
		} else if (e.getSource() == vehicleBtn) {// vehicle list button
			Content.removeAll();
			userPnl = new JPanel();
			DataPersistence db = new DataPersistence();
			List<Vehicle> con = db.getAllVehicles();// get all list of vehicle
			userdata = new Object[con.size()][usercolumn.length];
			for (int j = 0; j < con.size(); j++) {// add data in each row
				userdata[j][0] = con.get(j).getVehicleId();
				userdata[j][1] = con.get(j).getDealerId();
				userdata[j][2] = con.get(j).getYear();
				userdata[j][3] = con.get(j).getBrand();
				userdata[j][4] = con.get(j).getModel();
				userdata[j][5] = con.get(j).getMiles();
				userdata[j][6] = con.get(j).getPrice();
				

			}

			usertable = new JTable(userdata, usercolumn);
			userscroll = new JScrollPane(usertable);
			userscroll.setPreferredSize(new Dimension(750, 700));
			userPnl.add(userscroll);
			usertable.getModel().addTableModelListener(this);
			vPnl = new JPanel();
			vPnl1 = new JPanel();
			JPanel pan = new JPanel();
			usercolumnlabel[0] = new JLabel(usercolumn[0]); // add label and textfield by vehicle information
			usercolumntex[0] = new JTextField(30);
			usercolumntex[0].setEditable(false);
			pan.add(usercolumnlabel[0]);
			pan.add(usercolumntex[0]);
			vPnl1.add(pan);
			for (int i = 1; i < usercolumn.length; i++) {
				JPanel pan3 = new JPanel();
				usercolumnlabel[i] = new JLabel(usercolumn[i]);
				usercolumntex[i] = new JTextField(15);
				usercolumntex[i].setEditable(false);
				pan3.add(usercolumnlabel[i]);
				pan3.add(usercolumntex[i]);
				vPnl.add(pan3);

			}
			Content.add(userPnl);
			Content.add(vPnl1);
			Content.add(vPnl);
			Content.add(sub_v);
			Content.revalidate();
			usertable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {// show info in textfield by selected row
					for (int i = 0; i < usercolumn.length; i++) {
						usercolumntex[i].setText(usertable.getValueAt(usertable.getSelectedRow(), i).toString());

					}

				}
			});

			this.repaint();

		} else if (e.getSource() == dealerBtn) {// dealer list button
			Content.removeAll();
			userPnl2 = new JPanel();
			dPnl = new JPanel();
			DataPersistence db = new DataPersistence();
			List<Dealer> con2 = db.getAllDealers();// all list of dealer
			userdata2 = new Object[con2.size()][usercolumn2.length];
			for (int i = 0; i < con2.size(); i++) {// add data in each row
				userdata2[i][0] = con2.get(i).getDealerId();
				userdata2[i][1] = con2.get(i).getDealerName();
				userdata2[i][2] = con2.get(i).getDealerAddress().toCSVLine();
			}
			usertable2 = new JTable(userdata2, usercolumn2);
			userscroll2 = new JScrollPane(usertable2);
			userscroll2.setPreferredSize(new Dimension(750, 700));
			userPnl2.add(userscroll2);
			usertable2.getModel().addTableModelListener(this);
			for (int i = 0; i < usercolumn2.length; i++) {// add label and textfield by dealer information
				JPanel pan3 = new JPanel();
				usercolumn2label[i] = new JLabel(usercolumn2[i]);
				usercolumn2tex[i] = new JTextField(25);
				usercolumn2tex[i].setEditable(false);
				pan3.add(usercolumn2label[i]);
				pan3.add(usercolumn2tex[i]);
				dPnl.add(pan3);

			}
			dPnl.add(sub_d);
			Content.add(userPnl2);
			Content.add(dPnl);

			Content.revalidate();

			usertable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {// show info in textfield by selected row
					for (int i = 0; i < usercolumn2.length; i++) {
						usercolumn2tex[i].setText(usertable2.getValueAt(usertable2.getSelectedRow(), i).toString());

					}

				}
			});

			this.repaint();
		} else if (e.getSource() == sub_v) {// subscribe vehicle button
			for (int i = 0; i < selecteduser.length; i++) {// get data from selected row of vehicle list table
				selecteduser[i] = usercolumntex[i].getText();
			}

			try {
				car.insertVehicle((String) selecteduser[0], (String) selecteduser[1], customer);// insert vehicle info
																								// to database
				model.addRow(selecteduser);// add new row
				JOptionPane.showMessageDialog(null, "subscribe successfully");
			} catch (Exception ee) {
				System.out.println("no subv");
				JOptionPane.showMessageDialog(null, "You have subscribed this vehicle");
			}

			this.repaint();

		} else if (e.getSource() == sub_d) {// subscribe dealer button
			for (int i = 0; i < selecteduser2.length; i++) {// get data from selected row of dealer list table
				selecteduser2[i] = usercolumn2tex[i].getText();
			}

			try {
				dealer.insertDealer((String) selecteduser2[0], (String) selecteduser2[1], customer);
				;// insert dealer info to database
				model2.addRow(selecteduser2);
				JOptionPane.showMessageDialog(null, "subscribe successfully");
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null, "You have subscribed this dealer");
			}
			this.repaint();

		} else if (e.getSource() == unsub_v) {// unsubscribe vehicle button

			Content.revalidate();

			try {
				JOptionPane.showMessageDialog(null, "unsubscribe successfully");
				car.deleteVehicle((String) selected_subv[0], customer);// delete vehicle from database
				removeRow(model, sub_vtable);

			} catch (Exception ee) {
				System.out.println("unsub_v");
			} finally {

			}

			this.repaint();
		} else if (e.getSource() == unsub_d) {// unsubscribe dealer button

			System.out.println(sub_dtable.getSelectedRow());
			try {
				JOptionPane.showMessageDialog(null, "unsubscribe successfully");
				dealer.deleteDealer((String) selected_subd[0], customer);// delete dealer from database
				removeRow(model2, sub_dtable);

				Content.revalidate();
			} catch (Exception ee) {
				System.out.println("unsub_d");
			}
			this.repaint();
		}

	}

	// remove selected row from table
	public static void removeRow(DefaultTableModel m, JTable t) throws Exception {
		m.removeRow(t.getSelectedRow());
	}

//	public static void main(String[] args) throws Exception {
//		Customer j = new Customer("5454", "yi", "jacky");
//		Subscription2 gui = new Subscription2(j);
//
//		// gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void tableChanged(TableModelEvent e) {

	}
}
