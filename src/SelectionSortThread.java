
import javax.swing.*;
import java.util.ArrayList;

public class SelectionSortThread extends Thread
{
    int ID;
    int progress;
    int max;
    ArrayList<String> filesList;
    FileHandler fileHandler;
    String Filename;

    JProgressBar progressBar;
    JLabel label;
    ProgressBars ContentPane;
    boolean writeToConsole = false;

    public SelectionSortThread(int ID, ArrayList<String> filesList, JProgressBar progressBar, JLabel label, ProgressBars ContentPane)
    {
        this.ID = ID;
        this.filesList = filesList;
        this.progressBar = progressBar;
        this.label = label;
        this.fileHandler = new FileHandler();
        this.ContentPane = ContentPane;
    }

    public ArrayList<Integer> SelectionSort(ArrayList<Integer> array)
    {
        max = array.size();
        progressBar.setMaximum(max);
        progressBar.setStringPainted(true);
        label.setText("Sorting file " + Filename);
        int percent=0;

        ArrayList<Integer> returned = new ArrayList<>();

        while(array.size()!=0)
        {
            int smallest = array.get(0);
            int smallest_index = 0;
            for(int j=0; j<array.size(); j++)
            {
                if(array.get(j)<smallest)
                {
                    smallest = array.get(j);
                    smallest_index = j;
                }
            }

            smallest = array.remove(smallest_index);
            returned.add(smallest);
            progress = returned.size();
            progressBar.setValue(progress);

            if(returned.size()*100/max>percent)
            {
                percent++;
                if(writeToConsole) System.out.println("Thread " + ID + " - " + percent +"%");
            }
        }
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        label.setText("Idle");
        return returned;
    }

    @Override
    public void run()
    {
        while(filesList.size()!=0)
        {
            Filename = filesList.remove(0);
            if(writeToConsole) System.out.println("Thread " + ID + " is assigned to file " + Filename);
            ArrayList<Integer> array = fileHandler.ReadFromFile("input", Filename);

            array = SelectionSort(array);

            if(writeToConsole) System.out.println("Process " + ID + " completed sorting array from file " + Filename);
            fileHandler.SaveToFile(array,"output", "sorted_" + Filename);
        }
        ContentPane.ShowMessageIfDone();    // shows messagebox if all threads are finished
    }
}
