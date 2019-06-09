# 猫狗队列

## [题目]

宠物、狗和猫的类如下: 

```java
public class Pet {
    private String type;
    public Pet(String type) {
        this.type = type;
    }
    public String getPetType() {
        return this.type;
    }
}

public class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}

public class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}
```

实现一种猫狗队列的数据结构，要求如下:

- 用户可以调用add方法将cat类或者dog类的实例放入队列中；
- 用户可以调用pollAll方法，将队列中所有的实例按照进队的先后顺序依次弹出；
- 用户可以调用pollDog方法，将队列中所有的dog类的实例按照进队的先后顺序依次弹出；
- 用户可以调用pollCat方法，将队列中所有的cat类的实例按照进队的先后顺序依次弹出；
- 用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
- 用户可以调用isDogEmpty方法，检查队列中是否还有dog的实例；
- 用户可以调用isCatEmpty方法，检查队列中是否还有cat的实例。

## [解答]

### [常见错误]

- cat队列只放cat实例，dog队列只放dog实例，再用一个总队列放所有的实例。
**错误原因:** cat、dog以及总队列的更新问题

- 用哈希表，key表示一个cat实例或dog实例，value表示这个实例进队列的次序。
**错误原因:** 不能支持一个实例对此进队的功能需求，因为哈希表的key只能对应一个value。

- 将用户原有的cat或dog类改写，加一个计数项表示某个实例进队的时间。
**错误原因:** 不能擅自修改用户的类结构。

### [题解]

#### 一、定义一个类PetEnterQueue。

用于使用计数器，记录不同实例的进队时间。

```java
public class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return this.pet;
    }

    public long getCount() {
        return this.count;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}
```

PetEnterQueue类在构造时，pet是用户的实例，count就是这个实例的时间戳。

#### 二、定义一个类DogCatQueue。

首先有一个不断累加的计数器，用来表示实例的进队时间；
同时又两个队列，一个是只放dog类的实例的队列dogQ，一个是只放cat类的实例的队列catQ。

```java
public class DogCatQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;

    public DogCatQueue() {
        this.dogQ = new LinkedList<PetEnterQueue>();
        this.catQ = new LinkedList<PetEnterQueue>();
        this.count = 0;
    }

    /**
     * add: 
     * 如果实例是dog，就加上时间戳，生成对应的PetEnterQueue实例并放入dogQ队列;
     * 如果实例是cat，就加上时间戳，生成对应的PetEnterQueue实例并放入catQ队列;
     */
    public void add(Pet pet) {        
        if (pet.getPetType().equals("dog")) {
            this.dogQ.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getPetType().equals("cat")) {
            this.catQ.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RunTimeException("error! not dog or cat");
        }
    }

    /**
     * pollAll:
     * dogQ表示所有的dog实例队列，catQ表示所有的cat实例队列，
     * 那么比较dogQ队头和catQ队头的时间戳，谁更早就弹出谁。
     */
    public Pet pollAll() {
        if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
            if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                return this.dogQ.poll().getPet();
            } else {
                return this.catQ.poll().getPet();
            }
        } else if (!this.dogQ.isEmpty()) {
            return this.dogQ.poll().getPet();
        } else if (!this.catQ.isEmpty()) {
            return this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("error! queue is empty!");
        }
    }
    
    /**
     * pollCat:
     * 只想弹出cat，从catQ中不断弹出即可。
     */
    public Cat pollCat() {
        if (!this.isDogQueueEmpty()) {
            return (Cat)this.catQ.poll().getPet();
        } else if {
            throw new RuntimeException("Cat queue is empty!");
        }
    }
    
    /**
     * pollDog:
     * 只想弹出dog，从dogQ中不断弹出即可。
     */
    public Dog pollDog() {
        if (!this.isDogQueueEmpty()) {
            return (Dog)this.dogQ.poll().getPet();
        } else if {
            throw new RuntimeException("Dog queue is empty!");
        }
    }
    
    public boolean isEmpty() {
        return this.dogQ.isEmpty() && this.catQ.isEmpty();
    }
    
    public boolean isDogQueueEmpty() {
        return this.dogQ.isEmpty();
    }
    
    public boolean isCatQueueEmpty() {
        return this.catQ.isEmpty();
    }
}
```

