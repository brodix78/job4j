public class C {
    private int x;
    private int y;

    public static void main(String [] args)
    {
        User Ivan = new User("Ivan", 0.0, 0.0, "ss");
        Ivan.init();
        System.out.println(InstrumentationAgent.getObjectSize(Ivan));
        User Petr = new User("Petr", 150.0, 200.0, "ss");
        System.out.println(InstrumentationAgent.getObjectSize(Petr));
        System.out.println(InstrumentationAgent.getObjectSize(new User("Ivan", 40.0, 0.0, "ss")));
        System.out.println(Ivan.getName());
        System.out.println(InstrumentationAgent.getObjectSize(Petr));
        System.out.println(Ivan.sum());
        System.out.println(Petr.getSas());
        System.out.println(Ivan.getValueOne());
    }
}
