import java.util.*;
class Solution {
    // Function to find the shortest path from a source node to all other nodes
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) 
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        int n=adj.size();

        int[] d=new int[n];
        
    
        
        for (int i = 0; i < n; i++) 
        {
            d[i]=Integer.MAX_VALUE;
            
        }
        d[src]=0;
        while(!q.isEmpty())
        {
            int node=q.poll();
            for(int it:adj.get(node))
            {
                if(d[node]+1<d[it])
                {
                d[it]=d[node]+1;
                q.add(it);
                }
            }
        }
        for (int i = 0; i < n; i++) 
        {
            if(d[i]==Integer.MAX_VALUE)
            {
                d[i]=-1;
            }
            
        }
        return d;
        
    }
}
