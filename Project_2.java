//Johnathan Nguyen
//CS 241
//Prof. Hao Ji
//Due 02/13/2018
//
//  Heap:
//  A specialized tree-based data structure that satisfies the heap property:
//  "if B is a child node of A, then key(A) >= key(B)"
//
//  Heap Rules:
//  The element inside each node is '>=' the elements of that node's children.
//  The tree is a 'complete binary tree' (Every level except the last level is
//  completely filled and all the nodes are left justified).
//    
//  Heap Implementation:
//  A common approach is to store the heap in an array.  
//  The parent and children of each node can be found by simple arithmetic
//  on 'array indices'.
//
//
//  Max-heap:
//  The element with the greatest number/value/key is always in the root
//  node.
//
//  Min-heap:
//  The element with the greatest number/value/key is always in the root
//  node.
//
//  Up-heap operation: Adding an Entry to heap.
//  Add the element to bottom level of heap.
//  Compare that element with parent; if in correct order, stop.
//  else, swap that element with parent and return to previous step.
//
//  Down-heap operation: Removing the root.
//  Replace root with the last element on the last level.
//  Compare new root with its children; if they are in the correct order, stop.
//  else, swap that element with one of its children (swap with smaller child
//  in min-heap, larger child in max-heap) and return to previous step.
//
//
//  HW Description:
//  Build a max-heap. Use an array to implement the heap.
//  Your program should allow the user to select one of the following:
//  (Your program needs to implement both choices)
//  (1) test your program with 100 randomly generated integers(no duplicates,
//   positive numbers with proper range)
//  (2) test your program with the following 100 fixed values from 1, 2, 3,..100
//
//  Implement both methods of building a max heap:
//  Using sequential insertions (Time complexity O(nlogn))
//  Using the optimal method (Time complexity O(n))
//  Remember to keep track of how many swaps (swapping parent and child) are
//  required to build a heap
//
//  For (1), you need to generate 20 sets of randomly generated integers;
//  compute, print, and document in your project report the average number of
//  swaps for both methods (an average over 20 sets)
//
//  For (2), your program should output the first 10 integers in your array
//  and the number of swaps for both methods. Then perform 10 removals on the
//  heap and output the first 10 integers.

//package project_2;

import java.util.*;

class data
{
    public int key;
    
    public data(int key)
    {
        this.key = key;
    }
    
    public String toString()
    {
        return Integer.toString(key);
    }
}
class Heap3 
{
    
    public data[] theheap; // the heap = an array of 'data' nodes
    public int itemsInArray = 0;
    public int maxSize;
    public int end = 0;
    public int swaps = 0;
    
    public Heap3(int maxSize) // constructor
    {
        this.maxSize = maxSize;
        
        theheap = new data[maxSize];
    }
    
    public void insert(int index, data newData)
    {
        theheap[index] = newData;
    }
    
    public void incrementArray()
    {
        itemsInArray++;
    }
    
    public data pop()
    {
        // remove the root from heap/array
        // store the root value from our heap into 'root'
        // save the last item in our array into the root position of heap
        // re-heap the array
        if (itemsInArray != 0) // if heap is not empty
        {
            int tempItemsInArray = itemsInArray - 1;
            data root = theheap[0];
            theheap[0] = theheap[--itemsInArray];
            // Send to the array heap method starting with index 0
            heapTheArray(0);
            return root;
	}
	return null;
    }
    
    public void generateFilledArray(int randNum) // generate random values
    {
	data randData;
	for (int i = 0; i < this.maxSize; i++) 
        {
            randData = new data((int) (Math.random() * randNum) + 1);
            this.insert(i, randData);
            incrementArray();
	}
    }

    public void generateFixedArray() // for fixed values 1-100
    {
        data randData;
        for (int i = 0; i < this.maxSize; i++)
        {
            randData = new data(i + 1);
            this.insert(i, randData);
            incrementArray();
        }
    }
    
    public void heapTheArray(int index) // for optimal method -- O(n)
    {
	int largestChild;
	data root = theheap[index];
	while (index < itemsInArray / 2) 
        {
            // Get the index for the leftChild
            int leftChild = 2 * index + 1;
            // Get the index for the rightChild
            int rightChild = leftChild + 1;
            // If leftChild is less then rightChild
            // save rightChild in largestChild
            if (rightChild < itemsInArray
			&& theheap[leftChild].key < theheap[rightChild].key)
            {
		largestChild = rightChild;
            } 
            else
            {
		// Otherwise save leftChild in largestChild
		largestChild = leftChild;
            }
            // If root is greater then the largestChild
            // jump out of while
            if (root.key >= theheap[largestChild].key)
            {
                break;
            }
            // Save the value in largest child into the top
            // index
            theheap[index] = theheap[largestChild];
            index = largestChild;
            swaps++;
	}
	theheap[index] = root;
    }
    
