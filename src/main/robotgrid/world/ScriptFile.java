package robotgrid.world;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import robotgrid.server.Server;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;

public class ScriptFile {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private static final Logger _logger = new Logger(ScriptFile.class, Logger.Level.Debug);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static void run(final String fileName) {
        Result<List<String>, String> readResult = read(fileName);
        if (readResult.isSuccess) {
            _logger.info("Executing commands in " + fileName);
            List<String> commands = readResult.successValue();
            execute(commands);
        }
    }

    public static Result<Void, String> execute(final List<String> lines) {
        for (String line : lines) {
            String line1 = line.strip();
            if (line1.length() == 0) {
                continue;
            }
            if (line1.startsWith("#")) {
                continue;
            }
            _logger.debug("Executing command '" + line + '\'');
            Server.THE_SERVER.handleCommandString(line);
        }
        return new Result.Success<>();
    }

    public static Result<List<String>, String> read(final String fileName) {
        Path filePath = Paths.get(fileName);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            return new Result.Success<List<String>, String>(lines);
        }
        catch (final IOException exn) {
            String message = exn.getMessage();
            assert (message != null);
            return new Result.Failure<List<String>, String>(message);
        }
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
