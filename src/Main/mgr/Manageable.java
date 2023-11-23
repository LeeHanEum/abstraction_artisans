package Main.mgr;

import java.util.Scanner;

public interface Manageable {

    void read(Scanner scan);

    void print();

    boolean matches(String kwd);

    String getName();
    int getPrice();
}
