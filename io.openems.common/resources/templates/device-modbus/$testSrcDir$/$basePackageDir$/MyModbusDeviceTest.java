package $basePackageName$;

import org.junit.Test;

import io.openems.edge.common.test.AbstractComponentTest.TestCase;
import io.openems.common.types.ChannelAddress;
import io.openems.edge.batteryinverter.kaco.blueplanetgridsave.KacoSunSpecModel;
import io.openems.edge.bridge.modbus.test.DummyModbusBridge;
import io.openems.edge.common.test.ComponentTest;

public class MyModbusDeviceTest {

	private static final String COMPONENT_ID = "component0";

	private static final String MODBUS_ID = "modbus0";

	@Test
	private void test() throws Exception {
		new ComponentTest(new MyModbusDevice()) //
				.addReference("setModbus", new DummyModbusBridge(MODBUS_ID)) //
				.activate(MyConfig.create() //
						.setId(COMPONENT_ID) //
						.setModbusId(MODBUS_ID) //
						.build())
				.next(new TestCase());
	}

}
