import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandler
{
    boolean writeToConsole = false;

    public void SaveToFile(ArrayList<Integer> array, String directory, String fileName)
    {
        File file = new File("./" + directory + "/"+fileName);
        file.getParentFile().mkdirs();

        try
        {
            if (!file.exists())
                file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int i : array)
                writer.write(i + "\n");

            if(writeToConsole) System.out.println("Saved "+ file.getPath());
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void SaveToMultipleFiles(ArrayList<ArrayList<Integer>>arrays, String directory, String fileName)
    {
        int i=0;

        for(ArrayList<Integer> array : arrays)
            SaveToFile(array, directory, fileName + "_" + i++ + ".txt");
    }

    public void SaveToMultipleFiles(ArrayList<ArrayList<Integer>>arrays, String directory, ArrayList<String> files, String prefix)
    {
        for(int i=0;i<arrays.size();i++)
            SaveToFile(arrays.get(i), directory,  prefix + "_" + files.get(i));
    }

    public ArrayList<String> GetFilesList(String directory)
    {
        File path = new File(directory);
        ArrayList<String> FileNames = new ArrayList<>(Arrays.asList(path.list()));
        return FileNames;
    }

    public ArrayList<Integer> ReadFromFile(String directory, String fileName)
    {
        ArrayList<Integer> returned = new ArrayList<>();
        File file = new File("./" + directory + "/" + fileName);
        String line;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null)
                returned.add(Integer.parseInt(line));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return returned;
    }

    public ArrayList<ArrayList<Integer>> ReadFromMultipleFile(String directory, ArrayList<String> files) throws IOException
    {
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();

        for(String file : files)
            arrays.add(ReadFromFile(directory, file));

        return arrays;
    }

    public boolean DeleteFilesFrom(String directory)
    {
        boolean success = true;
        File path = new File(directory);
        ArrayList<File> Files = new ArrayList<>(Arrays.asList(path.listFiles()));
        for (File file : Files)
            if(!file.delete()) success = false;

        return success;
    }
}
