public interface MerkleTreeInterface {
    // Returns the Merkle root of the tree
    MerkleNode root();

    /**
     * Merkel root level is 0, while in the given example it is 2.
     *
     * @return number of levels of the tree
     */
    int height();

    // Returns an Array containing the hashes of the given level
    byte[][] level(int index);
}
