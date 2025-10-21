package obstaculos;

import robos.Robo;

public class Bomba extends Obstaculo {
    public Bomba(int x, int y) {
        super("B", x, y); // 'B' de Bomba
    }

    @Override
    public void bater(Robo robo) {
        robo.explodirRobo(); 
    }
}