package main;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

/**
 * CoffeeGUI class
 * This class launches a GUI that allows the user to view the menu's items, select and order them.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class CoffeeShopGUI extends JFrame implements ActionListener {
	JFrame discountsFrame;
	JTextField promoText, totalAmount;
	JButton substract, add, delete, discounts, order, close;
	JScrollPane scrollList, scrollListB, scrollListC, scrollListD;
	JTextArea displayList, detailsList;
	
	private CurrentOrderList col = new CurrentOrderList();
	private MenuList ml;
	private OrderList ordersList;
	private Customer c = new Customer();
	
	int currentOrderListIndex = -1;
	int currentOrderListSize = 0;
	
	JList<String> foodList;
	JList<String> beverageList;
	JList<String> merchandizingList;
	JList<String> currentOrderList;

	public CoffeeShopGUI(MenuList menuListIn, OrderList ordersListIn) {
		ml = menuListIn;
		ordersList = ordersListIn;
		
		foodList = new JList<String>(ml.getListFood());
		beverageList = new JList<String>(ml.getListBeverage());
		merchandizingList = new JList<String>(ml.getListMerchandise());
		currentOrderList = new JList<String>(col.getList());

		setTitle("Coffee Shop Menu");

		createTop();
		createMiddle();
		createBottom();
		
		addWindowListener(new WindowAdapter() {
            		public void windowClosing(WindowEvent e) {
                		CoffeeShopIO.createReport(ml,ordersList);
                		System.exit(0);
            		}
        	});
		
		pack();
		//centers the GUI in the middle of the screen
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	//the top part of the GUI consists of the labels for each menu
	private void createTop() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,3));
		northPanel.add(new JLabel("    Food     "), BorderLayout.WEST);
		northPanel.add(new JLabel("    Beverage   "), BorderLayout.CENTER);
		northPanel.add(new JLabel("    Merchandising"), BorderLayout.EAST);

		//set up northPanel containing the JLabels
		this.add(northPanel, BorderLayout.NORTH);
	}

	//the middle part of the GUI consists of the 3 menus, the order list and its commands
	private void createMiddle() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2,1));

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1,3));
		
		//the Food menu
		JPanel menuPanelA = new JPanel();
		foodList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,12));
		foodList.setFixedCellWidth(400);
		foodList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			    if (e.getValueIsAdjusting() == false) {
			        if (foodList.getSelectedIndex() != -1 && foodList.getSelectedIndex() != 0) {
			        	int index;
			        	index = foodList.getSelectedIndex();
			        	col.addToList(ml.getMenuItem((String)(ml.getListFood().elementAt(index))));
			        	currentOrderList.setModel(col.getList());
			        	foodList.clearSelection();
			        	totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
			        	promoText.setText(col.getDiscountsLine());
			        }
			        if (foodList.getSelectedIndex() == 0)
			        	foodList.clearSelection();
			    }
			}
		});
		scrollList = new JScrollPane(foodList);
		menuPanelA.add(scrollList);
		
		//the Beverage menu
		JPanel menuPanelB = new JPanel();
		beverageList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,12));
		beverageList.setFixedCellWidth(400);
		beverageList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			    if (e.getValueIsAdjusting() == false) {
			        if (beverageList.getSelectedIndex() != -1 && beverageList.getSelectedIndex() != 0) {
			        	int index;
			        	index = beverageList.getSelectedIndex();
			        	col.addToList(ml.getMenuItem((String)(ml.getListBeverage().elementAt(index))));
			        	currentOrderList.setModel(col.getList());
			        	beverageList.clearSelection();
			        	totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
			        	promoText.setText(col.getDiscountsLine());
			        }
			        if (beverageList.getSelectedIndex() == 0)
			        	beverageList.clearSelection();
			    }
			}
		});
		scrollListB = new JScrollPane(beverageList);
		menuPanelB.add(scrollListB);
		
		//the Merchandise menu
		JPanel menuPanelC = new JPanel();
		merchandizingList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,12));
		merchandizingList.setFixedCellWidth(400);
		merchandizingList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			    if (e.getValueIsAdjusting() == false) {
			        if (merchandizingList.getSelectedIndex() != -1 && merchandizingList.getSelectedIndex() != 0) {
			        	int index;
			        	index = merchandizingList.getSelectedIndex();
			        	col.addToList(ml.getMenuItem((String)(ml.getListMerchandise().elementAt(index))));
			        	currentOrderList.setModel(col.getList());
			        	merchandizingList.clearSelection();
			        	totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
			        	promoText.setText(col.getDiscountsLine());
			        }
			        if (merchandizingList.getSelectedIndex() == 0)
			        	merchandizingList.clearSelection();
			    }
			}
		});
		scrollListC = new JScrollPane(merchandizingList);
		menuPanelC.add(scrollListC);

		menuPanel.add(menuPanelA);
		menuPanel.add(menuPanelB);
		menuPanel.add(menuPanelC);
		
		//the buttons to add one, remove one or delete an item from the bill
		JPanel commandsPanel = new JPanel();
		commandsPanel.setLayout(new GridLayout(2,1));
		
		JPanel commandsPanelTop = new JPanel();
		substract = new JButton("-");
		substract.addActionListener(this);
		commandsPanelTop.add(substract);
		add = new JButton("+");
		add.addActionListener(this);
		commandsPanelTop.add(add);
		
		delete = new JButton("Delete");
		delete.addActionListener(this);
		
		commandsPanel.add(commandsPanelTop);
		commandsPanel.add(delete);
		 
		//the order list
		JPanel orderPanel = new JPanel();
		currentOrderList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,12));
		currentOrderList.setFixedCellWidth(1000);
		currentOrderList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			    if (e.getValueIsAdjusting() == false) {
			        if (currentOrderList.getSelectedIndex() != -1) {
			        	currentOrderListIndex = currentOrderList.getSelectedIndex();
			        }
			    }
			}
		});
		scrollListD = new JScrollPane(currentOrderList);
		orderPanel.add(new JLabel("    Order    "));
		orderPanel.add(scrollListD, BorderLayout.WEST);
		orderPanel.add(commandsPanel);
		
		centerPanel.add(menuPanel);
		centerPanel.add(orderPanel);
		this.add(centerPanel,BorderLayout.CENTER);
	}

	//the bottom part of the GUI consists of the Discount and Total sections and the Checkout and Close buttons
	private void createBottom() {
		 JPanel southPanel = new JPanel();
		 southPanel.setLayout(new GridLayout(2,1));
		 
		 //the Discount and Total sections
		 JPanel modifPanel = new JPanel();
		 discounts = new JButton("Discounts:");
		 discounts.addActionListener(this);
		 modifPanel.add(discounts, BorderLayout.CENTER);
		 //modifPanel.add(new JLabel("Discounts:"), BorderLayout.CENTER);
		 promoText = new JTextField(52);
		 promoText.setEditable(false);
		 modifPanel.add(promoText, BorderLayout.CENTER);
		 modifPanel.add(new JLabel("Total:"), BorderLayout.CENTER);
		 totalAmount = new JTextField(5);
		 totalAmount.setEditable(false);
		 totalAmount.setText(String.valueOf(col.calculateTotal()));
		 promoText.setText(col.getDiscountsLine());
		 modifPanel.add(totalAmount, BorderLayout.CENTER);
		 
		 //the Checkout and Close buttons
		 JPanel orderAndQuitPanel = new JPanel();
		 order = new JButton("Checkout");
		 order.addActionListener(this);
		 orderAndQuitPanel.add(order, BorderLayout.EAST);
		 close = new JButton("Close");
		 close.addActionListener(this);
		 orderAndQuitPanel.add(close, BorderLayout.EAST);

		 southPanel.add(modifPanel);
		 southPanel.add(orderAndQuitPanel);

		 this.add(southPanel, BorderLayout.SOUTH);
	}

	//display the frame containing the details of each discount
	public void displayDiscounts() {
		discountsFrame = new JFrame("A window");
		discountsFrame.setTitle("Available Discounts");
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(13,1));
		northPanel.add(new JLabel(""));
		northPanel.add(new JLabel("    3 Identical Beverages Discount:"));
		northPanel.add(new JLabel("        For 3 identical beverage items bought, the third one is free."));
		northPanel.add(new JLabel("        Applies every 3 identical beverage."));
		northPanel.add(new JLabel(""));
		northPanel.add(new JLabel("    3 Merchandise Items And More Discount:"));
		northPanel.add(new JLabel("        For 3 merchandise items and more bought, the cheapest one is free.    "));
		northPanel.add(new JLabel(""));
		northPanel.add(new JLabel("    10 Items And More Discount:"));
		northPanel.add(new JLabel("        For 10 items and more bought, 10% are deduced from your order."));
		northPanel.add(new JLabel(""));
		northPanel.add(new JLabel("    All offers are cumulative."));
		northPanel.add(new JLabel(""));
		discountsFrame.add(northPanel, BorderLayout.NORTH);
		discountsFrame.pack();
		discountsFrame.setLocationRelativeTo(null); //center it
		discountsFrame.setVisible(true);
	}

	//respond to the click of a button, according to the button that has been pressed
	public void actionPerformed(ActionEvent e) {
		int index = foodList.getSelectedIndex();
		if (e.getSource() == add && currentOrderListIndex != -1 && currentOrderListIndex != 0) {
			col.addOne(col.getCurrentOrderItem((String)(col.getList().elementAt(currentOrderListIndex))));
        	currentOrderList.setModel(col.getList());
        	currentOrderList.setSelectedIndex(currentOrderListIndex);
        	totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
        	promoText.setText(col.getDiscountsLine());
	    }
		if (e.getSource() == substract && currentOrderListIndex != -1 && currentOrderListIndex != 0) {
			currentOrderListSize = currentOrderList.getModel().getSize();
			col.substractOne(col.getCurrentOrderItem((String)(col.getList().elementAt(currentOrderListIndex))));
        	currentOrderList.setModel(col.getList());
        	if (currentOrderListIndex != 0 && (currentOrderList.getModel().getSize() < currentOrderListSize))
        		currentOrderList.setSelectedIndex(currentOrderListIndex - 1);
        	else
        		currentOrderList.setSelectedIndex(currentOrderListIndex);
        	totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
        	promoText.setText(col.getDiscountsLine());
	    }
		if (e.getSource() == delete && currentOrderListIndex != -1 && currentOrderListIndex != 0) {
			currentOrderListSize = currentOrderList.getModel().getSize();
			col.removeItem(col.getCurrentOrderItem((String)(col.getList().elementAt(currentOrderListIndex))));
        	currentOrderList.setModel(col.getList());
        	currentOrderList.setSelectedIndex(currentOrderListIndex - 1);
        	totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
        	promoText.setText(col.getDiscountsLine());
	    }
		
		if (e.getSource() == discounts) {
			displayDiscounts();
		}
		if (e.getSource() == order) {
			if (col.calculateTotal() != 0)
				System.out.println("Total order for " + totalAmount.getText() + " has been confirmed and is being prepared !");
			ordersList.addCurrentOrderToList(c, col);
			try {
				c.generatenewcustomer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			col.removeAllItems();
			currentOrderList.setModel(col.getList());
			totalAmount.setText(String.format("%.2f$", col.calculateTotal()));
			promoText.setText(col.getDiscountsLine());
		}
		if (e.getSource() == close) {
			CoffeeShopIO.createReport(ml, ordersList);
			System.exit(0);
		}
	}
	
	
	
}
