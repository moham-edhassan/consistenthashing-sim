package main.model;

import java.util.TreeMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HashingRing {
    private TreeMap<Long, VirtualNode> ring;

    public HashingRing() {
        this.ring = new TreeMap<>();
    }

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

}
