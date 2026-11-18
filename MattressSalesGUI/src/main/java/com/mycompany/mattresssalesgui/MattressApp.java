package com.mycompany.mattresssalesgui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;


public class MattressApp extends JFrame {
    // Q2.2: text box for the customer name, combo box for the product, text box for the price
    private final JTextField txtCustomer = new JTextField(15);
    private final JComboBox<String> cmbProduct = new JComboBox<>(
            new String[]{"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JTextField txtPrice = new JTextField(15);

    public MattressApp() {
        setTitle("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // The process button
        JButton btnProcess = new JButton("PROCESS");
        btnProcess.addActionListener(e -> processSale());

        // Labels and inputs in a grid, with the button under the price
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 15));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.add(new JLabel("Customer Name:"));
        form.add(txtCustomer);
        form.add(new JLabel("Select Product:"));
        form.add(cmbProduct);
        form.add(new JLabel("Enter Price:"));
        form.add(txtPrice);
        form.add(new JLabel(""));
        form.add(btnProcess);

        add(form, BorderLayout.CENTER);

        setJMenuBar(createMenuBar());
        pack();
        setLocationRelativeTo(null);
    }

    // Q2.4: File > Exit, Tools > Process, Clear
    private JMenuBar createMenuBar() {
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        JMenuItem clear = new JMenuItem("Clear");
        process.addActionListener(e -> processSale());   // same logic as the button
        clear.addActionListener(e -> clearForm());
        tools.add(process);
        tools.add(clear);

        JMenuBar bar = new JMenuBar();
        bar.add(file);
        bar.add(tools);
        return bar;
    }

    // Q2.3: capture the input, calculate, show the message box and save the file
    private void processSale() {
        String customer = txtCustomer.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();
        double price;

        // Stop if the price is not a number
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a number for the price.");
            return;
        }

        // Stop if the name is empty or the price is not above zero
        if (customer.isEmpty() || price <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Enter the customer name and a price above zero.");
            return;
        }

        // The interface type is used, the object is a ProductCalculations
        IProductCalculations calc = new ProductCalculations(price);

        // The report lines as shown in the sample
        String report = "Customer Name: " + customer + "\n"
                + "Product: " + product + "\n"
                + "VAT: R " + calc.GetVat() + "\n"
                + "COMMISSION: R " + calc.GetCommission() + "\n"
                + "TOTAL: R " + calc.GetTotal();

        // Show the report in a message box, then save it
        JOptionPane.showMessageDialog(this, report);
        saveData(report);
    }

    // Q2.3: saves the report to data.txt in the sample format
    private void saveData(String report) {
        String stars = "**********************************";
        try (FileWriter writer = new FileWriter("data.txt")) {
            writer.write("DATA LOG\n" + stars + "\n" + report + "\n" + stars + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save: " + ex.getMessage());
        }
    }

    // Q2.4: clears the text boxes and sets the combo box back to the first item
    private void clearForm() {
        txtCustomer.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MattressApp().setVisible(true));
    }
}

