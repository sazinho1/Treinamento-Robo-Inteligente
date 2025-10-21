package obstaculos;

import robos.Robo;

public abstract class Obstaculo {
    protected String id; //
        protected int x;
        protected int y;

        public Obstaculo(String id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getX() { 
            return x; 
        }
        public int getY() { 
            return y; 
        }
        public String getId() { 
            return id; 
        }
        
        public abstract void bater(Robo robo);
}
