/**
 * Created by Владимир on 02.08.2017.
 */
public class StringAlgorithms {

    // Проверка соответствия количества скобок
    public boolean balanceOfBrackets(String expression) {
        int count = 0;
        int len = expression.length();
        for (int i = 0; i < len; i++)
            if (expression.charAt(i) == '(')
                count++;
            else if (expression.charAt(i) == ')') {
                count--;
                if (count < 0) return false;
            }
        if (count != 0)
            return false;
        return true;
    }

    // Поиск подстроки в строке
    public int searchSubstring(String text, String pattern) {
        int len = text.length();
        int len2 = pattern.length();
        for (int i = 0; i < len; i++) {
            boolean check = true;
            for (int j = 0; j < len2; j++)
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    check = false;
                    break;
                }
            if (check)
                return i;
        }
        return -1;
    }

    // Алгоритм Бойера-Мура
    public int searchBoyerMoore(String text, String pattern) {
        int len = text.length();
        int lPat = pattern.length();
        char[] p = pattern.toCharArray();
        char[] t = text.toCharArray();
        if (len < lPat)
            return -1;
        int k = lPat - 1;
        int i = lPat - 1;
        int i1 = lPat-1;
        while (k >= 0 && i < len) {
            if (t[i] == p[k]) {
                k--;
                i--;
            } else {
                // Проверяем есть ли несовпавший элемент из текста в шаблоне, после индекса шаблона, на котором прервалось совпадение
                int pos = getLastPosition(t[i], p, k-1);
                // Такого индекса нет, и мы сдвигаем счётчик на длину шаблона
                if(pos == -1)
                    i1+=lPat;
                else
                    // Такой индекс есть, и мы считаем смещение и добавляем его к индексу на котором остановились в тексте
                    i1 = i +(lPat-pos);
                i = i1;
                k = lPat - 1;
            }
        }
        // Строка найдена
        if (k < 0)
            return i+1;
        return -1;
    }

    // Ищет последнее вхождение элемента в массив с определённого конечного положения
    private int getLastPosition(char c, char[] mas, int startIndex) {
        for (int i = startIndex; i > -1; i--)
            if (mas[i] == c) {
                return i+1;
            }
        return -1;
    }

}
