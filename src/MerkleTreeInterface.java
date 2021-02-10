/**
 * Interface with specified methods.
 */
public interface MerkleTreeInterface {

    MerkleTree createMerkleTree(String[] data);

    // Returns the Merkle root of the tree
    MerkleNode root();

    /**
     * Merkel root level is 0, thus in the given example height is 2.
     *
     * @return number of levels of the tree
     */
    int height();

    // Returns an Array containing the hashes of the given level
    byte[][] level(int index);
}
