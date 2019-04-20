public class test1 {
    public static void main(String[] args) {
        MyStack1 myStack1 = new MyStack1();
        int[] nums = {3, 4, 5, 1, 2, 1};

        for (Integer num : nums) {
            myStack1.push(num);
            System.out.print(myStack1.peekData() + "\t");
            System.out.println(myStack1.peekMin());
        }
        System.err.println("+++++++++++");
        System.out.print(myStack1.peekData() + "\t");
        System.out.println(myStack1.peekMin());
        System.out.println("+++++++++++");
        int min = myStack1.getMin();
        System.out.println(min);
        
    }
}