package robotgrid.utils;

import java.util.Optional;

public class Result<S, F> {

    // Static inner classes ===================================================

    public static class Success<S, F> extends Result<S, F> {
        public final S value;
        public Success(final S value) {
            super(Optional.of(value), Optional.empty());
            this.value = value;
        }
    }

    public static class Failure<S, F> extends Result<S, F> {
        public final F value;
        public Failure(final F value) {
            super(Optional.empty(), Optional.of(value));
            this.value = value;
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final Optional<S> _successValue;
    protected final Optional<F> _failureValue;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Result(final Optional<S> successValue, final Optional<F> failureValue) {
        this._successValue = successValue;
        this._failureValue = failureValue;
    }

    // Instance methods =======================================================

    public boolean isSuccess() {
        return _successValue.isPresent();
    }

    public boolean isFailure() {
        return _failureValue.isPresent();
    }

    public S successValue() {
        return _successValue.get();
    }

    public F failureValue() {
        return _failureValue.get();
    }

}
