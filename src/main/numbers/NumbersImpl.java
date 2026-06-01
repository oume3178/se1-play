package numbers;

import java.util.List;
import java.util.Set;

class NumbersImpl implements Numbers {

    @Override
    public long sum(int[] numbers) {
        if (numbers == null) return 0;
        long total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    @Override
    public long sumPositiveEvenNumbers(int[] numbers) {
        if (numbers == null) return 0;
        long total = 0;
        for (int num : numbers) {
            if (num > 0 && num % 2 == 0) {
                total += num;
            }
        }
        return total;
    }

    @Override
    public long sumRecursive(int[] numbers, int i) {
        if (numbers == null || i < 0 || i >= numbers.length) {
            return 0;
        }
        return numbers[i] + sumRecursive(numbers, i + 1);
    }

    // Dummy-Implementierungen für den Moment, damit es kompiliert
    @Override
    public int findFirst(int[] numbers, int x) {
        if (numbers == null) return -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int findLast(int[] numbers, int x) {
        if (numbers == null) return -1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        if (numbers == null) return List.of();
        List<Integer> indices = new java.util.ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == x) {
                indices.add(i);
            }
        }
        return indices;
    }
    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {
        if (numbers == null) return Set.of();
        
        // Wir nutzen ein Set, um Duplikate wie (a,b) und (b,a) automatisch zu filtern
        Set<Pair> pairs = new java.util.LinkedHashSet<>();
        
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == sum) {
                    // Sortieren, damit (5,7) und (7,5) als gleiches Paar erkannt werden
                    int a = Math.min(numbers[i], numbers[j]);
                    int b = Math.max(numbers[i], numbers[j]);
                    pairs.add(new Pair(a, b));
                }
            }
        }
        return pairs;
    }
    @Override
    public Set<Set<Integer>> findAllSums(int[] numbers, int sum) {
        if (numbers == null) return Set.of();
        
        // Wir sortieren das Array zuerst. Das ist der Trick, um den Algorithmus extrem schnell zu machen!
        int[] sorted = numbers.clone();
        java.util.Arrays.sort(sorted);
        
        Set<Set<Integer>> results = new java.util.LinkedHashSet<>();
        findSubsets(sorted, sum, 0, new java.util.ArrayList<>(), results);
        return results;
    }

    // Hilfsmethode für das rekursive Backtracking
    private void findSubsets(int[] numbers, int targetSum, int startIndex, 
                             List<Integer> currentSubset, Set<Set<Integer>> results) {
        
        if (targetSum == 0) {
            // Treffer! Wir fügen die gefundene Kombination als neues Set hinzu
            results.add(new java.util.LinkedHashSet<>(currentSubset));
            return;
        }

        for (int i = startIndex; i < numbers.length; i++) {
            // Der "Bruteforce-Killer": Da das Array sortiert ist, können wir sofort abbrechen,
            // wenn die aktuelle Zahl schon größer als die gesuchte Restsumme ist!
            if (numbers[i] > targetSum) {
                break;
            }
            
            // Zahl zum aktuellen Versuch hinzufügen
            currentSubset.add(numbers[i]);
            
            // Rekursiv weitersuchen mit der neuen Restsumme
            findSubsets(numbers, targetSum - numbers[i], i + 1, currentSubset, results);
            
            // Zahl wieder entfernen (Backtracking), um den nächsten Zweig zu probieren
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}