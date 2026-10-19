package utils;

import java.io.FileReader;
import java.io.IOException;

public class File {
    private static final String SHADERS_DIR = "src/main/resources/shaders/";

    private final java.io.File file;
    private final FileTypes fileType;
    private long lastModified;

    public File( FileTypes fileType , String name, String extension ) {
        this.fileType = fileType;
        file = new java.io.File( getFileTypeDir( name ) + "." + extension );
        lastModified = file.lastModified();
    }

    public String read() {
        String output;
        try ( FileReader reader = new FileReader( file ) ) {
            output = reader.readAllAsString();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
        return output;
    }

    public boolean isModified() {
        boolean modified = lastModified != file.lastModified();
        if ( modified ) lastModified = file.lastModified();
        return modified;
    }

    private String getFileTypeDir( String name ) {
        return switch ( fileType ) {
            case SHADER -> SHADERS_DIR + name + "/shader";
        };
    }
}
