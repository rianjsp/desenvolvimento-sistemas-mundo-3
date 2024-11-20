/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastroserver;

import javax.persistence.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Rian Joseph
 */
public class CadastroServer {
    public static void main(String[] args) {
        try {
            // Inicializa o EntityManagerFactory
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroPU");
            ProdutoJpaController ctrl = new ProdutoJpaController(emf);
            UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);

            // Inicializa o servidor na porta 4321
            ServerSocket serverSocket = new ServerSocket(4321);

            System.out.println("Servidor esperando conexões...");

            // Loop infinito para aceitar múltiplas conexões
            while (true) {
                Socket socket = serverSocket.accept();
                // Inicia uma nova thread para cada cliente
                CadastroThread thread = new CadastroThread(ctrl, ctrlUsu, socket);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}