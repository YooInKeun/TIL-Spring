import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String args[]) {
        /**
         * filter method: 람다식의 조건에 맞게 필터링
         */
        List<String> list = Arrays.asList("Yoo", "In", "Keun");
        List<String> filteredList = list.stream()
                .filter(i -> i.contains("n"))
                .collect(Collectors.toList());
        System.out.println(filteredList);

        List<Integer> scoreList = Arrays.asList(60, 59, 29, 93, 77);
        List<Integer> filteredScoreList = scoreList.stream()
                .filter(i -> i < 50)
                .collect(Collectors.toList());
        System.out.println(filteredScoreList);

//        List<Integer> filteredScoreList;
//        for (Integer score : scoreList) {
//            if (score < 50) {
//                filteredScoreList.add(score);
//            }
//        }
//        System.out.println(filteredScoreList);

        /**
         * map method: 람다 식의 결과를 반환
         */
        List<Integer> rectangularList = Arrays.asList(3, 5, 2, 7, 6);
        List<Integer> rectangularArea = rectangularList.stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
        System.out.println(rectangularArea);

//        Arrays.asList(3, 5, 2, 7, 6)
//                .stream()
//                .map(i -> i * i)
//                .forEach(System.out::println);

        List<String> helloWorld = Arrays.asList("Hello", "World");
        List<String> lowerCaseHelloWorld = helloWorld.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        System.out.println(lowerCaseHelloWorld);

//        lowerCaseHelloWorld = helloWorld.stream()
//                .map(String::toLowerCase)
//                .filter(i -> i.contains("hello"))
//                .collect(Collectors.toList());
//        System.out.println(lowerCaseHelloWorld);

        /**
         * sorted: 정렬
         */
        List<Integer> numbers = Arrays.asList(3, 6, 1, 2);
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedNumbers);

        sortedNumbers = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(sortedNumbers);

        /**
         * distinct: 중복 제거
         */
        List<String> names = Arrays.asList("홍길동", "세종대왕", "이순신", "세종대왕");
        List<String> distinctNames = names.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctNames);

        /**
         * peek: 특정 연산 수행, 주로 중간에 값 출력
         */
        List<Integer> randomNumbers = Arrays.asList(123, 124124, 15125, 23, 123, 124124, 5);
        List<Integer> selectedRandomNumbers = randomNumbers.stream()
                .filter(i -> i > 500)
                .map(i -> i * 2)
                .peek(System.out::println)
                .filter(i -> i < 150000)
                .peek(System.out::println)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(selectedRandomNumbers);
    }
}
