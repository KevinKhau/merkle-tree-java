import lombok.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MerkleNode {

    @SneakyThrows
    public static byte[] hash(String data) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data.getBytes(StandardCharsets.UTF_8));
    }

}
