import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

public class FileCreator
{
    JFrame frame;
    private JPanel FileCreator;
    private JButton okButton;
    private JTextField NumberOfFilesText;
    private JTextField ArraySizeText;
    private JTextField MinText;
    private JTextField MaxText;
    private JTextField FilesNameText;
    JFrame ParentFrame;

    FileHandler fileHandler;
    ArraysCreator arraysCreator;

    public void setParentFrame(JFrame ParentFrame)
    {
        this.ParentFrame = ParentFrame;
    }

    public FileCreator()
    {
        FileHandler fileHandler = new FileHandler();
        arraysCreator = new ArraysCreator();

        /*
        frame.addWindowListener(new WindowAdapter()  // enable main window when closing
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                ParentFrame.setEnabled(true);
            }
        });
*/
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int amount = Integer.parseInt(NumberOfFilesText.getText());
                int arraySize = Integer.parseInt(ArraySizeText.getText());
                int min = Integer.parseInt(MinText.getText());
                int max = Integer.parseInt(MaxText.getText());

                ArrayList<ArrayList<Integer>> arrays = arraysCreator.CreateMultipleArrayLists(amount, arraySize, min, max);
                fileHandler.SaveToMultipleFiles(arrays, "input", FilesNameText.getText());

                System.out.println("\nfiles list:\n");
                ArrayList<String> filesList = fileHandler.GetFilesList("./input");

                String filesListStr = "\n";

                for (String fileName : filesList)
                {
                    filesListStr = filesListStr + "\n" + fileName;
                    System.out.println(fileName);
                }

                JOptionPane.showMessageDialog(frame, "Current files list" + filesListStr, "Files list", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void FileCreatorShow()
    {
        frame = new JFrame("File Creator");
        frame.setContentPane(new FileCreator().FileCreator);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
