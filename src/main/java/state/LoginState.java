package state;

public class LoginState implements AppState {

    private final static int type = 1;

    @Override
    public AppState getNext() {
        AppState next = new UnLoginState();
        return next;
    }

    @Override
    public int getType() {
        return type;
    }

}
