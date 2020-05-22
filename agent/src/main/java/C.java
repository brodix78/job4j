public class C {
    private int x;
    private int y;

    public static void main(String [] args)
    {
        User Ivan = new User("Ivan");
        System.out.println("Size of object User is: " + InstrumentationAgent.getObjectSize(Ivan));
    }
}
