# 设计一个有getMin功能的栈

## [题目]

实现一个特殊的栈, 在实现栈的基本功能的基础上, 再实现返回栈中最小元素的操作.

## [要求]

1. pop、push、getMin操作的时间复杂度都是O(1)。
2. 设计的栈类型可以使用现成的栈结构。

## [解答]

在设计上, 使用两个栈:
1. 一个栈保存当前栈中的所有元素, 记为stackData;
2. 另外一个栈保存每次保存的最小值, 记为stackMin.

#### [解法1]

-   压入数据规则
    假设当前数据为newNum, 先将其压入stackData. 然后判断stackMin是否为空:
     - stackMin为空, 同步压入stackMin
     - stackMin不为空, 比较newNum和stackMin栈顶元素的大小:
        - newNum > stackMin栈顶元素, 那么不压入stackMin
        - newNum <= stackMin栈顶元素, 那么同步压入stackMin

```java
public void push(int newNum) {
    if(this.stackMin.isEmpty()) {
        this.stackMin.push(newNum);
    } else if(this.stackMin.peek() >= newNum) {
        this.stackMin.push(newNum);
    }
    this.stackData.push(newNum);
}
```

 - 弹出数据规则(**个人理解: 这步是为了去除stackMin中重复的最小元素**)

    现在stackData弹出栈顶, 并记为value. 然后和stackMin栈顶元素比较大小(由压入数据得知, stackMin中的元素由下至上越来越小, 且stackMin的栈顶是最小的袁术, 不可能出现value < stackMin栈顶元素的情况):
    
     - value = stackMin栈顶元素, stackMin弹出栈顶元素;
     - value > stackMin栈顶元素, stackMin不弹出栈顶元素;
     - 返回value.


```java
public int pop() {
    if(this.stackData.isEmpty()) {
        throw new RuntimeException("Your Stack is Empty!");
    }
    int value = this.stackData.pop();
    if(value == this.getMin()) {
        this.stackMin.pop();
    }
    return value;
}
```

 - 查询最小元素值
    由前可得, stackMin的栈顶始终是最小元素值

```java
public int getMin() {
    if(this.stackMin.isEmpty()) {
        throw new RuntimeException("Your Stack is Empty!");
    }
    return this.stackMin.peek();
}
```

#### [解法2]

