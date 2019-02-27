package hu.davidszabo.davefilesbackend.util;

import java.util.Arrays;

public class Sha256 {

    private byte[] sha256;

    public Sha256(byte[] sha256) {
        this.sha256 = sha256;
    }

    public byte[] getBytes() {
        return sha256;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : sha256) sb.append(Integer.toHexString(b & 0xff));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sha256 sha2561 = (Sha256) o;
        return Arrays.equals(sha256, sha2561.sha256);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(sha256);
    }
}