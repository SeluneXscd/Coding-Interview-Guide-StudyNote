import java.util.Stack;

import javax.management.RuntimeErrorException;

public class MyStack1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    /**
     * 压入规则:
     * 先把newNum压入, 和stackMin比较:
     * 1. 如果stackMin为空, 那么同步压入stackMin;
     * 2. 如果stackMin不为空, 那么和stackMin栈顶比较:
     *      1. 如果newNum <= stackMin栈顶, 那么同步压入stackMin;
     *      2. 如果newNum > stackMin栈顶, 那么不压入stackMin.
     * @param newNum 当前数据
     */
    public void push(int newNum) {
        if(this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if(this.stackMin.peek() > newNum) {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int peekData() {
        return this.stackData.peek();
    }

    public int peekMin() {
        return this.stackMin.peek();
    }

    /**
     * 弹出规则:
     * 如果 stackData 栈顶和 stackMin 栈顶相等, 那么弹出stackMin栈顶
     * (可以去除stackMin 中重复元素)
     * @return value stackData栈顶元素
     */
    public int pop() {
        if(this.stackData.isEmpty()) {
            throw new RuntimeException("Your Stack is Empty!");
        }
        int value = this.stackData.pop();
        if(value == this.stackMin.peek()) {
            this.stackMin.pop();
        }
        return value;
    }

    /**
     * 获取最小元素
     * @return this.stackMin.peek() 返回最小元素
     */
    public int getMin() {
        if(this.stackMin.isEmpty()) {
            throw new RuntimeException("Your Stack is Empty!");
        }
        return this.stackMin.peek();
    }
}