public interface MerkleTreeInterface {
    // Returns the Merkle root of the tree
    MerkleTree root();

    // Returns the number of levels of the tree
    int height();

    // Returns an Array containing the hashes of the given level
    String level(int index);
}
