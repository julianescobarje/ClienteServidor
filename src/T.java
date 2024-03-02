
public class T extends Thread {
  private String palabra;
  private boolean tipo; // (True: Cliente) (False: Servidor)
  private static Buffer buffer;

  public T(boolean tipo, String palabra) {
    this.tipo = tipo;
    this.palabra = palabra;
  }

  @Override
  public void run() {
    if (tipo) {
      // System.out.println("Hola, soy un cliente");
      // System.out.println("Quiero traducir: " + palabra);
      Solicitud solicitud = new Solicitud();
      solicitud.generarSolicitud(palabra);
      // System.out.println("Generé la solicitud");
      buffer.almacenarSolicitud(solicitud);

      synchronized (solicitud) {
        try {
          solicitud.wait();
          System.out.println("Estoy esperando mi traduccion");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      System.out.println("Palabra traducida: " + solicitud.getPalabraTraducida());

    } else {
      while (true) {
        Solicitud otraSolicitud = buffer.retirarSolicitud();
        otraSolicitud.generarTraduccion();
        synchronized (otraSolicitud) {
          otraSolicitud.notify();
        }
      }
    }
  }

  public static void setBuffer(Buffer buffer) {
    T.buffer = buffer;
  }

  public static void main(String[] args) {
    int P = 1;
    int C = 3;
    int S = 4;

    for (int i = 0; i < C; i++) {
      T cliente = new T(true, "Palabra " + i);
      cliente.start();
    }

    for (int i = 0; i < S; i++) {
      T servidor = new T(false, null);
      servidor.start();
    }
  }
}