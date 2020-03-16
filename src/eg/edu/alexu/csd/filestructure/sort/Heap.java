package eg.edu.alexu.csd.filestructure.sort;

import java.util.*;

public class Heap <T extends Comparable<T>> implements IHeap<T> {
    private INode<T> [] heap;
    private int heapsize =0;

    public Heap() {
        this.heap = new Node[10000000];
      }

    @Override

    public INode getRoot() {
        if(heap == null || heapsize == 0)
            return null;
        else return heap[0];
    }

    @Override
    public int size() {
        return heapsize;
    }

    @Override
    public void heapify(INode node) {
        if(node == null)
            return;
         INode largest = null;
         INode left = node.getLeftChild();
         INode right= node.getRightChild();
        if(left != null && ((Node)left).index  < heapsize && left.getValue().compareTo(node.getValue()) > 0){
            largest = left;
        }else
            largest = node;
        if(right != null && ((Node)right).index < heapsize && right.getValue().compareTo(largest.getValue()) > 0){
            largest = right;
        }
        if(largest != node){
             swap(((Node)largest).index,((Node)node).index);
             heapify(largest);
        }
    }

    @Override
    public T extract() {

        //No inistialized heap
        if(heap == null)
            return null;
        //No elements
        if(heapsize == 0)
            return null;
        T target = heap[0].getValue();
        //Just one node
        if(heapsize == 1){
            heapsize--;
            return target;
        }
        swap(0,heapsize-1);
        heapsize--;
        heapify(heap[0]);
        return target;
    }


    @Override
    public void insert(Comparable element) {
        if(element == null)
            return;
         INode newnode = new Node(element);
          heap[heapsize++] = newnode;
        ((Node)newnode).index = heapsize-1;
          if(heapsize == 1)
              return;
          int parentIndex = (int)Math.floor((heapsize-2)/2);
        ((Node)newnode).setParent(heap[parentIndex]);
          if(heapsize % 2 == 0)((Node)heap[parentIndex]).setLeftChild(newnode);
          else ((Node)heap[parentIndex]).setRightChild(newnode);
         while(newnode.getParent() != null && newnode.getValue().compareTo(newnode.getParent().getValue()) > 0){
             swap(((Node)newnode).index,((Node)(newnode.getParent())).index);
             newnode = newnode.getParent();
         }
    }

    @Override
    public void build(Collection unordered)
    {
        if(unordered == null)
            return;
         Iterator itr = unordered.iterator();
         int index=0;
         while (itr.hasNext()){
             Object obj = itr.next();
             Node newnode = new Node((T)obj);
             newnode.index = index++;
             heap[heapsize++] = newnode;
             if(heapsize > 1)((Node) heap[heapsize-1]).setParent(heap[(heapsize-2)/2]);
         }

         for(int i = (heapsize-2)/2 ; i >= 0 ; i--) {
             if (2 * i + 1 < heapsize) ((Node) heap[i]).setLeftChild(heap[2 * i + 1]);
             if (2 * i + 2 < heapsize) ((Node) heap[i]).setRightChild(heap[2 * i + 2]);
             heapify(heap[i]);
             //printheap();
         }


    }

    public INode<T>[] getHeap() {
        return heap;
    }

    public void setHeapsize(int heapsize) {
        this.heapsize = heapsize;
    }

    public void printheap(){
        for(int i=0 ; i<heapsize ; i++)
            System.out.print(heap[i].getValue());
        System.out.println();
    }
    public void swap(int a , int b){
       T tmp = heap[a].getValue();
       heap[a].setValue(heap[b].getValue());
       heap[b].setValue(tmp);
    }
}
