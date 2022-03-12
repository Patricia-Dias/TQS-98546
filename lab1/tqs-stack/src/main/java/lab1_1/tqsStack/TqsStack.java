package lab1_1.tqsStack;

import java.util.Stack;

public class TqsStack {
    private Integer cap;
    private Stack<Integer> s = new Stack<>();
    public TqsStack(Integer cap){
        this.cap = cap;
    }
    public TqsStack(){}

    //saves i in stack
    public void push(Integer i){
        if (cap!=null && this.size()>=this.cap)
            throw new IllegalStateException();
        s.push(i);
    }

    //returns 1st element and removes it
    public Integer pop(){
        return s.pop();
    }

    //returns 1st element
    public Integer peek(){
        return s.peek();
    }

    //returns the size of the stack
    public Integer size(){
        return s.size();
    }

    //check if stack is empty
    public Boolean isEmpty(){
        return s.empty();
    }
}
