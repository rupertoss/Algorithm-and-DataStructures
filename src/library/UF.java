package library;

public abstract class UF {
	public abstract void union(int p, int q);
	public abstract int count();
	public abstract boolean connected(int p, int q);
	public abstract int find(int p);
}
