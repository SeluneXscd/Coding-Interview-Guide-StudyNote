# 打印两个有序链表的公共部分

## [题目]

给定两个有序链表的头指针head1和head2，打印两个链表的公共部分。

## [解答]

- 如果head1的值小于head2的值，那么head1往下移；

- 如果head1的值大于head2的值，那么head2往下移；

- 如果head1的值和head2的值相等，那么head1和head2都往下移，并打印这个值；

- head1或head2中，任意一个移动到null，那么终止程序。

### Node

```java
public class Node {
	private int value;
	private Node next;
	private Node(int value) {
		this.value = value;
	}
}
```

### printCommonPart

```java
public void printCommonPart(Node head1, Node head2) {
    System.out.print("Common part: ");
    while(head1 != null && head2 != null) {
        if (head1.value < head2.value) {
            head1 = head1.next;
        } else if (head1.value > head1.value) {
            head2 = head2.next;
        } else {
            System.out.println(head1.value + " ");
            head1 = head1.next;
            head2 = head2.next;
        }
    }
    System.out.println();
}
```

