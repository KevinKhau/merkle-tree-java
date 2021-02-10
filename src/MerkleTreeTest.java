import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MerkleTreeTest {

    private String[] data;
    private MerkleTree merkleTree;
    MerkleNode node00;
    MerkleNode node01;
    MerkleNode node10;
    MerkleNode node11;
    MerkleNode node0;
    MerkleNode node1;
    MerkleNode root;

    @BeforeEach
    void beforeEach() {
        data = new String[]{"Sorare", "Kevin", "Blockchain", "Football"};
        merkleTree = new MerkleTree(data);
        node00 = new MerkleNode("Sorare");
        node01 = new MerkleNode("Kevin");
        node10 = new MerkleNode("Blockchain");
        node11 = new MerkleNode("Football");
        node0 = new MerkleNode(node00, node01);
        node1 = new MerkleNode(node10, node11);
        root = new MerkleNode(node0, node1);
    }

    @Test
    void givenData_whenCreateMerkleTree_then() {
        assertEquals(merkleTree.getLeaves().size(), 4);
        assertEquals(merkleTree.getLeaves(), Arrays.asList(node00, node01, node10, node11));
        assertEquals(merkleTree.getNodes().size(), 7);
        assertEquals(merkleTree.getRoot(), root);
    }

    @Test
    void givenData_whenCreateMerkleTree_thenEqualsRoot() {
        assertNotNull(merkleTree);
        assertEquals(merkleTree.getRoot(), root);
        assertEquals(merkleTree.getRoot().getHash(), root.getHash());
    }

    @Test
    void givenMerkleTree_whenRoot_then() {
        // Not Null
        assertNotNull(merkleTree);

        // Same root
        assertEquals(merkleTree.root(), root);
        assertEquals(merkleTree.root().hashCode(), root.hashCode());

        // Same root hash
        assertEquals(merkleTree.root().getHash(), root.getHash());
    }

    @Test
    void height() {
        assertEquals(merkleTree.height(), 2);
    }

    @Test
    void level() {
        assertEquals(merkleTree.level(0), new byte[][]{root.getHash()});
        assertEquals(merkleTree.level(1), new byte[][]{node0.getHash(), node1.getHash()});
        assertEquals(merkleTree.level(2), new byte[][]{node00.getHash(), node01.getHash(), node10.getHash(), node11.getHash()});
    }

    @Test
    void log() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + Math.log(i)/Math.log(2));
        }
    }
}
