package Minor;

import java.util.*;

class Timetable2 {
    private int V; // Number of vertices or tasks
    private Map<Integer, String> taskNames; // Map to store task names
    private LinkedList<Integer> adj[];

    Timetable2(int v) {
        V = v;
        adj = new LinkedList[v];
        taskNames = new HashMap<>();
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(v);
    }

    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Print the schedule with task names
        System.out.println("Scheduled tasks:");
        while (!stack.isEmpty()) {
            System.out.print(taskNames.get(stack.pop()) + " ");
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();

        Timetable2 timetable = new Timetable2(numTasks);

        Map<Integer, String> taskNamesMap = new HashMap<>();
        for (int i = 0; i < numTasks; i++) {
            System.out.print("Enter task " + i + " name: ");
            String taskName = scanner.next();
            taskNamesMap.put(i, taskName);
            timetable.taskNames.put(i, taskName);
        }

        System.out.println("Enter task dependencies (e.g., A B for A -> B):");
        System.out.println("Enter -1 to end input.");

        while (true) {
            String task1 = scanner.next();
            if (task1.equals("-1")) {
                break;
            }
            String task2 = scanner.next();

            int task1Index = getKeyByValue(taskNamesMap, task1);
            int task2Index = getKeyByValue(taskNamesMap, task2);

            if (task1Index != -1 && task2Index != -1) {
                timetable.addEdge(task1Index, task2Index);
            } else {
                System.out.println("Invalid task name. Try again.");
            }
        }

        timetable.topologicalSort(); // Output the scheduled tasks

        scanner.close();
    }

    // Method to get the key by value in a map
    static int getKeyByValue(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return -1; // If not found
    }
}
