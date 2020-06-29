package ru.avers.informica.report.source;

/**
 *
 * @author Dias
 */
public class Pair<K, T> {
    private K first;
    private T second;

    public Pair() {
    }    
    
    public Pair(K pK, T pT) {
        this.first = pK;
        this.second = pT;
    }

    public K getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
    
}
