/*
HashingRing.java class

This class represents the hashing ring data structure that is used to store the nodes and keys.

@author: Mohamed Hassan
@date: 2026-02-24
*/

package main.model;

//importing the required libraries
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HashingRing {
    //instance variables
    private TreeMap<Long, VirtualNode> ring;
    private List<KeyEntry> keyEntry;
    private List<PhysicalNode> node;

    //constructor
    public HashingRing() {
        this.ring = new TreeMap<>();
        this.keyEntry = new ArrayList<>();
        this.node = new ArrayList<>();
    }

    //hash function that takes a string and returns a long
    public Long hash(String input) throws NoSuchAlgorithmException {
        // Making a SHA-1 digest engine
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // Feeding the input string as bytes
        byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        // Geeting the first bytes and wrapping them into a Long (0-7) and treats them a
        // single 64-bit number
        Long result = ByteBuffer.wrap(bytes, 0, 8).getLong();
        // Making sure the hash is positive and returning it
        return Math.abs(result);
    }

    //getOwner function that takes a long and returns a physical node
    public PhysicalNode getOwner(long hashValue) {
        //checking if the ring is empty
        if (ring.isEmpty()) {
            //throwing an exception if the ring is empty
            throw new IllegalStateException("There's no nodes in the ring");
        }
        //getting the ceiling entry of the ring
        Map.Entry<Long, VirtualNode> entry = ring.ceilingEntry(hashValue);
        //checking if the entry is null
        if (entry == null) {
            //getting the first entry of the ring
            entry = ring.firstEntry();
        }
        //returning the parent node(owner) of the virtual node
        return entry.getValue().getParentNode();
    }

    //assignKey function that takes a string and returns a key entry
    public KeyEntry assignKey(String keyName) throws NoSuchAlgorithmException {
        //checking if the ring is empty
        if (ring.isEmpty()) {
            //throwing an exception if the ring is empty 
            throw new IllegalStateException("There's no nodes in the ring");
        }
        //hashing the key name to get the position
        long position = hash(keyName);
        //getting the owner for the position
        PhysicalNode owner = getOwner(position);
        //making a new key entry with the key name, position, and owner
        KeyEntry key = new KeyEntry(keyName, position, owner);
        //adding the key entry to the list
        keyEntry.add(key);
        //returning the key entry
        return key;
    }

    //addNode function that takes a physical node and returns a list of key entries
    public List<KeyEntry> addNode(PhysicalNode physicalNode, int numVirtualNodes) throws NoSuchAlgorithmException {
        //adding the physical node to the list
        node.add(physicalNode);
        //looping through the number of virtual nodes
        for (int i = 0; i < numVirtualNodes; i++) {
            //hashing the physical node name and virtual node index to get the position
            String hashInput = physicalNode.getName() + "-" + i;
            long position = hash(hashInput);
            //making a new virtual node with the index, position, and physical node
            VirtualNode vnode = new VirtualNode(i, position, physicalNode);
            //adding the virtual node to the ring
            ring.put(position, vnode);
        }
        //making a new list for migrated key entries
        List<KeyEntry> migratedKeys = new ArrayList<>();
        //looping through the key entries
        for (KeyEntry keys : keyEntry) {
            //getting the new owner for the key
            PhysicalNode newOwner = getOwner(keys.getHashValue());
            //checking if the new owner is different from the assigned physical node
            if (newOwner != keys.getAssignedPhysicalNode()) {
                //setting the new owner for the key
                keys.setAssignedPhysicalNode(newOwner);
                //adding the key entry to the list of migrated key entries
                migratedKeys.add(keys);
            }
        }
        //returning the list of migrated key entries
        return migratedKeys;

    }

    //this removeNode function is nearly identical to the addNode function, only a few differences
    public List<KeyEntry> removeNode(PhysicalNode physicalNode, int numVirtualNodes) throws NoSuchAlgorithmException {
        //removing the physical node from the list
        node.remove(physicalNode);
        for (int i = 0; i < numVirtualNodes; i++) {
            String hashInput = physicalNode.getName() + "-" + i;
            long position = hash(hashInput);
            //removing the virtual node from the ring
            ring.remove(position);
        }
        List<KeyEntry> migratedKeys = new ArrayList<>();
        for (KeyEntry keys : keyEntry) {
            PhysicalNode newOwner = getOwner(keys.getHashValue());
            if (newOwner != keys.getAssignedPhysicalNode()) {
                keys.setAssignedPhysicalNode(newOwner);
                migratedKeys.add(keys);
            }
        }
        //returning the list of migrated key entries
        return migratedKeys;
    }

    //getStats function that returns a map of physical nodes and their number of keys
    public Map<PhysicalNode, Integer> getStats() {
        //making a hashmap to store the physical nodes and their number of keys
        HashMap<PhysicalNode, Integer> map = new HashMap<>();
        //looping through the key entries
        for (KeyEntry key : keyEntry) {
            //getting the owner node for the key
            PhysicalNode ownerNode = key.getAssignedPhysicalNode();
            //checking if the owner node is already in the map
            if (map.containsKey(ownerNode)) {
                //if the owner node is already in the map, incrementing the number of keys
                map.put(ownerNode, map.get(ownerNode) + 1);
            } else {
                //if the owner node is not in the map, adding it with a number of keys of 1
                map.put(ownerNode, 1);
            }
        }
        //returning the map of physical nodes and their number of keys
        return map;
    }

    //getters
    public List<PhysicalNode> getNodes() {
        return node;
    }
    public List<KeyEntry> getKeyEntry() {
        return keyEntry;
    }
    public List<VirtualNode> getVirtualNodes() {
        return new ArrayList<>(ring.values());
    }
}