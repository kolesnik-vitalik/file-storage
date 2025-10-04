package urban.filestorage.service;

import com.github.sardine.Sardine;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import urban.filestorage.config.WebDavConfig;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final Sardine sardine;
    private final WebDavConfig webDavConfig;

    public void upload(String path, byte[] content) throws URISyntaxException, IOException {
        String fullPath = new URIBuilder(webDavConfig.getRoot()).setPath(path).toString();
        sardine.put(fullPath, content);
    }
}
