package com.businessapp.logic;
import java.util.ArrayList;
/**
 * ********************************************************************************
 * Local implementation class (unfinished) of calculator logic.
 * <p>
 * Instance is invoked with public interface method nextToken( Token tok ) passing
 * an input token that is created at the UI from a key event. Input tokens are defined
 * in CalculatorIntf and comprised of digits [0-9,.], numeric operators [+,-,*,/,VAT]
 * and control tokens [\backspace,=,C,CE,K_1000].
 * <p>
 * Outputs are are passed through display properties:<p>
 * 	- CalculatorIntf.DISPLAY for numbers and<p>
 * 	- CalculatorIntf.SIDEAREA for VAT calculations.
 * <p>
 * Method(s):
 *	- public void nextToken( Token tok );	;process next token from UI controller
 * 
 */
class CalculatorLogic implements CalculatorLogicIntf {

	private StringBuffer dsb = new StringBuffer();
//        String operatorPressed  = "";
        String totalResult;
//        SimpleDoubleProperty curNumber = new SimpleDoubleProperty(0);
//        SimpleStringProperty stringDisplayed = new SimpleStringProperty();
        
        private ArrayList<Double> numbers = new ArrayList<Double>();
        private ArrayList<String> strings = new ArrayList<String>();
        double number = 0.0;



	/**
	 * Local constructor.
	 */
	CalculatorLogic() {
		nextToken( Token.K_C );		// reset buffers
	}

	/**
     * Process next token from UI controller. Tokens are defined in CalculatorIntf.java
     * <p>
     * Outputs are are passed through display properties:
     * 	- CalculatorIntf.DISPLAY for numbers and
     * 	- CalculatorIntf.SIDEAREA for VAT calculations.
     * <p>
     * @param tok the next Token passed from the UI, CalculatorViewController.
     */
        @Override
	public void nextToken( Token tok ) {
		String d = tok==Token.K_DOT? "." : CalculatorLogicIntf.KeyLabels[ tok.ordinal() ];
		try {
			switch( tok ) {
			case K_0: case K_1: case K_2: case K_3: case K_4:
			case K_5: case K_6: case K_7: case K_8: case K_9:
				appendBuffer( d );
                                totalResult = (CalculatorLogicIntf.DISPLAY.get()+d);
                                number = Double.parseDouble(totalResult);
                                System.out.println(number);
                                CalculatorLogicIntf.SIDEAREA.set(String.valueOf(number));
				break;
                                

			case K_1000:
				nextToken( Token.K_0 );
				nextToken( Token.K_0 );
				nextToken( Token.K_0 );
				break;

			case K_DIV:
                            isString(dsb);
                            strings.add("/");
                            numbers.add(number);
                            System.out.println("/");
                            System.out.println(numbers);
                            dsb.delete( 0,  dsb.length() );
                            break;
			case K_MUL:
                            isString(dsb);
                            strings.add("*");
                            numbers.add(number);
                            System.out.println("*");
                            System.out.println(numbers);
                            dsb.delete( 0,  dsb.length() );
                            break;
			case K_PLUS:
                            isString(dsb);
                            strings.add("+");
                            System.out.println("+");
                            numbers.add(number);
                            System.out.println(numbers);
                            dsb.delete( 0,  dsb.length() );
                            break;
                            
			case K_MIN:
                            isString(dsb);
                            strings.add("-");
                            numbers.add(number);
                            System.out.println("-");
                            System.out.println(numbers);
                            dsb.delete( 0,  dsb.length() );
                            break;
                            
                        case K_EQ:
                            numbers.add(number);
                            dsb.delete(0, dsb.length());
                            double erg = 0.0;
                            
                            boolean ende = false;
                            while(!ende) {
                                System.out.println(numbers);
                                    for (int i=0; i<numbers.size()-1; i++) {
                                        if (strings.get(i).equals("*")) {
                                            erg = numbers.get(i) * numbers.get(i+1);
                                            System.out.println(erg);
                                            numbers.set(i, erg);
                                            numbers.remove(i+1);
                                            strings.remove(i);
                                            System.out.println(numbers);
                                            break;
                                        }
                                        
                                        if (strings.get(i).equals("/")) {
                                            
                                            if (numbers.get(i+1) == 0) {
                                                CalculatorLogicIntf.SIDEAREA.set("");
                                                dsb.delete(0, dsb.length());
                                                numbers.clear();
                                                strings.clear();
                                                throw new ArithmeticException("Nicht durch 0 teilen");
                                  
                                            }else{
                                                erg = numbers.get(i) / numbers.get(i+1);
                                                numbers.set(i, erg);
                                                strings.remove(i);
                                                numbers.remove(i+1);
                                                break;
                                            }
                                        }
                                    }
                                ende = true;
                            }
                            if(strings.size() > 0 && numbers.size() > 1) {
				switch (strings.get(0)) {
                                    case "+":
                                        System.out.println(numbers);
					erg = numbers.get(0) + numbers.get(1);
					break;
                                    case "-":
					erg = numbers.get(0) - numbers.get(1);
					break;
				}
                                numbers.set(0, erg);
				numbers.remove(1);
				strings.remove(0);
                            }
                            CalculatorLogicIntf.SIDEAREA.set(String.valueOf(erg));
                            numbers.clear();
                            strings.clear();
                            System.out.println(numbers.toString());
                            System.out.println(strings.toString());
                            break;

			case K_VAT:
                            double brutto = Double.parseDouble(CalculatorLogicIntf.DISPLAY.get());
                            double netto = (Math.round((brutto / 1.19) * 100.0) / 100.0);
                            double MwSt = (Math.round((netto * 0.19) * 100.0) / 100.0);
                            CalculatorLogicIntf.SIDEAREA.set(
                                    "Brutto:  " + brutto + "\n"
                                    + VAT_RATE + "% MwSt:  " + MwSt + "\n"
                                    + "Netto:  " + netto
                            );
                            break;

			case K_DOT:
                            
                                if(dsb.toString().equals("")) break;
                                if(dsb.toString().charAt(dsb.toString().length()-1) == '.'){
                                    break;
                                }else{
                                    appendBuffer(d);
                                }
				break;

			case K_BACK:
				dsb.setLength( Math.max( 0, dsb.length() - 1 ) );
				break;

			case K_C:
                                dsb.delete( 0,  dsb.length() );
				CalculatorLogicIntf.SIDEAREA.set( "" );
			case K_CE:
				dsb.delete( 0,  dsb.length() );
                                CalculatorLogicIntf.SIDEAREA.set( "" );
				break;

			default:
			}
			String display = dsb.length()==0? "0" : dsb.toString();
			CalculatorLogicIntf.DISPLAY.set( display );

		} catch( ArithmeticException e ) {
			CalculatorLogicIntf.DISPLAY.set( e.getMessage() );
		}
	}


	/*
	 * Private method(s).
	 */
	private void appendBuffer( String d ) {
		if( dsb.length() <= DISPLAY_MAXDIGITS ) {
			dsb.append( d );
		}
	}
        
        private void isString(StringBuffer dsb){
		try {
			number = Double.parseDouble(dsb.toString());
		}
		catch(NumberFormatException e){
			CalculatorLogicIntf.SIDEAREA.set("Falsche Eingabe");
			numbers.clear();
			strings.clear();
		}
	}            
}