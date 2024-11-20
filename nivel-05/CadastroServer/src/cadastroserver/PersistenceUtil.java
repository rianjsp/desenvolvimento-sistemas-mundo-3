/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver;
import javax.persistence.*;

/**
 *
 * @author Rian Joseph
 */
public class PersistenceUtil {

    private static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("lojaPU");
        }
        return emf.createEntityManager();
    }
}
