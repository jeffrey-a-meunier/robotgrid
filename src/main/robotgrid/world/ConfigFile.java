package robotgrid.world;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import robotgrid.utils.Result;

public class ConfigFile {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static Result<Properties, String> read(final String fileName) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
            properties.load(inputStream);
            return new Result.Success<Properties, String>(properties);
        }
        catch (final IOException exn) {
            return new Result.Failure<Properties, String>(exn.getMessage());
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (final IOException exn) {
                    return new Result.Failure<Properties, String>(exn.getMessage());
                }
            }
        }
    
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
