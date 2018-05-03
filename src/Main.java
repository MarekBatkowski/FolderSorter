import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void PrintMenu()
    {
        System.out.println("");
        System.out.println("1 - create new input files");
        System.out.println("2 - sort arrays in existing files");
        System.out.println("3 - exit");
    }

    public static void main(String[] args)
    {
        int option = 0; // 1 - create new input files 2 - sort arrays in existing files 3 - exit
        FileHandler fileHandler = new FileHandler();
        ArraysCreator arraysCreator = new ArraysCreator();
        Scanner scanner = new Scanner(System.in);

        while(option!=3)
        {
            PrintMenu();
            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                {
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

                    fileHandler = new FileHandler();
                    ArrayList<ArrayList<Integer>> arrays = arraysCreator.CreateMultipleArrayLists(amount, arraySize, min, max);
                    fileHandler.SaveToMultipleFiles(arrays, "input", names);

                    System.out.println("\nfiles list:\n");
                    ArrayList<String> filesList = fileHandler.GetFilesList("./input");

                    for (int i = 0; i < filesList.size(); i++)
                        System.out.println(filesList.get(i));
                }
                break;

                case 2:
                {
                //    ProgressBars progressBars = new ProgressBars();
                //   progressBars.createWindow();
                //    progressBars.setThreads(threads);

                    /*
                    ArrayList<String> filesList = fileHandler.GetFilesList("./input");
                    ArrayList<SelectionSortThread> threads = new ArrayList<>();
                    for (int i = 0; i<4; i++)
                    {
                        threads.add(new SelectionSortThread(i, filesList));
                        threads.get(i).start();
                    }

                    for (int i = 0; i<4; i++)
                    {
                        try
                        {
                            threads.get(i).join();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }*/
                }
                break;

                case 3:
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }
}


