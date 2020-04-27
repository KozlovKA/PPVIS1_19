

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


    public static void shellSwap(Shell shell, int num,ArrayList<int[]> coordinates) throws InterruptedException {
        int[] xCoordinate;
        int[] yCoordinate;
        xCoordinate = coordinates.get(num+1);
        yCoordinate = coordinates.get(num+1);
        shell.setLocation(xCoordinate[0],yCoordinate[1]);

    }


    public static Shell addShell(Display display) {
        Shell shell = new Shell(display);
        shell.setSize(250, 250);
        shell.setLocation(0, 0);

        return (shell);
    }
    public static ArrayList<int[]> getCoordinates(int coordNumber) {
        ArrayList<int[]> listOfNumbers = new ArrayList<>();

        int x0 = 500;
        int y0 = 500;
        int r = 300;

        for (int i = 1; i < coordNumber + 1; i++) {
            float angle = i * (360 / coordNumber);

            int x = (int) (x0 + r * Math.cos(Math.toRadians(angle)));
            int y = (int) (y0 + r * Math.sin(Math.toRadians(angle)));

            x = (int) x;
            y = (int) y;

            int[] point = new int[]{x, y};
            listOfNumbers.add(point);
        }


        return listOfNumbers;
    }

    public static void setCircle(ArrayList<Shell> shells, ArrayList<int[]> coordinates) {
        int[] xCoordinate;
        int[] yCoordinate;
        int idx = 0;
        for (Shell shell_temp : shells) {
            xCoordinate = coordinates.get(idx);
            yCoordinate = coordinates.get(idx);
            shell_temp.setLocation(xCoordinate[0],yCoordinate[1]);

            idx++;
        }
    }


    public static void openShells(ArrayList<Shell> shells) {
        for (Shell shell_temp : shells) {
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
        int[] x;
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Main");
        shell.setSize(550, 550);
        shell.open();
        ArrayList<int[]> coordinatesXY;
        ArrayList<Shell> shells = new ArrayList<Shell>();
        coordinatesXY = getCoordinates(6);
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
        ComboBox comboBox = new ComboBox();
        comboBox.getTask(shell);
        SwapNames swap = new SwapNames();
        swap.getTask2(shell);
        RadioButton radioButon = new RadioButton();
        radioButon.getTask3(shell);
        CheckButton checkButton = new CheckButton();
        checkButton.getTask4(shell);
        TableWidget widget = new TableWidget();
        widget.getTable(shell, display);
        comboBox.getTask(shell1);
        swap.getTask2(shell2);
        radioButon.getTask3(shell3);
        checkButton.getTask4(shell4);
        widget.getTable(shell5, display);


        display.addFilter(SWT.KeyDown, new Listener() {
            @Override
            public void handleEvent(final Event ke) {
                List<String> list = new ArrayList<>();

                if ((ke.stateMask & SWT.CTRL) != 0) {
                    list.add("CTRL");
                }

                list.add(Character.toString((char) ke.keyCode));


                if ((list.size() == 2) & !OPENED) {
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


        while (!shell.isDisposed()) {

            if (TO_CONTINUE & TO_STOP & display.readAndDispatch()) {
                System.out.println("Check2");
                TO_STOP = false;
                TO_CONTINUE = false;
            }

            if (TO_STOP) {

                display.sleep();

            } else {

                if (WAIT_A_SECOND) {
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
                    setCircle(shells, coordinatesXY);
                    WAIT_A_SECOND = true;
                    TO_CIRCLE = false;
                    TO_SWAP = true;
                }

                if (!WAIT_A_SECOND & TO_SWAP) {
                    shellSwap(shells.get(WINDOW_NUMBER), WINDOW_NUMBER,coordinatesXY);
                    WAIT_A_SECOND = true;
                    ++WINDOW_NUMBER;

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
