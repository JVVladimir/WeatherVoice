/**
 * Created by Владимир on 27.07.2017.
 */
public class MyHashTable {

    private static final int LENGTH = 100;
    private List[] mas = null;

    MyHashTable() { // добавить коэффициент заполнения
        mas = new List[LENGTH];
    }

    private int hashFunction(String key) { // Хеш-функция
        int sum;
        if (key.length() >= 2)
            sum = key.charAt(0) + key.charAt(1) + key.length();
        else
            sum = key.charAt(0);
        return sum % LENGTH; // Хеш-сумма
    }

    public void put(String key, Object object) {
        int index = hashFunction(key);
        if (mas[index] != null)
            mas[index].addLast(object);
        else {
            mas[index] = new List();
            mas[index].addFirst(object);
            mas[index].key = key;
        }
    }

    public boolean delete(String key, Object object) {
        int index = hashFunction(key);
        if (mas[index] == null)
            return false;
        mas[index].deleteLast();
        return true;
    }

    public Object find(String key) {// обьект надо искать а не только ключ
        int index = hashFunction(key);
        if (mas[index] == null)
            return null;
        return mas[index];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < LENGTH; i++)
            if (mas[i] == null) ;
            else
                str.append(mas[i].toString() + "\n");
        return str.toString();
    }
}
