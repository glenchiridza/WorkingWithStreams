import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
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
//        or could use Path sourceFile = FileSystems.getDefault().getPath("file1.txt") but Path is shorter
        Path destinationFile = zipFs.getPath("/fileCopied.txt");

        Files.copy(sourceFile,destinationFile,StandardCopyOption.REPLACE_EXISTING);

//        StandardCopyOption.REPLACE_EXISTING -- if there is an already existing file, replace it
    }
}
