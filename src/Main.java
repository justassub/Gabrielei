import Login.Login;
import Login.Reader;
import java.io.*;

public class Main {
    public static void main(String[] args)  {
        Reader reader = new Reader();
        Login login = new Login();
        reader.readUsers();
        reader.readItems();
        login.connectAs();
    }

}
