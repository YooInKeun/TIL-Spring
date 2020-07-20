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
    }
}
