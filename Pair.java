public class Pair<T1,T2> {
    private T1 First;
    private T2 Second;

    public Pair(T1 f, T2 s){
        this.First = f;
        this.Second = s;
    }

    public T1 getFirst(){
        return this.First;
    }

    public T2 getSecond(){
        return this.Second;
    }

    public void setFirst(T1 f){
        this.First = f;
    }

    public void setSecond(T2 s){
        this.Second = s;
    }
}
