package hu.davidszabo.davefilesbackend.entity.converter;

import hu.davidszabo.davefilesbackend.util.Sha256;

import javax.persistence.AttributeConverter;

public class Sha256Converter implements AttributeConverter<Sha256, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(Sha256 sha256) {
        if (sha256 == null)
            return null;
        return sha256.getBytes();
    }

    @Override
    public Sha256 convertToEntityAttribute(byte[] bytes) {
        if (bytes == null)
            return null;
        return new Sha256(bytes);
    }
}