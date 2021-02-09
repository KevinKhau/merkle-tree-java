import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

class MerkleTreeTest {

    @Test
    void givenArray_whenCreateMerkleTree_thenMerkleTree() {
        String[] data = new String[]{"Sorare", "Kevin", "Blockchain", "Football"};
        MerkleTree merkleTree = MerkleTree.createMerkleTree(data);
        assertNotNull(merkleTree);
    }

    @Test
    void root() {
    }

    @Test
    void height() {
    }

    @Test
    void level() {
    }
}
