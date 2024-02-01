import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static TaskList taskListProgram=new TaskList();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    FileInputStream fileIn = new FileInputStream("tasks.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    taskListProgram = (TaskList) in.readObject();

                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();

                } catch (ClassNotFoundException c) {
                    System.out.println("Employee class not found");
                    c.printStackTrace();

                }

                new TaskPage().setVisible(true);

              }
        });
    }
}