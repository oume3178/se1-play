package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class NumbersImpl implements Numbers {

    @Override
    public long sum(int[] numbers) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        long total = 0;
        for (int num : numbers) total += num;
        return total;
    }

    @Override
    public long sumPositiveEvenNumbers(int[] numbers) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        long total = 0;
        for (int num : numbers) {
            if (num > 0 && num % 2 == 0) total += num;
        }
        return total;
    }

    @Override
    public long sumRecursive(int[] numbers, int i) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        if (i < 0 || i >= numbers.length) return 0;
        return numbers[i] + sumRecursive(numbers, i + 1);
    }

    @Override
    public int findFirst(int[] numbers, int x) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == x) return i;
        }
        return -1;
    }

    @Override
    public int findLast(int[] numbers, int x) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == x) return i;
        }
        return -1;
    }

    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == x) result.add(i);
        }
        return result;
    }

    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        Set<Pair> pairs = new LinkedHashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == sum) {
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
        if (numbers == null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        Set<Set<Integer>> results = new LinkedHashSet<>();
        findSubsets(sorted, sum, 0, new ArrayList<>(), results);
        return results;
    }

    private void findSubsets(int[] numbers, int targetSum, int startIndex, 
                             List<Integer> currentSubset, Set<Set<Integer>> results) {
        if (targetSum == 0) {
            results.add(new LinkedHashSet<>(currentSubset));
            return;
        }
        for (int i = startIndex; i < numbers.length; i++) {
            if (numbers[i] > targetSum) break;
            currentSubset.add(numbers[i]);
            findSubsets(numbers, targetSum - numbers[i], i + 1, currentSubset, results);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}