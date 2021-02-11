import lombok.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MerkleNode {

    private byte[] hash;
    private MerkleNode left;
    private MerkleNode right;

    public MerkleNode(MerkleNode left, MerkleNode right) {
        this.left = left;
        this.right = right;
        this.hash = hash(concatHashes(left.hash, right.hash));
    }
    public MerkleNode(String data) {
        this.hash = hash(data);
    }

    private String concatHashes(byte[] h1, byte[] h2) {
        byte[] concatenation = Arrays.copyOf(h1, h1.length + h2.length);
        System.arraycopy(h2, 0, concatenation, h1.length, h2.length);
        return new String(concatenation, StandardCharsets.UTF_8);
    }
    /**
     * Used to compute MerkleTree#level without adding a `level` property
     * to MerkleNode.
     * @return height from this node to the leaf.
     */
    public int heightToLeaf() {
        MerkleNode node = this;
        int distance = 0;
        while (node.left != null) {
            node = node.left;
            distance++;
        }
        return distance;
    }

    @SneakyThrows
    public static byte[] hash(String data) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data.getBytes(StandardCharsets.UTF_8));
    }

}
