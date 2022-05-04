import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

public class Tests {
    @Test //№1
    public void testStandardConstructor() {
        Rational standard = new Rational();
        assertEquals("Standard constructor returns wrong numerator", 0, standard.getNumerator());
        assertEquals("Standard constructor returns wrong denominator", 1, standard.getDenominator());
    }

    @Test //№2
    public void testParamConstructorWithPosDenominator() {
        for (int i = 0; i < 5; ++i) {
            int number1 = getRandomNumber(-10000000, 10000000);
            int number2 = getRandomNumber(1, 10000000);
            int gcd = getGCD(number1, number2);
            Rational obj = new Rational(number1, number2);

            assertEquals("Constructor with params returns wrong numerator", number1 / gcd, obj.getNumerator());
            assertEquals("Constructor with params returns wrong denominator", number2 / gcd, obj.getDenominator());
        }
    }

    @Test //№3
    public void testParamConstructorWithNegDenominator() {
        for (int i = 0; i < 5; ++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumber(-1000000, -1);
            int gcd = getGCD(number1, number2);
            Rational obj = new Rational(number1, number2);

            assertEquals("Constructor with params returns wrong numerator", -number1 / gcd, obj.getNumerator());
            assertEquals("Constructor with params returns wrong denominator", Math.abs(number2) / gcd, obj.getDenominator());
        }
    }

    @Test(expected = ArithmeticException.class) //№4
    public void testParamConstructorWithZeroDenominator() {
        int number2 = 0;
        int number1 = getRandomNumber(-1000000, 1000000);
        int gcd = getGCD(number1, number2);
        Rational obj = new Rational(number1, number2);
    }

    @Test //№5
    public void testNumeratorSetter() {
        int expectedNumerator;
        for (int i = 0; i < 5; ++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumberNoZero(-1000000, 1000000);
            Rational obj = new Rational(number1, number2);

            int oldDenom = obj.getDenominator();

            int newNumerator = getRandomNumber(-1000000, 1000000);
            obj.setNumerator(newNumerator);
            int gcd = getGCD(newNumerator, oldDenom);

            assertEquals("Numerator setter returns wrong numerator", newNumerator / gcd, obj.getNumerator());
            assertEquals("Numerator setter returns wrong denominator", oldDenom / gcd, obj.getDenominator());
        }
    }

    @Test //№6
    public void testDenominatorSetter() {
        for (int i = 0; i < 5; ++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumberNoZero(-1000000, 1000000);
            Rational obj = new Rational(number1, number2);

            int oldNumerator = obj.getNumerator();

            int newDenominator = getRandomNumber(-1000000, 1000000);
            if (newDenominator == 0) newDenominator += getRandomNumber(1, 1000000);
            obj.setDenominator(newDenominator);
            int gcd = getGCD(oldNumerator, newDenominator);

            if (newDenominator < 0) oldNumerator *= -1;

            assertEquals("Denominator setter returns wrong numerator", oldNumerator / gcd, obj.getNumerator());
            assertEquals("Denominator setter returns wrong denominator", Math.abs(newDenominator) / gcd, obj.getDenominator());
        }
    }

  @Test(expected = ArithmeticException.class) //№7
    public void testDenominatorSetterZero() {

       int number1 = getRandomNumber(-1000000, 1000000);
       int number2 = getRandomNumberNoZero(-1000000, 1000000);
       Rational obj = new Rational(number1, number2);

       obj.setDenominator(0);
   }

    @Test //№8
    public void testEquals() {
        for (int i = 0; i < 5; ++i) {
            int number1 = getRandomNumber(-1000000, 1000000);
            int number2 = getRandomNumberNoZero(-1000000, 1000000);
            int number3 = getRandomNumberNoZero(-1000000, 1000000);

            Rational obj1 = new Rational(number1, number2);
            Rational obj2 = new Rational(number1, number2);
            Rational obj3 = new Rational(getRandomNumber(-1000000, 1000000), number3);

            String str1 = String.join("/", String.valueOf(number1), String.valueOf(number2));

            assertTrue("Func Equals work incorrect", obj1.equals(obj2));
            assertTrue("Func Equals work incorrect", obj2.equals(obj1));
            assertFalse("Func Equals work incorrect", obj1.equals(obj3));
            assertFalse("Func Equals work incorrect", obj3.equals(obj1));
            assertFalse("Func Equals work incorrect", obj1.equals(number1));
            assertFalse("Func Equals work incorrect", obj1.equals(str1));
        }
    }

