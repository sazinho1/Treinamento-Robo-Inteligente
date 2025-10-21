package obstaculos;

import robos.Robo;

public class Rocha extends Obstaculo {
    public Rocha(int x, int y) {
        super("R", x, y); // 'R' de Rocha
    }

    @Override
    public void bater(Robo robo) {
        robo.voltarPosicaoAnterior();
    }
}