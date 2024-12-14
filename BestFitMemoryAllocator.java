import java.util.ArrayList;
import java.util.Scanner;

class MemoryBlock {
    int id, size;
    boolean allocated;

    MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

public class BestFitMemoryAllocator {
    private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

    public static void addMemoryBlock(int size) {
        memoryBlocks.add(new MemoryBlock(memoryBlocks.size() + 1, size));
    }

    public static void allocateMemory(int requestSize) {
        // Input validation: Check if the request size is invalid
        if (requestSize <= 0) {
            System.out.println("Allocation failed. Request size must be greater than 0.");
            return;
        }
    
        MemoryBlock bestBlock = null;
        for (MemoryBlock block : memoryBlocks) {
            if (!block.allocated && block.size >= requestSize && (bestBlock == null || block.size < bestBlock.size)) {
                bestBlock = block;
            }
        }
    
        if (bestBlock != null) {
            bestBlock.size -= requestSize;
            if (bestBlock.size == 0) bestBlock.allocated = true; 
            System.out.println("Allocation successful. Request size: " + requestSize);
        } else {
            System.out.println("Allocation failed. No suitable block found.");
        }
    }

    public static void displayMemoryStatus() {
        System.out.println("\nMemory Blocks Status:");
        for (MemoryBlock block : memoryBlocks) {
            System.out.println("Block ID: " + block.id + ", Size: " + block.size + ", Allocated: " + block.allocated);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------");
        System.out.println("Best Fit Memory Allocation Simulator");
        System.out.println("------------------------------------");

        System.out.print("\nEnter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Enter size for block " + (i + 1) + ": ");
            addMemoryBlock(scanner.nextInt());
        }

        boolean running = true;
        while (running) {
            System.out.println("\nOptions:\n1. Allocate memory\n2. Display memory status\n3. Exit\n\nChoose an option: ");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.print("Enter request size: ");
                    allocateMemory(scanner.nextInt());
                    break;
                case 2:
                    displayMemoryStatus();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
