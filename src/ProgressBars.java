import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgressBars
{
    static JFrame frame;
    private JPanel MainPanel;
    private JProgressBar Thread0Progress;
    private JProgressBar Thread1Progress;
    private JProgressBar Thread2Progress;
    private JProgressBar Thread3Progress;
    private JButton buttonStart;
    private JButton createFilesButton;
    private JLabel label0;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton deleteInputFilesButton;
    private JButton deleteOutputFilesButton;

    ProgressBars This = this;
    int threadsFinished;

    public void ShowMessageIfDone()     // shows messagebox if all threads are finished
    {
        threadsFinished++;
        if(threadsFinished==4)
            JOptionPane.showMessageDialog(frame, "All arrays have been sorted", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public ProgressBars()
    {
        ArrayList<JProgressBar> bars = new ArrayList<>();
        bars.add(Thread0Progress);
        bars.add(Thread1Progress);
        bars.add(Thread2Progress);
        bars.add(Thread3Progress);

        ArrayList<JLabel> labels = new ArrayList<>();
        labels.add(label0);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);

        FileHandler fileHandler = new FileHandler();

        buttonStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                threadsFinished = 0;
                ArrayList<String> filesList = fileHandler.GetFilesList("./input");
                ArrayList<SelectionSortThread> threads = new ArrayList<>();
                for(int i = 0; i<4; i++)
                {
                    threads.add(new SelectionSortThread(i, filesList, bars.get(i), labels.get(i), This));
                    threads.get(i).start();
                }
            }
        });

        createFilesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FileCreator fileCreator = new FileCreator();
                fileCreator.FileCreatorShow();
            //  fileCreator.setParentFrame(frame);
            //  frame.setEnabled(false);
            /*
                Scanner scanner = new Scanner(System.in);
                System.out.println("Number of files: ");
                int amount = scanner.nextInt();
                System.out.println("Array size: ");
                int arraySize = scanner.nextInt();
                System.out.println("Minimum value: ");
                int min  = scanner.nextInt();
                System.out.println("Maximum value: ");
                int max  = scanner.nextInt();
                System.out.println("Files name (without suffix): ");
                String names  = scanner.next();
                System.out.println("");

                ArrayList<ArrayList<Integer>> arrays = arraysCreator.CreateMultipleArrayLists(amount, arraySize, min, max);
                fileHandler.SaveToMultipleFiles(arrays, "input", names);

                System.out.println("\nfiles list:\n");
                ArrayList<String> filesList = fileHandler.GetFilesList("./input");

                for(int i = 0; i < filesList.size(); i++)
                    System.out.println(filesList.get(i));
            */
            }
        });
        deleteInputFilesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(fileHandler.DeleteFilesFrom("./input"))
                    JOptionPane.showMessageDialog(frame, "All input files deleted", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "Some input files couldn't be deleted", "Operation failed", JOptionPane.WARNING_MESSAGE);
            }
        });
        deleteOutputFilesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(fileHandler.DeleteFilesFrom("./output"))
                    JOptionPane.showMessageDialog(frame, "All output files deleted", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "Some output files couldn't be deleted", "Operation failed", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    //public void createWindow()
    public static void main(String[] args)
    {
        frame = new JFrame("Folder Sorter");
        frame.setContentPane(new ProgressBars().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
