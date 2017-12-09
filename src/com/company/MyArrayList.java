package com.company;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<T> implements Collection, List, Iterable {

    private Object[] array;
    private int nextIndex;
    private int capacity;

    public MyArrayList() {
        nextIndex = 0;
        capacity = 10;
        array = new Object[10];
    }

    private Object[] checkUpCapacity(Object[] array) {
        if (nextIndex == capacity - 1) {
            capacity = (int) (capacity * 1.5 + 1);
            array = Arrays.copyOf(array, capacity);
            return array;
        } else {
            return array;
        }
    }

    @Override
    public boolean add(Object o) {
        array = checkUpCapacity(array);
        array[nextIndex++] = o;
        return true;
    }

    @Override
    public Object get(int index) {
        if (index >= 0 && index < nextIndex) {
            return (T) array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return nextIndex;
    }

    @Override
    public boolean isEmpty() {
        return nextIndex == 0;
    }

    @Override
    public boolean remove(Object o) {
        T elem = (T) o;
        for (int i = 0; i < nextIndex; i++) {
            if (elem.equals(array[i])) {
                for (int j = i; j < nextIndex - 1; j++) {
                    array[j] = array[j + 1];
                }
                nextIndex--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int nextId = 0;

            @Override
            public boolean hasNext() {
                if (nextId == nextIndex) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public T next() {
                return (T) array[nextId++];
            }
        };
    }

    @Override
    public Object remove(int index) {
        if (index >= 0 && index < nextIndex) {
            for (int i = index; i < nextIndex - 1; i++) {
                array[i] = array[i + 1];
            }
            nextIndex--;
            return true;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void sort(Comparator c) {
        T tmp;
        int j;
        for (int i = 0; i < nextIndex - 1; i++) {
            if (c.compare(array[i], array[i + 1]) == 1) {
                tmp = (T) array[i + 1];
                array[i + 1] = array[i];
                j = i;
                while (j > 0 && c.compare(tmp, array[j - 1]) == -1) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = tmp;
            }
        }

    }

    @Override
    public void add(int index, Object element) {
        array = checkUpCapacity(array);
        nextIndex++;
        for (int i = nextIndex; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
    }

    @Override
    public void clear() {
        array = new Object[10];
        capacity = 10;
        nextIndex = 0;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        boolean result = false;
        for (int i = 0; i < nextIndex; i++) {
            if (filter.test(array[i])) {
                remove(i);
                i = -1;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < nextIndex; i++) {
            if (array[i].hashCode() == o.hashCode()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[0];
        if (nextIndex > 0) {
            result = Arrays.copyOfRange(array, 0, nextIndex - 1);
        }
        return result;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean containsAll(Collection c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {

        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean result = false;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            if (this.remove(iterator.next())) {
                iterator = c.iterator();
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean result = false;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            Integer value = (Integer) it.next();
            if (!c.contains(value)) {
                this.remove(value);
                result = true;
                it = this.iterator();
            }
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (index >= 0 && index <= nextIndex) {
            for (Object object : c) {
                this.add(index++, (T) object);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }


    @Override
    public Object set(int index, Object element) {
        if (index >= 0 && index < nextIndex) {
            array[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < nextIndex; i++) {
            if (o == array[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = nextIndex - 1; i >= 0; i--) {
            if (o == array[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < nextIndex && toIndex >= 0 && toIndex < nextIndex) {
            List<T> list = new ArrayList<>();
            for (int i = fromIndex; i <= toIndex; i++) {
                list.add((T) array[i]);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return null;
    }

    public void trimToSize() {
        if (nextIndex > 0) {
            array = Arrays.copyOfRange(array, 0, nextIndex - 1);
            capacity = array.length;
            nextIndex = capacity;
        }
    }

    @Override
    public void replaceAll(UnaryOperator operator) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }
}
