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
