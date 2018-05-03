import java.util.ArrayList;
import java.util.Random;

public class ArraysCreator
{
    public ArrayList<Integer> CreateArrayList(int arraySize, int min, int max)
    {
        ArrayList<Integer>array = new ArrayList<>();
        for(int i=0;i<arraySize;i++)
        {
            Random random = new Random();
            array.add(random.nextInt(max+1-min)+min);
        }
        return array;
    }

    public ArrayList<ArrayList<Integer>> CreateMultipleArrayLists(int amount, int arraySize, int min, int max)
    {
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();
        for(int i=0; i<amount; i++)
            arrays.add(CreateArrayList(arraySize, min, max));

        return arrays;
    }
}
