
package br.edu.ifpb.terceira.atividade.pod;

/**
 *
 * @author Edilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue q0 =new Queue("Q0", 100);
        Queue q1 =new Queue("Q1", 50);
        Attendant attendant1 = new Attendant(q0);
        Attendant attendant2 = new Attendant(q0);
        Attendant attendant3 = new Attendant(q1);
        Gerenciador g1 = new Gerenciador(q0);
        Gerenciador g2 = new Gerenciador(q1);
        IncomingManager eng = new IncomingManager(g1, g2, attendant1, attendant2, attendant3, q0, q1);
        eng.exec();
    }
    
}
