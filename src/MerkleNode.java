import lombok.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MerkleNode {

    private MerkleNode left;
    private MerkleNode right;
    private byte[] hash;

    public MerkleNode(MerkleNode left, MerkleNode right) {
        this.left = left;
        this.right = right;
    }
    public MerkleNode(byte[] hash) {
        this.hash = hash;
    }
    public MerkleNode(String data) {
        this.hash = hash(data);
    }

    @SneakyThrows
    public static byte[] hash(String data) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data.getBytes(StandardCharsets.UTF_8));
    }

}
