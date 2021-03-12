package com.huan.common.filter.response;

import com.huan.common.util.result.R;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 统一处理响应体
 * @author huan
 */
@SuppressWarnings("FieldMayBeFinal")
public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;

    ResponseWrapper(HttpServletResponse resp) {
        super(resp);
        buffer = new ByteArrayOutputStream();// 真正存储数据的流
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return out;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    byte[] getResponseData() throws IOException {
        flushBuffer();
        if (StringUtils.contains(buffer.toString(), "\"data\":") && StringUtils.contains(buffer.toString(), "\"code\":")
                && StringUtils.contains(buffer.toString(), "\"status\":")) {
            return buffer.toByteArray();
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(R.successWithJsonData(buffer.toString()).toJson().getBytes());
        return stream.toByteArray();
    }

    public String getContent() throws IOException {
        flushBuffer();
        if (StringUtils.contains(buffer.toString(), "\"data\":") && StringUtils.contains(buffer.toString(), "\"code\":")
                && StringUtils.contains(buffer.toString(), "\"status\":")) {
            return buffer.toString();
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(R.successWithJsonData(buffer.toString()).toJson().getBytes());
        return stream.toString();
    }


    @SuppressWarnings({"InnerClassMayBeStatic", "NullableProblems"})
    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        WapperedOutputStream(ByteArrayOutputStream stream) {
            bos = stream;
        }

        @Override
        public void write(int b) {
            bos.write(b);
        }

        @Override
        public void write(byte[] b) {
            bos.write(b, 0, b.length);
        }

        @Override
        public void write(byte[] b, int off, int len) {
            bos.write(b, off, len);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}
