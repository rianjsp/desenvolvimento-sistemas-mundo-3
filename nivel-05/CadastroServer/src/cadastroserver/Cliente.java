/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver;

import java.io.*;
import java.net.*;
/**
 *
 * @author Rian Joseph
 */
public class Cliente {
    public static void main(String[] args){
        try{
            // Conecta ao servidor na porta existente
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            
            // Envia o login e senha
            System.out.print("Digite o login: ");
            String login = userInput.readLine();
            System.out.print("Digite a senha: ");
            String senha = userInput.readLine();
            out.println(login);
            out.println(senha);
            
            // Recebendo a resposta do servidor
            String resposta = in.readLine();
            System.out.println("Servidor: " + resposta);
            
            // Verificação das respostas validas
            if(resposta.contains("validas")){
                System.out.println("Digite 'L' para ver os produtos: ");
                String comando = userInput.readLine();
                out.println(comando);
                
                // Recevendo a lista de produtos
                resposta = in.readLine();
                System.out.println("Produtos: " + resposta);
            }
            
            // Fechando as conexoes
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
