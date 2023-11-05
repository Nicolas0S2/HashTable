public class HashTable {
    private Node[][] table;
    private int size;
    private int collision = 0;
    private int comparisons = 1;

    public HashTable(int size) {
        this.size = size;
        table = new Node[2][size];
    }

    public int getCollision() {
        return collision ;
    }

    public int divisionHash(int key, int tableSize) {
        return key % tableSize;
    }

    public int multiplicativeHash(int key, int tableSize) {
        double constant = 0.6180339887; //
        double hashedValue = key * constant;
        hashedValue -= (int) hashedValue;
        return (int) (tableSize * hashedValue);
    }

    public int multiplicationFixedHash(int key, int tableSize) {
        int multiplier = 881;
        long hashedValue = (long) key * multiplier;
        return (int) ((hashedValue >> 16) % tableSize);
    }

    public int hashFunction(int f, int key, int tableSize){
        switch (f) {
            case 1 -> {
                return divisionHash(key, tableSize);
            }
            case 2 -> {
                return multiplicativeHash(key, tableSize);
            }
            case 3 -> {
                return multiplicationFixedHash(key, tableSize);
            }
        }
        return f;
    }

    public Node change(Node current, Node next){
        Node aux_current = new Node(current);
        Node aux_next = new Node(next);

        next = new Node(aux_current);
        next.setNext(aux_next.getNext());

        current = new Node(aux_next);
        current.setNext(next);

        current.setPrevious(aux_current.getPrevious());
        next.setPrevious(current);

        if (next.getNext() != null) {
            next.getNext().setPrevious(next);
        } else {
            next.setPrevious(next.getPrevious());
        }

        return current;
    }

    public Node sort(Node root){
        int change = 1;
        do{
            change = 0;
            Node current = root;
            Node next = root.getNext();
            while (current.getNext() != null){
                if (next.getKey() < current.getKey()){
                    if (current == root){
                        current = change(current, next);
                        root = current;
                        change = change + 1;
                    }
                    else {
                        current = change(current, next);
                        current.getPrevious().setNext(current);
                        change = change + 1;
                    }
                }
                next = next.getNext();
                current = current.getNext();
            }
        }while (change != 0);
        return root;
    }

    public Node insertOnLinkedList(Node root, Node newNode){
        Node current = root;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        newNode.setPrevious(current);
        return sort(root);
    }

    public void insert(int value, int f){
        Node newNode = new Node();
        newNode.setKey(value);
        int hashedValue = hashFunction(f, value, this.size);

        if (table[0][hashedValue] == null) {
            table[0][hashedValue] = newNode;
        }else if (table[1][hashedValue] == null){
            table[1][hashedValue] = newNode;
            this.collision += 1;
        }
        else {
            Node root = new Node();
            root = table[1][hashedValue];
            table[1][hashedValue] = insertOnLinkedList(root, newNode);
            this.collision += 1;
        }
    }
         //String
    public void searchOnLinkedList(Node root, int value){
        while (root.getNext() != null){
            if (root.getKey() == value){
                //return "Valor encontrado!";
                break;
            }
            else {
                root = root.getNext();
                this.comparisons += 1;
            }
        }
       //return "Valor não encontrado!";
    }

         //void
    public int search(int f, int value){
        int hashedValue = hashFunction(f, value, this.size);

        if (this.table[0][hashedValue].getKey() == value){
            //System.out.println("Valor encontrado!");
            return 1;
        } else if (this.table[1][hashedValue].getKey() == value) {
            //System.out.println("Valor encontrado!");
            return 2;
        }else {
            searchOnLinkedList(this.table[1][hashedValue], value);
        }
        int c = 2 + this.comparisons;
        this.comparisons = 0;
    return c;
    }
}
