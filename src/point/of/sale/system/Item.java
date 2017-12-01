package point.of.sale.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Item implements Serializable {

    static File file = new File("/Users/hunk/Inventory List.txt");

    private static ArrayList<String> name;
    private ArrayList<Double> price;
    private static ArrayList<Integer> inventory = new ArrayList<Integer>();
    private static final String[] itemNameList = {"APPLE", "BANANA", "CHERRY"};

    public Item() {

        name = new ArrayList<String>();
        name.add("apple");
        name.add("banana");
        name.add("cherry");

        price = new ArrayList<Double>();
        price.add(3D);
        price.add(2D);
        price.add(5D);

    }

    public static void getInvertoryFromFile() {

        try {
            inventory.clear();
            int id = 0;

            BufferedReader in = new BufferedReader(new FileReader(file));

            String s = "";

            while ((s = in.readLine()) != null) {

                Integer itemInventory = Integer.parseInt(s);

                /**
                 * Update inventory with supplier automatically Add 20 whenever
                 * surplus less than 10
                 */
                if (itemInventory < 10) {
                    itemInventory += 20;

                    JOptionPane.showMessageDialog(null, "Supplier has updated "
                            + itemNameList[id]
                            + " by 20",
                            "SYSTEM_MESSAGE", JOptionPane.INFORMATION_MESSAGE);

                }

                inventory.add(itemInventory);
                id++;
            }

            in.close();
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        } catch (NumberFormatException e2) {
            System.out.println("Format is wrong!");
        }
    }

    public static void updateInventoryToFile() {
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            System.out.println(inventory.size());

            for (int i = 0; i < inventory.size(); i++) {

                out.write(inventory.get(i) + "\n");
            }

            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saleItem(int n, int reduceNumber) {

        int currentNumber = inventory.get(n) - reduceNumber;
        inventory.set(n, currentNumber);
    }

    public void returnItem(int n, int increaseNumber) {

        int currentNumber = inventory.get(n) + increaseNumber;
        inventory.set(n, currentNumber);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < name.size(); i++) {

            String s1 = "Item Name: " + name.get(i);
            String s2 = "Price: " + price.get(i);
            String s3 = "Surplus: " + inventory.get(i);

            sb.append(s1 + "  " + s2 + "  " + s3 + "\n");
        }
        return sb.toString();
    }

}
