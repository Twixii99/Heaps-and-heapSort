package eg.edu.alexu.csd.filestructure.sort;

import java.util.*;

public class Sort<T extends Comparable<T>> implements ISort<T> {
    /**
     * Sorts the given collection of elements using heap-sort algorithm in-place,
     * and returns a clone of the complete heap that you constructed during
     * the sorting, and before you empty it.
     * Runs in O(n lg n) time
     *
     * @param unordered unordered elements
     * @return heap structure used
     */
    @Override
    public IHeap heapSort(ArrayList unordered) {
        Heap<T> heapx = new Heap<>();
        if(unordered == null || unordered.size() == 0)
            return heapx;
        heapx.build(unordered);
        for(int i = heapx.size()-1; i > 0; --i) {
            heapx.swap(i, 0);
            heapx.setHeapsize(heapx.size()-1);
            heapx.heapify(heapx.getHeap()[0]);
        }
        heapx.setHeapsize(unordered.size());
        return heapx;
    }

    /**
     * Sorts the given collection of elements using any O(n^2) algorithm in-place
     *
     * @param unordered unordered elements
     */
    @Override
    public void sortSlow(ArrayList unordered) {
        if(!this.getCheck(unordered))
            return;
        int k;
        for(int i = 0; i < unordered.size(); ++i) {
            k = i;
            for(int j = i+1; j < unordered.size(); ++j) {
                if(((T)(unordered.get(j))).compareTo((T)(unordered.get(k))) < 0) {
                    k = j;
                }
            }
            swap(unordered, i, k);
        }
    }

    private void swap(ArrayList dummy, int j, int k) {
        T val = (T)dummy.get(j);
        dummy.set(j,dummy.get(k));
        dummy.set(k,val);
    }

    /**
     * Sorts the given collection of elements using any O(n lg n) algorithm in-place
     *
     * @param unordered unordered elements
     */
    @Override
    public void sortFast(ArrayList unordered) {
        if(!this.getCheck(unordered))
            return;
        arrMergeSort2(unordered, 0, unordered.size()-1);
    }


    public Boolean getCheck(Collection collections) {
        if(collections == null || collections.isEmpty())
            return false;
        return true;
    }

    private void arrMerge(ArrayList list, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        Comparable[] L = new Comparable[n1];
        Comparable[] R = new Comparable[n2];

        for (int i = 0; i < n1; ++i)
            //L[i] = input[start + i];
            L[i] = (T)list.get(start+i);
        for (int j=0; j < n2; ++j)
            //R[j] = input[mid + 1 + j];
            R[j] = (T)list.get(mid+1+j);

        int i = 0, j = 0, k = start;

        while (i < n1 && j < n2)
        {
            if (((T)L[i]).compareTo((T)R[j]) <= 0)
            {
                list.set(k,(T)L[i]);
                i++;
            }
            else
            {
                list.set(k,(T)R[j]);
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            list.set(k,(T)L[i]);
            i++;
            k++;
        }

        while (j < n2)
        {
            list.set(k,(T)R[j]);
            j++;
            k++;
        }
    }

    private void arrMergeSort2(ArrayList list, int start, int end) {
        if (start < end) {
            int mid = (start+end)/2;
            arrMergeSort2(list, start, mid);
            arrMergeSort2(list, mid + 1, end);
            arrMerge(list, start, mid, end);
        }
    }
}
