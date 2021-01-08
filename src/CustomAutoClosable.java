import java.io.IOException;

/**
 * Created by glenc on Jan 2021
 **/
public class CustomAutoClosable implements AutoCloseable {

    public void someMessage() throws IOException{
        System.out.println("oh hi ");
    }

    @Override
    public void close() throws Exception {
        System.out.println("closed");
    }
}
