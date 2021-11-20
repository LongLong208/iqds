package state;

public class UnLoginState implements AppState {

    private final static int type = 0;

    @Override
    public AppState getNext() {
        AppState next = new LoginState();
        return next;
    }

    @Override
    public int getType() {
        return type;
    }

}
