package hu.davidszabo.davefilesbackend.repository;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FileMetaRepository extends JpaRepository<FileMeta, UUID> {

    Optional<FileMeta> findByPath(Path path);

    Collection<FileMeta> findAllBySha256IsNull();

    Collection<FileMeta> findAllByMainTypeIsNull();

    Collection<FileMeta> findAllByMainType(String mainType);

    Collection<FileMeta> findAllByPathEndingWith(String string);
}
