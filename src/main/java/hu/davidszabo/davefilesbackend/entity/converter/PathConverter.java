package hu.davidszabo.davefilesbackend.entity.converter;

import javax.persistence.AttributeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathConverter implements AttributeConverter<Path, String> {

    @Override
    public String convertToDatabaseColumn(Path path) {
        return path.toString();
    }

    @Override
    public Path convertToEntityAttribute(String s) {
        return Paths.get(s);
    }
}