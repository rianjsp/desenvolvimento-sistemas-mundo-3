/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver;

import java.io.*;
import java.net.Socket;
import java.util.List;
/**
 *
 * @author Rian Joseph
 */

public class CadastroClient {
    public static void main(String[] args) {
        try {
            // Conecta ao servidor
            Socket socket = new Socket("localhost", 4321);

            // Inicializa os fluxos de entrada e saída
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envia o login e senha
            output.writeObject("op1");
            output.writeObject("op1");

            // Envia o comando L para listar os produtos
            output.writeObject("L");

            // Recebe a lista de produtos
            List<Produto> produtos = (List<Produto>) input.readObject();
            for (Produto produto : produtos) {
                System.out.println("Produto: " + produto.getNome());
            }

            // Fecha a conexão
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}