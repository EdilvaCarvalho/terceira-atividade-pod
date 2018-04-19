package br.edu.ifpb.terceira.atividade.pod;

/**
 *
 * @author Edilva
 */
public class Attendant {

    private Queue queue;
    private People people;
    private int qtdePeoples;

    public Attendant(Queue queue) {
        this.queue = queue;
        this.qtdePeoples = 0;
    }

    public void attending() {
        people = this.queue.pop();
        qtdePeoples++;
        System.out.println(people.getName() + " está sendo sendo atendida na " + queue.getName() + ".");
    }

    public void finishing() {
        System.out.println("Atendimento à " + people.getName() + " finalizado.");
        people = null;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public int getQtdePeoples() {
        return qtdePeoples;
    }

    public void setQtdePeoples(int qtdePeoples) {
        this.qtdePeoples = qtdePeoples;
    }

}
