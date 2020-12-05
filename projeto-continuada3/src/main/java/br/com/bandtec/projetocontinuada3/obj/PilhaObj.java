package br.com.bandtec.projetocontinuada3.obj;

public class PilhaObj <T> {

    private T[] pilha;

    public PilhaObj(int tamanho) {
        this.pilha = (T[])new Object[tamanho];
    }

    Integer topo = -1;

    public boolean isEmpty(){
        if (topo == -1) {
            return true;
        }else{
            return false;
        }
    }

    public boolean isFull(){
        if(topo == pilha.length -1) {
            return true;
        }else {
            return false;
        }
    }

    public void push(T info) {
        if (!isFull()){
            topo++;
            pilha[topo] = info;
        }
    }

    public T peek(){
        if(!isEmpty()){
            return pilha[topo];
        }else {
            return null;
        }
    }

    public T pop(){
        if(!isEmpty()){
            return pilha[topo--];
        }else{
            return null;
        }
    }

    public void exibe(){
        if(isEmpty()){
            System.out.println("A pilha está fazia");
        }else {
            for(int i = 0; i <= topo; i++){
                System.out.println(pilha[i]);
            }
        }
    }

}
