package hu.davidszabo.davefilesbackend.entity;

import hu.davidszabo.davefilesbackend.entity.converter.PathConverter;
import hu.davidszabo.davefilesbackend.entity.converter.Sha256Converter;
import hu.davidszabo.davefilesbackend.util.Sha256;
import lombok.Data;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.UUID;

@Entity
@Data
public class FileMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;
    private String name;
    @Convert(converter = PathConverter.class)
    @Column(length = 1000)
    private Path path;
    @Convert(converter = Sha256Converter.class)
    private Sha256 sha256;
    private String mainType;
    private String subType;
    private long size;
    private long lastModified;
    @Version
    private Long ver;

    private FileMeta() {
    }

    public FileMeta(String name, Path path) {
        this.name = name;
        this.path = path;
    }
}
