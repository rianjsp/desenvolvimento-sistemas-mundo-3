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
public class Servidor {
    
    public static void main(String[] args){
        try{
            //Criando o servidor
            ServerSocket servidorSocket = new ServerSocket(12345);
            System.out.println("Servidor escutando conexoes");
            
            // Aguarda conexão do cliente
            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());
            
            // Streams para ler e enviar os dados
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            
            // Processamento do cliente, onde recebe o login e a senha
            String login = in.readLine(); 
            String senha = in.readLine(); 
            
            // Validação das credenciais simples
            if(validarCredenciais(login, senha)){
                out.println("Credenciais válidas! Envie L para ver os produtos.");
                String comando = in.readLine(); // Esperando o comando L
                
                if("L".equals(comando)){
                    out.println("Produto 1 - Produto 2 - Produto 3");
                }
                else {
                    out.println("Credenciais Invalidas. Desconectando...");
                }
                
                // Fechando as conexoes
                in.close();
                out.close();
                clienteSocket.close();
                servidorSocket.close();        
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    // Medotodo de validacao (exemplo simples e fixo)
    public static boolean validarCredenciais(String login, String senha){
        return "admin".equals(login) && "1234".equals(senha);
    };
}
