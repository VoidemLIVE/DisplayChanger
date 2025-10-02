package uk.callumtelfer;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

import javax.swing.*;
import java.awt.*;

public class Main {

    // display config consts - reference in README.md
    private static final int SDC_APPLY = 0x00000080;
    private static final int SDC_TOPOLOGY_EXTEND = 0x00000004;
    private static final int SDC_TOPOLOGY_EXTERNAL = 0x00000008;

    public interface User32Extended extends User32 {
        User32Extended INSTANCE = Native.load("user32", User32Extended.class, W32APIOptions.DEFAULT_OPTIONS);

        WinDef.LONG SetDisplayConfig(
                int numPathArrayElements,
                Object pPathArray,
                int numModeInfoArrayElements,
                Object pModeInfoArray,
                int flags
        );
    }

    public static void toggleDisplay(boolean extend) {
        int topology = extend ? SDC_TOPOLOGY_EXTEND : SDC_TOPOLOGY_EXTERNAL;

        WinDef.LONG result = User32Extended.INSTANCE.SetDisplayConfig(0, null, 0, null, SDC_APPLY | topology);

        if (result.intValue() == 0) {
            System.out.println("Display mode set to: " + (extend ? "Extend" : "Show only on 2"));
        }
    }

    public static void main(String[] args) {
        // create the gui - looks bit shit might change later
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Display Toggle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(350, 150);
            frame.setLocationRelativeTo(null);

            final boolean[] isExtended = {true};

            JButton toggle = new JButton("Switch to: Show only on 2");
            toggle.setFont(new Font("Arial", Font.PLAIN, 14));

            toggle.addActionListener(e -> {
                isExtended[0] = !isExtended[0];
                toggleDisplay(isExtended[0]);

                if (isExtended[0]) {
                    toggle.setText("Switch to: Show only on 2");
                } else {
                    toggle.setText("Switch to: Extend displays");
                }
            });

            frame.add(toggle, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}