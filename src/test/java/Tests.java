import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void testStandardConstructor() {
        Rational standard = new Rational();
        assertEquals("Standard constructor returns wrong numerator", 0, standard.getNumerator());
        assertEquals("Standard constructor returns wrong denominator", 1, standard.getDenominator());
    }

    @Test
    public void testParamConstructorWithPosDenominator() {
        for(int i=0;i<5;++i) {
            int number1 = getRandomNumber(-10000000, 10000000);
            int number2 = getRandomNumber(1, 10000000);
            int gcd = getGCD(number1, number2);
            Rational obj = new Rational(number1, number2);

            assertEquals("Constructor with params returns wrong numerator", number1 / gcd, obj.getNumerator());
            assertEquals("Constructor with params returns wrong denominator", number2 / gcd, obj.getDenominator());
        }
    }

    @Test
    public void testParamConstructorWithNegDenominator() {
        for(int i=0;i<5;++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumber(-1000000, -1);
            int gcd = getGCD(number1, number2);
            Rational obj = new Rational(number1, number2);

            assertEquals("Constructor with params returns wrong numerator", -number1 / gcd, obj.getNumerator());
            assertEquals("Constructor with params returns wrong denominator", Math.abs(number2) / gcd, obj.getDenominator());
        }
    }

    @Test(expected=ArithmeticException.class)
    public void testParamConstructorWithZeroDenominator() {
        int number2 = 0;
        for(int i=0;i<5;++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int gcd = getGCD(number1, number2);
            Rational obj = new Rational(number1, number2);
        }
    }

    @Test
    public void testNumeratorSetter() {
        int expectedNumerator;
       for(int i=0;i<5;++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumber(-1000000, 1000000);
            if (number2==0) number2+=getRandomNumber(1, 1000000);
            //int gcd=getGCD(number1,number2);
            Rational obj = new Rational(number1, number2);

            int oldDenom = obj.getDenominator();

            int newNumerator = getRandomNumber(-1000000, 1000000);
            obj.setNumerator(newNumerator);
            int gcd = getGCD(newNumerator, oldDenom);

//            if(number2<0) expectedNumerator = -newNumerator; else expectedNumerator=newNumerator;

            assertEquals("Numerator setter returns wrong numerator", newNumerator / gcd, obj.getNumerator());
            assertEquals("Numerator setter returns wrong denominator", oldDenom / gcd, obj.getDenominator());
       }
    }

    @Test
    public void testDenominatorSetter() {
        int expectedDenominator;
        for(int i=0;i<5;++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumber(-1000000, 1000000);
            if (number2==0) number2+=getRandomNumber(1, 1000000);
            Rational obj = new Rational(number1, number2);

            int oldNumerator = obj.getNumerator();

            int newDenominator = getRandomNumber(-1000000, 1000000);
            if (newDenominator==0) newDenominator+=getRandomNumber(1, 1000000);
            obj.setDenominator(newDenominator);
            int gcd = getGCD(oldNumerator, newDenominator);

            if(newDenominator<0) oldNumerator *= -1;

            assertEquals("Denominator setter returns wrong numerator", oldNumerator / gcd, obj.getNumerator());
            assertEquals("Denominator setter returns wrong denominator", Math.abs(newDenominator) / gcd, obj.getDenominator());
        }
    }

    @Test(expected=ArithmeticException.class)
    public void testDenominatorSetterZero() {
        int expectedDenominator;
        for(int i=0;i<5;++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumber(-1000000, 1000000);
            if (number2==0) number2+=getRandomNumber(1, 1000000);
            Rational obj = new Rational(number1, number2);

            obj.setDenominator(0);
        }
    }

    private static int getRandomNumber(int min, int max) {
        return (int)(Math.random()*((max-min)+1))+min;
    }

    private int getGCD(int a, int b) {
        int n = abs(a);
        int d = abs(b);

        while (n > 0 && d > 0) {
            if (n > d) {
                n %= d;
            } else {
                d %= n;
            }
        }

        return n + d;
    }
}