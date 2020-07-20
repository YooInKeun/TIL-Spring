import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
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
    }
}
