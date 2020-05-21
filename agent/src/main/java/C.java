public class C {
    private int x;
    private int y;

    public static void main(String [] args)
    {
        User Ivan = new User("Ivan");
        System.out.println(InstrumentationAgent.getObjectSize(Ivan));
        User Petr = new User("Petr");
        System.out.println(InstrumentationAgent.getObjectSize(Petr));
        System.out.println(InstrumentationAgent.getObjectSize(new User("Ivan")));
        System.out.println(Ivan.getName());
        System.out.println(InstrumentationAgent.getObjectSize(Petr));
    }
}
