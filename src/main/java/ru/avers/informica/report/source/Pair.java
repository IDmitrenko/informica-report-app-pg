package ru.avers.informica.report.source;

/**
 *
 * @author Dias
 */
public class Pair<K, T> {
    private K m_k;
    private T m_t;

    public Pair() {
    }    
    
    public Pair(K p_k, T p_t) {
        this.m_k = p_k;
        this.m_t = p_t;
    }

    public K getFirst() {
        return m_k;
    }

    public T getSecond() {
        return m_t;
    }
    
}
