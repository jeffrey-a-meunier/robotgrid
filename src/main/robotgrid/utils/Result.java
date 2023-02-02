package robotgrid.utils;

import java.util.Optional;

public class Result<S, F> {

    // Static inner classes ===================================================

    public static class Success<S, F> extends Result<S, F> {
        public Success() {
            super(true, Optional.empty(), Optional.empty());
        }
        public Success(final S value) {
            super(true, Optional.of(value), Optional.empty());
        }
    }

    public static class Failure<S, F> extends Result<S, F> {
        public Failure() { 
            super(false, Optional.empty(), Optional.empty());
        }
        public Failure(final F value) {
            super(false, Optional.empty(), Optional.of(null));
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final boolean isSuccess;
    public final boolean isFailure;
    protected final Optional<S> _successValue;
    protected final Optional<F> _failureValue;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Result(final boolean isSuccess, final Optional<S> successValue, final Optional<F> failureValue) {
        this.isSuccess = isSuccess;
        this.isFailure = !isSuccess;
        this._successValue = successValue;
        this._failureValue = failureValue;
    }

    // Instance methods =======================================================

    public S successValue() {
        return _successValue.get();
    }

    public F failureValue() {
        return _failureValue.get();
    }

}
