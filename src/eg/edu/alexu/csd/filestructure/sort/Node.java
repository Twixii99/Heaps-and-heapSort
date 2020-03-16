package eg.edu.alexu.csd.filestructure.sort;

public class Node<T extends Comparable<T>> implements INode<T> {
    private INode parent;
    private INode leftChild;
    private INode rightChild;
    private T value;
    public int index;
    public Node(T value) {
        this.value = value;
    }

    public Node() {
    }

    public Node(INode parent, INode leftChild, INode rightChild, T value) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
    }

    public Node(INode parent, INode leftChild, INode rightChild) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Returns the left child of the current element/node in the heap tree
     *
     * @return INode wrapper to the left child of the current element/node
     */
    @Override
    public INode getLeftChild() {
        return this.leftChild;
    }

    /**
     * Returns the right child of the current element/node in the heap tree
     *
     * @return INode wrapper to the right child of the current element/node
     */
    @Override
    public INode getRightChild() {
        return this.rightChild;
    }

    /**
     * Returns the parent node of the current element/node in the heap tree
     *
     * @return INode wrapper to the parent of the current element/node
     */
    @Override
    public INode getParent() {
        return this.parent;
    }

    /**
     * Set/Get the value of the current node
     *
     * @return Value of the current node
     */
    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(Comparable value) {
        this.value = (T)value;
    }
/*
    @Override
    public int compareTo(Object o) {
        if(this.getValue().compareTo(((Node) o).getValue()) < 0) return -1;
        else if(this.getValue().compareTo(((Node) o).getValue()) < 0) return 1;
        return 0;
    }

 */

    public void setParent(INode parent) {
        this.parent = parent;
    }

    public void setLeftChild(INode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(INode rightChild) {
        this.rightChild = rightChild;
    }
}
