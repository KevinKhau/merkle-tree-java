import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MerkleTreeTest {

    private MerkleTree oneLeafTree;
    private MerkleTree twoLeafTree;
    private MerkleTree threeLeafTree;
    private MerkleTree fourLeafTree;
    private MerkleTree fiveLeafTree;
    private MerkleTree twentySevenLeafTree;
    MerkleNode node00;
    MerkleNode node01;
    MerkleNode node10;
    MerkleNode node11;
    MerkleNode node0;
    MerkleNode node1;
    MerkleNode root;

    @BeforeEach
    void beforeEach() {
        oneLeafTree = new MerkleTree(new String[]{"https://kevinkhau.github.io/polygons/index.html"});
        twoLeafTree = new MerkleTree(new String[]{"Satoshi Sakamoto", "Bitcoin"});
        threeLeafTree = new MerkleTree(new String[]{"Model", "View", "Controller"});
        fiveLeafTree = new MerkleTree(new String[]{"PSOne", "PS2", "PS3", "PS4", "PS5"});
        twentySevenLeafTree = new MerkleTree(IntStream.range(0, 27).mapToObj(i -> ((Integer) i).toString()).toArray(String[]::new));

        String[] data = new String[]{"Sorare", "Kevin", "Blockchain", "Football"};
        fourLeafTree = new MerkleTree(data);
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
            new MerkleTree(null);
            fail("Thrown exception expected");
        } catch (IllegalArgumentException iae) {
            assertEquals("Input data must have one or more items.", iae.getMessage());
        }
    }

    @Test
    void givenEmpty_whenCreateMerkleTree_thenException() {
        String[] empty = new String[0];
        try {
            new MerkleTree(empty);
            fail("Thrown exception expected");
        } catch (IllegalArgumentException iae) {
            assertEquals("Input data must have one or more items.", iae.getMessage());
        }
    }

    @Test
    void givenFourLeaf_whenCreateMerkleTree_then() {
        assertNotNull(fourLeafTree);
        assertEquals(4, fourLeafTree.getLeaves().size());
        assertEquals(Arrays.asList(node00, node01, node10, node11), fourLeafTree.getLeaves());
        assertEquals(7, fourLeafTree.getNodes().size());
        assertEquals(root, fourLeafTree.getRoot());
    }

    @Test
    void givenFourLeaf_whenRoot_then() {
        // Not Null
        assertNotNull(fourLeafTree);

        // Same root
        assertEquals(root, fourLeafTree.root());
        assertEquals(root.hashCode(), fourLeafTree.root().hashCode());

        // Same root hash
        assertEquals(root.getHash(), fourLeafTree.root().getHash());
    }

    /* Number of expected nodes : leaves * 2 - 1 */
    @Test
    void givenFourLeaf_whenGetNodeSize_then7() {
        assertEquals(7, fourLeafTree.getNodes().size());
    }
    @Test
    void givenOneLeaf_whenGetNodeSize_then1() {
        assertEquals(1, oneLeafTree.getNodes().size());
    }
    @Test
    void givenTwoLeaf_whenGetNodeSize_then3() {
        assertEquals(3, twoLeafTree.getNodes().size());
    }
    @Test
    void givenThreeLeaf_whenGetNodeSize_then5() {
        assertEquals(5, threeLeafTree.getNodes().size());
    }
    @Test
    void givenFiveLeaf_whenGetNodeSize_then9() {
        assertEquals(9, fiveLeafTree.getNodes().size());
    }
    @Test
    void givenTwentySevenLeaf_whenGetNodeSize_then53() {
        assertEquals(53, twentySevenLeafTree.getNodes().size());
    }

    @Test
    void givenFourLeaf_whenHeight_then2() {
        assertEquals(2, fourLeafTree.height());
    }
    @Test
    void givenOneLeaf_whenHeight_then0() {
        assertEquals(0, oneLeafTree.height());
    }
    @Test
    void givenTwoLeaf_whenHeight_then1() {
        assertEquals(1, twoLeafTree.height());
    }
    @Test
    void givenThreeLeaf_whenHeight_then2() {
        assertEquals(2, threeLeafTree.height());
    }
    @Test
    void givenFiveLeaf_whenHeight_then3() {
        assertEquals(3, fiveLeafTree.height());
    }
    @Test
    void givenTwentySevenLeaf_whenHeight_then5() {
        assertEquals(5, twentySevenLeafTree.height());
    }

    @Test
    void givenOneLeaf_whenLevel_thenSameHash() {
        assertArrayEquals(new String[]{MerkleNode.hash("https://kevinkhau.github.io/polygons/index.html")}, oneLeafTree.level(0));
    }

    @Test
    void givenThreeLeaf_whenLevel1_thenSameHash() {
        String hashModelView = MerkleNode.hash(MerkleNode.concatHashes(MerkleNode.hash("Model"), MerkleNode.hash("View")));
        assertArrayEquals(new String[]{hashModelView, MerkleNode.hash("Controller")}, threeLeafTree.level(1));
    }

    @Test
    void givenOneLeaf_whenLevel1_thenException() {
        try {
            oneLeafTree.level(1);
            fail("Thrown exception expected");
        } catch (IllegalArgumentException iae) {
            assertEquals("Input index incorrect. Expected: less or equal to 0.", iae.getMessage());
        }
    }

    @Test
    void givenThreeLeaf_whenLevel3_thenException() {
        try {
            threeLeafTree.level(3);
            fail("Thrown exception expected");
        } catch (IllegalArgumentException iae) {
            assertEquals("Input index incorrect. Expected: less or equal to 2.", iae.getMessage());
        }
    }

    @Test
    void givenFourLeaf_whenLevelX_thenSameHash() {
        assertArrayEquals(new String[]{node00.getHash(), node01.getHash(), node10.getHash(), node11.getHash()}, fourLeafTree.level(2));
        assertArrayEquals(new String[]{node0.getHash(), node1.getHash()}, fourLeafTree.level(1));
        assertArrayEquals(new String[]{root.getHash()}, fourLeafTree.level(0));
    }

    @Test
    void printExpectedHeightUsingLog() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Leaves: " + i + " ; Height: " + (int) Math.ceil(Math.log(i)/Math.log(2)));
        }
    }
}
