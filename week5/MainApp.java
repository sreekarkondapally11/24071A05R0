public class MainApp {
    public static void main(String[] args) throws Exception {

        StudentCRUD.insert(1, "Sai", 20);
        StudentCRUD.insert(2, "Dinesh", 21);

        StudentCRUD.read();

        StudentCRUD.update(1, 22);
        StudentCRUD.delete(2);

        StudentCRUD.read();
    }
}
