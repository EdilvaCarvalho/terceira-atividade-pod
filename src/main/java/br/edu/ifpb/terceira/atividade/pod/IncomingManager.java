package br.edu.ifpb.terceira.atividade.pod;

/**
 *
 * @author Edilva
 */
public class IncomingManager {

    private Gerenciador gerenciadorQ0, gerenciadorQ1;
    private Attendant attendant1, attendant2, attendant3;
    private Queue q0, q1;
    private Thread t0 = null;
    private boolean fim = false;
    private double vx = 0;

    public IncomingManager(Gerenciador gerQ0, Gerenciador gerQ1, Attendant attendant1, Attendant attendant2, Attendant attendant3, Queue q0, Queue q1) {
        this.gerenciadorQ0 = gerQ0;
        this.gerenciadorQ1 = gerQ1;
        this.attendant1 = attendant1;
        this.attendant2 = attendant2;
        this.attendant3 = attendant3;
        this.q0 = q0;
        this.q1 = q1;
    }

    private void printar() {
        System.out.println();
        System.out.println("Quantidade de pessoas que chegaram na Q0: " + gerenciadorQ0.count());
        System.out.println("Quantidade de pessoas que foram embora na Q0: " + gerenciadorQ0.fail());
        System.out.println("Quantidade de pessoas que chegaram na Q1: " + gerenciadorQ1.count());
        System.out.println("Quantidade de pessoas que foram embora na Q1: " + gerenciadorQ1.fail());
        System.out.println("Quantidade de pessoas na fila Q0: " + q0.getPeoples().size());
        System.out.println("Quantidade de pessoas na fila Q1: " + q1.getPeoples().size());
        System.out.println("Quantidade de pessoas atendidas pelo Atendente1: " + attendant1.getQtdePeoples());
        System.out.println("Quantidade de pessoas atendidas pelo Atendente2: " + attendant2.getQtdePeoples());
        System.out.println("Quantidade de pessoas atendidas pelo Atendente3: " + attendant3.getQtdePeoples());
    }

    private void relogio() {
        Runnable r0 = new Runnable() {
            @Override
            public void run() {
                //
                long ti = System.currentTimeMillis();
                //
                int timef = 0;
                boolean printed = false;
                //
                long time0 = System.currentTimeMillis();
                long time1 = System.currentTimeMillis();
                while (true) {
                    time1 = System.currentTimeMillis();
                    if (time1 - time0 >= 1000) {
                        timef++;
                        time0 = time1;
                        synchronized (t0) {
                            t0.notifyAll();
                        }
                        System.out.println("Tempo: " + timef + "s");
                    }
                    if (timef == 599 && printed == false) {
                        fim = true;
                        printed = true;
                        System.out.println("Fim!");
                    }
                    if (timef == 600) {
                        printar();
                        break;
                    }
                }
            }
        };
        //
        t0 = new Thread(r0);
        t0.start();
    }

    private double obterX() {
        return Math.random();
    }

    private int funcaoF(double x) {
        return (int) Math.round(0.833 * (Math.exp(x * -1)));
    }

    private int funcaoG(double x) {
        return (int) Math.round(2 * x);
    }

    private double funcaoH(Double x) {
        return (0.3 * (Math.pow(x, x)));
    }

    private void gerenciadorDeEntrada() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int time = 0;
                double x = 0;

                while (fim == false) {
                    x = obterX();
                    System.out.println("X: " + x);
                    synchronized (t0) {

                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (++time == 1) {
                        int qtQ0 = gerenciadorQ0.exec(funcaoF(x));
                        int qtQ1 = gerenciadorQ1.exec(funcaoG(x));
                        time = 0;
                        System.out.println("Ocorreu a entrada de " + qtQ0 + " pessoa(s) na fila Q0.");
                        System.out.println("Ocorreu a entrada de " + qtQ1 + " pessoa(s) ba fila Q1.");
                        obterX();
                    } else {
                        System.out.println("Aguardando a entrada de pessoas.");
                    }
                }
            }
        };
        Thread t1 = new Thread(r1);
        t1.start();
    }

    private void atendimento() {
        double tempoX = funcaoH(obterX());
        Runnable r2 = criaAtendimento(this.attendant1, tempoX);
        Runnable r3 = criaAtendimento(this.attendant2, tempoX);
        Runnable r4 = criaAtendimento(this.attendant3, tempoX);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        t2.start();
        t3.start();
        t4.start();

    }

    public void exec() {
        relogio();
        gerenciadorDeEntrada();
        atendimento();
    }

    private Runnable criaAtendimento(Attendant attendant, double tempo) {
        return new Runnable() {
            @Override
            public void run() {
                int x = (int) Math.round(1 / tempo);
                while (fim == false) {
                    synchronized (t0) {
                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        System.out.println("Verifacar se há algum atendimento.");
                        attendant.attending();
                    } catch (RuntimeException e) {
                        System.out.println("Ninguém para atendimento.");
                        continue;
                    }
                    int time = 0;
                    while (true) {
                        synchronized (t0) {
                            try {
                                t0.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        if (++time == x) {
                            attendant.finishing();
                            break;
                        }
                    }
                }
            }
        };
    }
}
