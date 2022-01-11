import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
 
public class HelloWorld{
  public static void main(String[] args) throws IOException{
    ServerSocket listener = new ServerSocket(8080);
    ArrayList<byte[]> leak = new ArrayList<byte[]>();
    while(true){
      Socket sock = listener.accept();
      leak.add(new byte[128*1024*1024]);  // #128 megs
      new PrintWriter(sock.getOutputStream(), true).
                println("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 11\r\n\r\nHello world");
      sock.close();
    }
  }
}
