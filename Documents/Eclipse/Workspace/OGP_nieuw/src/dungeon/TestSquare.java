package dungeon;


	import static org.junit.Assert.*;
	import org.junit.*;
	import java.math.BigDecimal;

	public class TestSquare {

	private Square highTempSquare, lowTempSquare, highHumiditySquare, lowHumiditySquare;
	private static Square slipperySquare;

	@Before
	public void setUpMutableFixture() {
		
		highTempSquare = new Square(new BigDecimal("100.00"), new BigDecimal("40.00"), false, true, false, false, false, false, true);
		lowTempSquare = new Square(new BigDecimal("-100.00"), new BigDecimal("40.OO"), false, false, false, false, false, false, true);
		highHumiditySquare = new Square(BigDecimal.TEN, new BigDecimal("100.00"), false, true, false, false, false, false, true);
		lowHumiditySquare = new Square(BigDecimal.TEN, BigDecimal.ZERO, false, false, false, false, false, false, true);
	}

	@BeforeClass
	public static void setUpImmutableFixture(){
		slipperySquare = new Square(BigDecimal.TEN, BigDecimal.ZERO, true, false, false, false, false, false, false);
	}

	@Test
	public void coldDamage_lowTempSquare() {
		assertEquals(9, lowTempSquare.coldDamage());
	}

	@Test
	public void coldDamage_highTempSquare() {
		assertEquals(0, highTempSquare.coldDamage());
	}

	@Test
	public void heatDamage_highTempSquare() {
		assertEquals(4, highTempSquare.heatDamage());
	}

	@Test
	public void heatDamage_lowTempSquare() {
		assertEquals(0, lowTempSquare.heatDamage());
	}

	@Test
	public void rustDamage_lowHumiditySquare() {
		assertEquals(0, lowHumiditySquare.rustDamage());
	}

	@Test
	public void rustDamage_highHumiditySquare() {
		assertEquals(10, highHumiditySquare.rustDamage());
	}

	@Test
	public void isSlippery_highHumiditySquare() {
		assertTrue(highHumiditySquare.isSlippery());
	}

	@Test
	public void isSlippery_lowTempSquare() {
		assertTrue(lowTempSquare.isSlippery());
	}

	@Test
	public void isSlippery_slipperySquare() {
		assertTrue(slipperySquare.isSlippery());
	}

	@Test
	public void isSlippery_lowHumiditySquare() {
		assertFalse(lowHumiditySquare.isSlippery());
	}

	@Test
	public void inhabitability_highHumiditySquare() {
		assertEquals(-0.838627869, highHumiditySquare.inhabitability(),0.0005);
	}

	@Test
	public void mergeWith_highTempSquare_lowTempSquare() {
		highTempSquare.mergeWith(lowTempSquare, 1);
		assertEquals(0, highTempSquare.getTemperature());
		assertEquals(0, lowTempSquare.getTemperature());
		assertEquals(40, highTempSquare.getHumidity());
		assertFalse(highTempSquare.getBorderAt(1));
		assertFalse(lowTempSquare.getBorderAt(6));
	}

	@Test
	public void mergeWith_highHumidtySquare_lowHumiditySquare() {
		highHumiditySquare.mergeWith(lowHumiditySquare, 1);
		assertEquals(BigDecimal.TEN, highTempSquare.getTemperature());
		assertEquals(BigDecimal.TEN, lowTempSquare.getTemperature());
		assertEquals(50, highTempSquare.getHumidity());
		assertFalse(highTempSquare.getBorderAt(1));
		assertFalse(lowTempSquare.getBorderAt(6));
	}




		
	}


