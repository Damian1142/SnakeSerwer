import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server implements Runnable{

    private int port;
    private boolean running = false;
    private Selector selector;
    ServerSocket serverSocket;
    private ByteBuffer buffer;

    public Server(int port, int bufferSize) {
        this.port = port;
        this.buffer = ByteBuffer.allocate(bufferSize);
    }

    public void start(){
        new Thread(this).start();
    }

    @SneakyThrows
    @Override
    public void run() {
        running = true;
        while(running){
            int client = selector.select();

            if (client == 0){
                continue;
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()){
                SelectionKey key = it.next();

                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){

                    Socket socket = serverSocket.accept();

                    System.out.println("Połączono z " + socket);

                    SocketChannel channel = socket.getChannel();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel channel = (SocketChannel) key.channel();

                    boolean connection = readData(channel,buffer);

                    if(!connection){
                        key.cancel();
                        Socket socket = channel.socket();
                        socket.close();
                    }
                }
                keys.clear();
            }
        }
    }

    private boolean readData(SocketChannel channel, ByteBuffer buffer) {
        return true;
    }

    @SneakyThrows
    public void open(){
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);

        serverSocket = socketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);
        selector = Selector.open();
        System.out.println("Serwer zainiclaizowany na porcie " + port);
    }
}
