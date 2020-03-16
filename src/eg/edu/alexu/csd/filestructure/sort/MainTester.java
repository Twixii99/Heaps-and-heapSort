package eg.edu.alexu.csd.filestructure.sort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainTester {
    public static void main(String[] args) throws IOException {
        // test heap sort performance
        Random random = new Random();
        ArrayList<Integer> arr;
        ArrayList<Integer> exp = new ArrayList<>();
        exp.add(0);
        for(int i = 0, dummy = 1; i < 8; ++i, dummy *= 10) exp.add(dummy);
        ArrayList<Long> data = new ArrayList<>();
        ISort<Integer> sort = new Sort<>();
        for(int i = 0; i < 9; ++i) {
            arr = new ArrayList<>();
            for(int j = 0; j < exp.get(i); ++j)
               arr.add(random.nextInt(Integer.MAX_VALUE));
            long time = System.currentTimeMillis();
            sort.heapSort(arr);
            time = (System.currentTimeMillis() - time);
            data.add(time);
        }
        FileWriter fileWriter = new FileWriter("Data.txt");
        for(Integer l : exp)
            fileWriter.write(l.toString() + System.lineSeparator());
        fileWriter.close();
        FileWriter fileWriter2 = new FileWriter("Time.txt");
        for(Long l : data)
            fileWriter2.write(l.toString() + System.lineSeparator());
        fileWriter2.close();
    }
}
