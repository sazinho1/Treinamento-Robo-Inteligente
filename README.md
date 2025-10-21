# Jogo de Simulação de Robôs em Java

## Descrição do Projeto

### Visão Geral e Objetivo do Jogo

* Este projeto é uma simulação de movimento de robôs em um grid 2D, desenvolvido em Java como um trabalho acadêmico focado em Orientação a Objetos e Tratamento de Exceções.
* A simulação envolve dois robôs, cada um identificado por uma cor única (ex: "azul" e "vermelho"), cujas posições são rastreadas em um grid cartesiano.
* O tabuleiro é um grid fixo de 4x4, com coordenadas de (0,0) a (3,3). Todos os robôs iniciam sua jornada a partir da casa (0,0).
* O objetivo principal para os robôs é ser o primeiro a alcançar um "alimento" que está previamente definido em alguma posição do grid, indicada pelo usuário.

### Tipos de Robôs e Regras de Jogada

* O jogo é construído sobre os conceitos de herança e polimorfismo, apresentando dois tipos distintos de robôs que o usuário pode simular.
* As simulações principais (Pontos 3 e 4) instanciam um de cada tipo para competir.
* Os tipos são:
    * **`RoboNormal`**: Move-se aleatoriamente (up, down, left, right). Caso o movimento aleatório escolhido seja inválido (resultando em coordenada negativa ou fora do tabuleiro 4x4), o robô simplesmente perde a vez, e uma `MovimentoInvalidoException` é lançada.
    * **`RoboInteligente`**: Uma subclasse de `Robo` que sobrescreve o método de movimento. Se este robô tenta um movimento inválido, ele garante que seu próximo movimento aleatório não será o mesmo que acabou de falhar. Além disso, ele "realiza outros movimentos até que seja feito um movimento válido", garantindo que ele sempre se mova em seu turno.
* A movimentação a cada rodada é definida por uma escolha aleatória de 1 a 4 (representando "up", "down", "right" e "left").

### Tabuleiro Interativo e Obstáculos

* O tabuleiro, além do alimento, pode ser populado com obstáculos que alteram a dinâmica da partida, inseridos pelo usuário no início da simulação.
* Estes obstáculos são implementados usando uma classe abstrata `Obstaculo` e classes concretas:
    * **`Bomba`**: Caso um robô mova-se para a casa que contém uma bomba, o robô "explode" e não pode mais se mover (essencialmente, sai do jogo). A bomba então desaparece do tabuleiro.
    * **`Rocha`**: Caso um robô mova-se para a casa que contém uma rocha, o movimento é impedido e o robô é forçado a voltar para a sua posição anterior.

### Interface e Recursos Técnicos

* A interface do jogo é executada inteiramente via console (terminal).
* A cada rodada, a interface exibe a posição atual de todos os elementos em uma matriz 4x4. Isso inclui os robôs (ex: '1' para Normal e '2' para Inteligente), o alimento ('A') e todos os obstáculos ativos ('B' para Bomba, 'R' para Rocha).
* Após a movimentação de um robô, o console informa a nova posição (x,y) ou uma mensagem de evento (como colisão com obstáculo ou erro de movimento).
* Ao final da partida, o sistema declara o resultado (quem encontrou o alimento, se ambos encontraram, ou se ambos explodiram) e apresenta um resumo com a posição final e a quantidade total de movimentos válidos e inválidos de cada robô.
* O projeto inclui múltiplas classes `Main` para testar diferentes cenários: `Main1` (para movimento manual de um robô), `Main2` (corrida aleatória de dois `RoboNormal`), `Main3` (corrida entre `RoboNormal` e `RoboInteligente`) e `Main4` (corrida com obstáculos).