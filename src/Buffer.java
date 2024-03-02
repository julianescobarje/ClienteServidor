import java.util.*;

public class Buffer {
  private int capacidad;
  private ArrayList<Solicitud> solicitudes;

  public Buffer(int capacidad) {
    this.capacidad = capacidad;
    solicitudes = new ArrayList<>();
  }

  public synchronized void almacenarSolicitud(Solicitud solicitud) {
    while (capacidad == solicitudes.size()) {
      System.out.println("Estoy lleno, te calmas hp");
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    solicitudes.add(solicitud);
    
    System.out.println("Almacené la solicitud");

    notifyAll();
  }

  public synchronized Solicitud retirarSolicitud() {
    while (solicitudes.size() == 0) {
      System.out.println("Servidor malparido, no sea atacado");
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    Solicitud solicitud = solicitudes.remove(0);

    notifyAll();

    System.out.println("Tome su solicitud");

    return solicitud;
  }
}
