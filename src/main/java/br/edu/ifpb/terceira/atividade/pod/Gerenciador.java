package br.edu.ifpb.terceira.atividade.pod;

/**
 *
 * @author Edilva
 */
public class Gerenciador {

    private int count = 0;
    private int fail = 0;
    private final Queue queue;

    private People createPeople() {
        count++;
        return new People("Pessoa" + count);
    }

    public Gerenciador(Queue queue) {
        this.queue = queue;
    }

    public int exec(int qt) {
        //randomizando o número de pessoas a serem criadas (entrada)
        //Random r = new Random();
        //int qt = r.nextInt(5) + 1;
        //criando e encaminhando para a fila
        for (int i = 0; i < qt; i++) {
            People p = createPeople();
            if (!queue.isFull()) {
                queue.getPeoples().offer(p);
            } else {
                queue.countFoiEmbora();
                fail = queue.getQtdeFoiEmbora();
            }
        }
        
        return qt;
    }

    public int count() {
        return count;
    }

    public int fail() {
        return fail;
    }
}
