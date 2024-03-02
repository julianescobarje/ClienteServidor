public class Solicitud {
  private String palabraOriginal;
  private String palabraTraducida;

  public void generarSolicitud(String palabra) {
    this.palabraOriginal = palabra;
  }

  public void generarTraduccion() {
    if (palabraOriginal == "falen") {
      this.palabraTraducida = "calen";
    } else {

      this.palabraTraducida = palabraOriginal + " no soy la original.";
    }
  }

  public String getPalabraTraducida() {
    return this.palabraTraducida;
  }
}
