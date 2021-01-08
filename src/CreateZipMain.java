import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by glenc on Jan 2021
 **/
public class CreateZipMain {

    public static void main(String[] args) {

        String[] data = {
                "Shelf 1",
                "Shelf 2 2",
                "Shelf 3 3 3",
                "Shelf 4 4 4 4",
                "Shelf 5 5 5 5 5",
        };

        try(FileSystem zipfs = openZip(Paths.get("contents.zip"));){

            copyToZip(zipfs);
        }catch (Exception e){
            System.out.println(e.getClass().getSimpleName() + "  =  "+ e.getMessage());
        }

    }

    private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {

        Map<String,String> providerProp = new HashMap<>();
        providerProp.put("create","true");

        URI zipUri = new URI("jar:file",zipPath.toUri().getPath(),null);
        FileSystem zipFs = FileSystems.newFileSystem(zipUri,providerProp);

        return zipFs;
    }

    private static void copyToZip(FileSystem zipFs) throws IOException{

        Path sourceFile = Paths.get("file1.txt");

    }
}
