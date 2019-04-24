# 由两个栈组成的队列

## [题目]

编写一个类， 用两个栈实现队列， 支持队列的基本操作（add、poll、peek）

## [解答]

栈的特点是先进后出， 队列的特点是i先进先出。我们用两个栈正好可以把顺序反过来实现类似队列的操作。

设置一个栈作为压入栈， 记为`stackPush`， 在压入数据时，只往这个栈压入；
设置一个栈作为弹出栈， 记为`stackPop`， 在弹出数据时， 只从这个栈弹出。

```java
public Stack<Integer> stackPush;
public Stack<Integer> stackPop;
```

因为栈是先进后出的， 只要把`stackPush`的数据再压入`stackPop`， 那么顺序就变回来了。
但是这必须要做到两点：
1. 如果`stackPush`往`stackPop`压入数据， 那么必须一次性把所有的数据压入；
2. 如果`stackPop`不为空， 那么`stackPush`绝对不能往`stackPop`压入数据。

压入数据：

```java
public void add(int pushInt) {
    stackPush.push(pushInt);
}
```

弹出数据（删除栈顶）：

```java
public int poll() {
    if (stackPop.empty() && stackPush.empty()) {
        throw new RuntimeException("Queue is Empty!");
    } else if (stackPop.empty()) {
        while (!stackPush.empty()) {
            stackPop.push(stackPush.pop());
        }
    }
    return stackPop.pop();
}
```

弹出数据（不删除栈顶）：

```java
public int poll() {
    if (stackPop.empty() && stackPush.empty()) {
        throw new RuntimeException("Queue is Empty!");
    } else if (stackPop.empty()) {
        while (!stackPush.empty()) {
            stackPop.push(stackPush.pop());
        }
    }
    return stackPop.peek();
}
```