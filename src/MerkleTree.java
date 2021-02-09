import lombok.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MerkleTree implements MerkleTreeInterface {

    private MerkleNode root;
    private List<MerkleNode> children;
    private byte[] hash;

    static MerkleTree createMerkleTree(String[] data) {
        return null;
    }

    @Override
    public MerkleTree root() {
        return null;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public String level(int index) {
        return null;
    }



}
