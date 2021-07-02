public interface CHashInterface {
    public void put(CKey k, CData d);

    public CData get(CKey k);

    public int getProbes();

    public double getLoad();
}
