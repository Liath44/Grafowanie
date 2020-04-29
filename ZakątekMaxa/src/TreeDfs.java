import java.util.Vector;

public class TreeDfs {
	
	private Vector<Vector<Integer>> levels = new Vector<>();
	private boolean odw[] ;
	
	public boolean getodw(int index) {
		return odw[index];
	}
	
	public void DFS(Integer akt, Integer level, Graf V) {
		//System.out.println(akt);
		if(this.levels.size() == level)
			this.levels.add(new Vector<>());
		this.levels.get(level).add(akt);
		this.odw[akt] = true;
		for(Integer n : V.pts.get(akt).neigh) {
			if(!odw[n])
				DFS(n, level+1, V);
		}
	}
	
	public void setcoordinates(Graf V, int x, int y) {
		int skoky = y/(levels.size()+1);
		for(int level = 0; level < levels.size(); level++) {
			int skokx = x/(levels.get(level).size()+1);
			Vector<Integer> aktlvl = levels.get(level);
			for(int i = 0; i < levels.get(level).size(); i++) {
				Point akt = V.pts.get(aktlvl.get(i));
				akt.setx(-x/2+(i+1)*skokx);
				akt.sety(-y/2+(level+1)*skoky);
			}
		}
	}
	
	public TreeDfs(Graf V, int root) {
		this.odw = new boolean[V.n];
		DFS(root, 0, V);
		for(int i = 0; i < V.n; i++) {
			if(!odw[i])
				DFS(i, 0, V);
		}
	}
	
}
