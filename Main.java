import java.sql.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements ActionListener, ChangeListener
{
    private JTextField userField;
    private JTextField mpgField;
    private JTextField horsepowerField;
    private JLabel userLabel;
    private JLabel mpgLabel;
    private JLabel horsepowerLabel;
    private JButton findButton;
    private JSlider mpgSlider;
    private JSlider horsepowerSlider;

    Main()
    {
        int mpgMin = 9;
        int mpgMax = 47;
        int mpgInit = 13;
        int horsepowerMin = 46;
        int horsepowerMax = 230;
        int horsepowerInit = 165;
        GridBagConstraints layoutConst = null;

        setTitle("Auto");

        userLabel = new JLabel("Find: ");
        mpgLabel = new JLabel("mpg: ");
        horsepowerLabel = new JLabel("horsepower: ");

        mpgField = new JTextField(5);
        mpgField.setEditable(true);
        mpgField.setText("13");

        horsepowerField = new JTextField(5);
        horsepowerField.setEditable(false);
        horsepowerField.setText("165");

        findButton = new JButton("Find");
        findButton.addActionListener(this);

        mpgSlider = new JSlider(mpgMin, mpgMax, mpgInit);
        mpgSlider.addChangeListener(this); // Use HeightConverter's stateChanged()
        mpgSlider.setMajorTickSpacing(10);
        mpgSlider.setMinorTickSpacing(1);
        mpgSlider.setPaintTicks(true);

        horsepowerSlider = new JSlider(horsepowerMin, horsepowerMax, horsepowerInit);
        horsepowerSlider.addChangeListener(this); // Use HeightConverter's stateChanged()
        horsepowerSlider.setMajorTickSpacing(10);
        horsepowerSlider.setMinorTickSpacing(1);
        horsepowerSlider.setPaintTicks(true);

        userField = new JTextField(5);
        userField.setEditable(true);

        setLayout(new GridBagLayout());

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 1);
        layoutConst.anchor = GridBagConstraints.LINE_START;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(mpgLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 1);
        layoutConst.anchor = GridBagConstraints.LINE_START;
        layoutConst.gridx = 2;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(horsepowerLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 1, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(mpgField, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 3;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(horsepowerField, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.gridwidth = 2;
        add(mpgSlider, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 1;
        layoutConst.gridwidth = 2;
        add(horsepowerSlider, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 5);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 1;
        add(findButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 1;
        add(userLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 2;
        add(userField, layoutConst);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        String userText = userField.getText();

        if (userText.equalsIgnoreCase("all"))
        {
            try
            {
                getAllResults();
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        }
        else if (userText.equalsIgnoreCase("find"))
        {
            String mpg = mpgField.getText();
            String horsepower = horsepowerField.getText();

            if (mpg.length() == 2 || mpg.length() == 1)
                mpg = mpg + ".0";

            if (horsepower.length() == 2)
                horsepower = horsepower + ".00";
            else if (horsepower.length() == 3)
                horsepower = horsepower + ".0";

            try
            {
                getSpecificResults(mpg, horsepower);
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent event)
    {
        int sliderVal;
        String strSliderVal;

        JSlider sourceEvent = (JSlider) event.getSource();

        if (sourceEvent == mpgSlider) {
            sliderVal = mpgSlider.getValue();      // Get slider value
            strSliderVal = Integer.toString(sliderVal); // Convert to int
            mpgField.setText(strSliderVal);        // Update display
        }
        else if (sourceEvent == horsepowerSlider) {
            sliderVal = horsepowerSlider.getValue();
            strSliderVal = Integer.toString(sliderVal);
            horsepowerField.setText(strSliderVal);
        }
    }

    public void getAllResults() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/Auto", "testuser", "greatbutter494");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery
                ("select * from mpg;");

        while (resultSet.next())
        {
            System.out.println(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" +
                    resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" +
                    resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" +
                    resultSet.getString(8) + "\t" + resultSet.getString(9));
        }
        connection.close();
    }

    public void getSpecificResults(String mpg, String horsepower) throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/Auto", "testuser", "greatbutter494");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery
                ("select * from mpg where mpg = '" + mpg + "' and horsepower = '" + horsepower + "';");

        while (resultSet.next())
        {
            System.out.println(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" +
                    resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" +
                    resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" +
                    resultSet.getString(8) + "\t" + resultSet.getString(9));
        }
        connection.close();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException
    {
        FileInputStream fileByteStream = new FileInputStream("data.txt");
        Scanner scnr = new Scanner(fileByteStream);
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/Auto", "testuser", "greatbutter494");
        System.out.println("Database connected");

        Statement statement = connection.createStatement();


        for (int i = 0; i < 406; i++)
        {
            String line = scnr.nextLine();
            String[] arr = line.split("\"");
            String[] secondArr = arr[0].split("\\s{2,}");

            if (secondArr[0].equals("NA"))
                secondArr[0] = null;
            if (secondArr[3].equals("NA"))
                secondArr[3] = null;

            String temp = "INSERT INTO mpg (mpg, cylinder, displacement, horsepower, weight, " +
                    "acceleration, year, origin, name)" + "VALUES ('" + secondArr[0] + "','" + secondArr[1] +
                    "','" + secondArr[2] + "','" + secondArr[3] + "','" + secondArr[4] + "','" + secondArr[5] + "','"
                    + secondArr[6] + "','" + secondArr[7] + "','" + arr[1] + "');";
            statement.executeUpdate(temp);
        }

        fileByteStream.close();
        connection.close();

        Main myFrame = new Main();

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}