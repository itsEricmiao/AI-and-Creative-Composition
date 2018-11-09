import java.util.*;
import java.lang.Object;
public class Pair<E> 
{
    private E first; //first member of pair
    private Integer second; //second member of pair

    public Pair(E first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }
    public void addCount()
    {
    	this.second = second + 1;
    }
}