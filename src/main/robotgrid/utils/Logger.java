package robotgrid.utils;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static Level DEFAULT_LEVEL = Level.Info;

    // Static initializer =====================================================
    // Static methods =========================================================

    protected static String _timeStamp() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        final Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    // Instance inner classes =================================================

    public static enum Level { All, Debug, Test, Trace, Info, Warn, Error, Fatal }

    // Instance variables =====================================================

    protected Class<?> _context;
    protected Level _level;
    protected PrintStream _printStream = System.out;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Logger(final Class<?> context) {
        this(context, DEFAULT_LEVEL, System.out);
    }

    public Logger(final Class<?> context, final Level level) {
        this(context, level, System.out);
    }

    public Logger(final Class<?> context, final Level level, final PrintStream printStream) {
        _context = context;
        _level = level;
        _printStream = printStream;
    }

    // Instance methods =======================================================

    public Logger debug(final Object ... objs) {
        _log(Level.Debug, ANSIColor.GREEN_BG, ANSIColor.BLACK_FG, objs);
        return this;
    }

    public Logger error(final Object ... objs) {
        _log(Level.Error, ANSIColor.YELLOW_FG, ANSIColor.RED_BG, objs);
        return this;
    }

    public Logger fatal(final Object ... objs) {
        _log(Level.Fatal, ANSIColor.YELLOW_FG, ANSIColor.RED_BG, objs);
        throw new RuntimeException("Fatal exeception");
    }

    public Logger info(final Object ... objs) {
        _log(Level.Info, ANSIColor.BLACK_FG, ANSIColor.WHITE_BG, objs);
        return this;
    }

    public Logger test(final Object ... objs) {
        _log(Level.Test, ANSIColor.CYAN_FG, ANSIColor.BLUE_BG, objs);
        return this;
    }

    public Logger trace(final Object ... objs) {
        _log(Level.Trace, ANSIColor.BLACK_FG, ANSIColor.CYAN_BG, objs);
        return this;
    }

    public Logger warn(final Object ... objs) {
        _log(Level.Warn, ANSIColor.BLACK_FG, ANSIColor.YELLOW_BG, objs);
        return this;
    }

    public Logger setLevel(final Level level) {
        _level = level;
        return this;
    }

    public Logger setPrintStream(final PrintStream printStream) {
        _printStream = printStream;
        return this;
    }

    protected void _log(final Level level, final String fgANSIColor, final String bgANSIColor, final Object[] objects) {
        if (level.compareTo(_level) >= 0) {
            _printStream.flush();
            synchronized (_printStream) {
                _printStream.print(fgANSIColor + bgANSIColor);
                _printStream.printf("[%-5s]", level.toString().toUpperCase());
                _printStream.print(ANSIColor.RESET + " ");
                _printStream.print(_timeStamp());
                _printStream.print(" |");
                _printStream.print(_context.getSimpleName());
                _printStream.print("| ");
                for (final Object obj : objects)
                    _printStream.print(obj);
                _printStream.println();
            }
        }
    }

}