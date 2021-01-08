import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

/**
 * Created by glenc on Jan 2021
 **/
public class Main {

    public static void main(String[] args) {
        doTryWithResources();
        doTryCatchFinally();
        doTryWithResourcesMulti();
        doCustomClose();
    }

    public static void doTryCatchFinally(){
        char[] buff = new char[8];
        int length;

        Reader reader =  null;

        try{
            reader = Helper.openReader("file1.txt");
            while((length = reader.read(buff)) >= 0){
                System.out.println("length: " + length);
                for (int i = 0; i < length; i++){
                    System.out.println(buff[i]);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (reader != null)
                    reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void doTryWithResources(){
        char[] buff = new char[8];
        int length;


        try( Reader reader = Helper.openReader("file1.txt");)

        {

            while((length = reader.read(buff)) >= 0){
                System.out.println("length: " + length);
                for (int i = 0; i < length; i++){
                    System.out.println(buff[i]);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //allthe work to close is done for us in try with resources
    }


    public static void doTryWithResourcesMulti(){
        char[] buff = new char[8];
        int length;


        try( Reader reader = Helper.openReader("file1.txt");
             Writer writer = Helper.openWriter("file2.txt");
            )

        {

            while((length = reader.read(buff)) >= 0){
                System.out.println("length: " + length);
                writer.write(buff,0,length);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //allthe work to close is done for us in try with resources
    }

    public static void doCustomClose(){

        try (CustomAutoClosable ac = new CustomAutoClosable()){
            ac.someMessage();
        } catch (Exception e){
            e.printStackTrace();

            for(Throwable t : e.getSuppressed()){
                System.out.println("get the suppressed exceptions "+t);
            }
        }
    }

}