    public void add(int num) // for sequential insertion -- O(nlogn)
    {
        data newData = new data(num);
        int i = end;
        theheap[i] = newData;
        int parent = (i - 1) / 2;
        while (i != 0 && newData.key > theheap[parent].key)
        {
            theheap[i] = theheap[parent];
            theheap[parent] = newData;
            swaps++;
            i = parent;
            parent = (i - 1) / 2;
        }
        end++;
        itemsInArray++;
    }
    
}

public class Project_2
{

    public static void main(String[] args)
    {
        System.out.println("Please select how to test the program: ");
        System.out.println("(1) 20 sets of 100 randomly generated integers");
        System.out.println("(2) Fixed integer values 1-100");
        System.out.print("Enter choice: ");
        Scanner kb = new Scanner(System.in);
        int choice = kb.nextInt();
        
        switch (choice)
        {
            case 1 :
                int totswaps = 0;
                Heap3 heapList1[] = new Heap3[20];
                for (int a = 0; a < 20; a++)
                {
                    heapList1[a] = new Heap3(100);
                    for (int b = 0; b < 100; b++)
                    {
                        heapList1[a].add((int) (Math.random()*100 ));
                    }
                    totswaps += heapList1[a].swaps;
                }
                double avgswaps = totswaps / 20;
                System.out.println("Average swaps for sequential method: " + avgswaps);
                Heap3 heapList[] = new Heap3[20];
                totswaps = 0;
                avgswaps = 0;
                for (int i = 0; i < 20; i++)
                {
                    heapList[i] = new Heap3(100);
                    heapList[i].generateFilledArray(i);
                    for (int k = heapList[i].maxSize / 2 - 1; k >= 0; k--)
                    {
                        heapList[i].heapTheArray(k);
                    }
                    totswaps += heapList[i].swaps;
                }
                avgswaps = totswaps / 20;
                System.out.println("Average swaps for optimal method: " + avgswaps);
                break;
            case 2 :
                System.out.println("Heap built using series of insertions");
                Heap3 newHeap = new Heap3(100);
                for(int i = 0; i < 100; i++)
                {
                    newHeap.add(i + 1);
                }
                System.out.print("Heaped Array: ");
                //System.out.println(Arrays.toString(newHeap.theheap) + "\n");
                for (int i = 0; i < 10; i++)
                {
                    System.out.print(newHeap.theheap[i].key + " ");
                }
                System.out.print("...");
                System.out.println();
                
                System.out.println(newHeap.swaps + " SWAPS");
                for (int i = 0; i < 10; i++)
                {
                    newHeap.pop();
                }
                System.out.print("Heap after 10 removals: ");
                //System.out.println(Arrays.toString(newHeap.theheap) + "\n");
                for (int i = 0; i < 10; i++)
                {
                    System.out.print(newHeap.theheap[i].key + " ");
                }
                System.out.print("...");
                System.out.println();
                
                System.out.println();
                System.out.println("Heap built using optimal method");
                newHeap = new Heap3(100);
                newHeap.generateFixedArray();
                System.out.print("Original Array: ");
                //System.out.println(Arrays.toString(newHeap.theheap));
                for (int i = 0; i < 10; i++)
                {
                    System.out.print(newHeap.theheap[i].key + " ");
                }
                System.out.print("...");
                System.out.println();
                for (int i = newHeap.maxSize / 2 - 1; i >= 0; i--)
                {
                    newHeap.heapTheArray(i);
                }
                System.out.print("Heaped Array: ");
                //System.out.println(Arrays.toString(newHeap.theheap) + "\n");
                for (int i = 0; i < 10; i++)
                {
                    System.out.print(newHeap.theheap[i].key + " ");
                }
                System.out.print("...");
                System.out.println();
                System.out.println(newHeap.swaps + " SWAPS");
                for (int i = 0; i < 10; i++)
                {
                    newHeap.pop();
                }
                System.out.print("Heap after 10 removals: ");
                //System.out.println(Arrays.toString(newHeap.theheap) + "\n");
                for (int i = 0; i < 10; i++)
                {
                    System.out.print(newHeap.theheap[i].key + " ");
                }
                System.out.print("...");
                System.out.println();
                break;
            default : System.out.println("Invalid selection.");
                break;
        }
    }
    
}
