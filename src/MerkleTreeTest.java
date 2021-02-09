import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class MerkleTreeTest {

    private String[] data;
    private MerkleTree merkleTree;
    MerkleTree root;

    @BeforeEach
    void beforeEach() {
        data = new String[]{"Sorare", "Kevin", "Blockchain", "Football"};
        merkleTree = MerkleTree.createMerkleTree(data);
    }

    @Test
    void givenRoot_whenGetHash_thenEqual() {
        assertNotNull(merkleTree);
        assertEquals(merkleTree.hashCode(), root.hashCode());
        assertEquals(merkleTree.getHash(), root.getHash());
    }

    @Test
    void root() {
        assertEquals(merkleTree.root(), root);
    }

    @Test
    void height() {
        assertEquals(merkleTree.height(), 2);
    }

    @Test
    void level() {
        assertEquals(merkleTree.level(4), null);
    }
}
