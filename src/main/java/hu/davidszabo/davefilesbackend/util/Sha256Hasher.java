package hu.davidszabo.davefilesbackend.util;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Sha256Hasher {

    private MessageDigest messageDigestInstance;

    public Sha256Hasher() throws NoSuchAlgorithmException {
        this.messageDigestInstance = MessageDigest.getInstance("SHA-256");
    }

    public Sha256 getSha256(FileMeta file) throws IOException {
        return getSha256(file.getPath());
    }

    public Sha256 getSha256(Path path) throws IOException {
        return getSha256(Files.newInputStream(path));
    }

    public Sha256 getSha256(InputStream inputStream) throws IOException {
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigestInstance);
        byte[] inputStreamBuffer = new byte[8192];
        while (digestInputStream.read(inputStreamBuffer) > -1) ;
        inputStream.close();
        return new Sha256(digestInputStream.getMessageDigest().digest());
    }

}
