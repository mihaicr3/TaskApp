import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class TaskPage extends JFrame {

    public JPanel panelAddButton=new JPanel();
    public static JPanel  panelTasks=new JPanel();

    public TaskPage()
    {
        setTitle("Tasks");
        JButton addTask=new JButton("Add task");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelAddButton.add(addTask);
        panelAddButton.setPreferredSize(new Dimension(400,50));
        panelAddButton.setMaximumSize(new Dimension(400,50));

        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTaskForm().setVisible(true);
            }
        });


        addTask.setSize(400,50);
        addTask.setPreferredSize(new Dimension(400,50));
        addTask.setMaximumSize(new Dimension(400,50));

        add(panelAddButton);
        setSize(400,500);
        setResizable(false);

        panelTasks.setLayout(new BoxLayout(panelTasks,BoxLayout.Y_AXIS));
        panelTasks.setSize(400,400);
        addTasksToTaskPanel((ArrayList<Task>) Main.taskListProgram.listOfTasks);


        JScrollPane scrollPane=new JScrollPane(panelTasks);
        add(scrollPane);
        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));



    }

    static void addTasksToTaskPanel(ArrayList<Task> taskList)
    {
        panelTasks.removeAll();
        for(Task task : taskList)
        {
            JPanel panel=new JPanel();

            panel.setMinimumSize(new Dimension(400,50));
            panel.setMaximumSize(new Dimension(10000,50));
            JButton button=new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelTasks.remove(panel);
                    panelTasks.revalidate();
                    panelTasks.repaint();
                    //// Could not work yet- check later, if it works delete comment
                    taskList.remove(task);

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
                }
            });

            button.setBackground(Color.GREEN);
            button.setForeground(Color.GREEN);
            button.setPreferredSize(new Dimension(25,25));

            panel.setLayout(new FlowLayout(FlowLayout.LEFT));

            panel.add(new JLabel(task.getName()));
            panel.add(Box.createHorizontalStrut(10));
            panel.add(new JLabel(task.getDateAdded().toString()));
            panel.add(Box.createHorizontalStrut(10));


            if(DAYS.between(task.getDeadlineDate(), LocalDate.now())>1)
                panel.add(new JLabel(task.getDeadlineDate().toString())).setForeground(Color.RED);
            else
            if(DAYS.between(task.getDeadlineDate(),LocalDate.now())==0)
                panel.add(new JLabel(task.getDeadlineDate().toString())).setForeground(Color.ORANGE);
            else
            if(DAYS.between(task.getDeadlineDate(),LocalDate.now())<0)
                panel.add(new JLabel(task.getDeadlineDate().toString())).setForeground(Color.GREEN);




            panel.add(Box.createHorizontalStrut(10));
            panel.add(button);

            panelTasks.add(panel);
            panelTasks.revalidate();
            panelTasks.repaint();


        }
    }
}
