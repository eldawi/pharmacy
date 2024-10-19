package com.mycompany.pharmacyapp;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;


public class Pharmacy extends JFrame {
    private static ArrayList<Drug> drugs = new ArrayList<>();
    private static int capacity;
    private static double totalSales;
    
    public void setCapacity(){
        JFrame frame = new JFrame();
        JTextField capacityField = new JTextField();
        JLabel capacityLabel = new JLabel("Enter pharmacy storage capacity:", JLabel.CENTER);
        capacityLabel.setFont(new Font("Courier", Font.PLAIN, 25));
        JButton submit = new JButton("SUBMIT");
        submit.setFont(new Font("Serif", Font.PLAIN, 30));
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(capacityLabel);
        panel.add(capacityField);
        panel.add(submit);
       
        
        frame.add(panel);
        frame.setTitle("Set Pharmacy capacity");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    capacity = Integer.parseInt(capacityField.getText());
                    JOptionPane.showMessageDialog(null, "Pharmacy capacity set successfully");
                    frame.dispose();
                    showMenu();
                }
                catch(NumberFormatException ee){
                    JOptionPane.showMessageDialog(null, "Invalid input. please enter a valid number.");
                }
            }
        });
        frame.setVisible(true);
    }
    
    public void AddDrug(){
        setTitle("Pharmacy System");
        
        JTextField name = new JTextField(30);
        JLabel nameLabel = new JLabel("Drug Name");
        JTextField id = new JTextField(10);
        JLabel idLabel = new JLabel("Drug ID");
        JTextField category = new JTextField(10);
        JLabel categoryLabel = new JLabel("Drug Category");
        JTextField price = new JTextField(10);
        JLabel priceLabel = new JLabel("Drug Price");
        JTextField quantity = new JTextField(10);
        JLabel quantityLabel = new JLabel("Drug Quantity");

        JButton submit = new JButton("Submit Drug");
        JButton setCapacityButton = new JButton("Set Capacity");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Enter information about drug"));        
        
        panel.add(nameLabel);
        panel.add(name);
        panel.add(new JLabel(""));
        panel.add(idLabel);
        panel.add(id);
        panel.add(new JLabel(""));
        panel.add(categoryLabel);
        panel.add(category);
        panel.add(new JLabel(""));
        panel.add(priceLabel);
        panel.add(price);
        panel.add(new JLabel(""));
        panel.add(quantityLabel);
        panel.add(quantity);
        panel.add(new JLabel(""));
        panel.add(submit);
        
        
        add(panel);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        submit.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               if(drugs.size() >= capacity){
                   JOptionPane.showMessageDialog(null, "Drugs are more than capacity");
                   dispose();
                   showMenu();
               }
               String dName = name.getText();
               int dId = Integer.parseInt(id.getText());
               double dPrice = Double.parseDouble(price.getText());
               String dCategory = category.getText();
               int dQuantity = Integer.parseInt(quantity.getText());
               Drug d = new Drug(dName, dId, dPrice, dCategory, dQuantity);
               drugs.add(d);
               
               try (FileWriter writer = new FileWriter("store.txt", true)){
                   writer.write(dName + "," + dId + "," + dPrice + "," + dCategory + "," + dQuantity + "\n");
               }catch(IOException w){
                   System.out.println("An error occured while writing to the file: " + w.getMessage());
               }  
               
               JOptionPane.showMessageDialog(null, "Drug has been added successfully");
               dispose();
               showMenu();
           }
        });
    }
    public void showMenu(){
        JFrame second = new JFrame();
        JButton btnAddDrug = new JButton("Add Drug");
        JButton btnRemove = new JButton("Remove Drug");
        JButton btnPlaceOrder = new JButton("Place an Order");
        JButton btnTotalsales = new JButton("GetTotal Sales");
        JButton btnExit = new JButton("Exit");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(btnAddDrug);
        panel.add(btnRemove);
        panel.add(btnPlaceOrder);
        panel.add(btnTotalsales);
        panel.add(btnExit);
        
        second.add(panel);
        second.setTitle("Pharmacy System");
        second.setSize(600, 400);
        second.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        second.setLocationRelativeTo(null);
        
        btnAddDrug.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                second.dispose();
                AddDrug();
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second.dispose();
                removeDrug();
            }
        });
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second.dispose();
                placeOrder();
            }
        });
        btnTotalsales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second.dispose();
                JOptionPane.showMessageDialog(null, "Total sales for the day is: " + totalSales);
                showMenu();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        second.setVisible(true);
    }
     public void removeDrug() {
        JFrame frame = new JFrame();
        JTextField drugId = new JTextField();
        JLabel drugIdLabel = new JLabel("Please enter drug ID");
        JButton submit = new JButton("Submit");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(drugIdLabel);
        panel.add(drugId);
        panel.add(submit);

        frame.add(panel);
        frame.setTitle("Remove Drug");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(drugId.getText());

                boolean removed = false;
                try (BufferedReader reader = new BufferedReader(new FileReader("store.txt"))) {
                    String line;
                    StringBuilder fileContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        int fileDrugId = Integer.parseInt(parts[1].trim());
                        if (fileDrugId == id) {
                            // Skip the line for the drug to be removed
                            removed = true;
                            continue;
                        }
                        fileContent.append(line).append("\n");
                    }
                    try (FileWriter writer = new FileWriter("store.txt")) {
                        writer.write(fileContent.toString());
                    }
                } catch (IOException w) {
                    System.out.println("An error occurred while reading or writing the file: " + w.getMessage());
                }

                if (removed) {
                    JOptionPane.showMessageDialog(null, "Drug is removed");
                } else {
                    JOptionPane.showMessageDialog(null, "Drug not found");
                }

                frame.dispose();
                showMenu();
            }
        });
        frame.setVisible(true);
    }
     public void placeOrder() {
        JFrame frame = new JFrame();
        JTextField drugId = new JTextField();
        JLabel drugIdLabel = new JLabel("Please enter drug ID to order");
        JButton submit = new JButton("Submit");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(drugIdLabel);
        panel.add(drugId);
        panel.add(submit);

        frame.add(panel);
        frame.setTitle("Place an Order");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(drugId.getText());
                Drug drug = null;
                int drugIndex = -1;
                try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Moslem\\Desktop\\PharmacyApp\\src\\main\\java\\com\\mycompany\\pharmacyapp\\store.txt"))) {
                    String line;
                    StringBuilder fileContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        int fileDrugId = Integer.parseInt(parts[1].trim());
                        if (fileDrugId == id) {
                            drug = new Drug(parts[0].trim(), fileDrugId, Double.parseDouble(parts[2].trim()), parts[3].trim(), Integer.parseInt(parts[4].trim()));
                            drugIndex = drugs.size() - 1;
                            drugs.add(drug);
                            continue;
                        }
                        fileContent.append(line).append("\n");
                    }

                    if (drug == null) {
                        JOptionPane.showMessageDialog(null, "Drug not found");
                        frame.dispose();
                        showMenu();
                    }

                    frame.dispose();
                    orderComplete(drug, drugIndex, fileContent);

                } catch (IOException w) {
                    System.out.println("An error occurred while reading the file: " + w.getMessage());
                }
            }

            
        });
        frame.setVisible(true);
    }
     public void orderComplete(Drug drug, int drugIndex, StringBuilder fileContent) {
        JFrame frame = new JFrame();
        JLabel foundLabel = new JLabel("Drug found: " + drug.getName());
        JLabel priceLabel = new JLabel("Unit price: " + drug.getPrice());
        JLabel marketLabel = new JLabel("Market price: " + drug.getPrice() * 1.2);
        JLabel orderLabel = new JLabel("Enter quantity to order: ");
        JButton orderButton = new JButton("Order");
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(foundLabel);
        panel.add(priceLabel);
        panel.add(marketLabel);
        panel.add(orderLabel);
        panel.add(quantityField);
        panel.add(orderButton);

        frame.add(panel);
        frame.setTitle("Order Complete");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quantityField.getText());
                totalSales += drug.getPrice()* 1.2  * quantity;
                if (quantity > drug.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Insufficient quantity in stock.");
                    frame.dispose();
                    showMenu();
                } else {
                    drug.setQuantity(drug.getQuantity() - quantity);
                    JOptionPane.showMessageDialog(null, "Order placed successfully.");

                    // Update the quantity of the drug in the "store.txt" file
                    try (FileWriter writer = new FileWriter("store.txt")) {
                        writer.write(fileContent.toString());
                        writer.write(drug.getName() + "," + drug.getId() + "," + drug.getPrice() + ","
                                + drug.getCategory() + "," + drug.getQuantity() + "\n");
                    } catch (IOException w) {
                        System.out.println("An error occurred while writing to the file: " + w.getMessage());
                    }

                    frame.dispose();
                    showMenu();
                }
            }
        });

        frame.setVisible(true);
    }
}
