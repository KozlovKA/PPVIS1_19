

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;


public class Last {
    //лабараторная работа 1
    public static boolean TO_OPEN = false;
    public static boolean TO_CIRCLE = false;
    public static boolean TO_SWAP = false;
    public static boolean TO_OPEN_MAIN = false;
    public static boolean TO_CONTINUE = false;

    public static boolean OPENED = false;
    public static boolean TO_STOP = false;
    public static boolean WAIT_A_SECOND = false;

    public static Long START_TIME = 0L;
    public static int WINDOW_NUMBER = 0;


    public static void shellSwap(Shell shell,int num) throws InterruptedException {
        int[] xLocation = new int[]{100, 150, 300, 300, 100};
        int[] yLocation = new int[]{300, 400, 300, 100, 100};


        shell.setLocation(xLocation[num], yLocation[num]);

    }


    public static Shell addShell(Display display) {
        Shell shell = new Shell(display);
        shell.setSize(250, 250);
        shell.setLocation(0, 0);

        return(shell);
    }

    public static void setCircle(ArrayList<Shell> shells) {
        int[] xCircle = new int[]{100, 100, 150, 300, 300};
        int[] yCircle = new int[]{100, 300, 400, 300, 100};

        int idx = 0;
        for(Shell shell_temp: shells) {
            shell_temp.setLocation(xCircle[idx], yCircle[idx]);

            idx++;
        }
    }




    public static void openShells(ArrayList<Shell> shells) {
        for(Shell shell_temp: shells) {
            shell_temp.open();
        }
    }

    public static boolean oneSecondPassCheck() {
        if (START_TIME == 0) {
            START_TIME = System.currentTimeMillis();
        }
        Long endTime = System.currentTimeMillis();

        if (endTime - START_TIME < 1000) {
            return false;
        } else {
            START_TIME = 0L;
            return true;
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Main");
        shell.setSize(550, 550);

        shell.open();

        ArrayList<Shell> shells = new ArrayList<Shell>();

        Shell shell1 = addShell(display);
        shell1.setText("First");
        shells.add(shell1);
        Shell shell2 = addShell(display);
        shell2.setText("Second");
        shells.add(shell2);
        Shell shell3 = addShell(display);
        shell3.setText("Third");
        shells.add(shell3);
        Shell shell4 = addShell(display);
        shell4.setText("Fourth");
        shells.add(shell4);
        Shell shell5 = addShell(display);
        shell5.setText("Fifth");
        shells.add(shell5);


        display.addFilter(SWT.KeyDown, new Listener() {
            @Override
            public void handleEvent(final Event ke) {
                List<String> list = new ArrayList<>();

                if ((ke.stateMask & SWT.CTRL) != 0) {
                    list.add("CTRL");
                }

                list.add(Character.toString((char) ke.keyCode));


                if ((list.size() == 2) & !OPENED){
                    if ((list.get(0).equals("CTRL"))
                            & (list.get(1).equals("r"))) {
                        System.out.println("Check1");

                        if (TO_STOP) {
                            TO_CONTINUE = true;
                        } else {
                            TO_OPEN = true;
                        }
                    }

                    if ((list.get(0).equals("CTRL"))
                            & (list.get(1).equals("s"))) {
                        System.out.println("Stop!");
                        TO_STOP = true;
                    }

                    System.out.println(list);
                    System.out.println(list.get(0));
                }
            }

        });


        int count = 0;

        while (!shell.isDisposed()) {

            if (TO_CONTINUE & TO_STOP & display.readAndDispatch()) {
                System.out.println("Check2");
                TO_STOP = false;
                TO_CONTINUE = false;
            }

            if (TO_STOP) {

                display.sleep();

            } else {

                if (WAIT_A_SECOND){
                    WAIT_A_SECOND = !oneSecondPassCheck();


                }

                if (!WAIT_A_SECOND & TO_OPEN_MAIN) {
                    shell.open();
                    TO_OPEN = true;
                    TO_OPEN_MAIN = false;
                    WAIT_A_SECOND = true;
                }


                if (!WAIT_A_SECOND & TO_OPEN) {
                    shell.setVisible(false);
                    openShells(shells);
                    WAIT_A_SECOND = true;
                    TO_OPEN = false;
                    TO_CIRCLE = true;
                }

                if (!WAIT_A_SECOND & TO_CIRCLE) {
                    setCircle(shells);
                    WAIT_A_SECOND = true;
                    TO_CIRCLE = false;
                    TO_SWAP = true;
                }

                if (!WAIT_A_SECOND & TO_SWAP) {
                    shellSwap(shells.get(WINDOW_NUMBER),WINDOW_NUMBER);
                    WAIT_A_SECOND = true;
                    WINDOW_NUMBER += 1;

                    if (WINDOW_NUMBER == 5) {
                        WINDOW_NUMBER = 0;
                        TO_SWAP = false;
                        TO_OPEN_MAIN = true;
                        WAIT_A_SECOND = true;
                    }
                }

            }
        }

        display.dispose();
    }

}
