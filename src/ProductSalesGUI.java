 import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

    public class ProductSalesGUI extends JFrame {
        private JButton btnLoad, btnSave;
        private JTextArea txtArea;
        private JLabel lblYears;
        private ProductSales productSales;
        private JMenuBar menuBar;
        private JMenu fileMenu, toolsMenu;
        private JMenuItem exitItem, loadItem, saveItem, clearItem;

        public ProductSalesGUI() {
            productSales = new ProductSales();
            initComponents();
        }

        private void initComponents() {
            setTitle("Product Sales Application");
            setSize(350, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());


            menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
            exitItem = new JMenuItem("Exit");
            fileMenu.add(exitItem);

            toolsMenu = new JMenu("Tools");
            loadItem = new JMenuItem("Load Product Data");
            saveItem = new JMenuItem("Save Product Data");
            clearItem = new JMenuItem("Clear");
            toolsMenu.add(loadItem);
            toolsMenu.add(saveItem);
            toolsMenu.add(clearItem);

            menuBar.add(fileMenu);
            menuBar.add(toolsMenu);
            setJMenuBar(menuBar);


            btnLoad = new JButton("Load Product Data");
            btnSave = new JButton("Save Product Data");
            btnLoad.setPreferredSize(new Dimension(250, 30));
            btnSave.setPreferredSize(new Dimension(250, 30));


            txtArea = new JTextArea(6, 25);
            txtArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(txtArea);


            lblYears = new JLabel("Years Processed: 0");
            lblYears.setPreferredSize(new Dimension(250, 20));

            add(btnLoad);
            add(btnSave);
            add(scroll);
            add(lblYears);


            btnLoad.addActionListener(e -> loadData());
            btnSave.addActionListener(e -> saveData());

            loadItem.addActionListener(e -> loadData());
            saveItem.addActionListener(e -> saveData());
            clearItem.addActionListener(e -> clearData());
            exitItem.addActionListener(e -> System.exit(0));
        }

        private void loadData() {
            int total = productSales.GetTotalSales();
            double avg = productSales.GetAverageSales();
            int over = productSales.GetSalesOverLimit();
            int under = productSales.GetSalesUnderLimit();
            int years = productSales.GetProductsProcessed();

            txtArea.setText("Total Sales: " + total + "\n"
                    + "Average Sales: " + Math.round(avg) + "\n"
                    + "Sales over limit: " + over + "\n"
                    + "Sales under limit: " + under);

            lblYears.setText("Years Processed: " + years);
        }

        private void saveData() {
            if (txtArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please load data first!");
                return;
            }
            try (FileWriter writer = new FileWriter("data.txt")) {
                writer.write("DATA LOG\n");
                writer.write("********************************\n");
                writer.write("Total Sales: " + productSales.GetTotalSales() + "\n");
                writer.write("Average Sales: " + Math.round(productSales.GetAverageSales()) + "\n");
                writer.write("Sales over limit: " + productSales.GetSalesOverLimit() + "\n");
                writer.write("Sales under limit: " + productSales.GetSalesUnderLimit() + "\n");
                writer.write("********************************\n");
                JOptionPane.showMessageDialog(this, "Data saved to data.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }

        private void clearData() {
            txtArea.setText("");
            lblYears.setText("Years Processed: 0");
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new ProductSalesGUI().setVisible(true));
        }
    }

