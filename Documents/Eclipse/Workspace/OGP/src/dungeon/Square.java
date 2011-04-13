package dungeon;

import java.math.*;
import java.util.*;


import be.kuleuven.cs.som.annotate.*;


/**
 * Smallest unit of location, a number of squares will be used as part of a dungeon.
 * A square consists of basic characteristics as temperature, humidity, slipperiness and six possible borders. 
 * @invar  The given humidity is a valid humidity.
 * 		  |isValidHumidity(getHumidity)
 * 
 * @author Lynn and Roeland
 *
 */
public class Square {
/**
 * Initialize a square with given temperature, humidity and default slipperiness as false and borders as true.
 * @param temperature
 * 		  The temperature for this new square in degrees of Celcius.
 * @param humidity
 * 		  The percentage of humidity for this new square, with a precision of 2 digits. 
 * @param slippery 
 * 		  Is false if the floor is not slippery, true if the floor is slippery. 
 * @param borders
 * 		  Is an array of 6 booleans where the boolean is true if there is a border at position i+1 of the square.  
 * @pre   The dimension of the array borders is 6    
 *        |borders.lenght=6
 * @post  If the temperature is not below its minimum or above its maximum value 
 * 		  and the scale of temperature is Celcius then the temperature of this square is set to the given value. 
 * 		  |new.getTemperature(scale.CELCIUS)==temperature
 * @post  If the humidity is not below its minimum or above its maximum value 
 * 		  then the humidity percentage is set to the given humidity. 
 *        |new.getHumidity()==humidity
 * @post  Slippery is set to the given value 
 * 		  |new.getSlippery()==slippery
 * @post  If there are 6 given values for the borders, borders are set to the given values
 *        |if (borders.lenght==6)new.getBorders()==borders
 // check in test: * @post  If there not 6 given values for the borders, the square is not initialized. 
 */
@Raw
public Square(BigDecimal temperature, BigDecimal humidity, boolean slippery, boolean...borders ){
	setTemperature(temperature);
	setHumidity(humidity);
	Slippery=slippery;
	if (borders.length==6)
	for (int i=1; i<=borders.length; i++)
	{setBorderAt(i, borders[i-1]);}
}
/**
 * @effect This new default square is initialized with a temperature of 0 degrees Celcius, a humidity of 0%, not slippery and no borders.
 * 		   | this(new BigDecimal("0.00"),new BigDecimal("0.00"),false,false,false,false,false,false,false);}
 */
@Raw
public Square(){
	this(BigDecimal.ZERO,BigDecimal.ZERO,false,false,false,false,false,false,false);
	}

/**
 * Return the temperature of this square in degrees Celsius, which is the default scale.	
 */
@Basic 
public BigDecimal getTemperature() {
	return temperature;
}
/**
 * * Returns the temperature of this square, expressed in the given(as a parameter) scale.
 * 
 * @param scale
 * 		  Indicates in which scale you want to express the temperature. The three
 * 		  possible values are: FAHRENHEIT, CELCIUS and KELVIN.
 * @return ... is dit nodig??????
 * @throws IllegalArgumentException(scale, this)
 * 		   The given scale is not a valid scale.
 * 		   | scale != (FAHRENHEIT || CELCIUS || KELVIN)
 */
public BigDecimal getTemperature(Scale scale)throws IllegalArgumentException{
	if (scale == Scale.FAHRENHEIT) 
	return getTemperature().multiply(constantef3).divide(constantef2).add(constantef1);
	if (scale == Scale.CELCIUS) 
	return getTemperature();
	if (scale == Scale.KELVIN) 
	return getTemperature().add(constantek1);
	throw new IllegalArgumentException("This is not a valid scale.");
}


/**
 * Checks whether the given temperature is a valid value for the temperature of a square.
 * 
 * @param temperature
 * 		  A BigDecimal representing the temperature of a square in degrees Celcius.
 * @return True if and only if the given temperature is not above maxTemperature or
 *		   below minTemperature.
 *		   | result == ((temperature >= minTemperature) && (temperature <= maxTemperature))
 */


private boolean isValidTemperature(BigDecimal temperature) { 	
	return ((temperature.compareTo(minTemperature)>=0) && (temperature.compareTo(maxTemperature) <=0 ));
}

/**
 * Sets the temperature in Celcius to the given value.
 * This is the basic setter that sets the variable temperature (which is expressed in degrees
 * Celcius) to the given value, if the given value is valid. By making this setter
 * private, we enforce the user to use the more extended setter, in which you are forced
 * to add a scale.
 * 
 * @param temperature
 * 		  A BigDecimal representing the temperature of a square in degrees Celcius.
 * @throws IllegalArgumentException (temperature, this)
 * 		   The given temperature is not a valid value for the temperature of a square.
 * 		   | ! canHaveAsTemperature(temperature)
 */



@Basic	
private void setTemperature(BigDecimal temperature)throws IllegalArgumentException{
	if (!isValidTemperature(temperature)) 
		throw new IllegalArgumentException("This is not a valid temperature.");
	this.temperature = temperature;
}

/**
 * 
 * Sets temperature of a square to the given temperature, expressed in the given scale.
 *   This setter first converts the temperature expressed in the given scale, to the
 *   temperature in Celcius and then sets the variable temperature to the according
 *   temperature in Celcius.
 * @param scale
 * 		  Indicates in which scale the given temperature is expressed. The three
 * 		  possible values are: FAHRENHEIT, CELCIUS and KELVIN.
 * @param temperature
 * 		  A BigDecimal representing the temperature of a square, expressed in the given scale.  
 * @throws IllegalArgumentException(scale, this)
 * 		   The given scale is not a valid scale.
 * 		   | scale != (FAHRENHEIT || CELCIUS || KELVIN)
 */

public void setTemperature(Scale scale, BigDecimal temperature)throws IllegalArgumentException{
	    if (scale == Scale.FAHRENHEIT) 
	    setTemperature((temperature.subtract(constantef1).multiply(constantef2).divide(constantef3)));
		if (scale == Scale.CELCIUS) 
		setTemperature(temperature);
		if (scale == Scale.KELVIN) 
		setTemperature(temperature.subtract(constantek1));
		throw new IllegalArgumentException("This is not a valid scale.");
}

private static final BigDecimal constantef1 = new BigDecimal("32.00");
private static final BigDecimal constantef2 = new BigDecimal("5.00");
private static final BigDecimal constantef3 = new BigDecimal("9.00");
private static final BigDecimal constantek1 = new BigDecimal("273.15");

/**
 * 
 * Creates an enumeration type Scale, which contains the values FAHRENHEIT, CELCIUS and
 * KELVIN.
 *
 */
public enum Scale { FAHRENHEIT ,CELCIUS, KELVIN ;}
/**
 * A BigDecimal representing the temperature of a square in degrees Celcius.
 */

private BigDecimal temperature;
/**
 * A BigIntger representing the lowest possible value of the variable temperature,
 * expressed in degrees Celcius.
 */

private BigDecimal minTemperature = new BigDecimal("-200.00");
/**
 * A BigIntger representing the highest possible value of the variable temperature,
 * expressed in degrees Celcius.
 */

private BigDecimal maxTemperature = new BigDecimal("5000.00");


/**
 * Returns the damage a creature occurs in this square, because the temperature is too low.
 *
 * @return If the temperature is above the coldTreshold, there is no cold damage.
 * 		   | if (getTemperature() > coldTreshold) result == 0
 * @return If the temperature is below or equal to the coldTreshold , one damage point 
 * 			is added per coldDamageFactor degrees below the coldTreshold.
 * 		   | if (getTemperature() <= coldTreshold)
 * 		   |    result == ((|getTemperature()|-|coldTreshold|)/coldDamageFactor)
 */

public final int coldDamage(){
		if (getTemperature().compareTo(coldTreshold) <= 0) 
		return (getTemperature().abs().subtract(coldTreshold.abs())).divideToIntegralValue(coldDamageFactor).intValue();
		return 0;
}

private static final BigDecimal coldTreshold = new BigDecimal("-5.00");
private static final BigDecimal coldDamageFactor = BigDecimal.TEN;
/**
 * Returns the damage a creature occurs in this square, because the temperature is too low.
 * 
 * @return If the temperature is below the heatTreshold, there is no heat damage.
 * 		   | if (getTemperature() < heatTreshold) result == 0
 * @return If the temperature is above or equal to the heatTreshold , one damage point 
 * 			is added per heatDamageFactor degrees below the heatTreshold.
 * 		   | if (getTemperature() >= heatTreshold)
 * 		   |    result == ((getTemperature()-heatTreshold)/heatDamageFactor)
 */

public int heatDamage(){
	if (getTemperature().compareTo(heatTreshold) >= 0) 
		if (heatTreshold.compareTo(BigDecimal.ZERO) >= 0)
	return (getTemperature().subtract(heatTreshold)).divideToIntegralValue(heatDamageFactor).intValue();
	return 0;
}

/**
 * Sets the heatTreshold to the given value, if the given value is valid.
 * 
 * @param newHeatTreshold
 * 		  BigDecimal representing the new heatTreshold
 * @post this.getHeatTreshold == newHeatTreshold
 */

public static void setHeatTreshold(BigDecimal newheattreshold){
	heatTreshold=newheattreshold;
//Every BigDecimal is a possible treshold, there is no need to validate a possible value.
}
/**
 * Returns the heatTreshold.
 */

public static BigDecimal getHeatTreshold(){
	return heatTreshold;
}

private static BigDecimal heatTreshold = new BigDecimal("35");
private static BigDecimal heatDamageFactor = new BigDecimal("15");
/**
 * Returns the humidity of this square.
 */
@Basic @Raw
public BigDecimal getHumidity(){
	return humidity;
}

/**
 * Set the humidity of the square to the given value.
 * @param humidity
 * The humidity to be registered as the new humidity 
 * @pre It must be a valid humidity
 * 		|isValidHumidity(humidity)
 * @post The new humidity of this Square is equal to the new humidity.
 *  	|new.getHumidity()==humidity 
 */


@Basic @Raw
public void setHumidity(BigDecimal humidity){
	if (isValidHumidity(humidity)) 
		this.humidity= humidity;
}

/**
 * Check whether the humidity is a valid humidity. 
 * @param humidity
 * 		  The humidity to check.
 * @pre The humidity is registered up to two digits after the floating point.
 *      |humidity.precision()==2
 * @return True if and only if the humidity doesn't exceed it's boundaries.
 * 		   |result == 
 * 		   |((humidity<=MAX_HUMIDITY) && (humidity>=MIN_HUMIDITY))
 */
@Raw
public boolean isValidHumidity(BigDecimal humidity){
	if (humidity.precision()==2);
	return 	 ((humidity.compareTo(MAX_HUMIDITY)<=0) && (humidity.compareTo(MIN_HUMIDITY)>=0));
}

private BigDecimal humidity;
public static final BigDecimal MAX_HUMIDITY = new BigDecimal("100.00");
public static final BigDecimal MIN_HUMIDITY = BigDecimal.ZERO;



/**
 * 
 * @pre Object must be of metal.
 * @pre The humidity must be valid.
 *      |isValidHumidity(getHumidity())
 * @pre Humidity must be higher than 30%.
 * 		|getHumidity()>= 30.00
 * 
 */

public int rustDamage(){
	
	return (getHumidity().subtract(constantrd1).divideToIntegralValue(constantrd2).intValue());
}
private static final BigDecimal constantrd1 = new BigDecimal("30.00");
private static final BigDecimal constantrd2 = new BigDecimal("7.00");



/**
 * 
 * @return True if the humidity is 100% and the temperature is above 0 degrees C 
 *		   or the humidity is above 10% and the temperature is below 0 degrees C
 *		   or if the material of the floor is slippery
 * 			| result = (this.getHumidity()==100 && this.getTemperature()>0)
 * 			| || (this.getHumidity()>10 && this.getTemperature()<=0)
 *  		| || (this.Slippery)
 */
public boolean isSlippery() throws IllegalArgumentException {
	if ((getHumidity().compareTo(MAX_HUMIDITY)==0) && (getTemperature().compareTo(BigDecimal.ZERO)>0))
		return true;
	if ((getHumidity().compareTo(BigDecimal.TEN)>0) && (getTemperature().compareTo(BigDecimal.ZERO)<=0))
		return true;
	if (getSlippery())
		return true;
	return false;}
/**
 * Returns whether the floor is slippery or not.
 *   If the floor is slippery, it returns the value true, if the floor is not slippery, the value is false
 */

@Basic @Immutable
public boolean getSlippery(){
	return Slippery;
}

private final boolean Slippery;
/**
 * Returns a value for the inhabitability of a square.
 *   The inhabitability is a general measure that combines the different damage measures to express how much damage a square will generate.
 * @return Calculate the inhabitability according to the following formula:
 * 		   | result == (- (heatDamage()^(3/2))/((101-getHumidity())^(1/2))-(coldDamage()^(1/2)))
 */

public Double inhabitability(){
return ((- Math.sqrt(Math.pow(heatDamage(),3))/Math.sqrt(101-(getHumidity().doubleValue())))-Math.sqrt(coldDamage()));
	
}

/**
 * Returns whether the square has a border in the given direction.
 */

public boolean getBorderAt(int index){
	return Borders[index];
}
/**
 * Returns a list of all the borders.
 */

public List<Integer> getBorders(){
	List<Integer> locallist= new ArrayList<Integer>();

	for (int i=0; i< Borders.length; i++){
		if (getBorderAt(i)) {locallist.add(i+1);}
	}
	return locallist;
}

/**
 * Sets the border in the given direction at the given value.
 * @param index
 * 		  Indicates in which direction the border will be given a new value.
 * @param border
 * 		  Boolean that expresses whether there is a border or not in the given direction
 * @post The border is set to the given value, at the according place in the array of borders
 * 		 | new.getBorderAt(index) == border
 */
public void setBorderAt(int index, boolean border){
	Borders[index-1]=border;
}
/**
 * An array of booleans, expressing whether there is a border or not in the six directions.
 */
private boolean[] Borders = new boolean[6];

/**
 * We assign the borders of the square as if it was a dice so the sum 
 * of the two opposing borders will always be seven.
 * @param mergerSquare
 * 		  An object of the type square, that will be used to merge with the current square.
 * @param direction
 * 		  A direction, along which the squares will be merged (seen from the current square).
 * @post In this square, the wall in the direction along which the squares will be merged, will disappear
 * 		 | new.getBorderAt(border)==false
 * @post In the mergerSquare, the wall will disappear, but in the opposite direction
 * 		 | (new mergerSquare).getBorderAt(7-border)==false
 * @post The new humidity has to be a valid humidity
 *       |isValidHumidity(new.getHumidity)
 * @post The humidity of both squares will be given a new value, which as the average of both current values
 * 		 | (new.getHumidity() == (this.getHumidity() + mergerSquare.getHumidity())/2)
 * 		 | && ((new mergerSquare).getHumidity() == (this.getHumidity() + mergerSquare.getHumidity())/2)
 * @effect The temperature of both squares will be given a new, merged, value
 * 		   | this.setTemperature(mergeTemperature(mergerSquare)
 * 		   | && mergerSquare.setTemperature(new.getTemperature)
 * @throws IllegalArgumentException (mergerSquare, this)
 * 		   You are trying to merge a square with itself
 * 		   | this == mergerSquare
 * @throws NullPointerException (null, this)
 * 			You are trying to merge with a non-existing square
 * 			| this == null
 */

public void mergeWith(Square mergerSquare, int border) throws IllegalArgumentException, NullPointerException{
	// We give the directions a number according to the numbering of a dice.
	// This means that the sum of the numbers of two opposing direction, always equal 7.
		if (this == mergerSquare)
			throw new IllegalArgumentException("You can not merge with yourself");
		if (mergerSquare == null)
			throw new NullPointerException();
		else
	setBorderAt(border,false);
	mergerSquare.setBorderAt(7-border,false);
	BigDecimal newHumidity=((getHumidity().add(mergerSquare.getHumidity())).divide(new BigDecimal("2")));
	setHumidity(newHumidity);
	mergerSquare.setHumidity(newHumidity);
	BigDecimal newTemperature=(mergeTemperature(mergerSquare));
	setTemperature(newTemperature);
	mergerSquare.setTemperature(newTemperature);	
}

/**
* Calculates the temperature after two squares are merged.
* @param mergerSquare
* 		  An object of the type square, that will be used to merge with the current square.
* @return ...
* 		   | (((this.getHumidity()*(1-b)/((this.getHumidity()+mergerSquare.getHumidity())/2))+b)
* 		   | *this.getTemperature() + ((mergerSquare.getHumidity()*(1-b)/((this.getHumidity()+mergerSquare.getHumidity())/2))+b)*mergerSquare.getTemperature())
*/

public BigDecimal mergeTemperature(Square mergerSquare){
	BigDecimal a = BigDecimal.ONE.subtract(getB());
	BigDecimal average= (getHumidity().add(mergerSquare.getHumidity())).divide(new BigDecimal("2"));
	BigDecimal weight1 = (getHumidity().multiply(a).divide(average)).add(getB());
	BigDecimal weight2= (mergerSquare.getHumidity().multiply(a).divide(average)).add(getB());
	return (weight1.multiply(getTemperature())).add((weight2).multiply(mergerSquare.getTemperature()));
}
/**
 * A constant between MIN_B en MAX_B
 */

private static BigDecimal b = new BigDecimal("0.20");
private final static BigDecimal MIN_B = new BigDecimal("0.10");
private final static BigDecimal MAX_B = new BigDecimal("0.40");

@Immutable
public static BigDecimal getMinB(){
	return MIN_B;
}
@Immutable
public static BigDecimal getMaxB(){
	return MAX_B;
}
/**
 * Returns the current value of b, a variable we need in the calculation of mergeTemperature.
 */
public static BigDecimal getB(){
	return b;  
}
/**
 * Sets b to the given value.
 * @param constant
 * 		  The new value that will be given to b, if it is a valid value.
 * @throws IllegalArgumentException(constant, this)
 * 			The value of constant is not a valid value for b.
 * 			(! canHaveAsB(constant))
 */
public static void setB(BigDecimal constant)throws IllegalArgumentException{
	if (! canHaveAsB(constant))	
		throw new IllegalArgumentException();
	b = constant;
}

/**
 * Checks whether the the given value is a valid value for b
 * @param constant
 * 		  The new value that will be given to b, if it is a valid value.
 * @return True if the value of constant is below or equal to MAX_B and above or equal to MIN_B
 * 		   | result == (constant<=getMaxB() && constant>=getMinB())
 */

public static boolean canHaveAsB(BigDecimal constant){
	if ((constant.compareTo(getMaxB())<=0) && (constant.compareTo(getMinB())>= 0)) return true;
	return false;
}
}






