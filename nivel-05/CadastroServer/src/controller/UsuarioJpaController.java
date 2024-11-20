/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Rian Joseph
 */
public class UsuarioJpaController {
    public Usuario findUsuario(String login, String senha) {
    try {
        // Criando a query JPA para buscar o usuário pelo login e senha
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        
        // Verifica se encontrou o usuário, caso contrário, retorna null
        List<Usuario> usuarios = query.getResultList();
        return usuarios.isEmpty() ? null : usuarios.get(0);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
