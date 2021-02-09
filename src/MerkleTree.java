import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MerkleTree implements MerkleTreeInterface {

    private MerkleNode root;
    private List<MerkleNode> children;

    static MerkleTree createMerkleTree(String[] data) {
        return null;
    }

    @Override
    public MerkleNode root() {
        return root;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public byte[][] level(int index) {
        return null;
    }



}
