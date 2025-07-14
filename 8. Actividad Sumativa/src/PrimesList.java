import java.util.ArrayList;

public class PrimesList extends ArrayList<Integer> {

    public PrimesList() {
        super();
    }
    // metodo estático para verificar si un número es primo
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    @Override
    public boolean add(Integer n) {// verifica si el número es primo antes de añadirlo
        if (isPrime(n)) {
            return super.add(n);
        } else {
            throw new IllegalArgumentException("Solo numeros primos pueden ser añadidos a esta lista.");
        }
    }
    @Override
    public boolean remove(Object o) { // método para eliminar un número primo
        if (o instanceof Integer && isPrime((Integer) o)) { //
            return super.remove(o);
        } else {
            throw new IllegalArgumentException("Solo numeros primos pueden ser eliminados de esta lista.");
        }
    }
    public int getPrimeCount() { // método para obtener la cantidad de números primos en la lista
        return this.size();
    }
}