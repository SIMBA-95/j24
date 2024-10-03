import java.awt.*;
import java.awt.event.*;

public class Eg1 extends Frame implements ActionListener {

    Label labelInput, labelResult, labelCurrency;
    TextField textInput, textResult;
    Button[] numberButtons = new Button[10]; // Buttons for 0-9
    Button convertButton, clearButton, exitButton;
    Choice currencyChoice;
    String enteredAmountStr = ""; // Handle input as a string

    // Conversion rates
    double rateUSD = 0.012;   // INR to USD
    double rateEUR = 0.011;   // INR to EUR
    double rateKRW = 15.91;   // INR to KRW
    double ratePKR = 3.31;    // INR to PKR
    double rateJPY = 1.65;    // INR to JPY (example rate)

    public Eg1() {
        setLayout(null); // Set layout to null for absolute positioning

        // Set background color of the frame to light purple
        setBackground(new Color(215, 189, 226)); // A light purple color

        // Input label and text field for entered amount
        labelInput = new Label("Entered INR: ");
        labelInput.setBounds(30, 50, 100, 30); // Set position
        add(labelInput);

        textInput = new TextField(10);
        textInput.setEditable(false);
        textInput.setBounds(150, 50, 200, 30);
        textInput.setBackground(Color.white);
        textInput.setFont(new Font("Arial", Font.BOLD, 18)); // Make the text look like a calculator
        add(textInput);

        // Result label and text field for the converted result
        labelResult = new Label("Converted Amount: ");
        labelResult.setBounds(30, 90, 120, 30);
        add(labelResult);

        textResult = new TextField(15);
        textResult.setEditable(false);
        textResult.setBounds(150, 90, 200, 30);
        textResult.setBackground(Color.white);
        textResult.setFont(new Font("Britannic", Font.BOLD, 18)); // Make the text bold
        add(textResult);

        // Number buttons for entering amounts (0 to 9)
        int x = 30, y = 140; // Coordinates for positioning buttons
        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setBounds(x, y, 50, 50); // Set size and position
            numberButtons[i].setBackground(new Color(165, 105, 189)); // Dark purple for buttons
            add(numberButtons[i]);
            numberButtons[i].addActionListener(this);
            x += 60; // Move to the next button
            if (i % 3 == 0) { // Move to the next row after 3 buttons
                x = 30;
                y += 60;
            }
        }

        numberButtons[0] = new Button("0");
        numberButtons[0].setBounds(90, y + 10, 50, 50); // Center it with minimal space below
        numberButtons[0].setBackground(new Color(165, 105, 189));
        add(numberButtons[0]);
        numberButtons[0].addActionListener(this);

        // Dropdown choice for selecting the currency
        labelCurrency = new Label("Select Currency: ");
        labelCurrency.setBounds(30, y + 70, 120, 30); // Adjust position based on last button
        add(labelCurrency);

        currencyChoice = new Choice();
        currencyChoice.add("USD");
        currencyChoice.add("EUR");
        currencyChoice.add("KRW");
        currencyChoice.add("PKR");
        currencyChoice.add("JPY"); // Added Japanese Yen
        currencyChoice.setBounds(150, y + 70, 200, 30);
        add(currencyChoice);

        // Convert button to trigger the currency conversion
        convertButton = new Button("Convert");
        convertButton.setBounds(30, y + 100, 100, 50);
        convertButton.setBackground(new Color(236, 240, 241)); // White for the convert button
        convertButton.setForeground(Color.black);
        add(convertButton);
        convertButton.addActionListener(this);

        // Clear button to clear the entered amount
        clearButton = new Button("Clear");
        clearButton.setBounds(150, y + 100, 100, 50);
        clearButton.setBackground(new Color(236, 240, 241)); // White for the clear button
        clearButton.setForeground(Color.black);
        add(clearButton);
        clearButton.addActionListener(this);

        // Exit button to close the application
        exitButton = new Button("Exit");
        exitButton.setBounds(270, y + 100, 100, 50);
        exitButton.setBackground(new Color(231, 76, 60)); // Red for exit button
        exitButton.setForeground(Color.black);
        add(exitButton);
        exitButton.addActionListener(this);

        // Frame properties
        setTitle("Currency Converter");
        setSize(400, 500);
        setVisible(true);

        // Adding window listener for confirmation on exit
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            dispose(); // Close the window
        }

        // Handle number buttons input as string concatenation
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                enteredAmountStr += i; // Append digit to string
                textInput.setText(enteredAmountStr);
            }
        }

        // Handle currency conversion
        if (e.getSource() == convertButton) {
            if (!enteredAmountStr.isEmpty()) {
                int enteredAmount = Integer.parseInt(enteredAmountStr); // Convert string to integer
                String selectedCurrency = currencyChoice.getSelectedItem();
                double rate = 0;
                String currencySymbol = "";

                // Using if-else block to get conversion rate
                if (selectedCurrency.equals("USD")) {
                    rate = rateUSD;
                    currencySymbol = "USD";
                } else if (selectedCurrency.equals("EUR")) {
                    rate = rateEUR;
                    currencySymbol = "EUR";
                } else if (selectedCurrency.equals("KRW")) {
                    rate = rateKRW;
                    currencySymbol = "KRW";
                } else if (selectedCurrency.equals("PKR")) {
                    rate = ratePKR;
                    currencySymbol = "PKR";
                } else if (selectedCurrency.equals("JPY")) {
                    rate = rateJPY;
                    currencySymbol = "JPY";
                }

                double convertedAmount = enteredAmount * rate;
                textResult.setText(String.format("%.2f %s", convertedAmount, currencySymbol));
            } else {
                textResult.setText("Please enter a valid amount");
            }
        }

        // Clear the input field and reset enteredAmountStr
        if (e.getSource() == clearButton) {
            enteredAmountStr = "";
            textInput.setText("");
            textResult.setText("");
        }
    }

    public static void main(String[] args) {
        new Eg1();
    }
}
