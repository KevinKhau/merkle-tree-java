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
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Input data must have one or more items.");
        }
        this.leaves = Arrays.stream(data).map(MerkleNode::new).collect(Collectors.toList());
        this.nodes = new ArrayList<>();
        buildMerkleTree(this.leaves);
        return this;
    }

    private void buildMerkleTree(List<MerkleNode> nodes) {
        if (nodes.size() == 1) {
            this.nodes.add(nodes.get(0));
            this.setRoot(nodes.get(0));
            return;
        }
        List<MerkleNode> parents = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i += 2) {
            if (i + 1 < nodes.size()) {
                this.nodes.add(nodes.get(i));
                this.nodes.add(nodes.get(i + 1));
                parents.add(new MerkleNode(nodes.get(i), nodes.get(i + 1))); // new node
            } else {
                parents.add(nodes.get(i)); // last node elevated to higher level
            }

        }
        buildMerkleTree(parents);
    }

    @Override
    public MerkleNode root() {
        return root;
    }

    @Override
    public int height() {
        // Constant implementation using binary tree properties
        return (int) Math.ceil(Math.log(this.leaves.size())/Math.log(2));
    }

    @Override
    public byte[][] level(int index) {
        int height = height();
        return this.nodes.stream().filter(n -> height - n.heightToLeaf() == index).map(MerkleNode::getHash).toArray(byte[][]::new);
    }

}
