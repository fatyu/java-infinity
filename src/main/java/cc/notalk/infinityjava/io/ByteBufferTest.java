package cc.notalk.infinityjava.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fatyu
 * 主要介绍bytebuff源码
 */
public class ByteBufferTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("d:\\nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        /**
         * 创建指定字节大小的缓冲区
         */
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int readByte = inChannel.read(buffer);
        while (readByte != -1) {
            System.out.println("读取到文件:" + readByte + "字节数据到缓冲区");
            buffer.flip();//标记缓冲区处于可读状态
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();//清空缓冲区 clear() 方法会清空整个缓冲区。compact() 方法只会清除已经读过的数据 当有未读数据的情况,下次channel调用read方法读取的数据填充到缓冲区未读数据的后面
            readByte = inChannel.read(buffer);
        }
        aFile.close();
    }
}
