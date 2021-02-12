import lombok.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "parent")
public class MerkleNode {

    private String hash;
    private MerkleNode parent;
    private MerkleNode left;
    private MerkleNode right;

    public MerkleNode(MerkleNode left, MerkleNode right) {
        this.left = left;
        this.right = right;
        this.left.setParent(this);
        this.right.setParent(this);
        this.hash = hash(concatHashes(left.hash, right.hash));
    }
    public MerkleNode(String data) {
        this.hash = hash(data);
    }

    /**
     * @param h1 left node hash
     * @param h2 right node hash
     * @return concatenation of both hashes
     */
    public static String concatHashes(String h1, String h2) {
        return h1 + h2;
    }

    /**
     * Used to compute MerkleTree#level without adding a `level` property
     * to MerkleNode.
     * @return height from this node to the root, using `parent` property.
     */
    public int heightToRoot() {
        MerkleNode node = this;
        int distance = 0;
        while (node.getParent() != null) {
            node = node.getParent();
            distance++;
        }
        return distance;
    }

    @SneakyThrows
    public static String hash(String data) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
