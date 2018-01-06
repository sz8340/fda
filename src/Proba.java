public class Proba {
  public static void main(String[] args) {
 
    System.out.println("Linux computer name throguh env:\"" + System.getenv("HOSTNAME") + "\"");
    //System.out.println("Linux computer name through exec:\"" + execReadToString("hostname") + "\"");
    //System.out.println("Linux computer name through /etc/hostname:\"" + execReadToString("cat /etc/hostname") + "\"");
  }

}
