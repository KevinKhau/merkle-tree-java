import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MerkleTree implements MerkleTreeInterface {

    private MerkleNode root;
    private List<MerkleNode> nodes;
    private List<MerkleNode> leaves;

    MerkleTree(String[] data) {
        createMerkleTree(data);
    }

    public MerkleTree createMerkleTree(String[] data) {
        this.leaves = Arrays.stream(data).map(MerkleNode::new).collect(Collectors.toList());
        this.nodes = new ArrayList<>();
        buildMerkleTree(this.leaves);
        return this;
    }

    private void buildMerkleTree(List<MerkleNode> nodes) {
        this.nodes.addAll(nodes);
        if (nodes.size() == 1) {
            this.setRoot(nodes.get(0));
            return;
        }
        List<MerkleNode> parents = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i += 2) {
            parents.add(new MerkleNode(nodes.get(i), (i + 1 < nodes.size()) ? nodes.get(i + 1) : null));
        }
        buildMerkleTree(parents);
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
