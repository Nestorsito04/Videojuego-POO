# Snake Classic Arcade

Un juego de Snake en Java desarrollado bajo el patrón de arquitectura MVC (Modelo-Vista-Controlador) con interfaz gráfica en Swing.

# Integrantes 
Nestor Rincon
Simon Chourio

## Descripción del Juego
Controlas a la serpiente para comer la mayor cantidad de comida posible. Cada elemento consumido aumenta el tamaño de la serpiente y suma 10 puntos. El juego termina si chocas contra los bordes de la pantalla o contra el propio cuerpo de la serpiente.

Al finalizar la partida, se puede registrar el nombre del jugador y la puntuación se guarda de forma persistente en un archivo binario (`scores.dat`).

---

## Controles
- **Moverse:** Flechas de dirección (Arriba, Abajo, Izquierda, Derecha) o teclas W, A, S, D.
- **Reiniciar:** Tecla R cuando aparece la pantalla de Game Over.

---

## Estructura del Proyecto
- `model/`: Lógica del juego (`SnakeModel`), modelo de datos (`ScoreEntry`) y persistencia binaria (`ScoreManager`).
- `view/`: Renderizado gráfico (`GamePanel`).
- `controller/`: Manejo de eventos del teclado y bucle principal del juego (`GameController`).

---

## Cómo Compilar y Ejecutar

### Requisitos
- JDK 17 o superior instalado.

### Pasos desde la Terminal
1. Clona el repositorio y entra a la carpeta raíz:
   ```bash
   git clone <URL_DE_TU_REPOSITORIO>
   cd snake-arcade