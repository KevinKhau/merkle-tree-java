import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MerkleTree implements MerkleTreeInterface {

    MerkleTree[] nodes;
    String hash;

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
