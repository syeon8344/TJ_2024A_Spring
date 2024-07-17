package example.day10.printerexample;

public class PrinterTest2 {
    public static void main(String[] args) {
        GenericPrinter<Powder>powderPrinter = new GenericPrinter<>();
        powderPrinter.setMaterial(new Powder());
        powderPrinter.printing();

        GenericPrinter<Plastic> PlasticPrinter = new GenericPrinter<>();
        PlasticPrinter.setMaterial(new Plastic());
        PlasticPrinter.printing();
    }
}
