import java.util.*;
class topology
{
    private int V;
    private LinkedList<Integer> adj[];
    
    topology(int v)
    {
        V=v;
        adj = new LinkedList[v];
        for (int i=0;i<V;i++)
        {
            adj[i] = new LinkedList();
        }
    }
    void addedge(int v, int w)
    {
        adj[v].add(w);
    }
    void topologi(int v, boolean visited[],Stack stk)
    {
        visited[v] =true;
        Integer i;
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();
            if(!visited[i])
            {
            topologi(i,visited ,stk);
            }   
        }
        stk.push(new Integer(v));
    }
    void sort()
    {
        Stack stk = new Stack();
        
        boolean visited[] = new boolean [V];
        for (int i = 0; i< V ; i++)
        visited[i] = false;
        for(int i = 0;i<V;i++)
        {
            if(visited[i] == false)
            topologi(i,visited,stk);
        }
        while(stk.empty()==false)
        System.out.print(stk.pop()+" ");
    }
        public static void main(String args[])
    {
        // Create a graph given in the above diagram
        topology g = new topology(6);
        g.addedge(5, 2);
        g.addedge(5, 0);
        g.addedge(4, 0);
        g.addedge(4, 1);
        g.addedge(2, 3);
        g.addedge(3, 1);
  
 
        System.out.println("Topological Sort");
        g.sort();
    }
}

