import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MerkleTreeTest {

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
        String[] data = new String[]{"Sorare", "Kevin", "Blockchain", "Football"};
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
    void givenNull_whenCreateMerkleTree_thenException() {
        try {
            MerkleTree emptyTree = new MerkleTree(null);
            fail("Thrown exception expected");
        } catch (IllegalArgumentException iae) {
            assertEquals("Input data must have one or more items.", iae.getMessage());
        }
    }

    @Test
    void givenEmpty_whenCreateMerkleTree_thenException() {
        String[] empty = new String[0];
        try {
            MerkleTree emptyTree = new MerkleTree(empty);
            fail("Thrown exception expected");
        } catch (IllegalArgumentException iae) {
            assertEquals("Input data must have one or more items.", iae.getMessage());
        }
    }

    @Test
    void givenData_whenCreateMerkleTree_then() {
        assertNotNull(merkleTree);
        assertEquals(4, merkleTree.getLeaves().size());
        assertEquals(Arrays.asList(node00, node01, node10, node11), merkleTree.getLeaves());
        assertEquals(7, merkleTree.getNodes().size());
        assertEquals(root, merkleTree.getRoot());
    }

    @Test
    void givenMerkleTree_whenRoot_then() {
        // Not Null
        assertNotNull(merkleTree);

        // Same root
        assertEquals(root, merkleTree.root());
        assertEquals(root.hashCode(), merkleTree.root().hashCode());

        // Same root hash
        assertArrayEquals(root.getHash(), merkleTree.root().getHash());
    }

    @Test
    void height() {
        assertEquals(merkleTree.height(), 2);
    }

    @Test
    void level() {
        assertArrayEquals(new byte[][]{node00.getHash(), node01.getHash(), node10.getHash(), node11.getHash()}, merkleTree.level(2));
        assertArrayEquals(new byte[][]{node0.getHash(), node1.getHash()}, merkleTree.level(1));
        assertArrayEquals(new byte[][]{root.getHash()}, merkleTree.level(0));
    }

    @Test
    void log() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + Math.log(i)/Math.log(2));
        }
    }
}
