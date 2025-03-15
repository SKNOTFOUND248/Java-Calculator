class stack {
    private final char[] ch;
    private int size;
    stack(int n){
        ch = new char[n];
        size = 0;
    }
    public boolean empty(){
        return ch.length == 0;
    }
    public boolean isfull(){
        return size == ch.length-1;
    }
    public void push(char c){
        if(isfull()){
            return;
        }
        ch[size++] = c;
    }
    public char pop(){
        if(empty()){
            return ' ';
        }
        return ch[size--];
    }
    public char peek(){
        return ch[size];
    }
}
