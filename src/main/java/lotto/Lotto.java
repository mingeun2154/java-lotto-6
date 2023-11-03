package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int LOTTO_NUMBER_MIN_INCLUDE = 1;
    private static final int LOTTO_NUMBER_MAX_INCLUDE = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) throws IllegalArgumentException {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto issue(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public int countHitNumbers(Lotto comparisonTarget) {
        int hit = 0;
        for (int number: comparisonTarget.numbers) {
            if (this.numbers.contains(number))
                hit++;
        }
        return hit;
    }

    private void validate(List<Integer> numbers) throws IllegalArgumentException {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) throws IllegalArgumentException {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicate(List<Integer> numbers) throws IllegalArgumentException {
        if (numbers.stream().distinct().collect(Collectors.toList()).size() < LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRange(List<Integer> numbers) throws IllegalArgumentException {
        for (int number: numbers) {
            if (number < LOTTO_NUMBER_MIN_INCLUDE || number > LOTTO_NUMBER_MAX_INCLUDE) {
                throw new IllegalArgumentException();
            }
        }
    }

    public boolean containsGivenNumber(int given) {
        return this.numbers.contains(given);
    }
}
