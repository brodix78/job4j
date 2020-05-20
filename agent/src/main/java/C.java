public class C {
    private int x;
    private int y;
    public static void main(String [] args)
    {
        System.out.println(InstrumentationAgent.getObjectSize(new C()));
    }
}
