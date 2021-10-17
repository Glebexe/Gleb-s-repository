import java.util.*;

public class Task_5 implements Task_5_base {
    @Override
    public ArrayList<Integer> subtask_1_ArrayList(ArrayList<Integer> data, int k, int n) {
        // Выбрать из данного списка элементы, кратные k, но не кратные n
        // и сформировать из них новый список.
        // Вернуть новый список в качестве результата
        ArrayList<Integer> intArr = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            if(data.get(i) % k == 0 && data.get(i) % n != 0){
                intArr.add(data.get(i));
            }
        }
        return intArr;
    }

    @Override
    public ArrayList<Integer> subtask_2_ArrayList(int size) {
        // сгенерировать и вернуть список размера size,
        // содержащий первые size элементов последовательности, описанной в
        // задаче subtask_2_for задания task_3
        ArrayList<Integer> intAr = new ArrayList<>();
        for(int i = 1; intAr.size() < size; i++){
            for(int j = 0; j < i && intAr.size() < size; j++){
                intAr.add(Integer.parseInt(Integer.toString(i)));
            }
        }
        return intAr;
    }

    @Override
    public HashSet<Integer> subtask_3_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // найдите пересечение множеств s1 и s2
        s1.retainAll(s2);
        return s1;
    }

    @Override
    public HashSet<Integer> subtask_4_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // найдите объединение множеств s1 и s2
        s1.addAll(s2);
        return s1;
    }

    @Override
    public HashSet<Integer> subtask_5_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // найдите дополнение множества s1 до множества s2
        HashSet<Integer> hsInter= new HashSet<>(s1);
        hsInter.retainAll(s2);
        s2.removeAll(hsInter);
        return s2;
    }

    @Override
    public HashSet<Integer> subtask_6_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // постройте множество, содержащее элементы, содержащиеся либо только в s1,
        // либо только в s2, но не в обоих множествах одновременно
        HashSet<Integer> hsInter= new HashSet<>(s1);
        HashSet<Integer> hsUniqS1 = new HashSet<>(s1);
        HashSet<Integer> hsUniqS2= new HashSet<>(s2);

        hsInter.retainAll(s2);
        hsUniqS1.removeAll(hsInter);
        hsUniqS2.removeAll(hsInter);

        HashSet<Integer> hs = new HashSet<>(hsUniqS1);
        hs.addAll(hsUniqS2);
        return hs;
    }

    @Override
    public HashMap<String, Double> subtask_7_HashMap(ArrayList<String> data) {
        // Дан список строк. Построить словарь, содержащий частоты слов
        // для данного списка в процентах
        HashMap<String,Double> hm = new HashMap<>();
        for(String i: data){
            hm.put(i, (double)Collections.frequency(data,i)*100/data.size());
        }
        return hm;
    }

    @Override
    public HashMap<String, Double> subtask_8_HashMap(ArrayList<Double> data) {
        // Дан список чисел. Сформировать словарь, содержащий среднее,
        // максимальное и минимальное значения из данного списка. Ключи
        // словаря "mean", "max", "min" соответственно
        HashMap<String, Double> hm = new HashMap<>();
        hm.put("max", Collections.max(data));
        hm.put("min", Collections.min(data));
        hm.put("mean", data.stream().mapToDouble(i->i).average().getAsDouble());
        return hm;
    }
}