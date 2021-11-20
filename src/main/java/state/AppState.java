package state;

public interface AppState {
    public AppState getNext();

    public int getType();
}
