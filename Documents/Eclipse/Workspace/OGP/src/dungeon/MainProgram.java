package dungeon;

import java.math.BigDecimal;

import dungeon.Square.Scale;

public class MainProgram {

public static void main(String args[]){ 
	Square square1= new Square(new BigDecimal("150.00"),new BigDecimal("100.00"),true,true,true,true,false,false,false);
	Square square2= new Square(new BigDecimal("40.00"),new BigDecimal("0.00"),false,false,true,true,true,false,false);

    
System.out.println(square1.getSlippery());
System.out.println();
System.out.println(square1.getHumidity());
System.out.println();
System.out.println(square1.getTemperature(Scale.CELCIUS));
System.out.println(square1.getTemperature(Scale.FAHRENHEIT));
System.out.println(square1.getTemperature(Scale.KELVIN));
System.out.println(square1.heatDamage());
System.out.println(square1.coldDamage());
System.out.println(square1.rustDamage());
System.out.println(square1.inhabitability());
System.out.println();
System.out.println(square1.getBorders());
System.out.println();
System.out.println(square2.getSlippery());
System.out.println();
System.out.println(square2.getHumidity());
System.out.println();
System.out.println(square2.getTemperature());
System.out.println();
System.out.println(square2.getBorders());

square1.mergeWith(square2, 2);
  
System.out.println(square1.getSlippery());
System.out.println();
System.out.println(square1.getHumidity());
System.out.println();
System.out.println(square1.getTemperature());
System.out.println();
System.out.println(square1.getBorders());
System.out.println();
System.out.println(square2.getSlippery());
System.out.println();
System.out.println(square2.getHumidity());
System.out.println();
System.out.println(square2.getTemperature());
System.out.println();
System.out.println(square2.getBorders());

}
	
}
