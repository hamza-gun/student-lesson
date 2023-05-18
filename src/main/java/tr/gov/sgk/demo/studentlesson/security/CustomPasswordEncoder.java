//package tr.gov.sgk.demo.studentlesson.security;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class CustomPasswordEncoder implements PasswordEncoder {
//    @Override
//    public String encode(CharSequence rawPassword) {
//        // Burada parola şifreleme işlemini gerçekleştirin
//        // Örneğin, BCryptPasswordEncoder sınıfını kullanabilirsiniz:
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder.encode(rawPassword);
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        // Burada doğrulama işlemini gerçekleştirin
//        // Örneğin, BCryptPasswordEncoder sınıfını kullanabilirsiniz:
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder.matches(rawPassword, encodedPassword);
//    }
//}
