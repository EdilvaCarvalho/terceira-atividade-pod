package br.edu.ifpb.terceira.atividade.pod;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Edilva
 */
public class Queue {

    private String name;
    private LinkedBlockingQueue<People> peoples;
    private int capacidade;
    private int qtde;
    private int qtdeFoiEmbora;

    public Queue(String name, int quantidade) {
        this.name = name;
        this.capacidade = quantidade;
        this.peoples = new LinkedBlockingQueue<>();
        this.qtde = 0;
    }

    public People pop() {
        if (!peoples.isEmpty()) {
            return peoples.poll();
        } else {
            throw new RuntimeException("A fila está vazia.");
        }
    }

    public void countFoiEmbora() {
        if(isFull()) {
            qtdeFoiEmbora++;
        }
    }

    public LinkedBlockingQueue<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(LinkedBlockingQueue<People> peoples) {
        this.peoples = peoples;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public boolean isFull() {
        return peoples.size() == capacidade;
    }

    public int getQtdeFoiEmbora() {
        return qtdeFoiEmbora;
    }

    public void setQtdeFoiEmbora(int qtdeFoiEmbora) {
        this.qtdeFoiEmbora = qtdeFoiEmbora;
    }

}
