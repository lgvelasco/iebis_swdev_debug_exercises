import java.math.BigDecimal;

public class Main {

        public static void main(String [] args) {
            BigDecimal d = new BigDecimal(0.0);
            BigDecimal max = new BigDecimal(1.0);

            while (d.equals(max)) {
                BigDecimal add = new BigDecimal(0.1);
                d = d.add(add);
            }

            System.out.println("d is 1");
        }
}
