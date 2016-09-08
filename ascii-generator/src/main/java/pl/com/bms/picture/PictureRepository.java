package pl.com.bms.picture;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PictureRepository extends Repository<Picture, Long> {

    Optional<Picture> findOne(Long id);

    Iterable<Picture> findAll();

    void save(Picture picture);
}
