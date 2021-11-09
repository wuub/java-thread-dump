import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
 
public class HelloWorld{
  public static void main(String[] args) throws IOException{
    ServerSocket listener = new ServerSocket(8080);
    while(true){
      Socket sock = listener.accept();
      new PrintWriter(sock.getOutputStream(), true).
                println("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 11\r\n\r\nHello world");
      sock.close();
    }
  }
}
