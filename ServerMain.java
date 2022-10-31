import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {
    static private int portaAscolto ;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        this.portaAscolto = Integer.parseInt(args[0]);

        try {
            serverSocket = new ServerSocket(portaAscolto);
        } catch (IOException f) {
            f.printStackTrace();
        }

        BufferedReader bufferedReader = null;
        PrintWriter printWriterOut = null;
        boolean flag = true;
        System.out.println("In ascolto su porta " + portaAscolto);
        while (true) {
            try {

                //accetto connessioni, in ascolto
                Socket clientSocket = serverSocket.accept();


                //creo InputStreamReader passando al costruttore .getInputStream()
                InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
                //assegno lo stream reader al buffer reader di input
                bufferedReader = new BufferedReader(inputStream);
                //
                //Setting output stream writer
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                //assegno l'output stream writer allo scrittore
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                //assegno a printwriter
                printWriterOut = new PrintWriter(outputStreamWriter, true);
                //


            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                String stringa = bufferedReader.readLine();
                if (stringa.equals("/end")) {
                    break;
                }

                System.out.println(stringa);
                printWriterOut.println(stringa);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                printWriterOut.close();
                bufferedReader.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}






