import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddTaskForm extends JFrame {
    JPanel panel=new JPanel();

    JTextField nameField=new JTextField();

    JTextField yearField=new JTextField();
    JTextField monthField=new JTextField();
    JTextField dayField=new JTextField();

    JButton addButton=new JButton("Add task");
    JButton cancelButton=new JButton("Cancel");

    AddTaskForm()
    {
        nameField.setToolTipText("Name");
        nameField.setPreferredSize(new Dimension(100,25));
        yearField.setToolTipText("Year");
        yearField.setPreferredSize(new Dimension(100,25));
        monthField.setToolTipText("Month");
        monthField.setPreferredSize(new Dimension(100,25));
        dayField.setToolTipText("Day");
        dayField.setPreferredSize(new Dimension(100,25));

        setSize(300,300);
        panel.setLayout(new GridLayout(5,2));
        add(panel);

        panel.add(nameField);
        panel.add(new JLabel("Task Name"));

        panel.add(yearField);
        panel.add(new JLabel("Year"));

        panel.add(monthField);
        panel.add(new JLabel("Month"));

        panel.add(dayField);
        panel.add(new JLabel("Day"));

        panel.add(addButton);
        panel.add(cancelButton);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task=new Task(nameField.getText(), LocalDate.now(),LocalDate.of(Integer.parseInt(yearField.getText()),Integer.parseInt(monthField.getText()),Integer.parseInt(dayField.getText())));
                Main.taskListProgram.listOfTasks.add(task);
                TaskPage.addTasksToTaskPanel((ArrayList<Task>) Main.taskListProgram.listOfTasks);



                try {
                    FileOutputStream fileOut = new FileOutputStream("tasks.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(Main.taskListProgram);
                    out.close();
                    fileOut.close();
                    System.out.printf("Serialized data is saved in /tmp/tasks.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }




                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }

}
