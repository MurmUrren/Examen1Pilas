public class Pila {
    private int[] pila;
    private int top;
    private int capacidad;

    public Pila(int capacidad) {
        this.capacidad = capacidad;
        pila = new int[capacidad];
        top = -1;
    }

    public void push(int elemento) {
        if (top == capacidad - 1) {
            throw new IllegalStateException("La pila está llena");
        }
        top++;
        pila[top] = elemento;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        int elemento = pila[top];
        top--;
        return elemento;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacidad - 1;
    }

    public int size() {
        return top + 1;
    }

    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return pila[top];
    }
}
