/*
Author: wolfeJob
Assignment: Ex_21-2
Date:11/19/2017 
Description: In this exercise, you’ll add a Clear button to the GUI version of the Future Value Calculator. 
*/

package future.fv; //class is located in this package
//All imports for frame data 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FutureValueFrame extends JFrame { //begin public child class inherits methods from jframe
//instance variables for frame class 

    private JTextField monthlyInvestmentField;
    private JTextField yearlyInterestRateField;
    private JTextField yearsField;
    private JTextField futureValueField;
    private JButton calculateButton;
    private JButton exitButton;
    private JButton clearButton;

    public FutureValueFrame() { ////begin public futureValueFrame 
        try { //begin try 
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |         //trys to catch the exception here.
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e); //will print 3 if error is caught 
        } //end catch 
        initComponents();//calling init components method
        setTitle("Future Value Calculator");//give our frame a title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close function for our box
        setLocationByPlatform(true);//boolean values
        setVisible(true);
    } //end public futureValueFrame

    private void initComponents() {  //creating field components for box
        monthlyInvestmentField = new JTextField();//component for monthlyinvestmentfield
        yearlyInterestRateField = new JTextField(); //component for yearlyInterestRateField
        yearsField = new JTextField();//component for years field
        futureValueField = new JTextField();//component for future value field

        futureValueField.setEditable(false);

        Dimension dim = new Dimension(150, 20); //setting dimensions for new dim object
        monthlyInvestmentField.setPreferredSize(dim); //passes dim, dimensions to this field for the size 
        yearlyInterestRateField.setPreferredSize(dim); 
        yearsField.setPreferredSize(dim);
        futureValueField.setPreferredSize(dim);
        monthlyInvestmentField.setMinimumSize(dim); 
        yearlyInterestRateField.setMinimumSize(dim);
        yearsField.setMinimumSize(dim);
        futureValueField.setMinimumSize(dim);

        calculateButton = new JButton("Calculate");//creating a new calculateButton
        exitButton = new JButton("Exit");//creating a new exit button 
        clearButton = new JButton("Clear"); //creating a new clear button 

        calculateButton.addActionListener((ActionEvent) -> {
            computeButtonClick();
        });
        exitButton.addActionListener((ActionEvent) -> {
            exitButtonClick();
        });
        clearButton.addActionListener((ActionEvent) -> { //added clearbutton action event 
            clearButtonClick();
        });

        //JLabel and JTextField panel
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.add(new JLabel("Monthly Investment:"),
                getConstraints(0, 0, GridBagConstraints.LINE_START));
        dataPanel.add(monthlyInvestmentField,
                getConstraints(1, 0, GridBagConstraints.LINE_START));
        dataPanel.add(new JLabel("Yearly Interest Rate:"),
                getConstraints(0, 1, GridBagConstraints.LINE_START));
        dataPanel.add(yearlyInterestRateField,
                getConstraints(1, 1, GridBagConstraints.LINE_START));
        dataPanel.add(new JLabel("Years:"),
                getConstraints(0, 2, GridBagConstraints.LINE_START));
        dataPanel.add(yearsField,
                getConstraints(1, 2, GridBagConstraints.LINE_START));
        dataPanel.add(new JLabel("Future Value:"),
                getConstraints(0, 3, GridBagConstraints.LINE_START));
        dataPanel.add(futureValueField,
                getConstraints(1, 3, GridBagConstraints.LINE_START));
        add(dataPanel, BorderLayout.CENTER);

        // JButton panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }//end method init components 

    // Helper method to return GridBagConstraints objects
    private GridBagConstraints getConstraints(int x, int y, int anchor) { // start private method called 
        GridBagConstraints c = new GridBagConstraints(); //creating a new instance of the Gridbagconstraints object named c 
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;//the x axis for c
        c.gridy = y;//the y axis for c
        c.anchor = anchor;//the anchor for c 
        return c; //returning c  
    }

    private void computeButtonClick() { //begin method computebuttonclick 
        double monthlyInvestment; //declaring a double named monthlyInvestment 
        double yearlyInterestRate;//declaring a double named yearlyInterestRate
        int years;//declaring an integer named years
        try {//begin a try catch block
            monthlyInvestment = Double.parseDouble(monthlyInvestmentField.getText());
            yearlyInterestRate = Double.parseDouble(yearlyInterestRateField.getText());
            years = Integer.parseInt(yearsField.getText());//years is equal to what the user enters 
            
            double futureValue = Financial.calculateFutureValue( monthlyInvestment, yearlyInterestRate, years);
            //Above we assign the double futureValue variable to equal the financial class, calculateFV method and pass 3 parameters
            
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            futureValueField.setText(currency.format(futureValue));
        } catch (NumberFormatException ex) {   //end the try and begin the catch
            JOptionPane.showMessageDialog(this,
                    "You have entered an invalid number. Please try again." + "\nError Message is \"" + ex.getMessage() + "\"",
                    "Invalid Number", JOptionPane.ERROR_MESSAGE);
        }//end catch 
    }//end compute button click method 

    private void exitButtonClick() { //start private method exitButtonClick
        System.exit(0);
    }//end private method exitButtonClick 
    
    private void clearButtonClick() { //start private method clearButtonClick 
        monthlyInvestmentField.setText("");//when clear is used, the monthlyinvestment text field is filled with this string
        yearlyInterestRateField.setText("");//when clear is used, the yearlyinterestrate text field is filled with this string 
        yearsField.setText("");//In this, we are calling rhe setText method, passing the string parameter of "" and applying it to our fields.
        futureValueField.setText("");
    }//end method for clearButonClick
}//end public child class 