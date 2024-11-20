/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver;

import java.io.*;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Rian Joseph
 */
public class CadastroThread extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private Socket s1;

    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {
        try {
            // Inicializa os fluxos de entrada e saída
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());

            // Recebe o login e senha
            String login = (String) input.readObject();
            String senha = (String) input.readObject();

            // Verifica se o usuário existe
            Usuario usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                s1.close();
                return;
            }

            // Loop de comunicação para os comandos
            while (true) {
                String comando = (String) input.readObject();
                if ("L".equals(comando)) {
                    // Retorna a lista de produtos
                    List<Produto> produtos = ctrl.findProdutoEntities();
                    output.writeObject(produtos);
                    output.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                s1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
