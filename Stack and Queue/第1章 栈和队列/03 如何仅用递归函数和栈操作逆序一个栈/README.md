# 如何仅用递归函数和栈操作逆序一个栈

## [题目]

一个栈依次压入`1, 2, 3, 4, 5`， 那么从栈顶到栈底分别为`5, 4, 3, 2, 1`。将这个栈
转置后， 从栈顶到栈底依次为`1, 2, 3, 4, 5`， 也就是实现栈中元素的逆序， 但是只能因递归函数来实现， 不能用其他数据结构。

## [解答]

#### [递归函数1]

将栈`stack`的栈底元素返回并移除

```java
public static int getAndRemoveLastElement(Stack<Integer> stack) {
    int result = stack.pop();
    if (stack.isEmpty()) {
        return result;
    } else {
        int last = getAndRemoveLastElement(stack);
        stack.push(result);
        return last;
    }
}
```

#### [递归函数2]

逆序一个栈

```java
public static void reverse(Stack<Integer> stack) {
    if (stack.isEmpty()) {
        return;
    }
    int i = getAndRemoveLastElement(stack);
    reverse(stack);
    stack.push(i);
}
```