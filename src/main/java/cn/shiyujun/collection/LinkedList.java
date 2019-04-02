package cn.shiyujun.collection;

import java.util.*;
/**
 * @author syj
 * CreateTime 2019/04/02
 * describe:LinkedList源码解析
 */
public class LinkedList <E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 876323262645176354L;

    //List的大小
    transient int size = 0;
    /**
     * 链表头部
     */
    transient Node<E> first;

    /**
     * 链表尾部
     */
    transient Node<E> last;

    /**
     * 默认构造方法
     */
    public LinkedList() {
    }

    /**
     * 添加一个集合
     */
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    /**
     * 更新链表的第一个节点
     */
    private void linkFirst(E e) {
        //获取当前第一个节点
        final Node<E> f = first;
        //创作e节点，并且指定下一个节点是第一个节点
        final Node<E> newNode = new Node<>(null, e, f);
        //第一个节点更新为e
        first = newNode;
        //如果第一个节点之前为空则List也是空的，把最后一个节点也置于e
        if (f == null)
            last = newNode;
        else //将之前的第一个节点的前节点置为e
            f.prev = newNode;
        //List的大小加1
        size++;
        //修改次数加1
        modCount++;
    }

    /**
     * 更新链表的最后一个节点
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    /**
     * 在succ节点之前插入一个节点
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    /**
     * 删除第一个节点
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 删除最后一个节点
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 删除x节点
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 获取第一个节点
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * 获取最后一个节点
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    /**
     * 删除第一个节点
     */
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * 删除最后一个节点
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    /**
     * 添加第一个节点
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 添加最后一个节点
     */
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 判断某个元素是否存在
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * List的大小
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    /**
     * 往最后一个节点添加一个数据
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }


    /**
     * 添加节点
     */
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }
    /**
     * 添加一个集合
     */
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * 从指定索引开始添加一个集合
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        //判断下标是否越界
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            //从链表尾部添加
            succ = null;
            pred = last;
        } else {
            //从链表中间添加
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            //创建Node节点，指定Node的前一个节点
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                //pred如果为空则代表整个链表为空，
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            //将节点放到最后
            last = pred;
        } else {
            //将节点插入
            pred.next = succ;
            succ.prev = pred;
        }
        //更新链表大小
        size += numNew;
        //更新修改次数
        modCount++;
        return true;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    /**
     * 清空List
     */
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }


    // Positional Access Operations

    /**
     * 获取指定索引的元素
     */
    public E get(int index) {
        //判断是否越界
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 将指定索引的元素替换
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }


    /**
     * 删除元素
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 删除节点
     */
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 是否存在的下标
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 判断是否是合适的可添加的下标
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 下标越界的提示
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    /**
     * 判断索引下标是否越界
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    /**
     * 判断是否是合适的可添加的下标
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 获取指定索引的元素
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    // Search Operations

    /**
     * 获取元素的索引
     */
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * 获取元素最后出现的位置
     */
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    // Queue operations.

    /**
     * 获取第一个元素
     */
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 获取第一个元素
     */
    public E element() {
        return getFirst();
    }

    /**
     * 弹出第一个元素（删除并获取）
     */
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 删除第一个元素
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * 往最后添加一个元素
     */
    public boolean offer(E e) {
        return add(e);
    }

    // Deque operations
    /**
     * 往第一个位置添加元素
     */
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * 往最后一个位置添加元素
     */
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    /**
     * 获取第一个元素
     */
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 获取最后一个元素
     */
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    /**
     * 弹出第一个元素（删除并获取）
     */
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 弹出最后一个元素（删除并获取）
     */
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    /**
     * 往List头部添加元素
     */
    public void push(E e) {
        addFirst(e);
    }

    /**
     *
     * 弹出第一个元素（删除并获取）
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * 删除顺序遍历到的第一个存在的元素
     */
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * 删除逆序遍历到的最后一个存在的元素
     */
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private static class Node<E> {
        //当前节点
        E item;
        //后一个节点
        Node<E> next;
        //前一个节点
        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }



    /**
     * 将List写入s，注意先写容量，然后在写数据
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (Node<E> x = first; x != null; x = x.next)
            s.writeObject(x.item);
    }

    /**
     * 从s读取，先读容量，再读数据
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
            linkLast((E)s.readObject());
    }



}
