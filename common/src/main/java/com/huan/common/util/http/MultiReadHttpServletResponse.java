package com.huan.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 多次读写BODY用HTTP RESPONSE - 解决流只能读一次问题
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
public class MultiReadHttpServletResponse extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final HttpServletResponse response;

    public MultiReadHttpServletResponse(HttpServletResponse response) {
        super(response);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        this.response = response;
    }

    public byte[] getBody() {
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(getOutputStream(), this.response.getCharacterEncoding()));
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    private static class ServletOutputStreamWrapper extends ServletOutputStream {

        private ByteArrayOutputStream outputStream;
        private HttpServletResponse response;

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }

        @Override
        public void write(int b) {
            this.outputStream.write(b);
        }

        @Override
        public void flush() throws IOException {
            if (!this.response.isCommitted()) {
                byte[] body = this.outputStream.toByteArray();
                ServletOutputStream outputStream = this.response.getOutputStream();
                outputStream.write(body);
                outputStream.flush();
            }
        }
    }
}
