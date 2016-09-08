package pl.com.bms.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
class DatabasePopulator implements CommandLineRunner {

    private final PictureRepository pictureRepository;

    @Autowired
    public DatabasePopulator(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        pictureRepository.save(new Picture("Image 1", new URL("http://localhost:7777/1.jpg") ));
        pictureRepository.save(new Picture("Image 2", new URL("http://localhost:7777/2.jpg" )));
        pictureRepository.save(new Picture("Image 3", new URL("http://localhost:7777/3.jpg" )));
        pictureRepository.save(new Picture("Image 4", new URL("http://localhost:7777/4.jpg" )));
    }
}
