package com.client;

import java.io.*;
import java.net.*;

public class Client {

//    private Socket socket;

    public Client(String url, int portNumber){
        //try with resources
        try( Socket socket = new Socket(url, portNumber);
        PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        BufferedReader stdin= new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ){

//            boolean access = ManageLogin(input, output);
//            if(!access){
//                System.out.println("fraud");
//                socket.close();
//            }
            System.out.println(("Connected to game server\n\n"));
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            String serverInput;

            while((serverInput=input.readLine())!=null){
                //while the server is saying something, print it
                System.out.println(serverInput); //welcome/status message

                serverInput =input.readLine();
                System.out.println(serverInput); //instructions
                if(serverInput.contains("Victory")||serverInput.contains("Defeat")){
                   socket.close();
                   break;
                }

                userInput = stdIn.readLine(); //gets user input

                if(userInput!= null){
                    //gets user input
                    output.println(userInput);
                }
            }

        }catch(UnknownHostException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private boolean ManageLogin(BufferedReader input, PrintWriter output) throws IOException {
        BufferedReader userInput =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println(input.readLine());
        String username = userInput.readLine();

        output.println(username);

        System.out.println(input.readLine());
        String password = userInput.readLine();
        output.println(password);

        String valid  = input.readLine();

        return valid.equals("Access");
    }
}
