//package tr.gov.sgk.demo.studentlesson.utility;
//
//import tr.gov.sgk.demo.studentlesson.entity.Student;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.IntStream;
//
//public class StudentUtils {
//    private static List<Student> books = new ArrayList<Student>();
//
//    private static final int NUM_BOOKS = 30;
//
//    private static final int MIN_BOOK_NUM = 1000;
//
//    public static List<Student> buildBooks() {
//        if (books.isEmpty()) {
//            IntStream.range(0, NUM_BOOKS).forEach(n -> {
//                books.add(new Student(MIN_BOOK_NUM + n + 1, "Spring in Action"));
//            });
//
//        }
//
//        return books;
//    }
//
//}
