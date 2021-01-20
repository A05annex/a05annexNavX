package org.a05annex.navx;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a set of tests for the methods in {@link NavX}.
 */
@RunWith(JUnitPlatform.class)
public class TestNavX {

    // -----------------------------------------------------------------------------------------------------------------
    // Tests for the length(...) method, test different numbers of arguments and reporting length per documentation.
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Order(1)
    @DisplayName("Test initialize, no configuration")
    void test_init_no_config() {
        assertNull(NavX.getInstance());
    }

//    @Test
//    @Order(2)
//    @DisplayName("Test initialize, robot configuration")
//    void test_init_robot_config() {
//        NavX.configure(new ArrayList<NavX.NavXConfig>(){{
//            new NavX.NavXConfig( NavX.Type.NAVX_MXP, SPI.Port.kMXP, null, null,
//                    NavX.Orientation.NORMAL);
//        }});
//        assertNull(NavX.getInstance());
//    }

    @Test
    @Order(3)
    @DisplayName("Test initialize, test configuration")
    void test_init_test_config() {
        NavX.configure(new ArrayList<NavX.NavXConfig>(){{
                new NavX.NavXConfig(NavX.Type.TEST, null, null, null,
                        NavX.Orientation.NORMAL, mock(AHRS.class));
            }});
        assertNull(NavX.getInstance());
    }
}
