# 用一个栈实现另一个栈的排序

## [题目]

一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但不能申请额外的数据结构。

## [解答]

将要排序的栈记为`stack`，申请的辅助栈记为`help`。在`stack`上执行`pop`操作，弹出的元素记为`cur`。

- 如果`cur`小于等于`help`的栈顶元素，则将`cur`直接压入`help`；
- 如果`cur`大于`help`的栈顶元素，则将`help`的元素逐一弹出，再逐一压入`stack`中，直到`cur`小于或等于`help`的栈顶元素，再将`cur`压入`help`。

一直执行以上操作，知道`stack`中的全部元素都压入`help`。最后将`help`中的所有元素逐一压入`stack`，完成排序。

```java
public static void sortStackByStack(Stack<Integer> stack) {
    Stack<Integer> help = new Stack<>();
    while (!stack.isEmpty()) {
        int cur = stack.pop();
        while (!help.isEmpty() && help.peek() < cur) {
            stack.push(help.pop());
        }
        help.push(cur);
    }
    while (!help.isEmpty()) {
        stack.push(help.pop());
    }
}
```