    @Test //№9
    public void testLess() {

        Rational obj1 = new Rational(123, 256);
        Rational obj2 = new Rational(123, 512);

        assertTrue("Func less work incorrect", obj2.less(obj1));

        obj1 = new Rational(123, -256);
        obj2 = new Rational(123, 512);

        assertTrue("Func less work incorrect", obj1.less(obj2));

        obj1 = new Rational(-123, -256);
        obj2 = new Rational(-321, -256);

        assertTrue("Func less work incorrect", obj1.less(obj2));

        obj1 = new Rational(-123, 824);
        obj2 = new Rational(-321, 946);

        //assertTrue("Func less work incorrect for different numerators and denominators", obj2.less(obj1));

        obj1 = new Rational(-123, 256);
        obj2 = new Rational(-123, 256);

        assertFalse("Func less work incorrect", obj1.less(obj2));
    }

    @Test //№10
    public void testLessOrEqual() {

        Rational obj1 = new Rational(123, 256);
        Rational obj2 = new Rational(123, 512);

        assertTrue("Func lessOrEqual work incorrect", obj2.lessOrEqual(obj1));

        obj1 = new Rational(123, -256);
        obj2 = new Rational(123, 512);

        assertFalse("Func lessOrEqual work incorrect because of incorrect work of Func less", obj2.lessOrEqual(obj1));

        obj1 = new Rational(-123, 256);
        obj2 = new Rational(-123, 256);

        assertTrue("Func lessOrEqual work incorrect", obj1.lessOrEqual(obj2));
    }

    @Test //№11
    public void testPlus()
    {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(654,9070);
        Rational obj3 = new Rational(4562,34);
        Rational obj4 = new Rational(4,3);
        Rational obj5 = new Rational(12,-9);

        assertEquals("Func Plus work incorrect", new Rational(31358062,231285), obj2.plus(obj3.plus(obj4)));
        assertEquals("Func Plus work incorrect", obj3, obj1.plus(obj3));
        assertEquals("Func Plus work incorrect", obj1, obj4.plus(obj5));
    }

    @Test //№12
    public void testMultiply()
    {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(654,900);
        Rational obj3 = new Rational(456,34);
        Rational obj4 = new Rational(3,4);
        Rational obj5 = new Rational(12,-9);

        assertEquals("Func Multiply work incorrect", new Rational(-16568,1275), obj2.multiply(obj3.multiply(obj5)));
        assertEquals("Func Multiply work incorrect", new Rational(-1,1), obj4.multiply(obj5));
        assertEquals("Func Multiply work incorrect", obj1, obj4.multiply(obj1));
    }

    @Test //№13
    public void testMinus()
    {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(654,900);
        Rational obj3 = new Rational(456,34);
        Rational obj4 = new Rational(3,4);
        Rational obj5 = new Rational(9,12);

        assertEquals("Func Minus work incorrect", new Rational(-60869,5100), obj2.minus(obj3.minus(obj4)));
        assertEquals("Func Minus work incorrect", obj1, obj4.minus(obj5));
        assertEquals("Func Minus work incorrect", new Rational(-654,900), obj1.minus(obj2));
    }

    private static int getRandomNumber(int min, int max) {
        return (int)(Math.random()*((max-min)+1))+min;
    }

    private static int getRandomNumberNoZero(int min, int max) {
        int res = (int)(Math.random()*((max-min)+1))+min;
        if(res==0) res+=getRandomNumber(1,max);

        return res;
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