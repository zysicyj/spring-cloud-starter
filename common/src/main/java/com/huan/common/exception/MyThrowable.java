package com.huan.common.exception;

/**
 * 处理参数校验异常
 *
 * @author <a href="mailto:zhuyongsheng@jinbill.com">zhuyongsheng</a>
 * @version 2.9.8
 * @date 2020/6/23
 */
public class MyThrowable extends Throwable {

    private static final long serialVersionUID = 7244283354807914604L;

    /**
     * Constructs a new throwable with the specified detail message,
     * cause, {@linkplain #addSuppressed suppression} enabled or
     * disabled, and writable stack trace enabled or disabled.  If
     * suppression is disabled, {@link #getSuppressed} for this object
     * will return a zero-length array and calls to {@link
     * #addSuppressed} that would otherwise append an exception to the
     * suppressed list will have no effect.  If the writable stack
     * trace is false, this constructor will not call {@link
     * #fillInStackTrace()}, a {@code null} will be written to the
     * {@code stackTrace} field, and subsequent calls to {@code
     * fillInStackTrace} and {@link
     * #setStackTrace(StackTraceElement[])} will not set the stack
     * trace.  If the writable stack trace is false, {@link
     * #getStackTrace} will return a zero length array.
     *
     * <p>Note that the other constructors of {@code Throwable} treat
     * suppression as being enabled and the stack trace as being
     * writable.  Subclasses of {@code Throwable} should document any
     * conditions under which suppression is disabled and document
     * conditions under which the stack trace is not writable.
     * Disabling of suppression should only occur in exceptional
     * circumstances where special requirements exist, such as a
     * virtual machine reusing exception objects under low-memory
     * situations.  Circumstances where a given exception object is
     * repeatedly caught and rethrown, such as to implement control
     * flow between two sub-systems, is another situation where
     * immutable throwable objects would be appropriate.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be
     *                           writable
     *
     * @see OutOfMemoryError
     * @see NullPointerException
     * @see ArithmeticException
     * @since 1.7
     */
    public MyThrowable(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, false, false);
    }
}
