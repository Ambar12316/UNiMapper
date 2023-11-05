import java.util.*;

class Timetable1 {
    private int V; // Number of vertices or tasks
    private LinkedList<Integer> adj[];

    Timetable1(int v) {
        V = v;
        adj = new LinkedList[v];
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

        // Print the schedule
        System.out.println("Scheduled tasks:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();

        Timetable timetable = new Timetable(numTasks);

        System.out.println("Enter task dependencies (e.g., 0 1 for A -> B):");
        System.out.println("Enter -1 to end input.");

        while (true) {
            int task1 = scanner.nextInt();
            if (task1 == -1) {
                break;
            }
            int task2 = scanner.nextInt();
            timetable.addEdge(task1, task2);
        }

        timetable.topologicalSort(); // Output the scheduled tasks

        scanner.close();
    }
}
