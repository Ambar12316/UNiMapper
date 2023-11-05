package Minor;

import java.util.*;

class Timetable {
    private int V; // Number of vertices or tasks
    private LinkedList<Integer> adj[];

    Timetable(int v) {
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
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String args[]) {
        Timetable timetable = new Timetable(5); // 5 tasks: A, B, C, D, E

        // Define the dependencies (edges)
        timetable.addEdge(0, 1); // A -> B
        timetable.addEdge(0, 3); // A -> D
        timetable.addEdge(1, 2); // B -> C
        timetable.addEdge(2, 3); // C -> D
        timetable.addEdge(3, 4); // D -> E

        System.out.println("Scheduled tasks:");
        timetable.topologicalSort(); // Output the scheduled tasks
    }
}
