package cn.shiyujun.collection;

import java.util.*;


/**
 * @author syj
 * CreateTime 2019/04/01
 * describe:ArrayList源码解析
 */
public class ArrayList <E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 数组默认的大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 使用数组大小为0时的默认缓冲区
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 使用ArrayList(int initialCapacity)构造方法时且initialCapacity为0时缓冲区
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 真实存储arraylist元素的数组缓冲区
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * List的实际大小
     *
     * @serial
     */
    private int size;
    /**
     * 数组可分配的最大大小
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 根据参数构建具有初始大小的构造方法
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 无参构造方法，初始化elementData
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 创建一个包含collection的ArrayList
     */
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 将当前容量值设为实际元素个数
     */
    public void trimToSize() {
        //继承自AbstractList的值，用来记录List修改的次数
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 判断List的大小是否大于本次添加所需要的容量（是否需要扩容）
     */
    public void ensureCapacity(int minCapacity) {
        //当前数组的容量
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    /**
     * 计算数组最新的容量
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        //如果创建ArrayList时指定大小为0
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            //如果本次添加的大小比初始容量10大的话则不使用默认的容量10，直接使用本次添加的大小作为初始容量
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    /**
     * 记录修改次数，调用扩容方法
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            //扩容
            grow(minCapacity);
    }



    /**
     * 扩容
     */
    private void grow(int minCapacity) {
        // 获取原来的数组长度
        int oldCapacity = elementData.length;
        //新容量设置为老容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //如果新容量还不够存放本次需要添加的大小，则直接扩容到本次添加的大小
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        //如果新容量超出数组最大容量
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 调用Arrays的复制方法更新数据缓冲池
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    //判断容量是否溢出
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 返回List当前大小
     */
    public int size() {
        return size;
    }

    /**
     * List是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * List是否存在某个元素
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * 某个元素在list中的索引
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 某个元素在List中最后出现的位置
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }


    public Object clone() {
        try {
           ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }


    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }


    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }


    public E get(int index) {
        //判断索引是否越界
        rangeCheck(index);

        return elementData(index);
    }


    public E set(int index, E element) {
        //判断索引是否越界
        rangeCheck(index);
        //获取此索引原先的值
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 添加元素
     */
    public boolean add(E e) {
        //计算数组最新的容量，以及判断是否需要扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    /**
     * 指定索引添加元素
     */
    public void add(int index, E element) {
        //判断索引是否越界
        rangeCheckForAdd(index);
        //计算数组最新的容量，以及判断是否需要扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //调用系统底层的复制方法
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        //List长度+1
        size++;
    }

    /**
     * 删除指定索引的元素
     *
     */
    public E remove(int index) {
        //判断是否越界
        rangeCheck(index);
        //记录修改次数
        modCount++;
        E oldValue = elementData(index);
        //计算需要移动的位置
        int numMoved = size - index - 1;
        if (numMoved > 0)
            //使用系统底层方法移动数组，将需要删除的元素放到数组最后
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        //数组长度减一，删除数组最后一个位置的元素
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    /**
     * 遍历数组，找出需要删除的元素的索引，并调用删除方法
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    //具体删除方法
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /*
     * 删除指定元素
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    /**
     * 清空数组
     */
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    /**
     * 添加一个集合
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 从指定位置开始插入集合
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 删除一段元素
     */
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    /**
     * 判断索引是否越界
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 判断索引是否越界
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 索引越界提示
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * 删除集合中存在的全部元素
     */
    public boolean removeAll(Collection<?> c) {
        //判断集合是否为空
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 删除除了集合之外的元素
     */
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }
    //删除一个集合
    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 将List写入s，注意先写容量，然后在写数据
     * @param s
     * @throws java.io.IOException
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // 首先写数组容量
        s.writeInt(size);

        // 遍历写数组中的元素
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 读取s中的List
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // 首先读数组容量
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }
    /**
     * 排序方法
     * @param c
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) elementData, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }
}
