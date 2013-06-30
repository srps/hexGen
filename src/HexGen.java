import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;

public class HexGen {

    public static void main(String[] args) {

        HexGen hg = new HexGen();

        int startSize = Integer.parseInt(args[0]);
        int endSize = Integer.parseInt(args[1]);

        StringBuilder sb;

        for (int size = startSize; size < endSize + 1; size++) {
            sb = new StringBuilder(size);
            sb.append("1");
            for (int i = size; i > 0; i--)
                sb.append("0");
            hg.run(size, sb.toString());
        }
    }

    public void run(int size, String s) {

        try (
                Writer w = new FileWriter("test" + size + ".txt")
        ) {
            String res;

            StringBuilder sb = new StringBuilder(size);

            BigInteger i = new BigInteger(s, 16);
            BigInteger j;

            for (j = BigInteger.ZERO; !j.equals(i); j = j.add(BigInteger.ONE)) {
                res = j.toString(16);

                if (res.length() != size) {
                    sb.append(s.substring(1, size - res.length() + 1));
                }
                sb.append(res);
                w.write(sb.toString() + System.getProperty("line.separator"));
                sb = new StringBuilder(size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
